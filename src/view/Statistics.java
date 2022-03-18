package view;

import controller.CreatePdf;
import controller.DBCompleteness;
import controller.NumbersStats;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import service.DateParser;
import service.DateRange;
import service.Utils;

public class Statistics extends javax.swing.JPanel {
    private SwingWorker<Integer, Integer> worker;
    private DateRange dateRange;
    private NumbersStats stats;
    private ArrayList<Object[]> numbersStats = new ArrayList<>();
    private ArrayList<Object[]> tzokersStats = new ArrayList<>();
    private DBCompleteness complete;
    
    /**
     * Creates new form Statistics
     */
    public Statistics() {
        initComponents();
        
        produceDates();
        tablesContainer.setVisible(false);
        pdfBtnContainer.setVisible(false);
    }
    
    // Γεμίζει τα text fields των ημερομηνιών με την σημερινή ημερομηνία και την
    // αντίστοιχη 3 μήνες πριν
    private void produceDates() {
        dateToField.setText(Utils.getTodayDate());
        dateFromField.setText(Utils.getThreeMonthsAgoDate());
    }
    
    // Γεμίζει τον πίνακα με τα δεδομένα των κληρώσεων
    public void loadTableWithData(JTable table, ArrayList<Object[]> data) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        // Διαβάζει τους αριθμούς
        for (Object[] number : data) {
            // Προσθέτει τα περιεχόμενα μια γραμμής στον πίνακα
            model.addRow(number);
        }
    }
    
    // Ελέγει την πληρότητα της βάσης
    private void checkDBCompleteness() {
        // Δημιουργεί Swing Worker για να τρέξει την άντληση των δεδομένων σε thread
        worker = new SwingWorker<Integer, Integer>() {
            @Override
            protected Integer doInBackground() throws Exception {
                // Εμφανίζει την πληρότητα της βάσης
                complete = new DBCompleteness(dateRange);
                complete.setWorker(this::publish);
                complete.calculate();

                return 0;
            }

            @Override
            protected void process(List<Integer> chunks) {
                for (Integer value : chunks) {
                    resultLabel.setText("Έλεγχος πληρότητας της βάσης δεδομένων... (" + value + "%)");
                }
            }

            @Override
            protected void done() {
                resultLabel.setText(complete.getResult());
            }
        };

        resultLabel.setVisible(true);
        worker.execute();
    }
    
    // Αντλεί τα δεδομένα από την βάση και τα εμφανίζει στους σχετικούς πίνακες
    private void fetchData() {
        // Δημιουργεί Swing Worker για να τρέξει την άντληση των δεδομένων σε thread
        worker = new SwingWorker<Integer, Integer>() {
            @Override
            protected Integer doInBackground() throws Exception {
                stats = new NumbersStats(dateRange);
                stats.calculateStats('1'); // Υπολογίζει τα στατιστικά των αριθμών
                stats.calculateStats('2'); // Υπολογίζει τα στατιστικά των τζόκερ
                
                checkDBCompleteness();
                
                return 0;
            }

            @Override
            protected void done() {
                numbersStats = stats.getNumbersStats();
                tzokersStats = stats.getTzokersStats();
                
                loadTableWithData(numbersTable, numbersStats);
                loadTableWithData(bonusesTable, tzokersStats);
                
                tablesContainer.setVisible(true);
                pdfBtnContainer.setVisible(true);
                
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                searchBtn.setEnabled(true);
            }
        };
        
        // Παίρνει το εύρος ημερομηνιών σε αντικείμενο DateRange
        DateParser parser = new DateParser(dateFromField.getText(), dateToField.getText());
        dateRange = parser.get();

        // Αν υπάρξει σφάλμα διακόπτει την εκτέλεση
        if (dateRange == null) {
            return;
        }
        
        // Διάφορες αρχικοποιήσεις
        searchBtn.setEnabled(false);
        resultLabel.setVisible(false);
        resultLabel.setText("");
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        worker.execute();
    }

    // Δημιουργεί το pdf έγγραφο
    private void createPdf() {
        if (numbersStats.isEmpty() || tzokersStats.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Δεν υπάρχουν δεδομένα για δημιουργία του εγγράφου", "Δημιουργία pdf", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Δημιουργεί Swing Worker για να τρέξει την άντληση των δεδομένων σε thread
        worker = new SwingWorker<Integer, Integer>() {
            @Override
            protected Integer doInBackground() throws Exception {
                CreatePdf pdf = new CreatePdf();
                pdf.setDateRange(dateRange);
                pdf.setNumbersStats(numbersStats);
                pdf.setTzokersStats(tzokersStats);
                pdf.create();

                return 0;
            }

            @Override
            protected void done() {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                JOptionPane.showMessageDialog(null, "To pdf δημιουργήθηκε", "Δημιουργία pdf", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        
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

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        dateFromField = new javax.swing.JFormattedTextField();
        dateToField = new javax.swing.JFormattedTextField();
        jPanel6 = new javax.swing.JPanel();
        searchBtn = new javax.swing.JButton();
        container = new javax.swing.JPanel();
        tablesContainer = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        numbersTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        bonusesTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        resultLabel = new javax.swing.JLabel();
        pdfBtnContainer = new javax.swing.JPanel();
        createPdfBtn = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setOpaque(false);

        jPanel5.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("DejaVu Serif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Ημερομηνίες");
        jPanel5.add(jLabel1);

        dateFromField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));
        dateFromField.setToolTipText("YYYY-MM-dd");
        dateFromField.setPreferredSize(new java.awt.Dimension(100, 21));
        jPanel5.add(dateFromField);

        dateToField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));
        dateToField.setToolTipText("YYYY-MM-dd");
        dateToField.setPreferredSize(new java.awt.Dimension(100, 21));
        jPanel5.add(dateToField);

        jPanel6.setOpaque(false);

        searchBtn.setFont(new java.awt.Font("DejaVu Serif", 1, 12)); // NOI18N
        searchBtn.setText("Αναζήτηση");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });
        jPanel6.add(searchBtn);

        container.setOpaque(false);

        tablesContainer.setOpaque(false);
        tablesContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setPreferredSize(new java.awt.Dimension(450, 403));

        numbersTable.setFont(new java.awt.Font("DejaVu Serif", 0, 12)); // NOI18N
        numbersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Αριθμός", "Εμφανίσεις", "Καθυστερήσεις"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        numbersTable.setMaximumSize(new java.awt.Dimension(0, 0));
        jScrollPane1.setViewportView(numbersTable);

        tablesContainer.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 36, 396, 553));

        jScrollPane2.setPreferredSize(new java.awt.Dimension(450, 403));

        bonusesTable.setFont(new java.awt.Font("DejaVu Serif", 0, 12)); // NOI18N
        bonusesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Αριθμός", "Εμφανίσεις", "Καθυστερήσεις"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        bonusesTable.setMaximumSize(new java.awt.Dimension(0, 0));
        jScrollPane2.setViewportView(bonusesTable);

        tablesContainer.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(408, 36, 390, 553));

        jLabel2.setFont(new java.awt.Font("DejaVu Serif", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Αριθμοί");
        tablesContainer.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 396, -1));

        jLabel3.setFont(new java.awt.Font("DejaVu Serif", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Τζόκερ");
        tablesContainer.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(408, 6, 390, -1));

        container.add(tablesContainer);

        resultLabel.setBackground(new java.awt.Color(255, 255, 255));
        resultLabel.setForeground(new java.awt.Color(0, 0, 0));
        resultLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pdfBtnContainer.setOpaque(false);

        createPdfBtn.setFont(new java.awt.Font("DejaVu Serif", 1, 12)); // NOI18N
        createPdfBtn.setText("Αποθήκευση σε PDF");
        createPdfBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        createPdfBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createPdfBtnActionPerformed(evt);
            }
        });
        pdfBtnContainer.add(createPdfBtn);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(resultLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pdfBtnContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, 809, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(615, 615, 615)
                .addComponent(pdfBtnContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(76, Short.MAX_VALUE)
                    .addComponent(container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(77, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void createPdfBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createPdfBtnActionPerformed
        createPdf();
    }//GEN-LAST:event_createPdfBtnActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        fetchData();
    }//GEN-LAST:event_searchBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bonusesTable;
    private javax.swing.JPanel container;
    private javax.swing.JButton createPdfBtn;
    private javax.swing.JFormattedTextField dateFromField;
    private javax.swing.JFormattedTextField dateToField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable numbersTable;
    private javax.swing.JPanel pdfBtnContainer;
    private javax.swing.JLabel resultLabel;
    private javax.swing.JButton searchBtn;
    private javax.swing.JPanel tablesContainer;
    // End of variables declaration//GEN-END:variables
}
