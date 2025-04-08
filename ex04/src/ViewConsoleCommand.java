/**
 * Команда перегляду поточних даних
 * @author Nazar
 * @version 4.0
 */
public class ViewConsoleCommand implements ConsoleCommand {
    private final View view;

    /**
     * Конструктор команди перегляду
     * @param view об'єкт представлення
     */
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
        System.out.println("Current data:");
        view.viewShow();
    }

    @Override
    public void undo() {
        System.out.println("View command cannot be undone");
    }
}
