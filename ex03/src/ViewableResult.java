/**
 * Фабрика для створення базового представлення
 */
public class ViewableResult implements Viewable {
    @Override
    public View getView() {
        return new ViewResult();
    }
}
