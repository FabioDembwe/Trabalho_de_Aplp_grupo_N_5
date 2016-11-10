/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sgpo.telas;

import java.awt.HeadlessException;
import static java.awt.SystemColor.desktop;
import java.sql.*;
import java.util.Locale;
import javax.swing.JOptionPane;
import org.sgpo.dal.ModuloConexao;

/**
 *
 * @author Inc Tec
 */
public final class TelaProva extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    PreparedStatement pst2 = null;
    ResultSet rs = null;
    ResultSet rs2 = null;

    /**
     * Creates new form TelaAluno
     */
    public TelaProva() {
        initComponents();
        conexao = ModuloConexao.conector();
        this.comboBusca();
        this.comboBusca2();
    }

    public void comboBusca() {
        String sql = "select * from disciplina";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                comboDisciplina.addItem(rs.getString(1));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void comboBusca2() {
        String sql = "select * from aluno";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                comboAluno.addItem(rs.getString(1));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Método Adicionar
    private void cadastrar() {
        String sql = "insert into prova(nota, obs, disciplina_iddisciplina, aluno_idaluno) values(?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNota.getText());
            pst.setString(2, textSobre.getText());
            pst.setString(3, comboDisciplina.getSelectedItem().toString());
            pst.setString(4, comboAluno.getSelectedItem().toString());

            // Validando os campos
            if (txtNota.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o campo Nota!");
            } else {

                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    //System.out.println(adicionado);
                    JOptionPane.showMessageDialog(null, "Prova Cadastrado com sucesso!");
                    txtNota.setText(null);
                    textSobre.setText(null);
                }
            }

        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
            txtNota.setText(null);
            textSobre.setText(null);
        }
    }

    // Metodo Consultar
    private void consultar() {
        String sql = "select * from prova where idprova=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdProva.getText());
            rs = pst.executeQuery();

            if (rs.next()) {
                txtNota.setText(rs.getString(2));
                textSobre.setText(rs.getString(3));
                comboDisciplina.setSelectedItem(rs.getString(4));
                comboAluno.setSelectedItem(rs.getString(5));

                // Para o ComboBox é diferente
            } else {
                JOptionPane.showMessageDialog(null, "Prova não encontrado!");
                txtNota.setText(null);
                textSobre.setText(null);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Metodo Editar
    private void editar() {
        String sql = "update prova set nota=?, obs=?, disciplina_iddisciplina=?, aluno_idaluno=? where idprova=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNota.getText());
            pst.setString(2, textSobre.getText());
            pst.setString(3, comboDisciplina.getSelectedItem().toString());
            pst.setString(4, comboAluno.getSelectedItem().toString());
            pst.setString(5, txtIdProva.getText());

            // Validando os campos
            if (txtNota.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o campo Nota!");
            } else {

                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    //System.out.println(adicionado);
                    JOptionPane.showMessageDialog(null, "Prova Alterado com sucesso!");
                    txtNota.setText(null);
                    textSobre.setText(null);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //Metodo Eliminar
    private void eliminar() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem a certeza que dezeja eliminar!");
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from prova where idprova=?";

            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdProva.getText());

                if (txtIdProva.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha o campo ID!");
                } else {
                    int adicionado = pst.executeUpdate();
                    if (adicionado > 0) {
                        JOptionPane.showMessageDialog(null, "Prova Eliminado com sucesso!");
                        txtNota.setText(null);
                        textSobre.setText(null);
                    }
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jLabel1 = new javax.swing.JLabel();
        txtIdProva = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNota = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        comboDisciplina = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textSobre = new javax.swing.JTextArea();
        btnAdicionar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnpesquisar = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        comboAluno = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Formulario de Alunos");
        setPreferredSize(new java.awt.Dimension(800, 600));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("ID:");

        txtIdProva.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("*Nota:");

        txtNota.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("*Disciplina:");

        comboDisciplina.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboDisciplina.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("Sobre:");

        textSobre.setColumns(20);
        textSobre.setRows(5);
        jScrollPane1.setViewportView(textSobre);

        btnAdicionar.setText("Adicionar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnpesquisar.setText("Pesquisar");
        btnpesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpesquisarActionPerformed(evt);
            }
        });

        comboAluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboAluno.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("*Aluno:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(236, 236, 236)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel18)
                            .addComponent(jLabel20)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtNota, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtIdProva, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(comboDisciplina, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(comboAluno, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(btnAdicionar)
                                    .addGap(27, 27, 27)
                                    .addComponent(btnAlterar)
                                    .addGap(46, 46, 46)
                                    .addComponent(btnEliminar)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnpesquisar))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIdProva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(comboDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(comboAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnpesquisar)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(419, 419, 419)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );

        setSize(new java.awt.Dimension(620, 415));
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        // TODO add your handling code here:
        cadastrar();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnpesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpesquisarActionPerformed
        // TODO add your handling code here:

        consultar();
    }//GEN-LAST:event_btnpesquisarActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // TODO add your handling code here:
        editar();
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnpesquisar;
    private javax.swing.JComboBox<String> comboAluno;
    private javax.swing.JComboBox<String> comboDisciplina;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea textSobre;
    private javax.swing.JTextField txtIdProva;
    private javax.swing.JTextField txtNota;
    // End of variables declaration//GEN-END:variables
}
