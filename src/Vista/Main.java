/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.Conexion;
import Controlador.GestionBD;
import Modelo.Cliente;
import Modelo.Mensualidad;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;



/**
 *
 * 
 */
public class Main extends javax.swing.JFrame {
    

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        
        try {
            Image image = ImageIO.read(new File("./src/Imagenes/logo.png"));
            Icon icon = new ImageIcon(image);
            lblLogo.setIcon(icon);
        } catch (IOException ex) {
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        setExtendedState(MAXIMIZED_BOTH);
        if (Conexion.conectar("localhost", "3306", "bd_spartan", "root","2877")) {
            
            Mensualidad m= new Mensualidad("000000000");
            if(!GestionBD.existeMensualidad(m)){
                for(Cliente c:GestionBD.getClientesActivos()){
                    m.setDni(c.getDni());
                    GestionBD.crearMensualidad(m);
                }
            }
        } else {
            //JOptionPane.showMessageDialog(null, "No se ha podido establecer la conexion");
            dispose();
        }
    }
    
    public JDesktopPane getEscritorio(){
        return escritorio;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        escritorio = new javax.swing.JDesktopPane();
        lblLogo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnCliente = new javax.swing.JMenu();
        mnitAltaC = new javax.swing.JMenuItem();
        mnMostrar = new javax.swing.JMenu();
        mnitVer = new javax.swing.JMenuItem();
        mnHistorico = new javax.swing.JMenu();
        mnitHist = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Spartan Fitness Center");

        escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblLogo.setBackground(new java.awt.Color(11, 10, 9));
        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setAlignmentY(0.0F);
        lblLogo.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        lblLogo.setOpaque(true);

        escritorio.setLayer(lblLogo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblLogo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblLogo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );

        mnCliente.setBackground(new java.awt.Color(255, 255, 255));
        mnCliente.setText("Alta Cliente");

        mnitAltaC.setBackground(new java.awt.Color(255, 255, 255));
        mnitAltaC.setText("Dar de alta");
        mnitAltaC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnitAltaCActionPerformed(evt);
            }
        });
        mnCliente.add(mnitAltaC);

        jMenuBar1.add(mnCliente);

        mnMostrar.setText("Ver Clientes");

        mnitVer.setText("Listado Clientes");
        mnitVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnitVerActionPerformed(evt);
            }
        });
        mnMostrar.add(mnitVer);

        jMenuBar1.add(mnMostrar);

        mnHistorico.setText("Historicos");

        mnitHist.setText("Listado historico");
        mnitHist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnitHistActionPerformed(evt);
            }
        });
        mnHistorico.add(mnitHist);

        jMenuBar1.add(mnHistorico);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void mnitAltaCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnitAltaCActionPerformed
        AltaCliente ac = new AltaCliente();
        escritorio.add(ac);
        ac.setVisible(true);
    }//GEN-LAST:event_mnitAltaCActionPerformed

    private void mnitVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnitVerActionPerformed

        VerClientes vc= new  VerClientes();
        escritorio.add(vc);
        vc.setVisible(true);
    }//GEN-LAST:event_mnitVerActionPerformed

    private void mnitHistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnitHistActionPerformed
        Historicos h= new Historicos();
        escritorio.add(h);
        h.setVisible(true);
    }//GEN-LAST:event_mnitHistActionPerformed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JMenu mnCliente;
    private javax.swing.JMenu mnHistorico;
    private javax.swing.JMenu mnMostrar;
    private javax.swing.JMenuItem mnitAltaC;
    private javax.swing.JMenuItem mnitHist;
    private javax.swing.JMenuItem mnitVer;
    // End of variables declaration//GEN-END:variables
    public void recargarHijos() {
        JInternalFrame[] allFrames = escritorio.getAllFrames();
        for (JInternalFrame a : allFrames) {
            ((Ventana) a).recargarDatos();
        }
    }
}
