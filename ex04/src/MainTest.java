import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.io.IOException;

/**
 * Комплексне тестування функціональності застосунку
 * @author Nazar
 * @version 4.0
 */
public class MainTest {

    @Test
    public void testItem2dRepresentations() {
        Item2d item = new Item2d(10);
        assertEquals("1010", item.getBinary());
        assertEquals("12", item.getOctal());
        assertEquals("a", item.getHex());

        item.setNumber(255);
        assertEquals("11111111", item.getBinary());
        assertEquals("377", item.getOctal());
        assertEquals("ff", item.getHex());
    }

    @Test
    public void testScaleCommand() {
        Item2d item = new Item2d(10);
        ScaleCommand cmd = new ScaleCommand(2.5);
        cmd.setItem(item);

        cmd.execute();
        assertEquals(25, item.getNumber());

        cmd.undo();
        assertEquals(10, item.getNumber());
    }

    @Test
    public void testViewCommand() {
        ViewResult view = new ViewResult();
        view.viewInit();
        ViewConsoleCommand cmd = new ViewConsoleCommand(view);

        cmd.execute();
    }

    @Test
    public void testGenerateCommand() {
        ViewResult view = new ViewResult();
        GenerateConsoleCommand cmd = new GenerateConsoleCommand(view);

        int initialSize = view.getItems().size();
        cmd.execute();

        assertEquals(initialSize, view.getItems().size());
        assertNotEquals(0, view.getItems().get(0).getNumber());
    }

    @Test
    public void testSaveRestoreCommands() throws Exception {
        ViewResult view = new ViewResult(3);
        view.viewInit();

        SaveConsoleCommand saveCmd = new SaveConsoleCommand(view);
        saveCmd.execute();

        view.getItems().get(0).setNumber(999);

        RestoreConsoleCommand restoreCmd = new RestoreConsoleCommand(view);
        restoreCmd.execute();

        assertNotEquals(999, view.getItems().get(0).getNumber());
    }

    @Test
    public void testMenuUndo() {
        Menu menu = new Menu();
        ViewResult view = new ViewResult();

        class TestCommand implements ConsoleCommand {
            boolean executed = false;
            boolean undone = false;

            public char getKey() { return 't'; }
            public String toString() { return "Test"; }
            public void execute() { executed = true; }
            public void undo() { undone = true; }
        }

        TestCommand testCmd = new TestCommand();
        menu.addCommand(testCmd);

        menu.executeCommand('t');
        assertTrue(testCmd.executed);

        menu.undoLastCommand();
        assertTrue(testCmd.undone);
    }

    @Test
    public void testApplicationSingleton() {
        Application app1 = Application.getInstance();
        Application app2 = Application.getInstance();
        assertSame(app1, app2);
    }
}
