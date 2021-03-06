package view;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import controller.MonthlyStats;
import java.awt.Cursor;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;
import service.FetchData;
import service.WrapLayout;

public class DataDisplay extends javax.swing.JPanel {
    private SwingWorker<Integer, Integer> worker;
    private final FetchData fetch = new FetchData();
    
    /**
     * Creates new form DataDisplay
     */
    public DataDisplay() {
        initComponents();
        
        jPanel8.setVisible(false);
        message.setVisible(false);
        
        produceYearsComboBox();
    }
    
    // Αντλεί τα έτη που είναι διαθέσιμα στην βάση δεδομένων
    // και τα προσθέτει στο combobox
    private void produceYearsComboBox() {
        // Δημιουργεί Swing Worker για να τρέξει την άντληση των δεδομένων σε thread
        worker = new SwingWorker<Integer, Integer>() {
            @Override
            protected Integer doInBackground() throws Exception {
                yearComboBox.addItem("");
                
                List years = fetch.getValidYears();
                
                // Γεμίζει το combobox με τα διαθέσιμα έτη
                for (Object year : years) { 
                    yearComboBox.addItem(year.toString());
                }
                
                if(years.isEmpty()) {
                    return -1;
                }

                return 0;
            }

            @Override
            protected void done() {
                try {
                    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    yearComboBox.setEnabled(true);
                    
                    if(get() == -1) {
                        message.setText("Δεν υπάρχουν δεδομένα στη βάση δεδομένων");
                        message.setVisible(true);
                        jPanel9.setVisible(false);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    System.out.println(e);
                } 
            }
        };

        // Διάφορες αρχικοποιήσεις
        yearComboBox.setEnabled(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        
        worker.execute();
    }
    
    // Εμφανίζει τους διαθέσιμους μήνες που ανήκουν στο έτος
    // Αντλεί τα δεδομένα από την βάση δεδομένων και προσθέτει buttons
    private void ShowMonths(){
        String year = yearComboBox.getSelectedItem().toString();
        jPanel4.setLayout(new WrapLayout());
        
        // Δημιουργεί Swing Worker για να τρέξει την άντληση των δεδομένων σε thread
        worker = new SwingWorker<Integer, Integer>() {
            @Override
            protected Integer doInBackground() throws Exception {
                // Εμφάνιση buttons
                for (Object month : fetch.getValidMonths(year)) {
                    JButton monthButton = new JButton(month.toString());

                    // Προσθήκη listener για click στο button
                    monthButton.addActionListener((ActionEvent e) -> {
                        // Εμφάνιση της οθόνης στατιστικών
                        jPanel8.setVisible(true);

                        // Αντλεί τα στατιστικά
                        MonthlyStats stats = new MonthlyStats(month.toString(), year);
                        stats.calculate();

                        // Εμφανίζει τα στατιστικά
                        monthField.setText(month + ", " + year);
                        sumOfGames.setText(Long.toString(stats.getSumOfColumns()));
                        dividentsLabel.setText(Long.toString(stats.getDividents()));
                        jackpotLabel.setText(Integer.toString(stats.getTzakpots()));
                    });

                    // Προσθήκη του button στο panel
                    jPanel4.add(monthButton);
                }

                return 0;
            }

            @Override
            protected void done() {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                
                // Κάνει render για να εμφανιστούν οι αλλαγές
                jPanel4.revalidate();
            }
        };

        // Άδειασμα προηγούμενων buttons
        jPanel4.removeAll();
        jPanel4.revalidate();
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        worker.execute();
    }
    
   /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        message = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        monthField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        sumOfGames = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        dividentsLabel = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jackpotLabel = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        yearComboBox = new javax.swing.JComboBox<>();

        setFocusCycleRoot(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setOpaque(false);

        message.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(message, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jPanel8.setOpaque(false);

        jPanel3.setOpaque(false);
        jPanel8.add(jPanel3);

        jPanel10.setOpaque(false);

        jLabel3.setFont(new java.awt.Font("DejaVu Serif", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Συγκεντρωτικά Δεδομένα του μήνα");

        monthField.setEditable(false);
        monthField.setBackground(new java.awt.Color(255, 255, 255));
        monthField.setFont(new java.awt.Font("DejaVu Serif", 1, 18)); // NOI18N
        monthField.setForeground(new java.awt.Color(0, 0, 0));
        monthField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        monthField.setBorder(null);

        jLabel1.setFont(new java.awt.Font("DejaVu Serif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Πλήθος στηλών:");

        sumOfGames.setEditable(false);
        sumOfGames.setBackground(new java.awt.Color(255, 255, 255));
        sumOfGames.setFont(new java.awt.Font("DejaVu Serif", 0, 14)); // NOI18N
        sumOfGames.setForeground(new java.awt.Color(0, 0, 0));
        sumOfGames.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        sumOfGames.setBorder(null);

        jLabel11.setFont(new java.awt.Font("DejaVu Serif", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Κέρδη που διανεμήθηκαν:");

        dividentsLabel.setEditable(false);
        dividentsLabel.setBackground(new java.awt.Color(255, 255, 255));
        dividentsLabel.setFont(new java.awt.Font("DejaVu Serif", 0, 14)); // NOI18N
        dividentsLabel.setForeground(new java.awt.Color(0, 0, 0));
        dividentsLabel.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        dividentsLabel.setBorder(null);

        jLabel12.setFont(new java.awt.Font("DejaVu Serif", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Τζακποτ:");

        jackpotLabel.setEditable(false);
        jackpotLabel.setBackground(new java.awt.Color(255, 255, 255));
        jackpotLabel.setFont(new java.awt.Font("DejaVu Serif", 0, 14)); // NOI18N
        jackpotLabel.setForeground(new java.awt.Color(0, 0, 0));
        jackpotLabel.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jackpotLabel.setBorder(null);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dividentsLabel)
                            .addComponent(sumOfGames)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jackpotLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 78, Short.MAX_VALUE))
            .addComponent(monthField)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(monthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(sumOfGames, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(dividentsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jackpotLabel))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel8.add(jPanel10);

        jPanel9.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("DejaVu Serif", 1, 14)); // NOI18N
        jLabel2.setText("Επιλογή Έτους");
        jPanel9.add(jLabel2);

        yearComboBox.setFont(new java.awt.Font("DejaVu Serif", 1, 14)); // NOI18N
        yearComboBox.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        yearComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                yearComboBoxItemStateChanged(evt);
            }
        });
        jPanel9.add(yearComboBox);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(146, 146, 146)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(281, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void yearComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_yearComboBoxItemStateChanged
        // Μόλις γίνει επιλογή έτους, εμφανίζει τους αντίστοιχους μήνες σε buttons
        if (evt.getStateChange() == evt.SELECTED) {
            if(evt.getItem() != "") {
                ShowMonths();
            }
        }
    }//GEN-LAST:event_yearComboBoxItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField dividentsLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTextField jackpotLabel;
    private javax.swing.JLabel message;
    private javax.swing.JTextField monthField;
    private javax.swing.JTextField sumOfGames;
    private javax.swing.JComboBox<String> yearComboBox;
    // End of variables declaration//GEN-END:variables
    }
