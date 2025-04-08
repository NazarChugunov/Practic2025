import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Головний клас застосунку
 * author @Nazar
 * @version 5.0
 */
public class Main {
    public static void main(String[] args) {
        View view = new ViewResult(10);
        Menu menu = new Menu();
        menu.add(new ViewConsoleCommand(view));
        menu.add(new GenerateConsoleCommand(view));
        menu.add(new ChangeConsoleCommand(view));
        menu.add(new ExecuteConsoleCommand(view));

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Parallel collection processing demo");
            System.out.println(menu);
            String input;
            do {
                System.out.print("Command (h for help): ");
                input = reader.readLine().trim().toLowerCase();
                if (input.length() > 0) {
                    char key = input.charAt(0);
                    if (key == 'h') {
                        System.out.println(menu);
                    } else if (key == 'q') {
                        break;
                    } else {
                        menu.execute(key);
                    }
                }
            } while (true);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
