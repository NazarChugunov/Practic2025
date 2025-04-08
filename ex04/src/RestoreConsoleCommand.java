import java.util.List;
import java.util.ArrayList;

/**
 * Команда відновлення даних
 * @author Nazar
 * @version 4.0
 */
public class RestoreConsoleCommand implements ConsoleCommand {
    private final View view;
    private List<Item2d> backupState;

    public RestoreConsoleCommand(View view) {
        this.view = view;
    }

    @Override
    public char getKey() {
        return 'r';
    }

    @Override
    public String toString() {
        return "'r'estore";
    }

    @Override
    public void execute() {
        // Зберігаємо поточний стан для можливого скасування
        backupState = new ArrayList<>(((ViewResult)view).getItems());

        try {
            System.out.println("Restoring data...");
            view.viewRestore();
            System.out.println("Restore completed successfully");
            view.viewShow();
        } catch (Exception e) {
            System.err.println("Restore error: " + e.getMessage());
        }
    }

    @Override
    public void undo() {
        if (backupState != null) {
            System.out.println("Undoing restore...");
            ((ViewResult)view).getItems().clear();
            ((ViewResult)view).getItems().addAll(backupState);
            view.viewShow();
        }
    }
}
