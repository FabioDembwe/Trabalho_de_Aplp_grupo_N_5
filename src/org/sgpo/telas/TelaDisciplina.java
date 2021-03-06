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
public final class TelaDisciplina extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    PreparedStatement pst2 = null;
    ResultSet rs = null;
    ResultSet rs2 = null;

    /**
     * Creates new form TelaAluno
     */
    public TelaDisciplina() {
        initComponents();
        conexao = ModuloConexao.conector();
        this.comboBusca();

    }

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

    // Método Adicionar
    private void cadastrar() {
        String sql = "insert into disciplina(nome, obs, curso_idcurso) values(?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNomeDisciplina.getText());
            pst.setString(2, textSobre.getText());
            pst.setString(3, comboCurso.getSelectedItem().toString());

            // Validando os campos
            if (txtNomeDisciplina.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o campo Nome!");
            } else {

                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    //System.out.println(adicionado);
                    JOptionPane.showMessageDialog(null, "Disciplina Cadastrado com sucesso!");
                    txtNomeDisciplina.setText(null);
                    textSobre.setText(null);
                }
            }

        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
            txtNomeDisciplina.setText(null);
            textSobre.setText(null);
        }
    }

    // Metodo Consultar
    private void consultar() {
        String sql = "select * from disciplina where iddisciplina=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdDisciplina.getText());
            rs = pst.executeQuery();

            if (rs.next()) {
                txtNomeDisciplina.setText(rs.getString(2));
                textSobre.setText(rs.getString(3));
                comboCurso.setSelectedItem(rs.getString(4));
                // Para o ComboBox é diferente

            } else {
                JOptionPane.showMessageDialog(null, "Disciplina não encontrado!");
                txtNomeDisciplina.setText(null);
                textSobre.setText(null);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Metodo Editar
    private void editar() {
        String sql = "update disciplina set nome=?, obs=?, curso_idcurso=? where iddisciplina=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNomeDisciplina.getText());
            pst.setString(2, textSobre.getText());
            pst.setString(3, comboCurso.getSelectedItem().toString());
            pst.setString(4, txtIdDisciplina.getText());

            // Validando os campos
            if (txtNomeDisciplina.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o campo Nome!");
            } else {

                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    //System.out.println(adicionado);
                    JOptionPane.showMessageDialog(null, "Disciplina Alterado com sucesso!");
                    txtNomeDisciplina.setText(null);
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
            String sql = "delete from disciplina where iddisciplina=?";

            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdDisciplina.getText());

                if (txtIdDisciplina.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha o campo ID!");
                } else {
                    int adicionado = pst.executeUpdate();
                    if (adicionado > 0) {
                        JOptionPane.showMessageDialog(null, "Disciplina Eliminado com sucesso!");
                        txtNomeDisciplina.setText(null);
                        textSobre.setText(null);
                    }
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Impossível apagar Disciplina, Verifique se está sendo usada ou conecte a Base de Dados \n " + e);
                txtNomeDisciplina.setText(null);
                textSobre.setText(null);
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
        txtIdDisciplina = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNomeDisciplina = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textSobre = new javax.swing.JTextArea();
        btnAdicionar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnpesquisar = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        lblCurso = new javax.swing.JLabel();
        comboCurso = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setTitle("Formulario de Alunos");
        setPreferredSize(new java.awt.Dimension(800, 600));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("ID:");

        txtIdDisciplina.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("*Nome:");

        txtNomeDisciplina.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

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

        lblCurso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCurso.setText("*Curso:");

        comboCurso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboCurso.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

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
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(btnAdicionar)
                                .addGap(55, 55, 55)
                                .addComponent(btnAlterar)
                                .addGap(50, 50, 50)
                                .addComponent(btnEliminar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                                .addComponent(btnpesquisar))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(lblCurso)
                                    .addComponent(jLabel19))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtIdDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNomeDisciplina)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE))))))
                .addContainerGap(122, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIdDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomeDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCurso)
                    .addComponent(comboCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnpesquisar)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(458, 458, 458)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );

        setSize(new java.awt.Dimension(701, 370));
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
    private javax.swing.JComboBox<String> comboCurso;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCurso;
    private javax.swing.JTextArea textSobre;
    private javax.swing.JTextField txtIdDisciplina;
    private javax.swing.JTextField txtNomeDisciplina;
    // End of variables declaration//GEN-END:variables
}
