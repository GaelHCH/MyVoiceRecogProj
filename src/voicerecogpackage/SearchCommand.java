package voicerecogpackage;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class SearchCommand extends Command{

    public SearchCommand(String dicPath, String lmPath) {
        super(dicPath, lmPath);
    }

    @Override
    public boolean execute() {
        while ((result = getRecognizer().getResult()) != null) {
            inputCommand = result.getHypothesis();
            System.out.println("Input command: " + inputCommand);
            openBrowserAndSearch(inputCommand);
            return true;
        }
        return false;
    }

    private static void openBrowserAndSearch(String searchTerm) {
        try {
            String encodedSearchTerm = URLEncoder.encode(searchTerm, StandardCharsets.UTF_8);
            String url = "https://www.google.com/search?q=" + encodedSearchTerm;

            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(url));
            } else {
                System.out.println("Desktop browsing is not supported.");
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        //Other method
//        Runtime runtime = Runtime.getRuntime();     //getting Runtime object
//
//        String[] s = new String[] {"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe", "https://javaconceptoftheday.com/"};
//
//        try
//        {
//            runtime.exec(s);        //opens "https://javaconceptoftheday.com/" in chrome browser
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
    }
}
