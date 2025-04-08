import java.io.*;
import java.util.ArrayList;

/**
 * Конкретная реализация View для работы с коллекцией Item2d.
 * @author Nazar
 * @version 2.0
 */
public class ViewResult implements View {
    private String fname = "items.bin"; // Поле для хранения имени файла
    private ArrayList<Item2d> items = new ArrayList<>();

    public ViewResult() {
        this(5); // По умолчанию 5 элементов
    }

    public ViewResult(int n) {
        for(int i = 0; i < n; i++) {
            items.add(new Item2d(i * 10));
        }
    }

    /**
     * Возвращает текущее имя файла для сохранения/восстановления
     * @return имя файла
     */
    public String getFname() {
        return fname;
    }

    /**
     * Устанавливает имя файла для сохранения/восстановления
     * @param fname имя файла
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * Получает текущее число элементов в коллекции
     * @return количество элементов
     */
    public int getNumber() {
        return items.size();
    }

    public ArrayList<Item2d> getItems() {
        return items;
    }

    public void init(int baseNumber) {
        for(int i = 0; i < items.size(); i++) {
            items.get(i).setNumber(baseNumber + i * 5);
            items.get(i).setComment("Item #" + (i+1));
        }
    }

    @Override
    public void viewInit() {
        init(10);
    }

    @Override
    public void viewSave() throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fname));
        os.writeObject(items);
        os.close();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void viewRestore() throws Exception {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(fname));
        items = (ArrayList<Item2d>) is.readObject();
        is.close();
    }

    @Override
    public void viewHeader() {
        System.out.println("Number representations (" + getNumber() + " items):");
    }

    @Override
    public void viewBody() {
        for(Item2d item : items) {
            System.out.printf("Number: %d | Binary: %s | Octal: %s | Hex: %s | Comment: %s\n",
                    item.getNumber(), item.getBinary(), item.getOctal(), item.getHex(), item.getComment());
        }
    }

    @Override
    public void viewFooter() {
        System.out.println("End of list.");
    }

    @Override
    public void viewShow() {
        viewHeader();
        viewBody();
        viewFooter();
    }
}