import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Application {
    private static Application instance;
    private final Menu menu;
    private final View view;
    private final BufferedReader reader;

    private Application() {
        this.menu = new Menu();
        this.view = new ViewableTable().getView();
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        configureCommands();
    }

    public static Application getInstance() {
        if (instance == null) {
            instance = new Application();
        }
        return instance;
    }

    private void configureCommands() {
        menu.addCommand(new ViewConsoleCommand(view));
        menu.addCommand(new GenerateConsoleCommand(view));
        menu.addCommand(new ScaleConsoleCommand(view));
        menu.addCommand(new SaveConsoleCommand(view));
        menu.addCommand(new RestoreConsoleCommand(view));
        menu.addCommand(new UndoConsoleCommand(menu));
    }

    public void run() {
        System.out.println("=== Number Representation Calculator ===");
        printHelp();

        while (true) {
            try {
                System.out.print("\nCommand> ");
                String input = reader.readLine().trim().toLowerCase();

                if (input.isEmpty()) continue;

                char command = input.charAt(0);

                switch (command) {
                    case 'h':
                        printHelp();
                        break;
                    case 'q':
                        System.out.println("Exiting...");
                        return;
                    case 'u':
                        menu.undoLastCommand();
                        break;
                    default:
                        menu.executeCommand(command);
                }
            } catch (IOException e) {
                System.err.println("Input error: " + e.getMessage());
            }
        }
    }

    private void printHelp() {
        System.out.println("\nEnter command...");
        System.out.print("'q'uit, 'v'iew, 'g'enerate, 'c'hange, 's'ave, 'r'estore, 'u'ndo, 'h'elp: ");
    }
}
