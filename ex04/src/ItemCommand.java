/**
 * Абстрактна команда для роботи з елементами колекції
 */
public abstract class ItemCommand implements Command {
    protected Item2d item;
    protected double previousValue;

    public ItemCommand setItem(Item2d item) {
        this.item = item;
        return this;
    }

    @Override
    public abstract void execute();

    @Override
    public abstract void undo();
}
