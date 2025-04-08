import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Основний клас застосунку з діалоговим інтерфейсом.
 * @author Nazar
 * @version 2.0
 */
public class Main {
    private View view = new ViewableResult().getView();

    private void menu() {
        String s = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        try {
            do {
                System.out.println("Enter command...");
                System.out.print("'q'uit, 'v'iew, 'i'nit, 's'ave, 'r'estore: ");

                s = in.readLine();

                switch(s.toLowerCase().charAt(0)) {
                    case 'v':
                        view.viewShow();
                        break;
                    case 'i':
                        System.out.print("Enter base number: ");
                        int num = Integer.parseInt(in.readLine());
                        ((ViewResult)view).init(num);
                        break;
                    case 's':
                        try {
                            view.viewSave();
                            System.out.println("Collection saved.");
                        } catch (IOException e) {
                            System.out.println("Save error: " + e);
                        }
                        break;
                    case 'r':
                        try {
                            view.viewRestore();
                            System.out.println("Collection restored.");
                        } catch (Exception e) {
                            System.out.println("Restore error: " + e);
                        }
                        break;
                    case 'q':
                        System.out.println("Exit.");
                        return;
                    default:
                        System.out.println("Invalid command.");
                }
            } while(true);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.menu();
    }
}