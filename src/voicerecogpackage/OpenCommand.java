package voicerecogpackage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class OpenCommand extends Command {

    public OpenCommand(String dicPath, String lmPath) {
        super(dicPath, lmPath);
    }

    @Override
    public boolean execute() throws IOException, InterruptedException {
        while ((result = getRecognizer().getResult()) != null) {
            inputCommand = result.getHypothesis();
            System.out.println("Input OpenCommand: " + inputCommand);

            if (inputCommand.toLowerCase().equals("exit")) {
                System.out.println("Exit command");
                return true;
            }
            else if (inputCommand.toLowerCase().equals("notepad") || inputCommand.toLowerCase().equals("note pad")) {
                System.out.println("OpenCommand: " + inputCommand);
                openExeApp("notepad");
                return true;
            }
            else if (inputCommand.toLowerCase().equals("coredata") || inputCommand.toLowerCase().equals("core data")) {
                System.out.println("OpenCommand: " + inputCommand);
                openApp("C:\\Program Files\\Core Temp\\Core Temp.exe");
                return true;
            }
            else if (inputCommand.toLowerCase().equals("idea")) {
                System.out.println("OpenCommand: " + inputCommand);
                openApp("C:\\Program Files\\JetBrains\\IntelliJ IDEA 2024.1.3\\bin\\idea64.exe");
                return true;
            }
            else if (inputCommand.toLowerCase().equals("firefox") || inputCommand.toLowerCase().equals("fire fox")) {
                System.out.println("OpenCommand: " + inputCommand);
                openApp("C:\\Users\\Gael\\Desktop\\Firefox - Shortcut.lnk");
                return true;
            }
            else if (inputCommand.toLowerCase().equals("craft")) {
                System.out.println("OpenCommand: " + inputCommand);
                openApp("C:\\Users\\Gael\\Desktop\\Craft.lnk");
                return true;
            }
            else if (inputCommand.toLowerCase().equals("flashcards") || inputCommand.toLowerCase().equals("flash cards")) {
                System.out.println("OpenCommand: " + inputCommand);
                openApp("C:\\Users\\Gael\\Desktop\\Anki.lnk");
                return true;
            }
            else if (inputCommand.toLowerCase().equals("steam")) {
                System.out.println("OpenCommand: " + inputCommand);
                openApp("C:\\Program Files (x86)\\Steam\\steam.exe");
                return true;
            }
            else if (inputCommand.toLowerCase().equals("password")) {
                System.out.println("OpenCommand: " + inputCommand);
                openApp("C:\\Users\\Gael\\Desktop\\Password.jar");
                return true;
            }
            else if (inputCommand.toLowerCase().equals("music")) {
                System.out.println("OpenCommand: " + inputCommand);
                openApp("C:\\Users\\Gael\\Desktop\\Apple Music - Shortcut.lnk");
                return true;
            }
            else if (inputCommand.toLowerCase().equals("pomodoro")) {
                System.out.println("OpenCommand: " + inputCommand);
                openApp("C:\\Users\\Gael\\Desktop\\PowerPom - Pomodoro Timer - Shortcut.lnk");
                return true;
            }
            else if (inputCommand.toLowerCase().equals("files")) {
                System.out.println("OpenCommand: " + inputCommand);
                openExeApp("explorer");
                return true;
            }
            else if (inputCommand.toLowerCase().equals("clock")) {
                System.out.println("OpenCommand: " + inputCommand);
                openApp("C:\\Users\\Gael\\Desktop\\Clock - Shortcut.lnk");
                return true;
            }
            else if (inputCommand.toLowerCase().equals("settings")) {
                System.out.println("OpenCommand: " + inputCommand);
                openApp("C:\\Users\\Gael\\Desktop\\Settings - Shortcut.lnk");
                return true;
            }
            else if (inputCommand.toLowerCase().equals("messages")) {
                System.out.println("OpenCommand: " + inputCommand);
                openApp("C:\\Users\\Gael\\Desktop\\WhatsApp - Shortcut.lnk");
                return true;
            }
            else if (inputCommand.toLowerCase().equals("task manager") || inputCommand.toLowerCase().equals("manager")) {
                System.out.println("OpenCommand: " + inputCommand);
                openApp("C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs\\System Tools\\Task Manager.lnk");
                return true;
            }
            else if (inputCommand.toLowerCase().equals("after burner") || inputCommand.toLowerCase().equals("burner")) {
                System.out.println("OpenCommand: " + inputCommand);
                openApp("C:\\Users\\Gael\\AppData\\Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\MSI Afterburner\\MSI Afterburner.lnk");
                return true;
            }
            else {
                System.out.println("OpenCommand: " + inputCommand);
                throw new FileNotFoundException(inputCommand + " app not found.");
            }
        }
        return false;
    }

    //Helper function to open apps that can be opened with exec
    private void openExeApp(String appName) {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(appName + ".exe");
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    //Helper function to open apps that can't be opened with exec (opens only shortcut files)
    private void openApp(String pathName) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", pathName);
        Process p = pb.start();
//        p.waitFor(); //This is only useful if you don't want the program to end after opening app
    }
}
