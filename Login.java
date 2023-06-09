package ClasesOperativas;

import Conexion.Conexion;
import Conexion.RellenarCombo;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {
    public Login() {
     initComponents();
     setLocationRelativeTo(null);
     Login.this.setBackground(new Color(0,0,0,0));
     setIconImage(getIconImage());
    
    }
    
    public Image getIconImage ()
    {
        Image retValue=Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Elementos/panaderia.png"));
        return retValue;
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        etqpanaderia = new javax.swing.JLabel();
        jPanelTransparente1 = new LIB.JPanelTransparente();
        etqlogin = new javax.swing.JLabel();
        etqlogo = new javax.swing.JLabel();
        etqusuario = new javax.swing.JLabel();
        txtusuario = new javax.swing.JTextField();
        etqusu = new javax.swing.JLabel();
        txtpaswoord = new javax.swing.JPasswordField();
        etqcandado = new javax.swing.JLabel();
        btnAcceder = new javax.swing.JButton();
        separador2 = new javax.swing.JSeparator();
        etqsalir = new javax.swing.JLabel();
        btncancelar2 = new javax.swing.JButton();
        etqcontraseña1 = new javax.swing.JLabel();
        etqfondo = new javax.swing.JLabel();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        etqpanaderia.setFont(new java.awt.Font("Yu Gothic Medium", 3, 18)); // NOI18N
        etqpanaderia.setForeground(new java.awt.Color(255, 255, 255));
        etqpanaderia.setText("     PANADERIA \"AMANECER\"");
        getContentPane().add(etqpanaderia, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 310, 40));

        jPanelTransparente1.setColorPrimario(new java.awt.Color(255, 255, 255));
        jPanelTransparente1.setColorSecundario(new java.awt.Color(255, 255, 255));
        jPanelTransparente1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        etqlogin.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        etqlogin.setForeground(new java.awt.Color(204, 0, 0));
        etqlogin.setText("      LOGIN");
        jPanelTransparente1.add(etqlogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 110, -1));

        etqlogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/programador.png"))); // NOI18N
        jPanelTransparente1.add(etqlogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 120, 120));

        etqusuario.setBackground(new java.awt.Color(51, 0, 51));
        etqusuario.setFont(new java.awt.Font("Yu Gothic Medium", 1, 14)); // NOI18N
        etqusuario.setForeground(new java.awt.Color(255, 255, 255));
        etqusuario.setText("      USUARIO");
        jPanelTransparente1.add(etqusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 143, 20));

        txtusuario.setBackground(new java.awt.Color(102, 102, 102));
        txtusuario.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtusuario.setForeground(new java.awt.Color(255, 255, 255));
        txtusuario.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtusuario.setCaretColor(new java.awt.Color(255, 255, 255));
        txtusuario.setOpaque(false);
        txtusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtusuarioActionPerformed(evt);
            }
        });
        jPanelTransparente1.add(txtusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 170, 30));

        etqusu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/Acceso.png"))); // NOI18N
        jPanelTransparente1.add(etqusu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        txtpaswoord.setBackground(new java.awt.Color(102, 102, 102));
        txtpaswoord.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtpaswoord.setForeground(new java.awt.Color(255, 255, 255));
        txtpaswoord.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtpaswoord.setCaretColor(new java.awt.Color(255, 255, 255));
        txtpaswoord.setOpaque(false);
        txtpaswoord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpaswoordActionPerformed(evt);
            }
        });
        txtpaswoord.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpaswoordKeyReleased(evt);
            }
        });
        jPanelTransparente1.add(txtpaswoord, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 170, 30));

        etqcandado.setForeground(new java.awt.Color(255, 255, 255));
        etqcandado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/contrasena.png"))); // NOI18N
        jPanelTransparente1.add(etqcandado, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, -1, -1));

        btnAcceder.setBackground(new java.awt.Color(102, 102, 102));
        btnAcceder.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        btnAcceder.setForeground(new java.awt.Color(255, 255, 255));
        btnAcceder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/Acceso.png"))); // NOI18N
        btnAcceder.setText("INGRESAR");
        btnAcceder.setBorder(null);
        btnAcceder.setOpaque(false);
        btnAcceder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccederActionPerformed(evt);
            }
        });
        jPanelTransparente1.add(btnAcceder, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 130, 40));

        separador2.setForeground(new java.awt.Color(0, 0, 0));
        jPanelTransparente1.add(separador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 290, 10));

        etqsalir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etqsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/letrax.png"))); // NOI18N
        etqsalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        etqsalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                etqsalirMouseClicked(evt);
            }
        });
        jPanelTransparente1.add(etqsalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, 30));

        btncancelar2.setBackground(new java.awt.Color(102, 102, 102));
        btncancelar2.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btncancelar2.setForeground(new java.awt.Color(255, 255, 255));
        btncancelar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/cancelado.png"))); // NOI18N
        btncancelar2.setText("CANCELAR");
        btncancelar2.setOpaque(false);
        btncancelar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelar2ActionPerformed(evt);
            }
        });
        jPanelTransparente1.add(btncancelar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 310, 130, 40));

        etqcontraseña1.setBackground(new java.awt.Color(51, 0, 51));
        etqcontraseña1.setFont(new java.awt.Font("Yu Gothic Medium", 1, 14)); // NOI18N
        etqcontraseña1.setForeground(new java.awt.Color(255, 255, 255));
        etqcontraseña1.setText(" CONTRASEÑA");
        jPanelTransparente1.add(etqcontraseña1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 120, -1));

        getContentPane().add(jPanelTransparente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 290, 360));

        etqfondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/Chef.png"))); // NOI18N
        etqfondo.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, new javax.swing.border.LineBorder(new java.awt.Color(2, 2, 2), 1, true)));
        getContentPane().add(etqfondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, 360));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAccederActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccederActionPerformed
         try
        {
            String jTextNom=txtusuario.getText();
            String jTextPass=txtpaswoord.getText();
            Conexion objdatos= new Conexion();
            objdatos.conectar();
            String sql="select * from usuarios where nombre='"+ jTextNom + "'and contraseña='"+ jTextPass +"'and idPuesto='"+ 2 +"'";
            ResultSet conjRegs=objdatos.obtenerDatos(sql);
            
            Conexion datos= new Conexion();
            datos.conectar();
            String CSQL="select * from usuarios where nombre='"+ jTextNom + "'and contraseña='"+ jTextPass + "'and idPuesto='"+ 1 +"'";
            ResultSet contipo=datos.obtenerDatos(CSQL);
            
            if(conjRegs.next())
             {
                JOptionPane.showMessageDialog(null," \t BIENVENIDO AL SISTEMA"+ jTextNom);
                Panaderia A= new Panaderia();
                A.setVisible(true);
                
                this.dispose();
            }
            else
            {
                if(contipo.next())
                {
                JOptionPane.showMessageDialog(null," \t BIENVENIDO AL SISTEMA "+ jTextNom);
                Administrador A= new Administrador();
                A.setVisible(true);
                this.dispose();
                }
                else
                {
                JOptionPane.showMessageDialog(rootPane, "NO SE ENCUENTRA EL USUARIO, FAVOR DE REGISTRARSE");
                txtusuario.setText("");
                txtpaswoord.setText("");   
                }
            }
        }catch(SQLException ex)
        {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAccederActionPerformed

    private void txtpaswoordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpaswoordKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
             try
        {
            String jTextNom=txtusuario.getText();
            String jTextPass=txtpaswoord.getText();
            Conexion objdatos= new Conexion();
            objdatos.conectar();
             String sql="select * from usuarios where nombre='"+ jTextNom + "'and contraseña='"+ jTextPass +"'and idPuesto='"+ 2 +"'";
            ResultSet conjRegs=objdatos.obtenerDatos(sql);
            
            
            Conexion datos= new Conexion();
            datos.conectar();
            String CSQL="select * from usuarios where nombre='"+ jTextNom + "'and contraseña='"+ jTextPass + "'and idPuesto='"+ 1 +"'";
            ResultSet contipo=datos.obtenerDatos(CSQL);
            
            if(conjRegs.next())
             {
                JOptionPane.showMessageDialog(null," \t BIENVENIDO AL SISTEMA "+ jTextNom);
                Panaderia A= new Panaderia();
                A.setVisible(true);
                this.dispose();
            }
            else
            {
                if(contipo.next())
                {
                JOptionPane.showMessageDialog(null," \t BIENVENIDO AL SISTEMA "+ jTextNom);
                Administrador A= new Administrador();
                A.setVisible(true);
                this.dispose();
                }
                else
                {
                JOptionPane.showMessageDialog(null,"NO SE ENCUENTRA EL USUARIO");
                txtusuario.setText("");
                txtpaswoord.setText(""); 
                }
            } 
        }catch(SQLException ex)
        {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_txtpaswoordKeyReleased

    private void txtusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtusuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtusuarioActionPerformed

    private void etqsalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_etqsalirMouseClicked
    System.exit(0);
    }//GEN-LAST:event_etqsalirMouseClicked

    private void txtpaswoordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpaswoordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpaswoordActionPerformed

    private void btncancelar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelar2ActionPerformed
     System.exit(0);
    }//GEN-LAST:event_btncancelar2ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAcceder;
    private javax.swing.JButton btncancelar2;
    private javax.swing.JLabel etqcandado;
    private javax.swing.JLabel etqcontraseña1;
    private javax.swing.JLabel etqfondo;
    private javax.swing.JLabel etqlogin;
    private javax.swing.JLabel etqlogo;
    private javax.swing.JLabel etqpanaderia;
    private javax.swing.JLabel etqsalir;
    private javax.swing.JLabel etqusu;
    private javax.swing.JLabel etqusuario;
    private javax.swing.JComboBox<String> jComboBox1;
    private LIB.JPanelTransparente jPanelTransparente1;
    private javax.swing.JSeparator separador2;
    private javax.swing.JPasswordField txtpaswoord;
    private javax.swing.JTextField txtusuario;
    // End of variables declaration//GEN-END:variables


}
