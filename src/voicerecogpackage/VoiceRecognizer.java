package voicerecogpackage;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

import java.io.IOException;

public class VoiceRecognizer {
    private Command startCommand;
    private String startDicPath;
    private String startLmPath;

    public VoiceRecognizer(String startDicPath, String startLmPath) {
        this.startDicPath = startDicPath;
        this.startLmPath = startLmPath;
    }

    public Command getStartCommand() {
        return startCommand;
    }

    //Init the start command
    public void start() throws Exception {
        startCommand = new StartCommand(startDicPath, startLmPath);
        startCommand.initConfig();
        startCommand.initRecognizer();
        startCommand.execute();
    }

//    public static void main(String[] args) throws IOException {
////        VoiceRecognizer voiceRecognizer = new VoiceRecognizer("resource:/edu/cmu/sphinx/models/en-us/en-us","resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict","resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
//
//        Configuration config = new Configuration();
//        config.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
//        config.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
//        config.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
//
//        LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(config);
//
//        recognizer.startRecognition(true);
//
//        SpeechResult result;
//
//        while ((result = recognizer.getResult()) != null) {
//            String input = result.getHypothesis();
//            System.out.println("Input command: " + input);
//
//            if (input.toLowerCase().equals("computer")) {
//                System.out.println("We've started new recog - just stopped old");
//            }
//        }
//
//    }


}
