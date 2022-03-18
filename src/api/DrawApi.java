// Κλάση που επιστρέφει μια συγκεκριμένη κλήρωση
//
// Παράδειγμα χρήσης:
//
// Api.setGameId("5104");     
// DrawApi api = new DrawApi("2402");
// System.out.println(api.getData());

package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import javax.swing.JOptionPane;
import model.Draw;

public class DrawApi extends Api {

    public DrawApi(String drawId) {
        super("/" + drawId, "draws");
    }
    
    // Επιστρέφει στο αντικείμενο draw το αποτέλεσμα της κλήσης του Api, 
    // για την κλήρωση drawId
    public Draw getData() {
        try {
            // Κάνει την κλήση στο api
            this.getJsonString();
            
            // Μετατρέπει το json string σε object
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonObject json = gson.fromJson(this.getJson(), JsonObject.class);

            // Παίρνει το json σε Draw object
            return getDrawObject(json);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Σφάλμα", JOptionPane.INFORMATION_MESSAGE);
            
            return null;
        }
    }
}
