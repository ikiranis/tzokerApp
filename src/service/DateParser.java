// Κλάση που κάνει το parse των ημερομηνιών σε αντικείμενο DateRange

package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class DateParser {
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private String from;
    private String to;

    public DateParser(String from, String to) {
        this.from = from;
        this.to = to;
    }
    
    // Επιστρέφει το αντικείμενο DateRange, κάνοντας κάποιους έλεγχους ασφάλειας
    public DateRange get() {
        try {
            // Παίρνει τα δεδομένα από την φόρμα
            Date dateFrom = formatter.parse(from);
            Date dateTo = formatter.parse(to);
            
            // Έλεγχος αν είναι σωστές οι ημερομηνίες
            try {
                return new DateRange(dateFrom, dateTo);

            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Σφάλμα", JOptionPane.INFORMATION_MESSAGE);
                
                return null;
            }
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, e, "Σφάλμα", JOptionPane.INFORMATION_MESSAGE);
            
            return null;
        }
    }
}
