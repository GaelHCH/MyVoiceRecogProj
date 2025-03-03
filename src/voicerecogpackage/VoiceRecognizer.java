package voicerecogpackage;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

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
//        VoiceRecognizer voiceRecognizer = new VoiceRecognizer();
//        voiceRecognizer.getStartCommand().initConfig();
//        voiceRecognizer.getStartCommand().initRecognizer();
//        voiceRecognizer.getStartCommand().execute();

        Configuration config = new Configuration();
        config.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        config.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        config.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

        LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(config);

        recognizer.startRecognition(true);

        SpeechResult result;

        while ((result = recognizer.getResult()) != null) {
            String input = result.getHypothesis();
            System.out.println("Input command: " + input);
        }

        recognizer.stopRecognition();
    }


}
