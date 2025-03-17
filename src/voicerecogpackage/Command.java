package voicerecogpackage;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public abstract class Command {
    private String modelPath = "resource:/edu/cmu/sphinx/models/en-us/en-us";
    private String dicPath;
    private String lmPath;
    private LiveSpeechRecognizer recognizer;
    private Configuration configuration;
    protected SpeechResult result;
    protected String inputCommand;
    protected boolean listening;
    private JFrame messageFrame;
    private JPanel messagePanel;

    public Command(String dicPath, String lmPath) {
        this.dicPath = dicPath;
        this.lmPath = lmPath;
        listening = true;
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

    //Shows a gui message on screen to let the user know when to talk
    public void showTalkMessage(String message) {
        messageFrame = new JFrame();
        messageFrame.setTitle("Talk");
        messageFrame.setSize(300, 200);
        messageFrame.setResizable(true);

        messagePanel = new JPanel();
        messagePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));
        JLabel label = new JLabel(message);
        label.setPreferredSize(new Dimension(100, 50));
        messagePanel.add(label);

        messageFrame.add(messagePanel);
        messageFrame.setVisible(true);
    }

    public void removeTalkMessage() {
        messageFrame.setVisible(false);
    }

    //Execute method once input is gathered
    public abstract boolean execute() throws Exception;

    public LiveSpeechRecognizer getRecognizer() {
        return recognizer;
    }

    public String getModelPath() {
        return modelPath;
    }

    public JFrame getMessageFrame() {
        return messageFrame;
    }

    public JPanel getMessagePanel() {
        return messagePanel;
    }

    public void setListening(boolean listening) {
        this.listening = listening;
    }
}
