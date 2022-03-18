// Κλάση με μεθόδους που διαβάζουν από την βάση δεδομένων

package service;

import api.Api;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import model.Draw;
import model.Game;

public class FetchData {
    
    // Διαβάσει τις κληρώσεις που γίνανε στο συγκεκριμένο εύρος ημερομηνιών
    public ArrayList<Draw> getDrawsInRange(DateRange dateRange) {
        ArrayList<Draw> drawsInDB = null;

        Game game = Api.getGameObj();
        
        try {
            DB.connect();
            Query query = DB.em.createNamedQuery("Draw.findByDrawDateRange", Draw.class);
            query.setParameter("drawFromDate", dateRange.getFrom());
            query.setParameter("drawToDate", dateRange.getTo());
            query.setParameter("gameId", game);
            drawsInDB = new ArrayList<>(query.getResultList());

            DB.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return drawsInDB;
    }
        
    // Επιστρέφει λίστα με τα έτη που βρίσκονται στην βάση σαν εγγραφές
    public List getValidYears() {
        List years;

        try {
            DB.connect();

            Query query = DB.em.createNativeQuery("select YEAR(DRAW_DATE) from DRAW WHERE game_id=?1 group by YEAR(DRAW_DATE) order by YEAR(DRAW_DATE)");
            query.setParameter(1, Integer.parseInt(Api.getGameId()));
            years = query.getResultList();

            DB.close();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

        return years;
    }

    // Επιστρέφει λίστα με τους μήνες που βρίσκονται στο έτος year, σαν εγγραφές 
    // στην βάση
    public List getValidMonths(String year) {
        List months;
        List monthsNames = new ArrayList();

        try {
            DB.connect();

            Query query = DB.em.createNativeQuery("select MONTH(DRAW_DATE) from DRAW where YEAR(DRAW_DATE)=?1 and game_id=?2 group by MONTH(DRAW_DATE) order by MONTH(DRAW_DATE)");
            query.setParameter(1, Integer.parseInt(year));
            query.setParameter(2, Integer.parseInt(Api.getGameId()));
            months = query.getResultList();

            for (int i = 0; i < months.size(); i++) {
                monthsNames.add(Utils.getMonthNumberToMonthName(months.get(i).toString()));
            }

            DB.close();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

        return monthsNames;
    }
   
}
