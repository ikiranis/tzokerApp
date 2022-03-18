// Controller κλάση που περιέχει μεθόδους για τραβάνε δεδομένα από το API
//
// Βασικές μέθοδοι που μπορεί να καλέσει ο χρήστης:
//    getSingleDraw()
//    getRangeDraws()
//    getLastDrawIds(int length)

package controller;

import api.CurrentDrawApi;
import api.DrawApi;
import api.RangeDrawApi;
import java.util.ArrayList;
import java.util.function.Consumer;
import model.Draw;
import service.DateRange;
import service.SplittedDateRange;

public class FetchFromApi {
    private ArrayList<Draw> drawsData = new ArrayList<>();
    private ArrayList<String> drawIds = new ArrayList<>();
    private Consumer<Integer> worker;
    private int lastId;

    public int getLastId() {
        return lastId;
    }

    public void setWorker(Consumer<Integer> worker) {
        this.worker = worker;
    }

    public ArrayList<Draw> getDrawsData() {
        return drawsData;
    }

    public ArrayList<String> getDrawIds() {
        return drawIds;
    }
    
    public void clearDraws() {
        drawsData.clear();
    }
    
    // Κάνει το διάβασμα από το Api για ένα μοναδικό drawId
    public void getSingleDraw(String drawId) {
        clearDraws();
        DrawApi api = new DrawApi(drawId);

        drawsData.add(api.getData());
    }
    
    // Κάνει το διάβασμα από το Api για ένα εύρος ημερομηνιών
    public void getRangeDraws(DateRange dateRange) {
        ArrayList<DateRange> dateRanges;
        int progress = 0;
        clearDraws();

        // Σπάει το range σε τρίμηνα
        SplittedDateRange splittedDateRange = new SplittedDateRange(dateRange);
        dateRanges = splittedDateRange.getSplitted();
        
        // Κάνει κλήσεις στο API για κάθε τρίμηνο και τα αποτελέσματα τα 
        // περνάει στην λίστα drawsData
        for (DateRange range : dateRanges) {
            RangeDrawApi api = new RangeDrawApi(range);

            drawsData.addAll(api.getData());
            
            // Επιστροφή στο worker του ποσοστού προόδου
            worker.accept(100*(progress+1)/dateRanges.size());
            progress++;
        }
    }
    
    // Επιστρέφει το drawId της τελευταίας κλήρωσης
    private int getLastDrawId() {
        CurrentDrawApi api = new CurrentDrawApi();

        Draw lastDraw = api.getData();

        return lastDraw.getId();
    }
    
    // Επιστρέφει μία λίστα με τα τελευταία length drawIds
    public void getLastDrawIds(int length) {
        lastId = getLastDrawId();

        for (int i = lastId; i >= lastId - length; i--) {
            drawIds.add(Integer.toString(i));
        }
    }

}
