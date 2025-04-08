/**
 * Конкретна фабрика для створення об'єктів ViewResult.
 * @author Nazar
 * @version 2.0
 */
public class ViewableResult implements Viewable {
    @Override
    public View getView() {
        return new ViewResult();
    }
}
