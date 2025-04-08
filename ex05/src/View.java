import java.io.IOException;

/**
 * Інтерфейс представлення даних
 */
public interface View {
    void viewShow();
    void viewInit();
    void viewSave() throws IOException;
    void viewRestore() throws Exception;
}
