package voicerecogpackage;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;

import java.io.IOException;

public class StartCommand extends Command {
    private SpeechResult result;
    private String inputCommand = "";
    private Configuration commandConfiguration;

    public StartCommand(String dicPath, String lmPath, Configuration configuration) {
        super(dicPath, lmPath, configuration);
        commandConfiguration = new Configuration();
    }

    @Override
    public boolean gatherInput() {
        return true;
    }

    @Override
    public boolean execute() {
        while ((result = getRecognizer().getResult()) != null) {
            inputCommand = result.getHypothesis();
            System.out.println("Input command: " + inputCommand);

            if (inputCommand.toLowerCase().equals("operation")) {

            }
            else if (inputCommand.toLowerCase().equals("search")) {

            }
            else if (inputCommand.toLowerCase().equals("computer")) {
                this.closeRecognizer(); //Closing before starting second listening

                ComputerCommand computerCommand = new ComputerCommand("src/corpusRes/computerDic.dic", "src/corpusRes/computerLm.lm", commandConfiguration); //Creating out computer command object
                computerCommand.initConfig(); //initializing the config values
                try {
                    computerCommand.initRecognizer(); //initializing the recognizer
                }
                catch (IOException e) {
                    System.out.println("Error: " + "ComputerCommand recognizer couldn't be initialized");
                }
                //Once both config and recog are initialized, then call execute
                computerCommand.execute();
                break; //do we need this here?
            }
            else if (inputCommand.toLowerCase().equals("open")) {

            }
        }
        return false;
    }
}
