package buddiboi.commands;

import buddiboi.ui.Ui;

/**
 * Command to echo the users input
 */
public class EchoCommand extends Command {

    private final String[] args;

    public EchoCommand(String... args) {
        this.args = args;
    }

    @Override
    public void execute(CommandContext context) {
        Ui.showEchoCommand(args);
    };
}
