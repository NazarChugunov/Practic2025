class ExitConsoleCommand implements ConsoleCommand {
    @Override
    public char getKey() {
        return 'q';
    }

    @Override
    public String toString() {
        return "Exit application";
    }

    @Override
    public void execute() {
    }

    @Override
    public void undo() {
    }
}