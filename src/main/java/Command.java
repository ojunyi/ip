public abstract class Command {
    public abstract void execute(CommandContext context);

    public boolean isExit() {
        return false;
    }
}
