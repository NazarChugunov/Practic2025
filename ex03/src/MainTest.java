import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Тестування функціональності
 */
public class MainTest {
    @org.junit.jupiter.api.Test
    public void testItem2d() {
        Item2d item = new Item2d(10);
        assertEquals("1010", item.getBinary());
        assertEquals("12", item.getOctal());
        assertEquals("a", item.getHex());
    }

    @Test
    public void testViewTable() {
        ViewTable tableView = new ViewTable(30, 3);
        assertEquals(30, tableView.getWidth());
        assertEquals(3, tableView.getItems().size());

        tableView.viewInit();

        ArrayList<Item2d> items = tableView.getItems();
        assertNotNull(items);
        assertEquals(3, items.size());

        for(Item2d item : items) {
            assertNotNull(item.getBinary());
            assertNotNull(item.getOctal());
            assertNotNull(item.getHex());
        }

        tableView.setWidth(40);
        assertEquals(40, tableView.getWidth());

        System.out.println("\nТестування viewShow():");
        tableView.viewShow();
    }

    @Test
    public void testPolymorphism() {
        View view1 = new ViewableResult().getView();
        View view2 = new ViewableTable().getView();

        assertTrue(view1 instanceof ViewResult);
        assertTrue(view2 instanceof ViewTable);
    }
}
