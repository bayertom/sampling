// Copyright (c) 2017 - 2018
// Tomas Bayer
// Charles University in Prague, Faculty of Science
// bayertom@natur.cuni.cz

// This library is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published
// by the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with this library. If not, see <http://www.gnu.org/licenses/>.

package adaptivesampling;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.shape.Polyline;
import static java.lang.Math.*;
/**
 *
 * @author Tomáš
 */
public class MainForm extends javax.swing.JFrame {
        
       private double a;
       private double b;  
       private List <Point2D.Double> points;
       private List <List<Point2D.Double>> fragments;
       private Draw draw;
      
       private IFunction ifx;
                
                
        public MainForm() {
                
                //Initialize intervals
                a = -5*PI;
                b = 5*PI;
                
                //Create empty lists of points and fragments
                points = new ArrayList<>();
                fragments = new ArrayList<List<Point2D.Double>>();
                
                //Initialize reference to function
                ifx = MainForm::function_fx1;

                //Init graphic components
                initComponents();
                
                //Set values a,b
                jTextField1.setText(Double.toString(a));
                jTextField2.setText(Double.toString(b));
                
                //Create GUI
                draw = new Draw(a, b, fragments, points);
                Canvas.add(draw);
        }
        
        
        public static double function_fx1(final double x)
        {
                return tan(x);
        }
        
        public static double function_fx2(final double x)
        {
                return 1.0/tan(x);
        }
        
        public static double function_fx3(final double x)
        {
                return x*sin(5/x);
        }
        
        public static double function_fx4(final double x)
        {
                return 1/(tan(x) * tan(x));
        }
        
        public static double function_fx5(final double x)
        {
                return 1/(tan(2*x) * tan(0.5*x)) ;
        }
        
        public static double function_fx6(final double x)
        {
                if (x > 0) return 1 + x;
                else return 0;
        }
        
        
        public static double function_fx7(final double x)
        {
                //return -5*x*exp(-x*x/5); 
                return 2*sin(10/x)/x;
        }
        
        
        public static double function_fx8(final double x)
        {
                return 5*sin(x) / x; 
        }
        
        
        public static double function_fx9(final double x)
        {
                return exp(-2*x) / (x-1); 
        }
        
        public static double function_fx10(final double x)
        {
                return x / sin(x); 
        }
        
        public static double function_fx11(final double x)
        {
                return sin(2*x) * x * x / (2 * x - 1); 
        }
        
        public static double function_fx12(final double x)
        {
                return exp(x) / tan(x);
        }
        
        public static double function_fx13(final double x)
        {
                return abs(x) / (1-x);
        }
        
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                Canvas = new javax.swing.JPanel();
                jPanel2 = new javax.swing.JPanel();
                jButton1 = new javax.swing.JButton();
                jButton2 = new javax.swing.JButton();
                jLabel1 = new javax.swing.JLabel();
                jComboBox1 = new javax.swing.JComboBox<>();
                jLabel2 = new javax.swing.JLabel();
                jComboBox2 = new javax.swing.JComboBox<>();
                jLabel3 = new javax.swing.JLabel();
                jTextField1 = new javax.swing.JTextField();
                jLabel4 = new javax.swing.JLabel();
                jLabel5 = new javax.swing.JLabel();
                jTextField2 = new javax.swing.JTextField();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setTitle("Adaptive sampling of the function");

                Canvas.setBackground(new java.awt.Color(255, 255, 255));
                Canvas.setLayout(new java.awt.BorderLayout());

                jButton1.setText("Sample");
                jButton1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButton1ActionPerformed(evt);
                        }
                });

                jButton2.setText("Clear");
                jButton2.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButton2ActionPerformed(evt);
                        }
                });

                jLabel1.setText("Implementation:");

                jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Stack-based", "Recursive" }));
                jComboBox1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jComboBox1ActionPerformed(evt);
                        }
                });

                jLabel2.setText("Select function: ");

                jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Function 1", "Function 2", "Function 3", "Function 4", "Function 5", "Function 6", "Function 7", "Function 8", "Function 9", "Function 10", "Function 11", "Function 12", "Function 13" }));
                jComboBox2.addItemListener(new java.awt.event.ItemListener() {
                        public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                jComboBox2ItemStateChanged(evt);
                        }
                });

                jLabel3.setText("Interval:");

                jTextField1.setText("-5");
                jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
                        public void focusLost(java.awt.event.FocusEvent evt) {
                                jTextField1FocusLost(evt);
                        }
                });

                jLabel4.setText("x_min:");

                jLabel5.setText("x_max:");

                jTextField2.setText("5");
                jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
                        public void focusLost(java.awt.event.FocusEvent evt) {
                                jTextField2FocusLost(evt);
                        }
                });

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
                                .addContainerGap())
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2)
                                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel4)
                                                        .addComponent(jLabel5))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE))
                );
                jPanel2Layout.setVerticalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 482, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addGap(43, 43, 43))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(Canvas, javax.swing.GroupLayout.DEFAULT_SIZE, 1215, Short.MAX_VALUE)
                                .addGap(15, 15, 15)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(Canvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(24, 24, 24)
                                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

                int s = 0;                              //Amount of the splits of the interval (a,b)
                int dmax = 50;                          //Maximum recursion depth (sampling)
                int smax = 5000;                        //Maximum amount of splits of the (a,b) interval
                double fmax = 100;                      //Maximum function value f(x)
                final double eps = 0.001;               //Size of the threshold
                final double ff = 0.4;                    //Function flatness
                
                //Clear fragments
                fragments.clear();

                //Stack-based implementation
                if (jComboBox1.getSelectedIndex() == 0)
                        AS.asf(ifx, fragments, a, b, fmax, smax, dmax, eps, ff);

                //Recursive implementation (less efficient)
                else
                        AS.asfr(ifx, fragments, a, b, s, fmax, smax, dmax, 50 * eps, ff);
                
                //Repaint situation
                Canvas.repaint();
        }//GEN-LAST:event_jButton1ActionPerformed

        private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
                // TODO add your handling code here:
        }//GEN-LAST:event_jComboBox1ActionPerformed

        private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
                final int index = jComboBox2.getSelectedIndex();
                
                //Select function
                if (index == 0)
                        ifx = MainForm::function_fx1;
                else if (index == 1)
                        ifx = MainForm::function_fx2;
                else if (index == 2)
                        ifx = MainForm::function_fx3;
                else if (index == 3)
                        ifx = MainForm::function_fx4;
                else if (index == 4)
                        ifx = MainForm::function_fx5;       
                else if (index == 5)
                        ifx = MainForm::function_fx6;               
                else if (index == 6)
                        ifx = MainForm::function_fx7;
                else if (index == 7)
                        ifx = MainForm::function_fx8;
                else if (index == 8)
                        ifx = MainForm::function_fx9;
                else if (index == 9)
                        ifx = MainForm::function_fx10;
                else if (index == 10)
                        ifx = MainForm::function_fx11;       
                else if (index == 11)
                        ifx = MainForm::function_fx12;               
                else if (index == 12)
                        ifx = MainForm::function_fx13;
        }//GEN-LAST:event_jComboBox2ItemStateChanged

        private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
                a = Double.valueOf(jTextField1.getText());
                draw.setA(a);
                Canvas.repaint();
        }//GEN-LAST:event_jTextField1FocusLost

        private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusLost
                b = Double.valueOf(jTextField2.getText());
                draw.setB(b);
                Canvas.repaint();
        }//GEN-LAST:event_jTextField2FocusLost

        private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
                //Clear screen
                fragments.clear();
                Canvas.repaint();
        }//GEN-LAST:event_jButton2ActionPerformed

        public static void main(String args[]) {
                /* Set the Nimbus look and feel */
                //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
                /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
                 */
                try {
                        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                                if ("Nimbus".equals(info.getName())) {
                                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                                        break;
                                }
                        }
                } catch (ClassNotFoundException ex) {
                        java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                        java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                        java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                        java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
                //</editor-fold>
                //</editor-fold>

                /* Create and display the form */
                java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                new MainForm().setVisible(true);
                        }
                });
        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JPanel Canvas;
        private javax.swing.JButton jButton1;
        private javax.swing.JButton jButton2;
        private javax.swing.JComboBox<String> jComboBox1;
        private javax.swing.JComboBox<String> jComboBox2;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JTextField jTextField1;
        private javax.swing.JTextField jTextField2;
        // End of variables declaration//GEN-END:variables
}
