// Η κλάση υλοποιεί συγκεκριμένο τύπο δεδομένων για ένα range ημερομηνιών
// που αρχίζει από την ημερομηνία from μέχρι την το, κάνοντας 
// και τους απαραίτητους ελέγχους για την ορθότητα των δεδομένων
// Επιστρέφει τις ημερομηνίες με getters, είτε σε Date, είτε σε string

package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateRange {
    private final Date from;
    private final Date to;
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public DateRange(Date from, Date to) throws ParseException {
        this.from = from;
        this.to = to;
        
        Date start = formatter.parse("2000-01-01");
        Date end = Calendar.getInstance().getTime();
        
        if (from.after(to))  {
           throw new IllegalArgumentException("H αρχική ημερομηνία είναι μεγαλύτερη από την τελική");  
        }
        
        if ( from.before(start) || to.after(end) ) {
            throw new IllegalArgumentException("Μη αποδεκτό διάστημα ημερομηνιών");
        } 
    }

    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
    }

    public String getFromString() {
        return formatter.format(from);
    }

    public String getToString() {
        return formatter.format(to);
    }
    
    @Override
    public String toString() {
        return "from: " + getFromString() + " to: " + getToString();
    }
    
}
