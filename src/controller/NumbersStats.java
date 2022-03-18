// Κλάση που υπολογίζει τα στατιστικά για τους αριθμούς και τα τζόκερ
//
// Παράδειγμα χρήσης:
//
// NumbersStats stats = new NumbersStats(dateRange);
// stats.calculateStats('1);
// stats.calculateStats('2);
// ArrayList<Object[]> numbers = stats.getNumbersStats();
// ArrayList<Object[]> tzokers = stats.getTzokersStats();
//

package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Draw;
import model.WinNumber;
import service.DateRange;
import service.FetchData;
import service.Utils;

public class NumbersStats {
    private final DateRange dateRange;
    private List<WinNumber> numbers = new ArrayList<>();
    private ArrayList<Object[]> numbersStats = new ArrayList<>();
    private ArrayList<Object[]> tzokersStats = new ArrayList<>();
    private final static int NUMBERS = 45;
    private final static int TZOKERS = 20;
    private int lastDrawId;

    public ArrayList<Object[]> getNumbersStats() {
        return numbersStats;
    }

    public ArrayList<Object[]> getTzokersStats() {
        return tzokersStats;
    }

    public NumbersStats(DateRange dateRange) {
        this.dateRange = dateRange;
        
        // Άντληση των δεδομένων
        fetchData();
    }
    
    // Διαβάζει τις κληρώσεις που γίνανε στο συγκεκριμένο διάστημα
    private void fetchData() {
        // Άντληση των κληρώσεων στο συγκεκριμένο εύρος ημερομηνιών
        FetchData fetch = new FetchData();
        ArrayList<Draw> drawsInDB = fetch.getDrawsInRange(dateRange);

        lastDrawId = drawsInDB.get(0).getId();
        
        // Για κάθε κλήρωση παίρνει τους αριθμούς που έχουν κληρωθεί σε αυτή
        for (Draw draw : drawsInDB) {
            numbers.addAll(draw.getWinNumberCollection());
        }
        
        Collections.reverse(numbers);
    }
    
    // Υπολογίζει τα στατιστικά των αριθμών ή τον τζόκερ. Ανάλογα με την τιμή
    // του type. '1' για αριθμούς, '2' για τζόκερς
    public void calculateStats(char type) {
        int maxNumbers = (type == '1') ? NUMBERS : TZOKERS;
        ArrayList<Object[]> stats = (type == '1') ? numbersStats : tzokersStats;

        // Προσωρινή επιστροφή τυχαίων δεδομένων
        for (int i=1;i<=maxNumbers;i++) {
            // Εύρεση της καθυστέρησης εμφάνισης ενός αριθμού. 
            // Αν ο αριθμός δεν έχει εμφανιστεί στο συγκεκριμένο range δίνει -1
            int lastDrawOfNumber = Utils.getLastDrawOfNumber(numbers, Integer.toString(i), type);
            int delay = (lastDrawOfNumber == 0) ? -1 : lastDrawId - lastDrawOfNumber;
            
            // Εύρεση του πλήθους εμφανίσεων του αριθμού
            int numberFrequence = Utils.countOccurrences(numbers, Integer.toString(i), type);
            
            // Προσθήκη των τιμών στο row
            Object[] row = {i, numberFrequence, delay};
            
            stats.add(row);
        }
    }
   
}
