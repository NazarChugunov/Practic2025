class UndoConsoleCommand implements ConsoleCommand {
    private final Menu menu;

    public UndoConsoleCommand(Menu menu) {
        this.menu = menu;
    }

    @Override
    public char getKey() {
        return 'u';
    }

    @Override
    public String toString() {
        return "Undo last command";
    }

    @Override
    public void execute() {
        menu.undoLastCommand();
    }

    @Override
    public void undo() {
    }
}
