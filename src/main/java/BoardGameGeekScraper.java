import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class BoardGameGeekScraper {

    private static final String URL_TO_SCRAPE = "https://boardgamegeek.com/browse/boardgame";

    public static String getHtml(){
        StringBuilder sb = new StringBuilder();

        //Get raw html
        try {
            //Convert string to url object
            URL url = new URL(URL_TO_SCRAPE);

            //Get connection to url
            URLConnection conn = url.openConnection();

            //Get stream of data
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));

            //Read data from the stream
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }

        // Handle exeptions i.e bad url or cant get connection
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        return sb.toString();
    }

    public static List<Game> getGames(int numGames, String html){
        numGames = Math.min(100, numGames);

        //Convert htm to document to allow easy searching by tag etc
        Document doc = Jsoup.parse(html);

        List<Game> games = new ArrayList<Game>();

        for(int i = 0; i < numGames; i++){
            // Retrieve table row for each game
            Element element = doc.select("#row_").get(i);

            // Extract information for the current game
            String rank = element.getElementsByClass("collection_rank").text().trim();
            String imageUrl = element.select(".collection_thumbnail > a").attr("href");
            String name = element.select(String.format("#results_objectname%s > a", Integer.toString(i+1))).text();

            //Build a new game object
            Game game = new Game(Integer.parseInt(rank), imageUrl, name);

            //Add the game to the array
            games.add(game);
        }

        return games;
    }
}
