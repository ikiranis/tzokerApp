// Η κλάση επιστρέφει μία λίστα με τους κωδικούς κληρώσεων που βρίσκονται μέσα σε ένα range
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
// RangeDrawIdsApi api = new RangeDrawIdsApi(dateRange);
// System.out.println(api.getData());

package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import service.DateRange;

public class RangeDrawIdsApi extends Api {
    
    public RangeDrawIdsApi(DateRange dateRange) {
        super("/draw-date/" + dateRange.getFromString() + "/" + dateRange.getToString() + "/draw-id?limit=180", "draws");
    }

    // Επιστρέφει στην λίστα draws Ids το αποτέλεσμα της κλήσης του Api, 
    // για ένα range ημερομηνιών
    public ArrayList<String> getData() {
        try {
            ArrayList<String> drawIds = new ArrayList<>();

            // Κάνει την κλήση στο api
            this.getJsonString();

            // Μετατρέπει το json string σε object
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonArray jsonArray = gson.fromJson(this.getJson(), JsonArray.class);

            for (JsonElement jsonDraw : jsonArray) {
                // Προσθέτει στην λίστα ένα Draw object
                drawIds.add(jsonDraw.getAsString());
            }

            return drawIds;
        } catch (Exception e) {
            System.out.println(e);

            return null;
        }
    }
    
}
