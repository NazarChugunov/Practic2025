import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Консольна команда масштабування чисел
 * @author Nazar
 * @version 4.0
 */
public class ScaleConsoleCommand implements ConsoleCommand {
    private final View view;

    public ScaleConsoleCommand(View view) {
        this.view = view;
    }

    @Override
    public char getKey() {
        return 'c';
    }

    @Override
    public String toString() {
        return "'c'hange scale";
    }

    @Override
    public void execute() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter scale factor: ");
            double factor = Double.parseDouble(reader.readLine());

            if (factor <= 0) {
                System.out.println("Factor must be positive!");
                return;
            }

            ViewResult vr = (ViewResult) view;
            vr.getItems().forEach(item -> {
                ScaleCommand cmd = new ScaleCommand(factor);
                cmd.setItem(item).execute();
            });

            System.out.println("Scaling completed. Current data:");
            view.viewShow();

        } catch (IOException | NumberFormatException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void undo() {
    }
}
