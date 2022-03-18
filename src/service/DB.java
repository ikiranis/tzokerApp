// Κλάση με βασικές μεθόδους για την χρήση της Βάσης δεδομένων

package service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class DB {
    private static final String UNIT_NAME = "JokerGame-StatsPU";
    public static EntityManagerFactory emf;
    public static EntityManager em;
    
    // Κάνει την σύνδεση στην βάση
    public static void connect() {
        // Δημιουργία EntityManager
        try {
            emf = Persistence.createEntityManagerFactory(UNIT_NAME);
            em = emf.createEntityManager();
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
    // Κλείνει την σύνδεση στην βάση
    public static void close() {
        em.close();
        emf.close();
    }
    
    // Έλεγχος ότι μπορεί να γίνει σύνδεση με την βάση δεδομένων.
    // Επιστρέφει true αν η βάση δεδομένων λειτουργεί
    public static boolean checkDB() {
        try {
            emf = Persistence.createEntityManagerFactory(UNIT_NAME);
            em = emf.createEntityManager();
            
            close();
        } catch (Exception e) {
            return false;
        }

        
        return true;
    }
}
