package voicerecogpackage;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;

import java.io.IOException;

public class ComputerCommand extends Command {
    private SpeechResult input;
    private String inputStr;

    public ComputerCommand(String dicPath, String lmPath, Configuration configuration) {
        super(dicPath, lmPath, configuration);
    }

    @Override
    public boolean gatherInput() {
        return false;
    }

    @Override
    public boolean execute() {
        while ((input = getRecognizer().getResult()) != null) {
            inputStr = input.getHypothesis();
            System.out.println("Input ComputerCommand: " + inputStr);

            if (inputStr.toLowerCase().equals("sleep")) {
                this.closeRecognizer();
                System.out.println("ComputerCommand - Sleep");
//                sleepComputer();
            }
            else if (inputStr.toLowerCase().equals("shut off") || inputStr.toLowerCase().equals("power off") || inputStr.toLowerCase().equals("turn off") || inputStr.toLowerCase().equals("shut down") || inputStr.toLowerCase().equals("off")) {

            }
            else if (inputStr.toLowerCase().equals("restart")) {

            }
            else if (inputStr.toLowerCase().equals("lock")) {

            }

        }
        return false;
    }

    //Helper function to sleep the computer
    private void sleepComputer() {
        try {
            // This command puts the computer to sleep
            String command = "rundll32.exe powrprof.dll,SetSuspendState 0,1,0";
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
