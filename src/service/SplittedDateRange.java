// Η κλάση δέχεται ένα range ημερομηνιών και το σπάει σε τρίμηνα

package service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SplittedDateRange {
    private final DateRange dateRange;
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public SplittedDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
    }
    
    public ArrayList<DateRange> getSplitted() {
        ArrayList<DateRange> splitted = new ArrayList<>();
        
        // Υπολογίζει τους μήνες στο συγκεκριμένο range
        Period period = Period.between(
                LocalDate.parse(dateRange.getFromString()),
                LocalDate.parse(dateRange.getToString()));
        long monthsInRange = period.toTotalMonths();
        // Διόρθωση σε περίπτωση που το range είναι μέσα στον ίδιο μήνα
        monthsInRange = monthsInRange == 0 ? 1 : monthsInRange;
       
        // Υπολογίζει σε πόσα τρίμηνα θα σπάσει το range
        int splittedPeriods = (int) monthsInRange / 3;
        // Αν υπάρχει κάποιο υπολοιπο, αυξάνει τα τρίμηνα κατά 1
        splittedPeriods += (monthsInRange % 3) > 0 ? 1 : 0;
        
        Date currentFromDate = dateRange.getFrom();
        
        // Υπολογίζει τις αρχικές και τελικές ημερομηνίες κάθε τριμήνου
        for (int i=0;i<splittedPeriods;i++) {
            // Η τελική ημερομηνία του τριμήνου
            Date nextToDate = Date
                    .from(LocalDate.parse(formatter.format(currentFromDate))
                    .plusMonths(3).atStartOfDay()
                    .toInstant(ZoneOffset.UTC));
            
            // Αν η τελευταία τελική ημερομηνία είναι μεγαλύτερη από την αρχική 
            // δοθέντα, τότε πέρνει την αρχική. Για να μην βγει από το όριο
            // που θέλει ο χρήστης
            if (nextToDate.after(dateRange.getTo())) {
                nextToDate = dateRange.getTo();
            }
            
            // Δημιουργεί το συγκεκριμένο DateRange και το προσθέτει στην λίστα
            try {
                DateRange currentRange = new DateRange(currentFromDate, nextToDate);
                
                splitted.add(currentRange);
            } catch (Exception ex) {
                Logger.getLogger(SplittedDateRange.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Υπολογίζει την αρχική ημερομηνία για το επόμενο τρίμηνο
            currentFromDate = Date
                    .from(LocalDate.parse(formatter.format(nextToDate))
                    .plusDays(1)
                    .atStartOfDay()
                    .toInstant(ZoneOffset.UTC));
        }
        
        // Αντιστροφή του array για να βρίσκονται τα τρίμηνα σε φθίνουσα σειρά
        Collections.reverse(splitted);
        
        return splitted;
    }
}
