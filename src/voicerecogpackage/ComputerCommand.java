package voicerecogpackage;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

import java.io.IOException;

public class ComputerCommand extends Command {

    public ComputerCommand(String dicPath, String lmPath) {
        super(dicPath, lmPath);
    }

    @Override
    public boolean execute() {
        while ((result = getRecognizer().getResult()) != null) {
            inputCommand = result.getHypothesis();
            System.out.println("Input ComputerCommand: " + inputCommand);
            if (inputCommand.toLowerCase().equals("exit")) {
                System.out.println("Exit command");
                return true;
            }
            else if (inputCommand.toLowerCase().equals("sleep")) {
                System.out.println("ComputerCommand - Sleep");
                sleepComputer();
                closeRecognizer();
                return true;
            }
            else if (inputCommand.toLowerCase().equals("shut off") || inputCommand.toLowerCase().equals("power off") || inputCommand.toLowerCase().equals("turn off") || inputCommand.toLowerCase().equals("shut down") || inputCommand.toLowerCase().equals("off")) {
                System.out.println("ComputerCommand - Power off");
                shutdownComputer();
                closeRecognizer();
                return true;
            }
            else if (inputCommand.toLowerCase().equals("restart")) {
                System.out.println("ComputerCommand - Restart");
                restartComputer();
                closeRecognizer();
                return true;
            }
            else if (inputCommand.toLowerCase().equals("lock")) {
                System.out.println("ComputerCommand - Lock");
                lockComputer();
                closeRecognizer();
                return true;
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

    //How to lock windows
    private void lockComputer() {
        try {
            Runtime.getRuntime().exec("Rundll32.exe user32.dll,LockWorkStation");
        }
        catch (IOException e) {
            // Handle potential errors
            System.err.println("Error locking computer: " + e.getMessage());
        }
    }

    //How to restart the computer
    private void restartComputer() {
        try {
            Runtime.getRuntime().exec("shutdown /r /t 0");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    //How to shut down the computer
    private void shutdownComputer() {
        try {
            Runtime.getRuntime().exec("shutdown /s"); // Execute shutdown command
        } catch (IOException e) {
            System.err.println("Error shutting down: " + e.getMessage());
        }
    }
}
