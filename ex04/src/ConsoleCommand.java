/**
 * Інтерфейс консольної команди
 */
public interface ConsoleCommand extends Command {
    char getKey();
    String toString();
}
