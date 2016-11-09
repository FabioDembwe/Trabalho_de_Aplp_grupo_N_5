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
public final class TelaAluno extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    PreparedStatement pst2 = null;
    ResultSet rs = null;
    ResultSet rs2 = null;

    /**
     * Creates new form TelaAluno
     */
    public TelaAluno() {
        initComponents();
        conexao = ModuloConexao.conector();
        this.comboBusca();
        this.comboBusca2();
        this.comboBusca3();
    }

    public void comboBusca() {
        String sql = "select * from turma";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                comboTurmaAluno.addItem(rs.getString(1));
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
                comboUniversidadeAluno.addItem(rs.getString(1));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void comboBusca3() {
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
        String sql = "insert into aluno(nome, sexo, data_nascimento, naturalidade, morada, ano_escolar, ano_lectivo, telefone, telefone_alter, email, obs, turma_idturma, universidade_iduniversidade, curso_idcurso) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNomeAluno.getText());
            pst.setString(2, comboSexoAluno.getSelectedItem().toString());
            pst.setString(3, txtDnAluno.getText());
            pst.setString(4, txtNatAluno.getText());
            pst.setString(5, txtMoradaAluno.getText());
            pst.setString(6, comboAcAluno.getSelectedItem().toString());
            pst.setString(7, comboAlAluno.getSelectedItem().toString());
            pst.setInt(8, txtTelAluno.getText().length());
            pst.setInt(9, txtTelAltAluno.getText().length());
            pst.setString(10, txtEmailAluno.getText());
            pst.setString(11, textSobreAluno.getText());
            pst.setString(12, comboTurmaAluno.getSelectedItem().toString());
            pst.setString(13, comboUniversidadeAluno.getSelectedItem().toString());
            pst.setString(14, comboCurso.getSelectedItem().toString());

            // Validando os campos
            if (txtNomeAluno.getText().isEmpty() || txtDnAluno.getText().isEmpty() || txtNatAluno.getText().isEmpty() || txtMoradaAluno.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos Obrigatorios!");
            } else {

                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    //System.out.println(adicionado);
                    JOptionPane.showMessageDialog(null, "Aluno Cadastrado com sucesso!");
                    txtNomeAluno.setText(null);
                    txtDnAluno.setText(null);
                    txtNatAluno.setText(null);
                    txtMoradaAluno.setText(null);
                    txtTelAluno.setText(null);
                    txtTelAltAluno.setText(null);
                    txtEmailAluno.setText(null);
                    textSobreAluno.setText(null);
                }
            }

        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
            txtNomeAluno.setText(null);
            txtDnAluno.setText(null);
            txtNatAluno.setText(null);
            txtMoradaAluno.setText(null);
            txtTelAluno.setText(null);
            txtTelAltAluno.setText(null);
            txtEmailAluno.setText(null);
            textSobreAluno.setText(null);
        }
    }

    // Metodo Consultar
    private void consultar() {
        String sql = "select * from aluno where idaluno=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdAluno.getText());
            rs = pst.executeQuery();

            if (rs.next()) {
                txtNomeAluno.setText(rs.getString(2));
                comboSexoAluno.setSelectedItem(rs.getString(3));
                txtDnAluno.setText(rs.getString(4));
                txtNatAluno.setText(rs.getString(5));
                txtMoradaAluno.setText(rs.getString(6));
                comboAcAluno.setSelectedItem(rs.getString(7));
                comboAlAluno.setSelectedItem(rs.getString(8));
                txtTelAluno.setText(rs.getString(9));
                txtTelAltAluno.setText(rs.getString(10));
                txtEmailAluno.setText(rs.getString(11));
                textSobreAluno.setText(rs.getString(12));
                comboTurmaAluno.setSelectedItem(rs.getString(13));
                comboUniversidadeAluno.setSelectedItem(rs.getString(14));
                comboCurso.setSelectedItem(rs.getString(15));
                // Para o ComboBox é diferente

            } else {
                JOptionPane.showMessageDialog(null, "Aluno não encontrado!");
                txtNomeAluno.setText(null);
                txtDnAluno.setText(null);
                txtNatAluno.setText(null);
                txtMoradaAluno.setText(null);
                txtTelAluno.setText(null);
                txtTelAltAluno.setText(null);
                txtEmailAluno.setText(null);
                textSobreAluno.setText(null);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Metodo Editar
    private void editar() {
        String sql = "update aluno set nome=?, sexo=?, data_nascimento=?, naturalidade=?, morada=?, ano_escolar=?, ano_lectivo=?, telefone=?, telefone_alter=?, email=?, obs=?, turma_idturma=?, universidade_iduniversidade=?, curso_idcurso=? where idaluno=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNomeAluno.getText());
            pst.setString(2, comboSexoAluno.getSelectedItem().toString());
            pst.setString(3, txtDnAluno.getText());
            pst.setString(4, txtNatAluno.getText());
            pst.setString(5, txtMoradaAluno.getText());
            pst.setString(6, comboAcAluno.getSelectedItem().toString());
            pst.setString(7, comboAlAluno.getSelectedItem().toString());
            pst.setInt(8, txtTelAluno.getText().length());
            pst.setInt(9, txtTelAltAluno.getText().length());
            pst.setString(10, txtEmailAluno.getText());
            pst.setString(11, textSobreAluno.getText());
            pst.setString(12, comboTurmaAluno.getSelectedItem().toString());
            pst.setString(13, comboUniversidadeAluno.getSelectedItem().toString());
            pst.setString(14, comboCurso.getSelectedItem().toString());
            pst.setString(15, txtIdAluno.getText());

            // Validando os campos
            if (txtNomeAluno.getText().isEmpty() || txtDnAluno.getText().isEmpty() || txtNatAluno.getText().isEmpty() || txtMoradaAluno.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos Obrigatorios!");
            } else {

                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    //System.out.println(adicionado);
                    JOptionPane.showMessageDialog(null, "Aluno Alterado com sucesso!");
                    txtNomeAluno.setText(null);
                    txtDnAluno.setText(null);
                    txtNatAluno.setText(null);
                    txtMoradaAluno.setText(null);
                    txtTelAluno.setText(null);
                    txtTelAltAluno.setText(null);
                    txtEmailAluno.setText(null);
                    textSobreAluno.setText(null);
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
            String sql = "delete from aluno where idaluno=?";

            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdAluno.getText());

                if (txtIdAluno.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha o campo ID!");
                } else {
                    int adicionado = pst.executeUpdate();
                    if (adicionado > 0) {
                        JOptionPane.showMessageDialog(null, "Aluno Eliminado com sucesso!");
                        txtNomeAluno.setText(null);
                        txtDnAluno.setText(null);
                        txtNatAluno.setText(null);
                        txtMoradaAluno.setText(null);
                        txtTelAluno.setText(null);
                        txtTelAltAluno.setText(null);
                        txtEmailAluno.setText(null);
                        textSobreAluno.setText(null);
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
        txtIdAluno = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNomeAluno = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        comboSexoAluno = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtDnAluno = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNatAluno = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtMoradaAluno = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTelAltAluno = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        comboAlAluno = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        comboTurmaAluno = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        comboAcAluno = new javax.swing.JComboBox<>();
        txtEmailAluno = new javax.swing.JTextField();
        txtTelAluno = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textSobreAluno = new javax.swing.JTextArea();
        btnAdicionar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnpesquisar = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        comboUniversidadeAluno = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        lblCurso = new javax.swing.JLabel();
        comboCurso = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Formulario de Alunos");
        setPreferredSize(new java.awt.Dimension(800, 600));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("ID:");

        txtIdAluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("*Nome:");

        txtNomeAluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("*Sexo:");

        comboSexoAluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboSexoAluno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "M", "F" }));
        comboSexoAluno.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        comboSexoAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSexoAlunoActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("*Data de Nasc.:");

        txtDnAluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("*Naturalidade:");

        txtNatAluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("*Morada:");

        txtMoradaAluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Telefone Alternativo:");

        txtTelAltAluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Email:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("*Ano Lectivo:");

        comboAlAluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboAlAluno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032" }));
        comboAlAluno.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("*Turma:");

        comboTurmaAluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboTurmaAluno.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Telefone:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("*Ano Curricular:");

        comboAcAluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboAcAluno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));
        comboAcAluno.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        comboAcAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboAcAlunoActionPerformed(evt);
            }
        });

        txtEmailAluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtTelAluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("Sobre:");

        textSobreAluno.setColumns(20);
        textSobreAluno.setRows(5);
        jScrollPane1.setViewportView(textSobreAluno);

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

        comboUniversidadeAluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboUniversidadeAluno.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("*Universidade:");

        lblCurso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCurso.setText("*Curso:");

        comboCurso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboCurso.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jButton1.setText("Ver Todos Alunos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel17)
                            .addComponent(jLabel12)
                            .addComponent(jLabel18)
                            .addComponent(jLabel20)
                            .addComponent(lblCurso)
                            .addComponent(jLabel19))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(comboSexoAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 547, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtDnAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNatAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMoradaAluno)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(comboAcAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel14)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(comboAlAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtTelAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel10)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTelAltAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(comboTurmaAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtEmailAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(comboUniversidadeAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(comboCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jScrollPane1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnAdicionar)
                                        .addGap(55, 55, 55)
                                        .addComponent(btnAlterar)
                                        .addGap(75, 75, 75)
                                        .addComponent(btnEliminar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnpesquisar))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNomeAluno)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtIdAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)))))
                .addGap(55, 55, 55))
            .addGroup(layout.createSequentialGroup()
                .addGap(236, 236, 236)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIdAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomeAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(comboSexoAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDnAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtNatAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtMoradaAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboAcAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)
                        .addComponent(comboAlAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtTelAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtTelAltAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtEmailAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(comboTurmaAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(comboUniversidadeAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCurso)
                    .addComponent(comboCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnpesquisar)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(208, 208, 208)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );

        setSize(new java.awt.Dimension(822, 633));
    }// </editor-fold>//GEN-END:initComponents

    private void comboSexoAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSexoAlunoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboSexoAlunoActionPerformed

    private void comboAcAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboAcAlunoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboAcAlunoActionPerformed

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        TelaListaAluno aluno1 = new TelaListaAluno();
        aluno1.setVisible(true);
        
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnpesquisar;
    private javax.swing.JComboBox<String> comboAcAluno;
    private javax.swing.JComboBox<String> comboAlAluno;
    private javax.swing.JComboBox<String> comboCurso;
    private javax.swing.JComboBox<String> comboSexoAluno;
    private javax.swing.JComboBox<String> comboTurmaAluno;
    private javax.swing.JComboBox<String> comboUniversidadeAluno;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCurso;
    private javax.swing.JTextArea textSobreAluno;
    private javax.swing.JTextField txtDnAluno;
    private javax.swing.JTextField txtEmailAluno;
    private javax.swing.JTextField txtIdAluno;
    private javax.swing.JTextField txtMoradaAluno;
    private javax.swing.JTextField txtNatAluno;
    private javax.swing.JTextField txtNomeAluno;
    private javax.swing.JTextField txtTelAltAluno;
    private javax.swing.JTextField txtTelAluno;
    // End of variables declaration//GEN-END:variables
}
