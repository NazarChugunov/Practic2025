import java.io.Serializable;

/**
 * Клас елемента даних
 */
public class Item2d implements Serializable {
    private double x;
    private double y;
    private static final long serialVersionUID = 1L;

    public Item2d() {
        this(0, 0);
    }

    public Item2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("(x=%.2f, y=%.2f)", x, y);
    }
}
