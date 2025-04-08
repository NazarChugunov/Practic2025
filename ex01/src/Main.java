import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Обчислення та відображення результатів.
 * Містить реалізацію статичного методу main().
 * @author Nazar
 * @version 1.0
 */
public class Main {
    /** Об'єкт класу {@linkplain Calc}.<br>Вирішує задачу інд. завдання. */
    private Calc calc = new Calc();

    /** Відображає меню. */
    private void menu() {
        String s = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        do {
            do {
                System.out.println("\nEnter command...");
                System.out.print("'q'uit, 'v'iew, 'e'nter number, 's'ave, 'r'estore, 'c'omment: ");
                try {
                    s = in.readLine();
                } catch(IOException e) {
                    System.out.println("Error: " + e);
                    System.exit(0);
                }
            } while (s.length() != 1);

            switch (s.charAt(0)) {
                case 'q':
                    System.out.println("Exit.");
                    break;

                case 'v':
                    System.out.println("View current:");
                    calc.show();
                    // Демонстрація transient поля
                    System.out.println("Transient field demo: " +
                            (calc.getResult().getComment() != null ?
                                    calc.getResult().getComment() : "[null]"));
                    break;

                case 'e':
                    System.out.print("Enter integer number: ");
                    try {
                        int num = Integer.parseInt(in.readLine());
                        calc.init(num);
                        // Встановлення коментаря за замовчуванням
                        calc.getResult().setComment("Calculation for " + num);
                        System.out.println("Calculated representations:");
                        calc.show();
                    } catch (IOException e) {
                        System.out.println("Input error: " + e);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format");
                    }
                    break;

                case 's':
                    System.out.println("Saving current...");
                    try {
                        calc.save();
                        System.out.println("Saved successfully:");
                        calc.show();
                        System.out.println("Note: Comment (transient) won't be saved");
                    } catch (IOException e) {
                        System.out.println("Serialization error: " + e);
                    }
                    break;

                case 'r':
                    System.out.println("Restoring last saved...");
                    try {
                        calc.restore();
                        System.out.println("Restored successfully:");
                        calc.show();
                        // Показуємо, що transient поле не збереглося
                        System.out.println("Transient field after restore: " +
                                (calc.getResult().getComment() != null ?
                                        calc.getResult().getComment() : "[null]"));
                    } catch (Exception e) {
                        System.out.println("Serialization error: " + e);
                    }
                    break;

                case 'c':
                    System.out.print("Enter comment text: ");
                    try {
                        String comment = in.readLine();
                        calc.getResult().setComment(comment);
                        System.out.println("Comment added!");
                    } catch (IOException e) {
                        System.out.println("Input error: " + e);
                    }
                    break;

                default:
                    System.out.print("Wrong command. ");
            }
        } while(s.charAt(0) != 'q');
    }

    /**
     * Виконується при запуску програми.
     * @param args – параметри запуску програми.
     */
    public static void main(String[] args) {
        Main main = new Main();
        main.menu();
    }
}