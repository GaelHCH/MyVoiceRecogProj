package voicerecogpackage;

import edu.cmu.sphinx.api.Configuration;

public class OperationCommand extends Command {
    private String input;

    public OperationCommand(String dicPath, String lmPath, Configuration configuration, String input) {
        super(dicPath, lmPath, configuration);
        this.input = input;
    }

    @Override
    public boolean gatherInput() {
        return false;
    }

    @Override
    public boolean execute() {
        return false;
    }
}
