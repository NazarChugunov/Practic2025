import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Головний клас застосунку
 * @author Nazar
 * @version 3.0
 */
public class Main {
    private View view;

    public Main(View view) {
        this.view = view;
    }

    public void menu() {
        String s = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        try {
            do {
                System.out.println("\nEnter command...");
                System.out.print("'q'uit, 'v'iew, 'i'nit, 'c'hange width, 's'ave, 'r'estore: ");
                s = in.readLine();

                switch(s.charAt(0)) {
                    case 'q':
                        System.out.println("Exit.");
                        return;
                    case 'v':
                        view.viewShow();
                        break;
                    case 'i':
                        System.out.print("Enter base number: ");
                        int baseNumber = Integer.parseInt(in.readLine());
                        view.viewInit(); // Використовуємо viewInit() замість init()
                        System.out.println("Data initialized with base number: " + baseNumber);
                        break;
                    case 'c':
                        if(view instanceof ViewTable) {
                            System.out.print("Enter table width: ");
                            int width = Integer.parseInt(in.readLine());
                            ((ViewTable)view).setWidth(width);
                        }
                        break;
                    case 's':
                        view.viewSave();
                        System.out.println("Saved successfully.");
                        break;
                    case 'r':
                        view.viewRestore();
                        System.out.println("Restored successfully.");
                        break;
                    default:
                        System.out.println("Invalid command.");
                }
            } while(true);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Main main = new Main(new ViewableTable().getView());
        main.menu();
    }
}
