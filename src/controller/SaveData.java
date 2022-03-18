// Κλάση που σώζει τα δεδομένα στην βάση

package controller;

import java.util.ArrayList;
import java.util.function.Consumer;
import javax.persistence.PersistenceException;
import model.Draw;
import org.eclipse.persistence.exceptions.DatabaseException;
import service.DB;
import service.DateRange;
import service.FetchData;

public class SaveData {
    private final DateRange dateRange;
    private final ArrayList<Draw> draws;
    private Consumer<Integer> worker;

    public SaveData(ArrayList<Draw> draws, DateRange dateRange) {
        this.draws = draws;
        this.dateRange = dateRange;
    }
    
    public void setWorker(Consumer<Integer> worker) {
        this.worker = worker;
    }
    
    // Αποθήκευση λίστας από draws
    public int saveDraws() {
        // Διάβάζει τις υπάρχουσες εγγραφές για να κάνει την σύγκριση
        // με αυτά που θα πάει να γράψει
        FetchData fetch = new FetchData();
        ArrayList<Draw> drawsInDB = fetch.getDrawsInRange(dateRange);
        
        int progress = 0;
        int counter = 0;
        
        try {
            DB.connect();
            
            DB.em.getTransaction().begin();

            for (Draw draw : draws) {

                if (!drawsInDB.contains(draw)) {
                    try {
                        DB.em.persist(draw);
                        DB.em.flush();
                        counter++;
                    } catch (DatabaseException | PersistenceException e) {
                        System.out.println(e);

                        DB.em.getTransaction().rollback();
                    }
                }

                // Επιστροφή στο worker του ποσοστού προόδου
                worker.accept(100 * (progress + 1) / draws.size());
                progress++;
            }

            DB.em.getTransaction().commit();
            
            DB.close();
        } catch (Exception e) {
            System.out.println(e);
        } 
        
        return counter;
    }
}
