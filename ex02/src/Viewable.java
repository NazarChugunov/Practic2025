/** Creator
 * (шаблон проєктування
 * Factory Method)<br>
 * Оголошує метод,
 * що "виготовляє" об'єкти
 * @author Nazar
 * @version 2.0
 * @see Viewable#getView()
 */
public interface Viewable {
    /** Створює об'єкт, який реалізує {@linkplain View} */
    public View getView();
}
