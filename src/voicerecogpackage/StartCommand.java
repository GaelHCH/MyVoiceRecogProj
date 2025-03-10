package voicerecogpackage;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

import java.io.IOException;

public class StartCommand extends Command {

    public StartCommand(String dicPath, String lmPath) {
        super(dicPath, lmPath);
    }


    @Override
    public boolean execute() throws IOException, InterruptedException {
        System.out.println("Starting voice recognizer - Start command");
        while ((result = getRecognizer().getResult()) != null) {
            inputCommand = result.getHypothesis();
            System.out.println("Input command: " + inputCommand);

            if (inputCommand.toLowerCase().equals("math")) {
                MathCommand mathCommand = new MathCommand("src/corpusRes/mathCommDic.dic", "src/corpusRes/mathCommLm.lm");
                mathCommand.initConfig();
                mathCommand.initRecognizer();
                mathCommand.execute();
                return true;
            }
            else if (inputCommand.toLowerCase().equals("search")) {
                SearchCommand searchCommand = new SearchCommand("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict", "resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
                searchCommand.initConfig();
                searchCommand.initRecognizer();
                searchCommand.execute();
                return true;
            }
            else if (inputCommand.toLowerCase().equals("computer")) {

                ComputerCommand computerCommand = new ComputerCommand("src/corpusRes/computerDic.dic","src/corpusRes/computerLm.lm");//Creating out computer command object
                computerCommand.initConfig();
                computerCommand.initRecognizer();
                //Once both config and recog are initialized, then call execute
                computerCommand.execute();
                return true;
            }
            else if (inputCommand.toLowerCase().equals("open")) {
                OpenCommand openCommand = new OpenCommand("src/corpusRes/openCommDic.dic","src/corpusRes/openCommLm.lm");
                openCommand.initConfig();
                openCommand.initRecognizer();
                openCommand.execute();
                return true;
            }
        }
        return false;
    }
}
