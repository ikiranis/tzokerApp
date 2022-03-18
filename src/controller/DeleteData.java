package controller;

import java.util.ArrayList;
import java.util.function.Consumer;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import model.Draw;
import org.eclipse.persistence.exceptions.DatabaseException;
import service.DB;

public class DeleteData {
    private final ArrayList<Draw> draws;
    private Consumer<Integer> worker;

    public DeleteData(ArrayList<Draw> draws) {
        this.draws = draws;
    }
    
    public void setWorker(Consumer<Integer> worker) {
        this.worker = worker;
    }
    
    // Διαγραφή λίστας από draws
    public int deleteDraws() {
        int progress = 0;
        int counter = 0;
        Draw drawInDB = null;
        Query query;

        try {
            DB.connect();
            
            DB.em.getTransaction().begin();

            for (Draw draw : draws) {
                query = DB.em.createNamedQuery("Draw.findById", Draw.class);
                query.setParameter("id", draw.getId());

                try {
                    drawInDB = (Draw) query.getSingleResult();
                } catch (Exception e) {
                    System.out.println(e);
                }

                if (drawInDB != null) {
                    try {
                        DB.em.remove(drawInDB);
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
