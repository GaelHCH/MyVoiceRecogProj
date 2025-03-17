package voicerecogpackage;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.sound.sampled.AudioSystem;
import java.io.IOException;

public class VoiceRecognizer extends JFrame implements KeyListener {
//    private Command startCommand;
    private String startDicPath;
    private String startLmPath;
    private Timer keyTimer;
    private boolean cntrPressed = false;

    public VoiceRecognizer(String startDicPath, String startLmPath) {
        super(""); //Doing the key istener start stuff

        this.startDicPath = startDicPath;
        this.startLmPath = startLmPath;

        //Doing the key istener start stuff
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);
    }

    //Init the start command
    private void start() throws Exception {
        StartCommand startCommand = new StartCommand(startDicPath, startLmPath);
        startCommand.initConfig();
        startCommand.initRecognizer();
        startCommand.execute();
        startCommand.closeRecognizer();
//        AudioSystem.getMixer(null).close();
//        AudioSystem.getLine(null).close();
//        System.gc();
        System.exit(0);
        System.out.println("Closing voice recognizer");
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
//       if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
//            cntrPressed = true;
//            System.out.println(cntrPressed);
//       }
//
//        //To start the program
//       if (cntrPressed && e.getKeyCode() == KeyEvent.VK_CAPS_LOCK) {
//           System.out.println("control capslock combo");
//           try {
//               start();
//           } catch (Exception ex) {
//               throw new RuntimeException(ex);
//           }
//       }
//
//       //To end the program
//        else if (cntrPressed && e.getKeyCode() == KeyEvent.VK_SPACE) {
//           System.out.println("control space combo");
//           System.exit(0);
//       }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            System.out.println("Entered voice recognizer");
            BeginRecognizer beginRecognizer = new BeginRecognizer(this);
            beginRecognizer.start();
        }
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.out.println("Exiting voice recognizer");
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private class BeginRecognizer extends Thread {
        VoiceRecognizer voiceRecognizer;

        public BeginRecognizer(VoiceRecognizer voiceRecognizer) {
            this.voiceRecognizer = voiceRecognizer;
        }

        @Override
        public void run() {
            try {
                voiceRecognizer.start();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
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
