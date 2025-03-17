package voicerecogpackage;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class StartCommand extends Command {

    public StartCommand(String dicPath, String lmPath) {
        super(dicPath, lmPath);
    }


    @Override
    public boolean execute() throws IOException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException {
        System.out.println("Starting voice recognizer - Start command");
        showTalkMessage("Ready for Input");

        while ((result = getRecognizer().getResult()) != null) {
            inputCommand = result.getHypothesis();
            System.out.println("Input command: " + inputCommand);

            if (inputCommand.toLowerCase().equals("math")) {
                removeTalkMessage(); //closing this frame until next input
                MathCommand mathCommand = new MathCommand("file:///C:/Users/Gael/IdeaProjects/MyVoiceRecogProj/src/corpusRes/mathCommDic.dic", "file:///C:/Users/Gael/IdeaProjects/MyVoiceRecogProj/src/corpusRes/mathCommLm.lm"); //make these absolute paths
                mathCommand.initConfig();
                mathCommand.initRecognizer();
                mathCommand.execute();
                mathCommand.closeRecognizer();
                return true;
            }
            else if (inputCommand.toLowerCase().equals("search")) {
                removeTalkMessage(); //closing this frame until next input
                SearchCommand searchCommand = new SearchCommand("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict", "resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
                searchCommand.initConfig();
                searchCommand.initRecognizer();
                searchCommand.execute();
                searchCommand.closeRecognizer();
                return true;
            }
            else if (inputCommand.toLowerCase().equals("computer")) {
                removeTalkMessage(); //closing this frame until next input
                ComputerCommand computerCommand = new ComputerCommand("file:///C:/Users/Gael/IdeaProjects/MyVoiceRecogProj/src/corpusRes/computerDic.dic","file:///C:/Users/Gael/IdeaProjects/MyVoiceRecogProj/src/corpusRes/computerLm.lm");//Creating out computer command object
                computerCommand.initConfig();
                computerCommand.initRecognizer();
                //Once both config and recog are initialized, then call execute
                computerCommand.execute();
                computerCommand.closeRecognizer();
                return true;
            }
            else if (inputCommand.toLowerCase().equals("open")) {
                removeTalkMessage(); //closing this frame until next input
                OpenCommand openCommand = new OpenCommand("file:///C:/Users/Gael/IdeaProjects/MyVoiceRecogProj/src/corpusRes/openCommDic.dic","file:///C:/Users/Gael/IdeaProjects/MyVoiceRecogProj/src/corpusRes/openCommLm.lm");
                openCommand.initConfig();
                openCommand.initRecognizer();
                openCommand.execute();
                openCommand.closeRecognizer();
                return true;
            }
        }
        return false;
    }
}
