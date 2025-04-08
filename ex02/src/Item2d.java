import java.io.Serializable;

/**
 * Клас для зберігання числа та його представлень у різних системах числення.
 * @author Nazar
 * @version 2.0
 */
public class Item2d implements Serializable {
    private int number;
    private String binary;
    private String octal;
    private String hex;
    private transient String comment;

    private static final long serialVersionUID = 1L;

    public Item2d() {
        this(0);
    }

    public Item2d(int number) {
        this.number = number;
        calculateRepresentations();
        this.comment = "Default comment";
    }

    private void calculateRepresentations() {
        this.binary = Integer.toBinaryString(number);
        this.octal = Integer.toOctalString(number);
        this.hex = Integer.toHexString(number);
    }

    // Гетери
    public int getNumber() {
        return number;
    }

    public String getBinary() {
        return binary;
    }

    public String getOctal() {
        return octal;
    }

    public String getHex() {
        return hex;
    }

    public String getComment() {
        return comment;
    }

    // Сетери
    public void setNumber(int number) {
        this.number = number;
        calculateRepresentations();
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return String.format("Number: %d, Binary: %s, Octal: %s, Hex: %s, Comment: %s",
                number, binary, octal, hex, comment);
    }
}
