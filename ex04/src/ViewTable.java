import java.util.Formatter;

/**
 * Табличне представлення результатів
 */
public class ViewTable extends ViewResult {
    private int width;

    public ViewTable() {
        this(20);
    }

    public ViewTable(int width) {
        super();
        this.width = width;
    }

    public ViewTable(int width, int n) {
        super(n);
        this.width = width;
    }

    @Override
    public void viewHeader() {
        outLine();
        System.out.printf("\n| %-"+(width/2)+"s | %-"+(width/2)+"s |\n", "Number", "Representations");
        outLine();
    }

    @Override
    public void viewBody() {
        for(Item2d item : getItems()) {
            System.out.printf("\n| %-"+(width/2)+"d | %-"+(width/2)+"s |",
                    item.getNumber(),
                    item.getBinary() + "/" + item.getOctal() + "/" + item.getHex());
        }
    }

    @Override
    public void viewFooter() {
        System.out.println();
        outLine();
    }

    private void outLine() {
        for(int i = 0; i < width + 4; i++) {
            System.out.print("-");
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
