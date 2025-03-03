package voicerecogpackage;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;

import java.io.IOException;

public abstract class Command {
    private String modelPath = "resource:/edu/cmu/sphinx/models/en-us/en-us";
    private String dicPath;
    private String lmPath;
    private Configuration configuration;
    private LiveSpeechRecognizer recognizer;

    public Command(Configuration configuration) {
        this.configuration = configuration;
    }

    public Command(String dicPath, String lmPath) {
        this.dicPath = dicPath;
        this.lmPath = lmPath;
    }

    public Command(String dicPath, String lmPath, Configuration configuration) {
        this.dicPath = dicPath;
        this.lmPath = lmPath;
        this.configuration = configuration;
    }

    //Initializes the model
    public void initConfig() {
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

    //Method to gather input
    public abstract boolean gatherInput();

    //Execute method once input is gathered
    public abstract boolean execute();

    //Getters and setters
    public Configuration getConfiguration() {
        return configuration;
    }

    public LiveSpeechRecognizer getRecognizer() {
        return recognizer;
    }

    public String getModelPath() {
        return modelPath;
    }
}
