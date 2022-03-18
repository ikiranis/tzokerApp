// Υποκλάση που δημιουργεί γράφημα για τις συχνότητες εμφάνισης των αριθμών 

package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Draw;
import model.WinNumber;
import service.FetchData;
import service.Utils;

public class NumbersFreqGraph extends Graph {
     
    public NumbersFreqGraph() {
        super("Συχνότητα αριθμών (5 πρώτοι)", "Εμφανίσεις", "Αριθμοί");
    }

    // Αντλεί τα δεδομένα για τις συχνότητες εμφάνισης των αριθμών
    @Override
    public Map<Integer, Long> getData() {
        ArrayList<Draw> drawsInDB;
        List<WinNumber> numbersInDB = new ArrayList<>();
        Map<Integer, Long> numbers = new HashMap<>();

        // Άντληση των κληρώσεων στο συγκεκριμένο εύρος ημερομηνιών
        FetchData fetch = new FetchData();
        drawsInDB = fetch.getDrawsInRange(getDateRange());

        // Για κάθε κλήρωση παίρνει τους αριθμούς που έχουν κληρωθεί σε αυτή
        for(Draw draw : drawsInDB) {
            numbersInDB.addAll(draw.getWinNumberCollection());
        }

        // Υπολογίζει τις εμφανίσεις του αριθμού
        for (int i = 1; i <= 45; i++) {
            numbers.put(i, (long) Utils.countOccurrences(numbersInDB, Integer.toString(i), '1'));
        }
        
        return Utils.sortNumbersByKey(numbers, 5);
    }
}
