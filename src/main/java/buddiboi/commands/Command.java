package buddiboi.commands;

/**
 * Abstract base class to be used for all commands.
 */
public abstract class Command {
    public abstract String execute(CommandContext context);

    public boolean isExit() {
        return false;
    }
}
