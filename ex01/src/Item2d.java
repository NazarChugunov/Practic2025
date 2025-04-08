import java.io.Serializable;

/**
 * Зберігає початкове число та його представлення в різних системах числення.
 * @author Nazar
 * @version 1.0
 */
public class Item2d implements Serializable {
    /** Початкове число для конвертації */
    private int number;

    /** Двійкове представлення числа */
    private String binary;

    /** Вісімкове представлення числа */
    private String octal;

    /** Шістнадцяткове представлення числа */
    private String hex;

    /** Автоматично згенерована константа */
    private static final long serialVersionUID = 1L;

    /**
     * Transient поле — не серіалізується.
     * Містить коментар до обчислень.
     */
    private transient String comment;

    /** Ініціалізує поля значеннями за замовчуванням */
    public Item2d() {
        number = 0;
        binary = "0";
        octal = "0";
        hex = "0";
    }

    /** Встановлює значення всіх полів
     * @param number — початкове число
     * @param binary — двійкове представлення
     * @param octal — вісімкове представлення
     * @param hex — шістнадцяткове представлення
     */
    public Item2d(int number, String binary, String octal, String hex) {
        this.number = number;
        this.binary = binary;
        this.octal = octal;
        this.hex = hex;
    }

    // Гетери та сетери

    public int getNumber() {
        return number;
    }

    public int setNumber(int number) {
        return this.number = number;
    }

    public String getBinary() {
        return binary;
    }

    public String setBinary(String binary) {
        return this.binary = binary;
    }

    public String getOctal() {
        return octal;
    }

    public String setOctal(String octal) {
        return this.octal = octal;
    }

    public String getHex() {
        return hex;
    }

    public String setHex(String hex) {
        return this.hex = hex;
    }

    /**
     * Повертає значення transient поля comment.
     * Це поле не серіалізується й буде null після відновлення з файлу.
     *
     * @return поточне значення коментаря або null, якщо об'єкт було десеріалізовано
     */
    public String getComment() {
        return this.comment;
    }

    /**
     * Встановлює значення transient поля comment.
     * Це значення не буде збережене під час серіалізації.
     *
     * @param comment — текст коментаря
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /** Встановлює всі представлення числа одразу
     * @param number — початкове число
     * @param binary — двійкове представлення
     * @param octal — вісімкове представлення
     * @param hex — шістнадцяткове представлення
     * @return this
     */
    public Item2d setAll(int number, String binary, String octal, String hex) {
        this.number = number;
        this.binary = binary;
        this.octal = octal;
        this.hex = hex;
        return this;
    }

    /** Представляє результат у вигляді рядка */
    @Override
    public String toString() {
        return String.format("Number: %d, Binary: %s, Octal: %s, Hex: %s",
                number, binary, octal, hex);
    }

    /** Порівнює об'єкти на рівність */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Item2d other = (Item2d) obj;
        if (number != other.number)
            return false;
        if (!binary.equals(other.binary))
            return false;
        if (!octal.equals(other.octal))
            return false;
        if (!hex.equals(other.hex))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + binary.hashCode();
        result = 31 * result + octal.hashCode();
        result = 31 * result + hex.hashCode();
        return result;
    }
}
