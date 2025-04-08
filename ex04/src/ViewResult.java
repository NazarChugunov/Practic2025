import java.io.*;
import java.util.ArrayList;

/**
 * Базова реалізація представлення результатів
 * @author Nazar
 * @version 4.0
 */
public class ViewResult implements View {
    protected ArrayList<Item2d> items = new ArrayList<>();
    private static final String FNAME = "items.bin";

    public ViewResult() {
        this(5);
    }

    public ViewResult(int n) {
        for(int i = 0; i < n; i++) {
            items.add(new Item2d());
        }
    }

    public ArrayList<Item2d> getItems() {
        return items;
    }

    public void init(int baseNumber) {
        for(int i = 0; i < items.size(); i++) {
            items.get(i).setNumber(baseNumber + i * 5);
        }
    }

    @Override
    public void viewInit() {
        init(10);
    }

    @Override
    public void viewSave() throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FNAME));
        os.writeObject(items);
        os.close();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void viewRestore() throws Exception {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(FNAME));
        items = (ArrayList<Item2d>) is.readObject();
        is.close();
    }

    @Override
    public void viewHeader() {
        System.out.println("Results:");
    }

    @Override
    public void viewBody() {
        for(Item2d item : items) {
            System.out.println(item);
        }
    }

    @Override
    public void viewFooter() {
        System.out.println("End of results.");
    }

    @Override
    public void viewShow() {
        viewHeader();
        viewBody();
        viewFooter();
    }
}
