// Κλάση που επιστρέφει την τρέχουσα κλήρωση

package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import javax.swing.JOptionPane;
import model.Draw;

public class CurrentDrawApi extends Api {

    public CurrentDrawApi() {
        super("/last-result-and-active", "draws");
    }

    public Draw getData() {
        try {
            // Κάνει την κλήση στο api
            this.getJsonString();

            // Μετατρέπει το json string σε object
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonObject json = gson.fromJson(this.getJson(), JsonObject.class);

            // Παίρνει το json σε Draw object
            return getDrawObject(json.getAsJsonObject("last"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Σφάλμα", JOptionPane.INFORMATION_MESSAGE);

            return null;
        }
    }
}
