import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Містить реалізацію методів для обчислення та відображення результатів.
 * @author Nazar
 * @version 1.0
 */
public class Calc {
    /** Ім'я файлу, що використовується під час серіалізації. */
    private static final String FNAME = "Item2d.bin";
    /** Зберігає результат обчислень. Об'єкт класу {@linkplain Item2d} */
    private Item2d result;

    /** Ініціалізує {@linkplain Calc#result} */
    public Calc() {
        result = new Item2d();
    }

    /** Встановлює значення {@linkplain Calc#result}
     * @param result — нове значення посилання на об'єкт {@linkplain Item2d} */
    public void setResult(Item2d result) {
        this.result = result;
    }

    /** Отримує значення {@linkplain Calc#result}
     * @return поточне значення посилання на об'єкт {@linkplain Item2d} */
    public Item2d getResult() {
        return result;
    }

    /** Обчислює двійкове, вісімкове та шістнадцяткове представлення числа.
     * @param number — ціле число для конвертації.
     * @return масив рядків із представленнями числа [binary, octal, hex].
     */
    private String[] calc(int number) {
        String binary = Integer.toBinaryString(number);
        String octal = Integer.toOctalString(number);
        String hex = Integer.toHexString(number);
        return new String[]{binary, octal, hex};
    }

    /** Обчислює представлення числа та зберігає
     * результат в об'єкті {@linkplain Calc#result}
     * @param number — ціле число для конвертації.
     */
    public void init(int number) {
        String[] representations = calc(number);
        result.setNumber(number);
        result.setBinary(representations[0]);
        result.setOctal(representations[1]);
        result.setHex(representations[2]);
    }

    /** Виводить результат обчислень. */
    public void show() {
        System.out.println(result);
    }

    /** Зберігає {@linkplain Calc#result} у файл {@linkplain Calc#FNAME}
     * @throws IOException
     */
    public void save() throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(
                new FileOutputStream(FNAME));
        os.writeObject(result);
        os.flush();
        os.close();
    }

    /** Відновлює {@linkplain Calc#result} з файлу {@linkplain Calc#FNAME}
     * @throws Exception
     */
    public void restore() throws Exception {
        ObjectInputStream is = new ObjectInputStream(
                new FileInputStream(FNAME));
        result = (Item2d)is.readObject();
        is.close();
    }
}
