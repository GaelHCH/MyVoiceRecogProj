package voicerecogpackage;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        VoiceRecognizer voiceRecognizer = new VoiceRecognizer("src/corpusRes/startCommandDic.dic","src/corpusRes/startCommandLm.lm");
        try {
            voiceRecognizer.start();
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }
}
