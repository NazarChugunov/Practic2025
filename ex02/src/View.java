import java.io.IOException;
/** Product
 * (шаблон проєктування
 * Factory Method)<br>
 * Інтерфейс "виготовлюваних"
 * об'єктів<br>
 * Оголошує методи
 * відображення об'єктів
 * @author Nazar
 * @version 2.0
 */
public interface View {
    /** Відображає заголовок */
    public void viewHeader();
    /** Відображає основну частину */
    public void viewBody();
    /** Відображає завершення */
    public void viewFooter();
    /** Відображає об'єкт повністю */
    public void viewShow();
    /** Виконує ініціалізацію */
    public void viewInit();
    /** Зберігає дані для подальшого відновлення */
    public void viewSave() throws IOException;
    /** Відновлює раніше збережені дані */
    public void viewRestore() throws Exception;
}
