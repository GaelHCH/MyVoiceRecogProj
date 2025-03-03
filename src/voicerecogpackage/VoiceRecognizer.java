package voicerecogpackage;

import edu.cmu.sphinx.api.Configuration;
import java.io.IOException;

public class VoiceRecognizer {
    private Command startCommand;
    private Configuration startConfig;

    public VoiceRecognizer() {
        startConfig = new Configuration();
        startCommand = new StartCommand("src/corpusRes/commandsDic.dic", "src/corpusRes/commandsLm.lm", startConfig);
    }

    public Command getStartCommand() {
        return startCommand;
    }

    public static void main(String[] args) throws IOException {
        VoiceRecognizer voiceRecognizer = new VoiceRecognizer();
        voiceRecognizer.getStartCommand().initConfig();
        voiceRecognizer.getStartCommand().initRecognizer();
        voiceRecognizer.getStartCommand().execute();

    }


}
