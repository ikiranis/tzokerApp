// Η κλάση επιστρέφει μία λίστα με κληρώσεις που βρίσκονται μέσα σε ένα range
//
// Παράδειγμα χρήσης:
// 
// Api.setGameId("5104");
//
// SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
//
// Date dateFrom = formatter.parse("2021-10-01");
// Date dateTo = formatter.parse("2022-01-01");
//
// DateRange dateRange = new DateRange(dateFrom, dateTo);
// RangeDrawApi api = new RangeDrawApi(dateRange);
// System.out.println(api.getData());

package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Draw;
import service.DateRange;

public class RangeDrawApi extends Api {
    
    public RangeDrawApi(DateRange dateRange) {
        super("/draw-date/" + dateRange.getFromString() + "/" + dateRange.getToString() + "?limit=180", "draws");
    }

    // Επιστρέφει στην λίστα draws το αποτέλεσμα της κλήσης του Api, 
    // για ένα range ημερομηνιών
    public ArrayList<Draw> getData() {
        try {
            ArrayList<Draw> draws = new ArrayList<>();
            
            // Κάνει την κλήση στο api
            this.getJsonString();

            // Μετατρέπει το json string σε object
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonObject json = gson.fromJson(this.getJson(), JsonObject.class);

            // Διαβάζει το array του json
            JsonArray jsonArray = json.getAsJsonArray("content");
            
            for (JsonElement jsonDraw : jsonArray) {
                // Προσθέτει στην λίστα ένα Draw object
                draws.add(getDrawObject(jsonDraw.getAsJsonObject()));
            }
            
            return draws;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Σφάλμα", JOptionPane.INFORMATION_MESSAGE);
  
            return null;
        }
    }
}
