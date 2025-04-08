/**
 * Команда генерації даних
 */
public class GenerateConsoleCommand implements ConsoleCommand {
    private View view;

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
        System.out.println("Generate new data...");
        view.viewInit();
        view.viewShow();
    }
}
