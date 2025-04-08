import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;

/**
 * Тестування основної функціональності.
 * @author Nazar
 * @version 2.0
 */
public class MainTest {
    @Test
    public void testItem2dGetters() {
        Item2d item = new Item2d(255);
        assertEquals(255, item.getNumber());
        assertEquals("11111111", item.getBinary());
        assertEquals("377", item.getOctal());
        assertEquals("ff", item.getHex());
    }

    @Test
    public void testViewResultMethods() {
        ViewResult view = new ViewResult(3);
        assertEquals(3, view.getNumber());

        view.setFname("test.bin");
        assertEquals("test.bin", view.getFname());
    }

    @Test
    public void testFileOperations() throws Exception {
        ViewResult view = new ViewResult(2);
        view.init(10);

        view.setFname("test_operations.bin");
        view.viewSave();

        ViewResult view2 = new ViewResult();
        view2.setFname("test_operations.bin");
        view2.viewRestore();

        assertEquals(2, view2.getNumber());
        assertEquals(10, view2.getItems().get(0).getNumber());
    }

    @Test
    public void testFnameMethods() {
        ViewResult view = new ViewResult();

        assertEquals("items.bin", view.getFname());

        view.setFname("custom.bin");
        assertEquals("custom.bin", view.getFname());

        view.setFname(null);
        assertNull(view.getFname());
    }
}
