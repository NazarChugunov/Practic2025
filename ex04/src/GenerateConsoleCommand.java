import java.util.ArrayList;
import java.util.List;

/**
 * Команда генерації нових даних
 * @author Nazar
 * @version 4.0
 */
public class GenerateConsoleCommand implements ConsoleCommand {
    private final View view;
    private List<Item2d> previousState;

    public GenerateConsoleCommand(View view) {
        this.view = view;
    }

    @Override
    public char getKey() {
        return 'g';
    }

    @Override
    public String toString() {
        return "'g'enerate";
    }

    @Override
    public void execute() {
        previousState = new ArrayList<>(((ViewResult)view).getItems());

        System.out.println("Generating new data...");
        view.viewInit();
        view.viewShow();
    }

    @Override
    public void undo() {
        if (previousState != null) {
            System.out.println("Undoing generation...");
            ((ViewResult)view).getItems().clear();
            ((ViewResult)view).getItems().addAll(previousState);
            view.viewShow();
        }
    }
}
