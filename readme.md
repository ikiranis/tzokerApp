# Εφαρμογή στατιστικών ΤΖΟΚΕΡ

Η συγκεκριμένη εφαρμογή έγινε στα πλαίσια ομαδικής εργασίας στην ΠΛΗ24, του Ελληνικού Ανοιχτού Πενεπιστημίου. Υλοποιήθηκε με την μεθοδολογία Scrum, σε 3 sprints (2 των 14 ημερών κι ένα των 10 ημερών). Τα μέλη της ομάδας ήταν:

- Κοντολιός Δημήτρης
- Κυράνης Γιάννης
- Ρούσσος Ιωσήφ

### Περιγραφή της εφαρμογής

Η εφαρμογή JokerGame-Stats αντλεί δεδομένα από τα web services της ΟΠΑΠ Α.Ε. για το τυχερό παιχνίδι ΤΖΟΚΕΡ και εμφανίζει διάφορα στατιστικά για τις κληρώσεις του. Δημιουργήθηκε στα πλαίσια της 3ης Ομαδικής Εργασίας στην ΠΛΗ24.

Η ΟΠΑΠ Α.Ε. παρέχει διάφορα δεδομένα μ’ ένα API (Application Programming Interface) τύπου REST (Representational State Transfer). Τα δεδομένα αυτά επιστρέφονται σε μορφή JSON (JavaScript Object Notation). Η εφαρμογή αναλαμβάνει να τα αντλήσει και αποθηκεύσει σε βάση δεδομένων, για περαιτέρω επεξεργασία  τους και εμφάνιση στον χρήστη.

Η περιγραφή των δεδομένων που μπορούν να αντληθούν από την ΟΠΑΠ Α.Ε. περιγράφονται στην σελίδα https://www.opap.gr/web-services. Τα κυριότερα από αυτά είναι:

·	Κληρώσεις που γίνανε σε συγκεκριμένο εύρος ημερομηνιών
·	Στοιχεία της τελευταίας και της τρέχουσας κλήρωσης
·	Στοιχεία για κληρώσεις με συγκεκριμένο κωδικό

Τεκμηρίωση για την δομή του JSON που επιστρέφεται, δίνεται με την μορφή Swagger αρχείου στην διεύθυνση https://api.opap.gr/numerics/v1.0/api-swagger.

### Screenshots

[image]:https://github.com/ikiranis/tzokerApp/blob/main/gitImages/1.png?raw=true

[image]:https://github.com/ikiranis/tzokerApp/blob/main/gitImages/2.png?raw=true

[image]:https://github.com/ikiranis/tzokerApp/blob/main/gitImages/3.png?raw=true

[image]:https://github.com/ikiranis/tzokerApp/blob/main/gitImages/4.png?raw=true

[image]:https://github.com/ikiranis/tzokerApp/blob/main/gitImages/5.png?raw=true

[image]:https://github.com/ikiranis/tzokerApp/blob/main/gitImages/6.png?raw=true

[image]:https://github.com/ikiranis/tzokerApp/blob/main/gitImages/7.png?raw=true
