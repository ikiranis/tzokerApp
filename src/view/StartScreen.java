package view;

import api.Api;
import java.awt.Cursor;
import javax.swing.JOptionPane;

public class StartScreen extends javax.swing.JFrame {

    /**
     * Creates new form StartScreen
     */
    public StartScreen() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        propo = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        extra5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        super3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        tzoker = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lotto = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        kino = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        opap_logo = new javax.swing.JLabel();

        jScrollPane1.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Αποτελέσματα παιγνίων της ΟΠΑΠ Α.Ε.");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setOpaque(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        propo.setBackground(new java.awt.Color(255, 255, 255));
        propo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        propo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                propoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                propoMouseEntered(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/propo.png"))); // NOI18N

        javax.swing.GroupLayout propoLayout = new javax.swing.GroupLayout(propo);
        propo.setLayout(propoLayout);
        propoLayout.setHorizontalGroup(
            propoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(propoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addContainerGap())
        );
        propoLayout.setVerticalGroup(
            propoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(propoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addContainerGap())
        );

        extra5.setBackground(new java.awt.Color(255, 255, 255));
        extra5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        extra5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                extra5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                extra5MouseEntered(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/extra5.png"))); // NOI18N

        javax.swing.GroupLayout extra5Layout = new javax.swing.GroupLayout(extra5);
        extra5.setLayout(extra5Layout);
        extra5Layout.setHorizontalGroup(
            extra5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(extra5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addContainerGap())
        );
        extra5Layout.setVerticalGroup(
            extra5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(extra5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addContainerGap())
        );

        super3.setBackground(new java.awt.Color(255, 255, 255));
        super3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        super3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                super3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                super3MouseEntered(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/super3.png"))); // NOI18N

        javax.swing.GroupLayout super3Layout = new javax.swing.GroupLayout(super3);
        super3.setLayout(super3Layout);
        super3Layout.setHorizontalGroup(
            super3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(super3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addContainerGap())
        );
        super3Layout.setVerticalGroup(
            super3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(super3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addContainerGap())
        );

        tzoker.setBackground(new java.awt.Color(255, 255, 255));
        tzoker.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tzoker.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tzokerMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tzokerMouseEntered(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/tzoker_logo_x200.png"))); // NOI18N

        javax.swing.GroupLayout tzokerLayout = new javax.swing.GroupLayout(tzoker);
        tzoker.setLayout(tzokerLayout);
        tzokerLayout.setHorizontalGroup(
            tzokerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tzokerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        tzokerLayout.setVerticalGroup(
            tzokerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tzokerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addContainerGap())
        );

        lotto.setBackground(new java.awt.Color(255, 255, 255));
        lotto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lotto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lottoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lottoMouseEntered(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/lotto.jpg"))); // NOI18N

        javax.swing.GroupLayout lottoLayout = new javax.swing.GroupLayout(lotto);
        lotto.setLayout(lottoLayout);
        lottoLayout.setHorizontalGroup(
            lottoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lottoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addContainerGap())
        );
        lottoLayout.setVerticalGroup(
            lottoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lottoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addContainerGap())
        );

        kino.setBackground(new java.awt.Color(255, 255, 255));
        kino.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        kino.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kinoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                kinoMouseEntered(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/kino.jpeg"))); // NOI18N

        javax.swing.GroupLayout kinoLayout = new javax.swing.GroupLayout(kino);
        kino.setLayout(kinoLayout);
        kinoLayout.setHorizontalGroup(
            kinoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kinoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        kinoLayout.setVerticalGroup(
            kinoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kinoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tzoker, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(propo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lotto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(extra5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(super3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lotto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tzoker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(super3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(extra5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(propo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel2);

        opap_logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        opap_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/opap_logo.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(391, 391, 391)
                .addComponent(opap_logo, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                .addGap(397, 397, 397))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(opap_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(91, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tzokerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tzokerMouseClicked
        dispose();

        MainFrame mainFrame = new MainFrame();

        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
        
        // Θέτει τον κωδικό του παιχνιδιού για το Τζόκερ, που χρειάζεται για το API
        Api.setGameId("5104");
    }//GEN-LAST:event_tzokerMouseClicked

    private void lottoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lottoMouseClicked
        JOptionPane.showMessageDialog(null, "Το Lotto δεν υποστηρίζεται ακόμη", "Υπενθύμιση", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_lottoMouseClicked

    private void kinoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kinoMouseClicked
        JOptionPane.showMessageDialog(null, "Το Kino δεν υποστηρίζεται ακόμη", "Υπενθύμιση", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_kinoMouseClicked

    private void propoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_propoMouseClicked
        JOptionPane.showMessageDialog(null, "Το Προ-πο δεν υποστηρίζεται ακόμη", "Υπενθύμιση", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_propoMouseClicked

    private void extra5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_extra5MouseClicked
        JOptionPane.showMessageDialog(null, "Το Extra5 δεν υποστηρίζεται ακόμη", "Υπενθύμιση", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_extra5MouseClicked

    private void super3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_super3MouseClicked
        JOptionPane.showMessageDialog(null, "Το Super3 δεν υποστηρίζεται ακόμη", "Υπενθύμιση", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_super3MouseClicked

    private void tzokerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tzokerMouseEntered
        tzoker.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_tzokerMouseEntered

    private void lottoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lottoMouseEntered
        lotto.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lottoMouseEntered

    private void kinoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kinoMouseEntered
        kino.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_kinoMouseEntered

    private void propoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_propoMouseEntered
        propo.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_propoMouseEntered

    private void extra5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_extra5MouseEntered
        extra5.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_extra5MouseEntered

    private void super3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_super3MouseEntered
        super3.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_super3MouseEntered

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel extra5;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel kino;
    private javax.swing.JPanel lotto;
    private javax.swing.JLabel opap_logo;
    private javax.swing.JPanel propo;
    private javax.swing.JPanel super3;
    private javax.swing.JPanel tzoker;
    // End of variables declaration//GEN-END:variables
}
