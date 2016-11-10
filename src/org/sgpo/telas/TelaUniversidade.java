/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sgpo.telas;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.sgpo.dal.*;

/**
 *
 * @author Fabio
 */
public class TelaUniversidade extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    PreparedStatement pst2 = null;
    ResultSet rs = null;
    ResultSet rs2 = null;

    /**
     * Creates new form TelaUniversidade
     */
    public TelaUniversidade() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    //Metodo Adiciona Universidade
    private void cadastrar() {
        String sql = "insert into universidade(nome, cgc, obs) values(?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtCgc.getText());
            pst.setString(3, txtObs.getText());

            // Validando os campos
            if (txtNome.getText().isEmpty() || txtCgc.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos Obrigatorios! * ");
            } else {

                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    //System.out.println(adicionado);
                    JOptionPane.showMessageDialog(null, "Universadade Cadastrado com sucesso!");
                    txtNome.setText(null);
                    txtCgc.setText("0");
                    txtObs.setText(null);

                }
            }

        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e + "Não foi Possível adicionar com Sucesso");
            txtNome.setText(null);
            txtCgc.setText("0");
            txtObs.setText(null);
        }
    }

    // Criando o Metodo Consultar Universidade
    private void consultarUniversidade() {
        String sql = "select * from universidade where iduniversidade=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdUniversidade.getText());
            rs = pst.executeQuery();

            if (rs.next()) {
                txtNome.setText(rs.getString(2));
                txtCgc.setText(rs.getString(3));
                txtObs.setText(rs.getString(4));

            } else {
                JOptionPane.showMessageDialog(null, "Universidade não encontrado!");
                txtNome.setText(null);
                txtCgc.setText("0");
                txtObs.setText(null);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Criando o Metodo Editar
    private void editarUniversidade() {
        String sql = "update universidade set nome=?, cgc=?, obs=? where iduniversidade=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtCgc.getText());
            pst.setString(3, txtObs.getText());
            pst.setString(4, txtIdUniversidade.getText());

            // Validando os campos
            if (txtNome.getText().isEmpty() || txtCgc.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos Obrigatorios! * ");
            } else {

                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    //System.out.println(adicionado);
                    JOptionPane.showMessageDialog(null, "Universadade Alterado com sucesso!");
                    txtNome.setText(null);
                    txtCgc.setText("0");
                    txtObs.setText(null);

                }
            }

        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e + "Não foi Possível Alterar com Sucesso");
            txtNome.setText(null);
            txtCgc.setText("0");
            txtObs.setText(null);
        }
    }

    // Criando o Metodo Eliminar
    private void eliminarUniversidade() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem a certeza que dezeja eliminar!");
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from universidade where iduniversidade=?";

            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdUniversidade.getText());

                if (txtIdUniversidade.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha o campo ID!");
                } else {
                    int adicionado = pst.executeUpdate();
                    if (adicionado > 0) {
                        JOptionPane.showMessageDialog(null, "Universidade Eliminado com sucesso!");
                        txtNome.setText(null);
                        txtCgc.setText("0");
                        txtObs.setText(null);
                    }
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Impossível apagar Universidade, Verifique se está sendo usada ou conecte a Base de Dados \n " + e);
                txtNome.setText(null);
                txtCgc.setText("0");
                txtObs.setText(null);
            }
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

        jLabel1 = new javax.swing.JLabel();
        txtIdUniversidade = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObs = new javax.swing.JTextArea();
        txtCgc = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Formulario de Universidade");

        jLabel1.setText("ID:");

        jLabel2.setText("*Nome:");

        jLabel3.setText("*CGC:");

        jLabel4.setText("OBS:");

        txtObs.setColumns(20);
        txtObs.setRows(5);
        jScrollPane1.setViewportView(txtObs);

        txtCgc.setText("0");

        btnAdicionar.setText("Adicionar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnConsultar.setText("Consultar");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2)
                        .addComponent(jLabel4)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdicionar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAlterar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)
                        .addGap(18, 18, 18)
                        .addComponent(btnConsultar))
                    .addComponent(txtIdUniversidade, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCgc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNome, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIdUniversidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCgc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionar)
                    .addComponent(btnAlterar)
                    .addComponent(btnEliminar)
                    .addComponent(btnConsultar))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        // TODO add your handling code here:
        cadastrar();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        // Chamando o Metodo de Consulta da Universidade
        consultarUniversidade();
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // TODO add your handling code here:
        editarUniversidade();
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // Chamando o metodo que elimina Univerdade
        eliminarUniversidade();
    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCgc;
    private javax.swing.JTextField txtIdUniversidade;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextArea txtObs;
    // End of variables declaration//GEN-END:variables
}
