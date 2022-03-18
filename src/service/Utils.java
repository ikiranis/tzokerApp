package service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Prize;
import model.WinNumber;

public class Utils {
    
    // Επιστρέφει Formatter
    public static SimpleDateFormat getFormatter() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }
    
    // Μετράει πόσες φορές έχει εμφανιστεί ένας αριθμός
    public static int countOccurrences(List<WinNumber> numbersInDB, String number, char type) {
        int counter = 0;

        // Έλεγχος για κάθε αριθμό number, με τύπο type
        for (WinNumber item : numbersInDB) {
            if (number.equals(item.getNumber()) && item.getType() == type) {
                counter++;
            }
        }

        return counter;
    }
    
    // Επιστρέφει τις μεγαλύτερες entries τιμές από την λίστα items
    public static Map sortNumbersByKey(Map<Integer, Long> items, int entries) {
        Map<Integer, Long> sortedNumbers = new HashMap<>();
        
        // Εκτελεί την διαδικασία για entries φορές
        for (int i = 1; i <= entries; i++) {
            long maxValue = 0;
            int maxKey = 0;

            // Βρίσκει την μεγαλύτερη από τις τιμές
            for (Map.Entry<Integer, Long> item : items.entrySet()) {
                if (item.getValue() > maxValue) {
                    maxValue = item.getValue();
                    maxKey = item.getKey();
                }
            }

            // Σβήνει την μεγαλύτερη εγγραφή από την παλιά λίστα και την 
            // προσθέτει στην καινούργια
            items.remove(maxKey);
            sortedNumbers.put(maxKey, maxValue);
        }
        
        return sortedNumbers;
    }
    
    // Επιστρέφει την τελευταία κλήρωση που εμφανίστηκε ο αριθμός
    public static int getLastDrawOfNumber(List<WinNumber> numbersInDB, String number, char type) {
        int lastDraw = 0;

        // Έλεγχος για κάθε αριθμό number, με τύπο type
        for (WinNumber item : numbersInDB) {
            if (number.equals(item.getNumber()) && item.getType() == type) {
                lastDraw = item.getDrawId().getId();
            }
        }

        return lastDraw;
    }
    
    // Επιστρέφει τον μέσο όρο κερδών για την κατηγορία κερδών categoryNumber
    public static long getAvgPrize(List<Prize> categoriesInDB, int categoryNumber) {
        long sum = 0;
        int counter = 0;
        
        // Έλεγχος σε κάθε κατηγορία κερδών για το categoryNumber
        // Αν είναι η σωστή κατηγορία προσθέτει τα κέρδη
        for (Prize category : categoriesInDB) {
            if (category.getCategoryNumber() == categoryNumber) {
                sum += category.getDivident();
                counter++;
            }
        }
        
        return (long) sum / counter;
    }
    
    // Επιστρέφει τα ονόματα των κατηγοριών
    public static Map<Integer, String> getCategoryNames() {
        Map<Integer, String> names = new HashMap<>();
        
        names.put(1, "5+1");
        names.put(2, "5");
        names.put(3, "4+1");
        names.put(4, "4");
        names.put(5, "3+1");
        names.put(6, "3");
        names.put(7, "2+1");
        names.put(8, "1+1");
        
        return names;
    }
    
    // Επιστρέφει σε string την σημερινή ημερομηνία
    public static String getTodayDate() {
        return getFormatter().format(new Date());
    }

    // Επιστρέφει σε string την ημερομηνία από 3 μήνες πριν
    public static String getThreeMonthsAgoDate() {
        return getFormatter().format(Date
                .from(LocalDate.parse(getTodayDate())
                        .minusMonths(3).atStartOfDay()
                        .toInstant(ZoneOffset.UTC)));
    }
    
    // Μετατροπή του ονόματος μήνα σε string
    public static int getMonthNameToMonthNumber(String month) {
        HashMap<String, String> map = new HashMap();
        
        map.put("Ιανουάριος", "1");
        map.put("Φεβρουάριος", "2");
        map.put("Μάρτιος", "3");
        map.put("Απρίλιος", "4");
        map.put("Μάιος", "5");
        map.put("Ιούνιος", "6");
        map.put("Ιούλιος", "7");
        map.put("Αύγουστος", "8");
        map.put("Σεπτέμβριος", "9");
        map.put("Οκτώβριος", "10");
        map.put("Νοέμβριος", "11");
        map.put("Δεκέμβριος", "12");
        
        return Integer.parseInt(map.get(month));
    }
    
    // Μετατροπή του αριθμού μήνα σε string
    public static String getMonthNumberToMonthName(String month) {
        HashMap<String, String> map = new HashMap();
        
        map.put("1", "Ιανουάριος");
        map.put("2", "Φεβρουάριος");
        map.put("3", "Μάρτιος");
        map.put("4", "Απρίλιος");
        map.put("5", "Μάιος");
        map.put("6", "Ιούνιος");
        map.put("7", "Ιούλιος");
        map.put("8", "Αύγουστος");
        map.put("9", "Σεπτέμβριος");
        map.put("10", "Οκτώβριος");
        map.put("11", "Νοέμβριος");
        map.put("12", "Δεκέμβριος");

        return map.get(month);
    }
    
}
