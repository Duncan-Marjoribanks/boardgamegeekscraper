import java.util.List;

public class Runner {
    public static void main(String[] args) {
        // Get the html
        String html = BoardGameGeekScraper.getHtml();

        // Parse the html for the games
        List<Game> games = BoardGameGeekScraper.getGames(100, html);

        // Display the games
        for(Game game : games){
            System.out.println(game + "\n");
        }
    }
}
