import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;

/**
 * Комплексне тестування функціональності застосунку
 * author @Nazar
 * @version 5.0
 */
public class MainTest {
    private static final int TEST_DATA_SIZE = 10;
    private ViewResult testView;
    private CommandQueue queue;

    @Before
    public void setUp() {
        testView = new ViewResult(TEST_DATA_SIZE);
        queue = new CommandQueue();
        System.out.println("Test initialization completed");
    }

    @After
    public void tearDown() {
        testView = null;
        queue = null;
        System.out.println("Test cleanup completed");
    }

    @Test
    public void testViewResultInitialization() {
        assertEquals("Initial items count mismatch",
                TEST_DATA_SIZE, testView.getItems().size());

        for (Item2d item : testView.getItems()) {
            assertNotNull("Item is null", item);
            assertTrue("Invalid Y value range",
                    item.getY() >= -50 && item.getY() <= 50);
        }
    }

    @Test
    public void testMaxCommandExecution() {
        MaxCommand maxCmd = new MaxCommand(testView);
        maxCmd.execute();

        assertFalse("Command should be completed", maxCmd.running());
        assertTrue("Invalid max index",
                maxCmd.getResult() >= 0 && maxCmd.getResult() < TEST_DATA_SIZE);

        double maxValue = testView.getItems().get(maxCmd.getResult()).getY();
        for (Item2d item : testView.getItems()) {
            assertTrue("Found value greater than max", item.getY() <= maxValue);
        }
    }

    @Test
    public void testAvgCommandExecution() {
        AvgCommand avgCmd = new AvgCommand(testView);
        avgCmd.execute();

        assertFalse("Command should be completed", avgCmd.running());

        double sum = 0;
        for (Item2d item : testView.getItems()) {
            sum += item.getY();
        }
        double expectedAvg = sum / TEST_DATA_SIZE;

        assertEquals("Average calculation incorrect",
                expectedAvg, avgCmd.getResult(), 0.001);
    }

    @Test
    public void testMinMaxCommandExecution() {
        MinMaxCommand minMaxCmd = new MinMaxCommand(testView);
        minMaxCmd.execute();

        assertFalse("Command should be completed", minMaxCmd.running());

        // Перевірка мінімального додатного значення
        if (minMaxCmd.getResultMin() != -1) {
            double minPos = testView.getItems().get(minMaxCmd.getResultMin()).getY();
            assertTrue("Min should be positive", minPos > 0);

            for (Item2d item : testView.getItems()) {
                if (item.getY() > 0) {
                    assertTrue("Found smaller positive value",
                            item.getY() >= minPos);
                }
            }
        }

        if (minMaxCmd.getResultMax() != -1) {
            double maxNeg = testView.getItems().get(minMaxCmd.getResultMax()).getY();
            assertTrue("Max should be negative", maxNeg < 0);

            for (Item2d item : testView.getItems()) {
                if (item.getY() < 0) {
                    assertTrue("Found larger negative value",
                            item.getY() <= maxNeg);
                }
            }
        }
    }

    @Test
    public void testCommandQueueWithMaxCommand() throws InterruptedException {
        MaxCommand maxCmd = new MaxCommand(testView);
        queue.put(maxCmd);

        int waitCount = 0;
        while (maxCmd.running() && waitCount < 100) {
            TimeUnit.MILLISECONDS.sleep(50);
            waitCount++;
        }

        assertFalse("Command should complete within timeout", maxCmd.running());
        assertTrue("Invalid max index",
                maxCmd.getResult() >= 0 && maxCmd.getResult() < TEST_DATA_SIZE);
    }

    @Test
    public void testCommandQueueWithMultipleCommands() throws InterruptedException {
        List<Command> commands = new ArrayList<>();
        commands.add(new MaxCommand(testView));
        commands.add(new AvgCommand(testView));
        commands.add(new MinMaxCommand(testView));

        for (Command cmd : commands) {
            queue.put(cmd);
        }

        // Очікування завершення всіх команд
        int waitCount = 0;
        boolean allCompleted;
        do {
            allCompleted = true;
            for (Command cmd : commands) {
                if (cmd instanceof MaxCommand && ((MaxCommand)cmd).running()) {
                    allCompleted = false;
                } else if (cmd instanceof AvgCommand && ((AvgCommand)cmd).running()) {
                    allCompleted = false;
                } else if (cmd instanceof MinMaxCommand && ((MinMaxCommand)cmd).running()) {
                    allCompleted = false;
                }
            }

            if (!allCompleted && waitCount < 200) {
                TimeUnit.MILLISECONDS.sleep(50);
                waitCount++;
            }
        } while (!allCompleted && waitCount < 200);

        assertTrue("All commands should complete within timeout", allCompleted);
    }

    @Test
    public void testViewSerialization() throws Exception {
        // Зберігаємо вихідні дані
        testView.viewSave();

        // Модифікуємо дані
        testView.viewInit();
        List<Item2d> modifiedItems = new ArrayList<>(testView.getItems());

        testView.viewRestore();

        assertEquals("Items count after restore mismatch",
                TEST_DATA_SIZE, testView.getItems().size());

        for (int i = 0; i < TEST_DATA_SIZE; i++) {
            assertNotEquals("Data should be different after modification",
                    modifiedItems.get(i).getY(),
                    testView.getItems().get(i).getY(), 0.001);
        }
    }

    @Test
    public void testConcurrentCommandExecution() throws InterruptedException {
        CommandQueue queue1 = new CommandQueue();
        CommandQueue queue2 = new CommandQueue();
        MaxCommand maxCmd = new MaxCommand(testView);
        AvgCommand avgCmd = new AvgCommand(testView);
        MinMaxCommand minMaxCmd = new MinMaxCommand(testView);
        queue1.put(maxCmd);
        queue2.put(avgCmd);
        queue2.put(minMaxCmd);
        int waitCount = 0;
        while ((maxCmd.running() || avgCmd.running() || minMaxCmd.running())
                && waitCount < 200) {
            TimeUnit.MILLISECONDS.sleep(50);
            waitCount++;
        }
        assertFalse("MaxCommand should complete", maxCmd.running());
        assertFalse("AvgCommand should complete", avgCmd.running());
        assertFalse("MinMaxCommand should complete", minMaxCmd.running());

        assertTrue("Invalid max index",
                maxCmd.getResult() >= 0 && maxCmd.getResult() < TEST_DATA_SIZE);
        double sum = 0;
        for (Item2d item : testView.getItems()) {
            sum += item.getY();
        }
        assertEquals("Average calculation incorrect",
                sum / TEST_DATA_SIZE, avgCmd.getResult(), 0.001);
        if (minMaxCmd.getResultMin() != -1) {
            double minPos = testView.getItems().get(minMaxCmd.getResultMin()).getY();
            assertTrue("Min should be positive", minPos > 0);
        }
        if (minMaxCmd.getResultMax() != -1) {
            double maxNeg = testView.getItems().get(minMaxCmd.getResultMax()).getY();
            assertTrue("Max should be negative", maxNeg < 0);
        }
        queue1.shutdown();
        queue2.shutdown();
    }
}
