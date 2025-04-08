import java.util.Stack;
import java.util.HashMap;
import java.util.Map;

/**
 * Клас меню з підтримкою скасування операцій
 */
public class Menu {
    private final Map<Character, ConsoleCommand> commands = new HashMap<>();
    private final Stack<Command> history = new Stack<>();

    public void addCommand(ConsoleCommand command) {
        commands.put(command.getKey(), command);
    }

    public void executeCommand(char key) {
        ConsoleCommand cmd = commands.get(key);
        if (cmd != null) {
            cmd.execute();
            history.push(cmd);
        }
    }

    public void undoLastCommand() {
        if (!history.isEmpty()) {
            Command cmd = history.pop();
            cmd.undo();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Available commands:\n");
        commands.values().forEach(cmd ->
                sb.append("'").append(cmd.getKey()).append("' - ").append(cmd).append("\n"));
        sb.append("'u' - undo last command\n");
        sb.append("'q' - quit");
        return sb.toString();
    }
}
