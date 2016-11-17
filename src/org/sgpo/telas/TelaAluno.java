/**
 * Documentação do Pacote package org.sgpo.telas
 * Neste Pacote constam todos Metodos das Classes Telas de Alunos, Cursos, Universidade, Turmas, Provas, Trabalhos ou Avaliações,
 * e Telas de Consultas ou Listas.
 */
package org.sgpo.telas;

import java.awt.HeadlessException;
import static java.awt.SystemColor.desktop;
import java.sql.*;
import java.util.Locale;
import javax.swing.JOptionPane;
import org.sgpo.dal.ModuloConexao;

/**
 * Documentação da Classe org.sgpo.telas.TelaAluno
 *
 * @Baldina
 * @Fernando
 * @Fábio
 * @Maquinha
 * @Lina
 * @Lidia
 * @Josias
 * @Ernesto
 * @version 1.0 Copyrigt 2016
 */
public final class TelaAluno extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    PreparedStatement pst2 = null;
    ResultSet rs = null;
    ResultSet rs2 = null;

    /**
     * Criando Novo Formulário TelaAluno
     */
    public TelaAluno() {
        initComponents();
        conexao = ModuloConexao.conector();
        this.comboBusca();
        this.comboBusca2();
        this.comboBusca3();
        setaNaText();
    }

    /**
     * @param comboBusca 
     * Este Metódo Busca Dados da Tabela Turma e insere em
     * uma ComoBox(comboTurmaAluno)
     */
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

    /*
    @SetaNaText
    Este Metódo quando é chamado, seta um texto neste caso o nome da turma quando o ID é referenciado numa ComboBox(comboTurmaAluno)
     */
    public void setaNaText() {
        String sql = "select * from turma where idturma = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, comboTurmaAluno.getSelectedItem().toString() + "");
            rs = pst.executeQuery();
            if (rs.next()) {
                txtTurma.setText(rs.getString(2));
                if (comboTurmaAluno.getSelectedItem().toString().isEmpty()) {
                    txtTurma.setText("Não existe Turma na Base de Dados");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /*
    @SetaNaText2
    Este Metódo quando é chamado, seta um texto neste caso o nome da Universidade quando o ID é referenciado numa ComboBox(comboTurmaAluno)
     */
    public void setaNaText2() {
        String sql = "select * from universidade where iduniversidade = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, comboUniversidadeAluno.getSelectedItem().toString() + "");
            rs = pst.executeQuery();
            if (rs.next()) {
                txtUniversidade.setText(rs.getString(2));
                if (comboUniversidadeAluno.getSelectedItem().toString().isEmpty()) {
                    txtTurma.setText("Não existe Universidade na Base de Dados");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /*
    @SetaNaText
    Este Metódo quando é chamado, seta um texto neste caso o nome da Curso quando o ID é referenciado numa ComboBox(comboTurmaAluno)
     */
    public void setaNaText3() {
        String sql = "select * from curso where idcurso = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, comboCurso.getSelectedItem().toString() + "");
            rs = pst.executeQuery();
            if (rs.next()) {
                txtCurso.setText(rs.getString(2));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /*
    *   @ComboBusca2;
    *   Este Metódo Busca Dados da Tabela Turma e insere em uma ComoBox(comboTurmaAluno)
     */
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
        txtTurma = new javax.swing.JTextField();
        txtUniversidade = new javax.swing.JTextField();
        txtCurso = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Formulario de Alunos");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("ID:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 56, -1, -1));

        txtIdAluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(txtIdAluno, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 53, 104, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("*Nome:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 85, -1, -1));

        txtNomeAluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(txtNomeAluno, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 82, 592, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("*Sexo:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 114, -1, -1));

        comboSexoAluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboSexoAluno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "M", "F" }));
        comboSexoAluno.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        comboSexoAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSexoAlunoActionPerformed(evt);
            }
        });
        getContentPane().add(comboSexoAluno, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 111, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("*Data de Nasc.:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 114, -1, -1));

        txtDnAluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(txtDnAluno, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 111, 90, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("*Naturalidade:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(344, 114, -1, -1));

        txtNatAluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(txtNatAluno, new org.netbeans.lib.awtextra.AbsoluteConstraints(436, 111, 252, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("*Morada:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 143, -1, -1));

        txtMoradaAluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(txtMoradaAluno, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 140, 321, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Telefone Alternativo:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(349, 172, -1, -1));

        txtTelAltAluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(txtTelAltAluno, new org.netbeans.lib.awtextra.AbsoluteConstraints(488, 169, 200, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Email:");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 201, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("*Ano Lectivo:");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(533, 143, -1, -1));

        comboAlAluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboAlAluno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032" }));
        comboAlAluno.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(comboAlAluno, new org.netbeans.lib.awtextra.AbsoluteConstraints(628, 140, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("*Turma:");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 227, -1, -1));

        comboTurmaAluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboTurmaAluno.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        comboTurmaAluno.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                comboTurmaAlunoComponentAdded(evt);
            }
        });
        comboTurmaAluno.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboTurmaAlunoItemStateChanged(evt);
            }
        });
        comboTurmaAluno.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                comboTurmaAlunoHierarchyChanged(evt);
            }
        });
        comboTurmaAluno.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                comboTurmaAlunoAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        comboTurmaAluno.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                comboTurmaAlunoMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                comboTurmaAlunoMouseMoved(evt);
            }
        });
        comboTurmaAluno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                comboTurmaAlunoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                comboTurmaAlunoFocusLost(evt);
            }
        });
        comboTurmaAluno.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                comboTurmaAlunoMouseWheelMoved(evt);
            }
        });
        comboTurmaAluno.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
                comboTurmaAlunoAncestorMoved(evt);
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
            }
        });
        comboTurmaAluno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboTurmaAlunoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                comboTurmaAlunoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                comboTurmaAlunoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                comboTurmaAlunoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                comboTurmaAlunoMouseReleased(evt);
            }
        });
        comboTurmaAluno.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                comboTurmaAlunoInputMethodTextChanged(evt);
            }
        });
        comboTurmaAluno.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                comboTurmaAlunoPropertyChange(evt);
            }
        });
        comboTurmaAluno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                comboTurmaAlunoKeyReleased(evt);
            }
        });
        comboTurmaAluno.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                comboTurmaAlunoVetoableChange(evt);
            }
        });
        getContentPane().add(comboTurmaAluno, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 227, 115, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Telefone:");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 172, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("*Ano:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(437, 143, -1, -1));

        comboAcAluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboAcAluno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));
        comboAcAluno.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        comboAcAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboAcAlunoActionPerformed(evt);
            }
        });
        getContentPane().add(comboAcAluno, new org.netbeans.lib.awtextra.AbsoluteConstraints(478, 140, 45, -1));

        txtEmailAluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(txtEmailAluno, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 198, 592, -1));

        txtTelAluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(txtTelAluno, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 169, 234, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("Sobre:");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 326, -1, -1));

        textSobreAluno.setColumns(20);
        textSobreAluno.setRows(5);
        jScrollPane1.setViewportView(textSobreAluno);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 326, 590, 112));

        btnAdicionar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAdicionar.setText("Adicionar");
        btnAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 456, -1, -1));

        btnEliminar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(405, 456, -1, -1));

        btnAlterar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAlterar.setText("Alterar");
        btnAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 456, -1, -1));

        btnpesquisar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnpesquisar.setText("Pesquisar");
        btnpesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnpesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpesquisarActionPerformed(evt);
            }
        });
        getContentPane().add(btnpesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(599, 456, -1, -1));
        getContentPane().add(filler1, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 689, -1, -1));

        comboUniversidadeAluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboUniversidadeAluno.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        comboUniversidadeAluno.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboUniversidadeAlunoItemStateChanged(evt);
            }
        });
        comboUniversidadeAluno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                comboUniversidadeAlunoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                comboUniversidadeAlunoFocusLost(evt);
            }
        });
        comboUniversidadeAluno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboUniversidadeAlunoMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                comboUniversidadeAlunoMouseExited(evt);
            }
        });
        getContentPane().add(comboUniversidadeAluno, new org.netbeans.lib.awtextra.AbsoluteConstraints(341, 227, 115, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("*Universidade:");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(247, 230, -1, -1));

        lblCurso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCurso.setText("*Curso:");
        getContentPane().add(lblCurso, new org.netbeans.lib.awtextra.AbsoluteConstraints(506, 230, -1, -1));

        comboCurso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboCurso.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(comboCurso, new org.netbeans.lib.awtextra.AbsoluteConstraints(573, 227, 115, -1));

        txtTurma.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTurma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTurmaActionPerformed(evt);
            }
        });
        txtTurma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTurmaKeyReleased(evt);
            }
        });
        getContentPane().add(txtTurma, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 256, 115, -1));

        txtUniversidade.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(txtUniversidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(341, 256, 115, -1));

        txtCurso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(txtCurso, new org.netbeans.lib.awtextra.AbsoluteConstraints(573, 256, 115, -1));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Ver");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 290, -1, -1));

        getAccessibleContext().setAccessibleDescription("");

        setSize(new java.awt.Dimension(762, 546));
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

    private void txtTurmaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTurmaKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTurmaKeyReleased

    private void comboTurmaAlunoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboTurmaAlunoKeyReleased
        // TODO add your handling code here:
        setaNaText();
    }//GEN-LAST:event_comboTurmaAlunoKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        setaNaText();
        setaNaText2();
        setaNaText2();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void comboTurmaAlunoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboTurmaAlunoItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_comboTurmaAlunoItemStateChanged

    private void comboTurmaAlunoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_comboTurmaAlunoPropertyChange
        // TODO add your handling code here:

    }//GEN-LAST:event_comboTurmaAlunoPropertyChange

    private void comboTurmaAlunoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboTurmaAlunoMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_comboTurmaAlunoMouseClicked

    private void comboTurmaAlunoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboTurmaAlunoMouseReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_comboTurmaAlunoMouseReleased

    private void comboTurmaAlunoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboTurmaAlunoMouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_comboTurmaAlunoMouseExited

    private void comboTurmaAlunoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboTurmaAlunoMouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_comboTurmaAlunoMouseEntered

    private void comboTurmaAlunoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboTurmaAlunoMousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_comboTurmaAlunoMousePressed

    private void comboTurmaAlunoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboTurmaAlunoMouseMoved
        // TODO add your handling code here:

    }//GEN-LAST:event_comboTurmaAlunoMouseMoved

    private void comboTurmaAlunoMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboTurmaAlunoMouseDragged
        // TODO add your handling code here:

    }//GEN-LAST:event_comboTurmaAlunoMouseDragged

    private void comboTurmaAlunoInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_comboTurmaAlunoInputMethodTextChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_comboTurmaAlunoInputMethodTextChanged

    private void comboTurmaAlunoHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_comboTurmaAlunoHierarchyChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_comboTurmaAlunoHierarchyChanged

    private void comboTurmaAlunoAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_comboTurmaAlunoAncestorAdded
        // TODO add your handling code here:

    }//GEN-LAST:event_comboTurmaAlunoAncestorAdded

    private void comboTurmaAlunoComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_comboTurmaAlunoComponentAdded
        // TODO add your handling code here:

    }//GEN-LAST:event_comboTurmaAlunoComponentAdded

    private void comboTurmaAlunoMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_comboTurmaAlunoMouseWheelMoved
        // TODO add your handling code here:

    }//GEN-LAST:event_comboTurmaAlunoMouseWheelMoved

    private void comboTurmaAlunoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_comboTurmaAlunoFocusGained
        // TODO add your handling code here:

    }//GEN-LAST:event_comboTurmaAlunoFocusGained

    private void comboTurmaAlunoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_comboTurmaAlunoFocusLost
        // TODO add your handling code here:

    }//GEN-LAST:event_comboTurmaAlunoFocusLost

    private void comboTurmaAlunoAncestorMoved(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_comboTurmaAlunoAncestorMoved
        // TODO add your handling code here:

    }//GEN-LAST:event_comboTurmaAlunoAncestorMoved

    private void comboTurmaAlunoVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_comboTurmaAlunoVetoableChange
        // TODO add your handling code here:

    }//GEN-LAST:event_comboTurmaAlunoVetoableChange

    private void txtTurmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTurmaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTurmaActionPerformed

    private void comboUniversidadeAlunoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboUniversidadeAlunoItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_comboUniversidadeAlunoItemStateChanged

    private void comboUniversidadeAlunoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboUniversidadeAlunoMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_comboUniversidadeAlunoMouseClicked

    private void comboUniversidadeAlunoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboUniversidadeAlunoMouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_comboUniversidadeAlunoMouseExited

    private void comboUniversidadeAlunoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_comboUniversidadeAlunoFocusGained
        // TODO add your handling code here:

    }//GEN-LAST:event_comboUniversidadeAlunoFocusGained

    private void comboUniversidadeAlunoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_comboUniversidadeAlunoFocusLost
        // TODO add your handling code here:
        setaNaText2();
    }//GEN-LAST:event_comboUniversidadeAlunoFocusLost


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
    private javax.swing.JTextField txtCurso;
    private javax.swing.JTextField txtDnAluno;
    private javax.swing.JTextField txtEmailAluno;
    private javax.swing.JTextField txtIdAluno;
    private javax.swing.JTextField txtMoradaAluno;
    private javax.swing.JTextField txtNatAluno;
    private javax.swing.JTextField txtNomeAluno;
    private javax.swing.JTextField txtTelAltAluno;
    private javax.swing.JTextField txtTelAluno;
    private javax.swing.JTextField txtTurma;
    private javax.swing.JTextField txtUniversidade;
    // End of variables declaration//GEN-END:variables
}
