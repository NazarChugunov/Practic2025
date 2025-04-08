import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Реалізація представлення даних
 */
public class ViewResult implements View {
    protected ArrayList<Item2d> items = new ArrayList<>();
    private static final String FNAME = "items.bin";
    private static final Random random = new Random();

    public ViewResult(int n) {
        for(int i = 0; i < n; i++) {
            items.add(new Item2d(i, random.nextDouble() * 100 - 50));
        }
    }

    public ArrayList<Item2d> getItems() {
        return items;
    }

    @Override
    public void viewInit() {
        items.clear();
        for(int i = 0; i < 10; i++) {
            items.add(new Item2d(i, random.nextDouble() * 100 - 50));
        }
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
    public void viewShow() {
        System.out.println("Current items:");
        for(Item2d item : items) {
            System.out.println(item);
        }
    }
}
