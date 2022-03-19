# Εφαρμογή στατιστικών ΤΖΟΚΕΡ

Η συγκεκριμένη εφαρμογή έγινε στα πλαίσια ομαδικής εργασίας στην ΠΛΗ24, του Ελληνικού Ανοιχτού Πανεπιστημίου. Υλοποιήθηκε με την μεθοδολογία Scrum, σε 3 sprints (2 των 14 ημερών κι ένα των 10 ημερών). Τα μέλη της ομάδας ήταν:

- Κοντολιός Δημήτρης
- Κυράνης Γιάννης
- Ρούσσος Ιωσήφ

### Περιγραφή της εφαρμογής

Η εφαρμογή JokerGame-Stats αντλεί δεδομένα από τα web services της ΟΠΑΠ Α.Ε. για το τυχερό παιχνίδι ΤΖΟΚΕΡ και εμφανίζει διάφορα στατιστικά για τις κληρώσεις του. Δημιουργήθηκε στα πλαίσια της 3ης Ομαδικής Εργασίας στην ΠΛΗ24.

Η ΟΠΑΠ Α.Ε. παρέχει διάφορα δεδομένα μ’ ένα API (Application Programming Interface) τύπου REST (Representational State Transfer). Τα δεδομένα αυτά επιστρέφονται σε μορφή JSON (JavaScript Object Notation). Η εφαρμογή αναλαμβάνει να τα αντλήσει και αποθηκεύσει σε βάση δεδομένων, για περαιτέρω επεξεργασία  τους και εμφάνιση στον χρήστη.

Η περιγραφή των δεδομένων που μπορούν να αντληθούν από την ΟΠΑΠ Α.Ε. περιγράφονται στην σελίδα https://www.opap.gr/web-services. Τα κυριότερα από αυτά είναι:

- Κληρώσεις που γίνανε σε συγκεκριμένο εύρος ημερομηνιών
- Στοιχεία της τελευταίας και της τρέχουσας κλήρωσης
- Στοιχεία για κληρώσεις με συγκεκριμένο κωδικό

Τεκμηρίωση για την δομή του JSON που επιστρέφεται, δίνεται με την μορφή Swagger αρχείου στην διεύθυνση https://api.opap.gr/numerics/v1.0/api-swagger.

### Τεχνολογίες που χρησιμοποιήθηκαν για την εκτέλεση του έργου

Η εφαρμογή υλοποιήθηκε σε Java και συγκεκριμένα χρησιμοποιήθηκε το JDK 1.8. Ενώ για βάση δεδομένων
χρησιμοποιήθηκε η Apache Derby (έκδοση 10.11.1.2). Το GUI δημιουργήθηκε με Swing.

Για την συνεργασία των μελών της ομάδας, στην συγγραφή του κώδικα, χρησιμοποιήθηκε το github και για IDE
χρησιμοποιήθηκε το Netbeans IDE 8.2.

Για τις διαδικασίες του Scrum χρησιμοποιήθηκαν διάφορα εργαλεία, όπως:

- Planningpoker.com (για τον υπολογισμό του effort και της προτεραιοποίησης)
- Trello (για διαχείριση του backlog)
- Toggl (για καταγραφή του χρόνου που ξοδεύει το κάθε μέλος στα User Stories)
- Discord (για επικοινωνία μεταξύ των μελών)

Χρησιμοποιήθηκε επίσης το Figma για σχεδίαση των mockups του GUI και το Visual Paradigm για την σχεδίαση
διαγραμμάτων, όπως και το ProjectLibre για σχεδίαση διαγράμματος Gantt

### Εκτέλεση της εφαρμογής

Μπορείτε να κατεβάσετε το .jar αρχείο και να το τρέξετε απευθείας, χωρίς
να χρειάζεται να γίνει compile κτλ. Μετά την πρώτη εκτέλεση θα δημιουργηθεί 
κι ένας φάκελος **db**, ο οποίος περιέχει την απαραίτηση βάση δεδομένων.

Για να εμφανίσει οποιαδήποτε στατιστικά, πρέπει ο χρήστης πρώτα να πάει στην 
"Διαχείριση δεδομένων", να αναζητήσει κάποια δεδομένα (με βάση κάποιο εύρος
ημερομηνιών) και να τα καταχωρήσει στην βάση.

[tzoker.jar](https://github.com/ikiranis/tzokerApp/blob/main/dist/tzoker.jar)

### Screenshots

![18-03--2022_19-47](https://user-images.githubusercontent.com/50238022/159056259-6d333d8a-4710-4160-bad7-c22fd2ec84aa.png)

![18-03--2022_19-48](https://user-images.githubusercontent.com/50238022/159056384-dd90e412-4ee1-4723-ba0c-ea5b90a02d19.png)

![18-03--2022_19-49](https://user-images.githubusercontent.com/50238022/159056487-85acfae0-4453-4730-858e-f1bc820563e8.png)

![18-03--2022_19-49_1](https://user-images.githubusercontent.com/50238022/159056571-29534f97-4c90-4147-9f69-f259866ebd9f.png)

![18-03--2022_19-50](https://user-images.githubusercontent.com/50238022/159056775-17202254-b117-46a9-a216-a3b73898b948.png)

![18-03--2022_19-50_1](https://user-images.githubusercontent.com/50238022/159056791-035ff1ac-453c-4467-9e39-5286019760a2.png)

![18-03--2022_19-51](https://user-images.githubusercontent.com/50238022/159056812-30dd0524-067e-4a42-99a1-036e26e0f70e.png)
