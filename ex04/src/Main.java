import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Головний клас застосунку з розширеною функціональністю
 * @author Nazar
 * @version 4.0
 */
public class Main {
    public static void main(String[] args) {
        Application app = Application.getInstance();
        app.run();
    }
}
