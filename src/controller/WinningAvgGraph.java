// Υποκλάση που δημιουργεί γράφημα με τον μέσο όρο κερδών ανα κατηγορία

package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Draw;
import model.Prize;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import service.FetchData;
import service.Utils;

public class WinningAvgGraph extends Graph {

    public WinningAvgGraph() {
        super("Μέσος όρος κερδών ανα κατηγορία", "Κέρδη", "Καγηγορίες");
    }

    // Αντλεί τα δεδομένα για τον μέσο όρο κερδών ανα κατηγορία
    @Override
    public Map<Integer, Long> getData() {
        ArrayList<Draw> drawsInDB;
        List<Prize> categoriesInDB = new ArrayList<>();
        Map<Integer, Long> numbers = new HashMap<>();

        // Άντληση των κληρώσεων στο συγκεκριμένο εύρος ημερομηνιών
        FetchData fetch = new FetchData();
        drawsInDB = fetch.getDrawsInRange(getDateRange());

        // Για κάθε κλήρωση παίρνει τις κατηγορίες κερδών που αντιστοιχούν σε αυτή
        for (Draw draw : drawsInDB) {
            categoriesInDB.addAll(draw.getPrizeCollection());
        }
        
        // Υπολοζίει τον μέσο όρο κερδών για κάθε κατηγορία
        for (int i = 1; i <= 8; i++) {
            numbers.put(i, Utils.getAvgPrize(categoriesInDB, i));
        }

        return numbers;
    }
    
    // Δημιουργεί το dataset
    @Override
    public CategoryDataset createDataset() {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Διαβάζει τα δεδομένα
        Map<Integer, Long> categories = getData();
        
        // Γεμίζει το dataset
        for (int i = 1; i <= categories.size(); i++) {
            dataset.addValue(categories.get(i), "Κατηγορία", Utils.getCategoryNames().get(i));
        }

        return dataset;
    }
}
