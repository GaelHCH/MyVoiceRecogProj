package voicerecogpackage;

import edu.cmu.sphinx.api.Configuration;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class SearchCommand extends Command{
    private String input;

    public SearchCommand(String dicPath, String lmPath, Configuration configuration, String input) {
        super(dicPath, lmPath, configuration);
        this.input = input;
    }

    @Override
    public boolean gatherInput() {
        return false;
    }

    @Override
    public boolean execute() {
        openBrowserAndSearch(input);
        return true;
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
    }
}
