/**
 * Команда масштабування числа та його представлень
 * @author Nazar
 * @version 4.0
 */
public class ScaleCommand extends ItemCommand {
    private double scaleFactor;
    private int originalNumber;

    public ScaleCommand(double scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    @Override
    public void execute() {
        originalNumber = item.getNumber();

        int scaledNumber = (int)(originalNumber * scaleFactor);
        item.setNumber(scaledNumber);

        System.out.printf("Scaled number: %d -> %d (factor: %.2f)\n",
                originalNumber, scaledNumber, scaleFactor);
    }

    @Override
    public void undo() {
        item.setNumber(originalNumber);
        System.out.println("Undo scaling. Restored number: " + originalNumber);
    }
}
