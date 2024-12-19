/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista;

import Controlador.GestionBD;
import Modelo.Cliente;
import java.util.ArrayList;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * 
 */
public class VerClientes extends javax.swing.JInternalFrame implements Ventana{

    /**
     * Creates new form VerClientes
     */
    public VerClientes() {
        initComponents();        
        dtm=(DefaultTableModel) tblClientes.getModel();
        cargarTabla();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        btnVer = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Ver Clientes");
        setVisible(false);

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cliente", "Telefono", "Estado", "Estado de pago"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblClientes);

        btnVer.setText("Ver");
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                .addGap(21, 21, 21))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVer)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnVer)
                .addGap(21, 21, 21))
        );

        setBounds(0, 0, 417, 322);
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed

        if (tblClientes.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "NO ai nah sElEcIOnAO");
            return;
        } 
        Cliente selecionado=(Cliente) dtm.getValueAt(tblClientes.getSelectedRow(), 0);
        MostrarCliente mc=new MostrarCliente(selecionado);
        JDesktopPane escritorio=((Main)getParent().getParent().getParent().getParent().getParent()).getEscritorio();
        escritorio.add(mc);
        mc.setVisible(true);
    }//GEN-LAST:event_btnVerActionPerformed


    DefaultTableModel dtm;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVer;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblClientes;
    // End of variables declaration//GEN-END:variables

    @Override
    public void recargarDatos() {
       cargarTabla();
    }
    
    public void cargarTabla(){
        dtm.setRowCount(0);
        ArrayList<Cliente> lista = GestionBD.getClientes();
        for (Cliente c:lista){
            dtm.setRowCount(dtm.getRowCount() + 1);
            dtm.setValueAt(c, dtm.getRowCount()-1, 0);
            dtm.setValueAt(c.getTelefono(), dtm.getRowCount()-1, 1);
            if(c.isActivo()){
                dtm.setValueAt("Activo",dtm.getRowCount()-1, 2);                
            }else{
                dtm.setValueAt("Inactivo",dtm.getRowCount()-1, 2);
            }
            if(GestionBD.isMoroso(c.getDni())){
                dtm.setValueAt("Moroso",dtm.getRowCount()-1, 3);
            }else{
                dtm.setValueAt("Todo Correcto",dtm.getRowCount()-1, 3);
            }
        }
    }
}
