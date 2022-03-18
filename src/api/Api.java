// Υπερκλάση για την χρήση του API του ΟΠΑΠ

package api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import model.Draw;
import model.Game;
import model.Prize;
import model.WinNumber;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import service.DB;

public class Api {
    private static final String drawsUrl = "https://api.opap.gr/draws/v3.0/";
    private static final String gamesUrl = "https://api.opap.gr/games/v1.0/";
    private static String gameId; 
    private String json;
    private final String jsonQuery;
    private final String apiVersion;
    private static Game game;

    public Api(String jsonQuery, String apiVersion) {
        this.jsonQuery = jsonQuery;
        this.apiVersion = apiVersion;
    }

    private Api() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String getJson() {
        return json;
    }

    public static String getGameId() {
        return gameId;
    }
    
    public static void setGameId(String id) {
        gameId = id;
    }
    
    // Δημιουργεί το τζοκερ στον πίνακα Game
    private Game createGame() {
        Game newGame = new Game(Integer.parseInt(gameId), "Τζόκερ");

        try {
            DB.connect();
            
            DB.em.getTransaction().begin();
            DB.em.persist(newGame);
            DB.em.getTransaction().commit();

            DB.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return newGame;
    }
    
    // Static μέθοδος που επιστρέφει το game σαν object
    public static Game getGameObj() {
        if (game == null) {
            // Κάνει query στην βάση για να πάρει το Game με id=gameId
            try {
                DB.connect();

                Query query = DB.em.createNamedQuery("Game.findById", Game.class);
                query.setParameter("id", Integer.parseInt(gameId));
                game = (Game) query.getSingleResult();

                DB.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        
        return game;
    }
    
    // Επιστρέφει αντικείμενο Game, για το id=gameId
    private Game getGame() {
        Game newGame = null;
        
        // Κάνει query στην βάση για να πάρει το Game με id=gameId
        try {
            DB.connect();
        
            Query query = DB.em.createNamedQuery("Game.findById", Game.class);
            query.setParameter("id", Integer.parseInt(gameId));
            newGame = (Game) query.getSingleResult();
            
            DB.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        // Αν δεν υπάρχει ήδη εγγραφή στην βάση, το δημιουργεί
        if (newGame == null) {
            newGame = createGame();
        }
        
        return newGame;
    }
    
    // Παίρνει το json string μετά την κλήση του κατάλληλου api
    public void getJsonString() throws Exception {
        if (game == null) {
            game = getGame();
        }
        
        String jsonUrl = (apiVersion == "draws" ? drawsUrl : gamesUrl)
                + gameId + jsonQuery;
        
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(jsonUrl).build();
        
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                json = response.body().string();
            } else {
                throw new Exception("Υπάρχει πρόβλημα στην άντληση των δεδομένων");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e, "Σφάλμα", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    // Διαβάζει μία jsonArray και περνάει τα νούμερα σε WinNumber model
    private List getWinNumbers(JsonArray numbersList, char type, Draw draw) {
        List winNumbers = new ArrayList<>();
        
        for (Object number : numbersList) {
            WinNumber winNumber = new WinNumber();
            winNumber.setNumber(number.toString());
            winNumber.setType(type);
            winNumber.setDrawId(draw);
            winNumbers.add(winNumber);
        }
        
        return winNumbers;
    }
    
    // Διαβάζει μία jsonArray και περνάει τις καγηγορίες κερδών σε Prize model
    private List getPrizeCategories(JsonArray categoriesList, Draw draw) {
        List prizeCategories = new ArrayList<>();

        for (JsonElement category : categoriesList) {
            Prize prize = new Prize();
            prize.setCategoryNumber(category.getAsJsonObject().get("id").getAsInt());
            // Υπολογισμός κερδών κατηγορίας
            int divident = category.getAsJsonObject().get("divident").getAsInt() 
                    * category.getAsJsonObject().get("winners").getAsInt();
            prize.setWinners(category.getAsJsonObject().get("winners").getAsInt());
            prize.setProfit(category.getAsJsonObject().get("divident").getAsInt());
            prize.setDivident(divident);
            prize.setDrawId(draw);
            prizeCategories.add(prize);
        }

        return prizeCategories;
    }
 
    // Παίρνει τα συγκεκριμένα πεδία του json και τα θέτει στα αντίστοιχα
    // properties του Draw
    public Draw getDrawObject(JsonObject json) {
        Draw draw = new Draw();
       
        draw.setId(json.get("drawId").getAsInt());
        
        // Μετατροπή πρώτα του timestamp σε Date
        Timestamp ts = new Timestamp(json.get("drawTime").getAsLong());
        Date convertedDate = new Date(ts.getTime());
        draw.setDrawDate(convertedDate);
        
        draw.setColumns(json.getAsJsonObject("wagerStatistics").get("columns").getAsInt());
        
        // Θέτει τα winning numbers
        List winNumbers = new ArrayList<>();
        winNumbers.addAll(getWinNumbers(json.getAsJsonObject("winningNumbers").getAsJsonArray("list"), '1', draw));
        winNumbers.addAll(getWinNumbers(json.getAsJsonObject("winningNumbers").getAsJsonArray("bonus"), '2', draw));
        draw.setWinNumberCollection(winNumbers);
        
        // Θέτει τις κατηγορίες κερδών
        List prizeCategories = new ArrayList<>();
        prizeCategories.addAll(getPrizeCategories(json.getAsJsonArray("prizeCategories"), draw));
        draw.setPrizeCollection(prizeCategories);
        
        draw.setGameId(game);

        return draw;
    }
}
