/**
 * Команда перегляду даних
 */
public class ViewConsoleCommand implements ConsoleCommand {
    private View view;

    public ViewConsoleCommand(View view) {
        this.view = view;
    }

    @Override
    public char getKey() {
        return 'v';
    }

    @Override
    public String toString() {
        return "'v'iew";
    }

    @Override
    public void execute() {
        System.out.println("View current data:");
        view.viewShow();
    }
}
