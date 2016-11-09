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
 * @author Fabio
 */
public class TelaTurma extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    PreparedStatement pst2 = null;
    ResultSet rs = null;
    ResultSet rs2 = null;

    /**
     * Creates new form TelaTurma
     */
    public TelaTurma() {
        initComponents();
        conexao = ModuloConexao.conector();
        this.comboBusca();
        this.comboBusca2();
    }

    // Criando o Metodo de Consulta das ComboBox
    public void comboBusca() {
        String sql = "select * from curso";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                comboCurso.addItem(rs.getString(1));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void comboBusca2() {
        String sql = "select * from universidade";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                comboUniversidade.addItem(rs.getString(1));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Criando o Metodo Cadastrar
    private void cadastrarTurma() {
        String sql = "insert into turma(nome, sala, horario, carga_horaria, obs, curso_idcurso, universidade_iduniversidade) values(?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtSala.getText());
            pst.setString(3, txtHorario.getText());
            pst.setString(4, txtChorario.getText());
            pst.setString(5, txtObs.getText());
            pst.setString(6, comboCurso.getSelectedItem().toString());
            pst.setString(7, comboUniversidade.getSelectedItem().toString());

            // Validando os campos
            if (txtNome.getText().isEmpty() || txtSala.getText().isEmpty() || txtHorario.getText().isEmpty() || txtChorario.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos Obrigatorios!");
            } else {

                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    //System.out.println(adicionado);
                    JOptionPane.showMessageDialog(null, "Turma Cadastrado com sucesso!");
                    txtNome.setText(null);
                    txtSala.setText(null);
                    txtHorario.setText(null);
                    txtChorario.setText(null);
                }
            }

        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
            txtNome.setText(null);
            txtSala.setText(null);
            txtHorario.setText(null);
            txtChorario.setText(null);
        }
    }

    // Metodo Consultar
    private void consultarTurma() {
        String sql = "select * from turma where idturma=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtId.getText());
            rs = pst.executeQuery();

            if (rs.next()) {
                txtNome.setText(rs.getString(2));
                txtSala.setText(rs.getString(3));
                txtHorario.setText(rs.getString(4));
                txtChorario.setText(rs.getString(5));
                txtObs.setText(rs.getString(6));
                comboCurso.setSelectedItem(rs.getString(7));
                comboUniversidade.setSelectedItem(rs.getString(8));

                // Para o ComboBox é diferente
            } else {
                JOptionPane.showMessageDialog(null, "Turma não encontrado!");
                txtNome.setText(null);
                txtSala.setText(null);
                txtHorario.setText(null);
                txtChorario.setText(null);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Metodo Editar
    private void editarTurma() {
        String sql = "update turma set nome=?, sala=?, horario=?, carga_horaria=?, obs=?, curso_idcurso=?, universidade_iduniversidade=? where idturma=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtSala.getText());
            pst.setString(3, txtHorario.getText());
            pst.setString(4, txtChorario.getText());
            pst.setString(5, txtObs.getText());
            pst.setString(6, comboCurso.getSelectedItem().toString());
            pst.setString(7, comboUniversidade.getSelectedItem().toString());
            pst.setString(8, txtId.getText());

            // Validando os campos
            if (txtNome.getText().isEmpty() || txtSala.getText().isEmpty() || txtHorario.getText().isEmpty() || txtChorario.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos Obrigatorios!");
            } else {

                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    //System.out.println(adicionado);
                    JOptionPane.showMessageDialog(null, "Turma Alterado com sucesso!");
                    txtNome.setText(null);
                    txtSala.setText(null);
                    txtHorario.setText(null);
                    txtChorario.setText(null);
                }
            }

        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
            txtNome.setText(null);
            txtSala.setText(null);
            txtHorario.setText(null);
            txtChorario.setText(null);
        }
    }

    //Metodo Eliminar
    private void eliminarTurma() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem a certeza que dezeja eliminar!");
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from Turma where idturma=?";

            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtId.getText());

                if (txtId.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha o campo ID!");
                } else {
                    int adicionado = pst.executeUpdate();
                    if (adicionado > 0) {
                        JOptionPane.showMessageDialog(null, "Turma Eliminado com sucesso!");
                        txtNome.setText(null);
                        txtSala.setText(null);
                        txtHorario.setText(null);
                        txtChorario.setText(null);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        txtSala = new javax.swing.JTextField();
        txtHorario = new javax.swing.JTextField();
        txtChorario = new javax.swing.JTextField();
        comboCurso = new javax.swing.JComboBox<>();
        comboUniversidade = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObs = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Formulario Turma");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("ID:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Nome:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Sala:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Horário:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Carga Horária");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("OBS:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Curso:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Universidade:");

        txtId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtNome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtSala.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtHorario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtChorario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        comboCurso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboCurso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        comboUniversidade.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtObs.setColumns(20);
        txtObs.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        txtObs.setRows(5);
        jScrollPane2.setViewportView(txtObs);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Adicionar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Alterar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setText("Pesquisar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton4.setText("Eliminar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(27, 27, 27)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4)
                        .addGap(27, 27, 27)
                        .addComponent(jButton3))
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSala, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtChorario, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboUniversidade, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNome)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtChorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(comboCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(comboUniversidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Chamando O Metodo que Adiciona
        cadastrarTurma();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // Chamando O Metodo que Consulta
        consultarTurma();

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Chamando O Metodo que Edita
        editarTurma();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // Chamando O Metodo que Elimina
        eliminarTurma();
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboCurso;
    private javax.swing.JComboBox<String> comboUniversidade;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtChorario;
    private javax.swing.JTextField txtHorario;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextArea txtObs;
    private javax.swing.JTextField txtSala;
    // End of variables declaration//GEN-END:variables
}
