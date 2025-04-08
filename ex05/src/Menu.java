import java.util.HashMap;
import java.util.Map;

/**
 * Клас меню
 */
public class Menu {
    private Map<Character, ConsoleCommand> commands = new HashMap<>();

    public void add(ConsoleCommand command) {
        commands.put(command.getKey(), command);
    }

    public void execute(char key) {
        ConsoleCommand cmd = commands.get(key);
        if (cmd != null) {
            cmd.execute();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Menu:\n");
        for (ConsoleCommand cmd : commands.values()) {
            sb.append("'").append(cmd.getKey()).append("' - ").append(cmd).append("\n");
        }
        return sb.toString();
    }
}
