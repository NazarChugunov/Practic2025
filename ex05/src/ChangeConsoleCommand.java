import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Команда зміни даних
 */
public class ChangeConsoleCommand implements ConsoleCommand {
    private View view;

    public ChangeConsoleCommand(View view) {
        this.view = view;
    }

    @Override
    public char getKey() {
        return 'c';
    }

    @Override
    public String toString() {
        return "'c'hange";
    }

    @Override
    public void execute() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter new Y value: ");
            double y = Double.parseDouble(reader.readLine());

            ViewResult vr = (ViewResult) view;
            for (Item2d item : vr.getItems()) {
                item.setY(y);
            }

            System.out.println("Items changed:");
            view.viewShow();

        } catch (IOException | NumberFormatException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
