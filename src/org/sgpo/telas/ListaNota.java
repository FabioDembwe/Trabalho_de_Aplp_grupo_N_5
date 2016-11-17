/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sgpo.telas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import org.sgpo.dal.ModuloConexao;

/**
 *
 * @author Fabio
 */
public class ListaNota extends javax.swing.JInternalFrame {
     Connection conexao = null;
    PreparedStatement pst = null;
    PreparedStatement pst2 = null;
    ResultSet rs = null;
    ResultSet rs2 = null;

    /**
     * Creates new form ListaTurma
     */
    public ListaNota() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
    // Nota
    private void pesquisaNota1(){
        String sql ="SELECT nota.idnota as ID,mac as Mac,media_final as Media_Final,media_prova as Media_Prova,disciplina.nome,prova.nota,trabalho.nota,aluno.nome from nota,disciplina,prova,trabalho,aluno  where nota.idnota like ? ";
        try {
            pst = conexao.prepareStatement(sql);
            
            pst.setString(1, txtPesquisaNotas.getText() + "%");
            
            rs = pst.executeQuery();
            
            tabNotas.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
}
    // Disciplina
     private void pesquisaNota2(){
        String sql ="SELECT nota.idnota as ID,mac as Mac,media_final as Media_Final,media_prova as Media_Prova,disciplina.nome,prova.nota,trabalho.nota,aluno.nome from nota,disciplina,prova,trabalho,aluno where disciplina.nome like ?";
        try {
            pst = conexao.prepareStatement(sql);
            
            pst.setString(1, txtPesquisaNotas.getText() + "%");
            
            rs = pst.executeQuery();
            
            tabNotas.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
}
     // pesqiui
    private void pesquisaNota3(){
        String sql ="SELECT nota.idnota as ID,mac as Mac,media_final as Media_Final,media_prova as Media_Prova,disciplina.nome,prova.nota,trabalho.nota,aluno.nome from nota,disciplina,prova,trabalho,aluno where prova.nota like ?";
        try {
            pst = conexao.prepareStatement(sql);
            
            pst.setString(1, txtPesquisaNotas.getText() + "%");
            
            rs = pst.executeQuery();
            
            tabNotas.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
}
     // pesqiui
    private void pesquisaNota4(){
        String sql ="SELECT nota.idnota as ID,mac as Mac,media_final as Media_Final,media_prova as Media_Prova,disciplina.nome,prova.nota,trabalho.nota,aluno.nome from nota,disciplina,prova,trabalho,aluno where trabalho.nota like ?";
        try {
            pst = conexao.prepareStatement(sql);
            
            pst.setString(1, txtPesquisaNotas.getText() + "%");
            
            rs = pst.executeQuery();
            
            tabNotas.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtPesquisaNotas = new javax.swing.JTextField();
        comboPesquisa = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabNotas = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);

        txtPesquisaNotas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPesquisaNotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisaNotasActionPerformed(evt);
            }
        });
        txtPesquisaNotas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaNotasKeyReleased(evt);
            }
        });

        comboPesquisa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboPesquisa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID:", "Disciplina:", "Prova:", "Trabalho:", "Aluno:" }));

        tabNotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabNotas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(comboPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPesquisaNotas, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisaNotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPesquisaNotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisaNotasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisaNotasActionPerformed

    private void txtPesquisaNotasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaNotasKeyReleased
        // TODO add your handling code here:
        /*
        if(comboPesquisa.getSelectedIndex() == 0){//A busca será por categoria
            tabAluno.setModel(new DefaultTableModel(TelaAluno.consultar(txtPesquisa.getText()), Cabecalho));

        }*/
        if(comboPesquisa.getSelectedIndex() == 0){
            pesquisaNota1();
        }else if(comboPesquisa.getSelectedIndex() == 1)
        {
            pesquisaNota2();
        }else if(comboPesquisa.getSelectedIndex() == 2){
          pesquisaNota3();
    }else{
            pesquisaNota4(); 
        }
    }//GEN-LAST:event_txtPesquisaNotasKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboPesquisa;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabNotas;
    private javax.swing.JTextField txtPesquisaNotas;
    // End of variables declaration//GEN-END:variables
}
