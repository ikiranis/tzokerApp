// Κλάση που υπολογίζει την πληρότητα των δεδομένων στην βάση
//
// Παράδειγμα χρήσης:
//
//  DBCompleteness complete = new DBCompleteness(dateRange);
//  complete.calculate();
//  String result = complete.getResult();
//

package controller;

import api.RangeDrawIdsApi;
import java.util.ArrayList;
import java.util.function.Consumer;
import model.Draw;
import service.DateRange;
import service.FetchData;
import service.SplittedDateRange;

public class DBCompleteness {
    private final DateRange dateRange;
    private int numberOfDrawsInDB;
    private int numberOfDrawsInApi;
    private Consumer<Integer> worker;
    private String result;

    public String getResult() {
        return result;
    }

    public void setWorker(Consumer<Integer> worker) {
        this.worker = worker;
    }

    public int getNumberOfDrawsInDB() {
        return numberOfDrawsInDB;
    }

    public int getNumberOfDrawsInApi() {
        return numberOfDrawsInApi;
    }

    public DBCompleteness(DateRange dateRange) {
        this.dateRange = dateRange;
    }
    
    // Επιστρέφει τις κληρώσεις που γίνανε στο συγκεκριμένο διάστημα
    private int getSumOfDrawsInRange() {
        ArrayList<String> drawIds = new ArrayList<>();
        int progress = 0;
        
        // Σπάει το range σε τρίμηνα
        SplittedDateRange splittedDateRange = new SplittedDateRange(dateRange);
        ArrayList<DateRange> dateRanges = splittedDateRange.getSplitted();

        // Κάνει κλήσεις στο API για κάθε τρίμηνο και τα αποτελέσματα τα 
        // περνάει στην λίστα drawIds
        for (DateRange range : dateRanges) {
            RangeDrawIdsApi api = new RangeDrawIdsApi(range);

            drawIds.addAll(api.getData());
            
            // Επιστροφή στο worker του ποσοστού προόδου
            worker.accept(100 * (progress + 1) / dateRanges.size());
            progress++;
        }

        return drawIds.size();
    }

    // Επιστρέφει το string για εμφάνιση της πληρότητας στην βάση δεδομένων
    public void calculate() {
        FetchData fetch = new FetchData();
        
        ArrayList<Draw> draws = fetch.getDrawsInRange(dateRange);
        numberOfDrawsInDB = draws.size();
        
        if (draws.isEmpty()) {
            result = null;
        }
        
        numberOfDrawsInApi = getSumOfDrawsInRange();
        
        double percentage = ((double) numberOfDrawsInDB / (double) numberOfDrawsInApi) * 100;

        result = "Υπάρχουν "
                + numberOfDrawsInDB
                + " εγγραφές στην βάση, από τις "
                + numberOfDrawsInApi
                + " που έχουν γίνει. Η πληρότητα στην βάση είναι: "
                + String.format("%.2f", percentage) + "%";
    }
}
