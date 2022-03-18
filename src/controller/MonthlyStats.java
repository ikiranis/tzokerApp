// Κλάση που υπολογίζει στατιστικά για συγκεκριμένο μήνα και έτος
//
// Παράδειγμα χρήσης:
//
//    MonthlyStats stats = new MonthlyStats("Ιανουάριος", "2021");
//    stats.calculate();
//    System.out.println(stats.getSumOfDraws());
//    System.out.println(stats.getTzakpots());
//    System.out.println(stats.getDividents());

package controller;

import api.Api;
import java.util.List;
import javax.persistence.Query;
import service.DB;
import service.Utils;

public class MonthlyStats {
    private final String month;
    private final String year;
    private long sumOfColumns;  // Πλήθος στηλών 
    private int tzakpots;    // Πλήθος τζακποτς
    private long dividents;   // Κέρδη που διανεμήθηκαν

    public MonthlyStats(String month, String year) {
        this.month = month;
        this.year = year;
    }

    public long getSumOfColumns() {
        return sumOfColumns;
    }

    public int getTzakpots() {
        return tzakpots;
    }

    public long getDividents() {
        return dividents;
    }
    
    // Υπολογίζει το πλήθος των στηλών που παίχτηκαν στον συγκεκριμένο μήνα
    private long calculateColumns() {
        long columns;
        
        try {
           DB.connect();

           Query query = DB.em.createNativeQuery("select sum(CAST(COLUMNS AS BIGINT)) from DRAW where YEAR(DRAW_DATE)=?1 and MONTH(DRAW_DATE)=?2 and GAME_ID=?3");
           query.setParameter(1, Integer.parseInt(this.year));
           query.setParameter(2, Utils.getMonthNameToMonthNumber(this.month));
           query.setParameter(3, Integer.parseInt(Api.getGameId()));
           columns = Long.valueOf(query.getSingleResult().toString());
        }
         
        catch (Exception e) {
           System.out.println(e);
           return 0;
       }
          
       return columns;
    }
    
    // Υπολογίζει το πλήθος των τζακποτς στον συγκεκριμένο μήνα
    private int calculateTzakpots() {
        List tzokerWinners;
        int tzakpot = 0;
        
        try {
            DB.connect();

            Query query = DB.em.createNativeQuery("select WINNERS from PRIZE, DRAW where PRIZE.DRAW_ID = DRAW.ID and CATEGORY_NUMBER=1 and YEAR(DRAW_DATE)=?1 and MONTH(DRAW_DATE)=?2 and DRAW.GAME_ID=?3");
            query.setParameter(1, Integer.parseInt(year));
            query.setParameter(2, Utils.getMonthNameToMonthNumber(month));
            query.setParameter(3, Integer.parseInt(Api.getGameId()));
            tzokerWinners = query.getResultList();

            for (int i = 0; i < tzokerWinners.size(); i++) {
                if ((int) tzokerWinners.get(i) == 0) {
                    tzakpot++;
                }
            }

            DB.close();
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
        
        return tzakpot;
    }
    
    // Υπολογίζει τα κέρδη που έχουν διανεμηθεί τον συγκεκριμένο μήνα
    private long calculateDividents() {
        long divident;
        
        try {
            DB.connect();

            Query query = DB.em.createNativeQuery("select sum(CAST(DIVIDENT AS BIGINT)) from PRIZE, DRAW where PRIZE.DRAW_ID = DRAW.ID and YEAR(DRAW_DATE)=?1 and MONTH(DRAW_DATE)=?2 and GAME_ID=?3");
            query.setParameter(1, Integer.parseInt(year));
            query.setParameter(2, Utils.getMonthNameToMonthNumber(month));
            query.setParameter(3, Integer.parseInt(Api.getGameId()));
            divident = Long.valueOf(query.getSingleResult().toString());

            DB.close();
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
        return divident;
    }
    
    // Βασική μέθοδος που τρέχει τον υπολογισμό
    public void calculate() {
        sumOfColumns = calculateColumns();
        tzakpots = calculateTzakpots();
        dividents = calculateDividents();
    }
}
