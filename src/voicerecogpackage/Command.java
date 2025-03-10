package voicerecogpackage;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

import java.io.IOException;

public abstract class Command {
    private String modelPath = "resource:/edu/cmu/sphinx/models/en-us/en-us";
    private String dicPath;
    private String lmPath;
    private LiveSpeechRecognizer recognizer;
    private Configuration configuration;
    protected SpeechResult result;
    protected String inputCommand;

    public Command(String dicPath, String lmPath) {
        this.dicPath = dicPath;
        this.lmPath = lmPath;
    }

    //Initializes the model
    public void initConfig() {
        configuration = new Configuration();
        configuration.setAcousticModelPath(modelPath);
        configuration.setDictionaryPath(dicPath);
        configuration.setLanguageModelPath(lmPath);
    }

    //Initializes the recognizer
    public void initRecognizer() throws IOException {
        recognizer = new LiveSpeechRecognizer(configuration);
        recognizer.startRecognition(true);
    }

    //Closes the recognizer
    public void closeRecognizer() {
        recognizer.stopRecognition();
    }

    //Execute method once input is gathered
    public abstract boolean execute() throws Exception;

    public LiveSpeechRecognizer getRecognizer() {
        return recognizer;
    }

    public String getModelPath() {
        return modelPath;
    }
}
