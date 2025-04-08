/**
 * Фабрика для створення табличного представлення
 */
public class ViewableTable implements Viewable {
    @Override
    public View getView() {
        return new ViewTable();
    }
}
