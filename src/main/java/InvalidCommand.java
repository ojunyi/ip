public class InvalidCommand extends Command {

    @Override
    public void execute(CommandContext context) {
        context.ui.showMessage("Invalid command.");
    }
}
