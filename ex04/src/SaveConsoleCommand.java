import java.io.IOException;

/**
 * Команда збереження даних
 * @author Nazar
 * @version 4.0
 */
public class SaveConsoleCommand implements ConsoleCommand {
    private final View view;
    private boolean wasExecuted = false;

    public SaveConsoleCommand(View view) {
        this.view = view;
    }

    @Override
    public char getKey() {
        return 's';
    }

    @Override
    public String toString() {
        return "'s'ave";
    }

    @Override
    public void execute() {
        try {
            System.out.println("Saving data...");
            view.viewSave();
            wasExecuted = true;
            System.out.println("Save completed successfully");
        } catch (IOException e) {
            System.err.println("Save error: " + e.getMessage());
            wasExecuted = false;
        }
    }

    @Override
    public void undo() {
        if (wasExecuted) {
            System.out.println("Warning: Cannot undo save operation");
        }
    }
}
