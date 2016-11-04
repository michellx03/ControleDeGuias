package controledeguias.forms;

import controledeguias.dao.GuiaDao;
import controledeguias.dao.PacienteDao;
import controledeguias.tabelasbanco.Guia;
import controledeguias.tabelasbanco.Paciente;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class JTCadastro extends javax.swing.JFrame {
    
    //Contrutor
    public JTCadastro() {
        initComponents();
        desabilitaDadosPaciente();
        desabilitaDadosGuia();
        setResizable(false);
        setLocationRelativeTo(null);
    }
    
    //PACIENTE
    
    public void alteraPaciente() throws SQLException{
      //Metodo altera paciente
      if(jTCadastroExibePaciente.getSelectedRow() != -1){
          if(validaPaciente()){
              Paciente paciente = new Paciente();
              PacienteDao dao = new PacienteDao();
              
              paciente.setNome(jTCadastroNome.getText());
              paciente.setMatricula_no_convenio(Long.valueOf(jTCadastroMatriculaNoConenio.getText()));
              paciente.setConvenio(jTextFieldConvenio.getText());
              paciente.setIdade(Long.valueOf(jTCadastroIdade.getText()));
              paciente.setId_paciente(Long.valueOf(jTCadastroIdPaciente.getText()));
              
              dao.alteraPaciente(paciente);
              JOptionPane.showMessageDialog(null, "Paciente alterado com sucesso!");              
          }
      }  
    }

    //Define o modelo da tabela e cria o list
    DefaultTableModel tmPaciente = new DefaultTableModel(null, new String[]{"Id", "Nome", "Convênio"});//modelo de tabela criado
    List<Paciente> paciente;//Lista todos os contatos  e manda para o banco
    ListSelectionModel lsmPaciente;// Modelo de seleçao da lista

    public void listarPaciente() throws SQLException {
        //Metodo de pesquisa paciente
        PacienteDao dao = new PacienteDao();

        paciente = dao.getListaP("%" + jTCadastroPesquisaPaciente.getText() + "%");
        mostraPesquisaPaciente(paciente);
    }
     
    private void mostraPesquisaPaciente(List<Paciente> paciente) {
        //Mostra os paciente da lista
        while(tmPaciente.getRowCount()>0){
            tmPaciente.removeRow(0);
        }
        if (paciente.size() == 0) {
            JOptionPane.showMessageDialog(null, "Nehum paciente cadastrado!");
        } else {
            String[] linhaPaciente = new String[]{null, null, null, null};
            for (int i = 0; i < paciente.size(); i++) {
                tmPaciente.addRow(linhaPaciente);
                tmPaciente.setValueAt(paciente.get(i).getId_paciente(), i, 0);
                tmPaciente.setValueAt(paciente.get(i).getNome(), i, 1);
                tmPaciente.setValueAt(paciente.get(i).getConvenio(), i, 2);

            }
        }
    }
     
    private void jTTabelaLinhaSelecionadaPaciente(JTable tabelaPaciente) {
        //Metodo de seleção de linhas Paciente
        if (jTCadastroExibePaciente.getSelectedRow() != -1) {
            habilitaDadosPaciente();
            jTCadastroIdPaciente.setText(String.valueOf(paciente.get(tabelaPaciente.getSelectedRow()).getId_paciente()));
            jTCadastroNome.setText(paciente.get(tabelaPaciente.getSelectedRow()).getNome());
            jTCadastroMatriculaNoConenio.setText(String.valueOf(paciente.get(tabelaPaciente.getSelectedRow()).getMatricula_no_convenio()));
            jTCadastroIdade.setText(String.valueOf(paciente.get(tabelaPaciente.getSelectedRow()).getIdade()));
            jTCadastroIdPacienteGuia.setText(String.valueOf(paciente.get(tabelaPaciente.getSelectedRow()).getId_paciente()));
            jTextFieldConvenio.setText(paciente.get(tabelaPaciente.getSelectedRow()).getConvenio());
        } else {
            jTCadastroIdPaciente.setText(null);
            jTCadastroNome.setText(null);
            jTCadastroMatriculaNoConenio.setText(null);
            jTCadastroIdade.setText(null);
            jTCadastroIdPacienteGuia.setText(null);
            jTextFieldConvenio.setText(null);
        }
    }
     
    public void desabilitaDadosPaciente() {
        // Desabilita dados para nao serem cadastrados
        jTCadastroIdPaciente.setEnabled(false);
        jTCadastroNome.setEnabled(false);
        jTCadastroMatriculaNoConenio.setEnabled(false);
        jTCadastroIdade.setEnabled(false);
        jTextFieldConvenio.setEnabled(false);
    }
     
    public void habilitaDadosPaciente() {
        // Habilita dados para cadastro
        jTCadastroNome.setEnabled(true);
        jTCadastroMatriculaNoConenio.setEnabled(true);
        jTCadastroIdade.setEnabled(true);
        jTextFieldConvenio.setEnabled(true);
    }
    
    public boolean validaPaciente() {
        // Valida os dados paciente
        if (!jTCadastroNome.getText().equals("") && !jTCadastroMatriculaNoConenio.getText().equals("")
                && !jTextFieldConvenio.getText().equals("")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Preencher campo NOME, MATRICULA NO CONVENIO e CONVENIO");
            return false;
        }
    }

    public void cadastroPaciente() {
        try {
            // Salva os pacientes:
            Paciente paciente = new Paciente();

            paciente.setNome(jTCadastroNome.getText());
            paciente.setMatricula_no_convenio(Long.parseLong(jTCadastroMatriculaNoConenio.getText()));
            paciente.setIdade(Long.parseLong(jTCadastroIdade.getText()));
            paciente.setConvenio(jTextFieldConvenio.getText());

            PacienteDao dao = new PacienteDao();
            dao.adicionaPaciente(paciente);
            JOptionPane.showMessageDialog(null, "Paciente cadastrado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar paciente!" + ex);
        }
    }
    
    public void limpaCampoPaciente() {
        //Limpa os campos do paciente
        jTCadastroIdPaciente.setText(null);
        jTCadastroNome.setText(null);
        jTCadastroMatriculaNoConenio.setText(null);
        jTCadastroIdade.setText(null);
        jTextFieldConvenio.setText(null);
    }
    
    public void excluiPaciente() {
    // Excluir paciente
        int resp = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir este paciente?", "Confirm"
                + "ação", JOptionPane.YES_NO_OPTION);
        
        if(resp == JOptionPane.YES_OPTION){
            
            PacienteDao dao;
            try {
                dao = new PacienteDao();
                dao.removePaciente(paciente.get(jTCadastroExibePaciente.getSelectedRow()));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir paciente! " + ex);
            }                 
        }
    } 
    
    public void iniciaListarGuia() throws SQLException {
        //Metodo de pesquisa paciente
        GuiaDao dao = new GuiaDao();

        guia = dao.getListaG(jTCadastroIdPaciente.getText());
}
    
    public boolean validacaoExcluirPaciente() throws SQLException{ 
        iniciaListarGuia();//Iniciar lista da guia antes de validar os dados do paciente
        if (guia.size() != 0) {
            JOptionPane.showMessageDialog(null, "A guias vinculadas a este paciente!");        
            return true;
        }else{
            excluiPaciente();
            return false;
        }
    }
     //GUIA-------------------------------------------------------------------------------------------------------
    
    public void alteraGuia() throws SQLException {
        //Metodo altera guia
        if (jTCadastroExibeGuia.getSelectedRow() != -1) {
            if (validaGuia()) {
                Guia guia = new Guia();
                GuiaDao dao = new GuiaDao();

                guia.setId_guia(Long.valueOf(jTCadastroIdGuia.getText()));
                guia.setNumero_guia_principal(Long.valueOf(jTCadastroNumeroDaGuia.getText()));
                guia.setCirugia(jTCadastroCirurgia.getText());
                guia.setLocalizacao(jTCadastroLocalizacao.getText());
                guia.setObservacao(jTCadastroObservacao.getText());
                guia.setStatus_da_guia(jTCadastroStatusDaGuia.getText());
                guia.setMedico_da_guia(jTCadastroMedicoDaGuia.getText());
                guia.setData_guia(jFormattedTextFieldDataGuia.getText());
                guia.setData_prontuario(jFormattedTextFieldDataProntuario.getText());
                guia.setNumero_do_prontuario(Long.valueOf(jTCadastroNumeroDoProntuario.getText()));
                guia.setId_paciente(Long.valueOf(jTCadastroIdPacienteGuia.getText()));
                
                dao.alteraGuia(guia);
                JOptionPane.showMessageDialog(null, "Guia alterado com sucesso!");
            }
        }
    }
        
    
    //Define o modelo da tabela e cria o list
    DefaultTableModel tmGuia = new DefaultTableModel(null, new String[]{"Cirugia", "Nº Guia", "Localizacao", ""
            + "Data", "Nº Pront.", "Médico"});//modelo de tabela criado
    List<Guia> guia;//Lista todos os contatos  e manda para o banco
    ListSelectionModel lsmGuia;// Modelo de seleçao da lista

    public void listarGuia() throws SQLException {
        //Metodo de pesquisa guia
        GuiaDao dao = new GuiaDao();

        guia = dao.getListaG(jTCadastroIdPaciente.getText());
        mostraPesquisaGuia(guia);
    }
    
    private void mostraPesquisaGuia(List<Guia> guia) {
        //Mostra as guias da lista
        while(tmGuia.getRowCount()>0){
            tmGuia.removeRow(0);
        }
        if (guia.size() == 0) {
            JOptionPane.showMessageDialog(null, "Nehum guia cadastrada!");
        } else {
            String[] linhaGuia = new String[]{null, null, null, null, null, null, null, null, null, null, null};
            for (int i = 0; i < guia.size(); i++) {
                tmGuia.addRow(linhaGuia);
                tmGuia.setValueAt(guia.get(i).getCirugia(), i, 0);
                tmGuia.setValueAt(guia.get(i).getNumero_guia_principal(), i, 1);
                tmGuia.setValueAt(guia.get(i).getLocalizacao(), i, 2);
                tmGuia.setValueAt(guia.get(i).getData_guia(), i, 3);
                tmGuia.setValueAt(guia.get(i).getNumero_do_prontuario(), i, 4);
                tmGuia.setValueAt(guia.get(i).getMedico_da_guia(), i, 5);
            }
        }
    }
    
    private void jTTabelaLinhaSelecionadaGuia(JTable tabelaGuia) {
        //Metodo de seleçao de linhas Guia
        if (jTCadastroExibeGuia.getSelectedRow() != -1) {
            habilitaDadosGuia();
            jTCadastroIdGuia.setText(String.valueOf(guia.get(tabelaGuia.getSelectedRow()).getId_guia()));
            jTCadastroNumeroDaGuia.setText(String.valueOf(guia.get(tabelaGuia.getSelectedRow()).getNumero_guia_principal()));
            jTCadastroCirurgia.setText(guia.get(tabelaGuia.getSelectedRow()).getCirugia());
            jTCadastroLocalizacao.setText(guia.get(tabelaGuia.getSelectedRow()).getLocalizacao());
            jTCadastroObservacao.setText(guia.get(tabelaGuia.getSelectedRow()).getObservacao());
            jTCadastroStatusDaGuia.setText(guia.get(tabelaGuia.getSelectedRow()).getStatus_da_guia());
            jTCadastroMedicoDaGuia.setText(guia.get(tabelaGuia.getSelectedRow()).getMedico_da_guia());
            jFormattedTextFieldDataGuia.setText(guia.get(tabelaGuia.getSelectedRow()).getData_guia());
            jFormattedTextFieldDataProntuario.setText(guia.get(tabelaGuia.getSelectedRow()).getData_prontuario());
            jTCadastroNumeroDoProntuario.setText(String.valueOf(guia.get(tabelaGuia.getSelectedRow()).getNumero_do_prontuario()));
        } else {
            jTCadastroIdGuia.setText(null);
            jTCadastroNumeroDaGuia.setText(null);
            jTCadastroCirurgia.setText(null);
            jTCadastroLocalizacao.setText(null);
            jTCadastroObservacao.setText(null);
            jTCadastroStatusDaGuia.setText(null);
            jTCadastroMedicoDaGuia.setText(null);
            jFormattedTextFieldDataGuia.setText(null);
            jFormattedTextFieldDataProntuario.setText(null);
            jTCadastroNumeroDoProntuario.setText(null);
        }
    }
    
    public void desabilitaDadosGuia() {
        //Desabilita dados para nao serem cadastrados
        jTCadastroIdGuia.setEnabled(false);
        jTCadastroNumeroDaGuia.setEnabled(false);
        jTCadastroCirurgia.setEnabled(false);
        jTCadastroLocalizacao.setEnabled(false);
        jTCadastroObservacao.setEnabled(false);
        jTCadastroStatusDaGuia.setEnabled(false);
        jTCadastroMedicoDaGuia.setEnabled(false);
        jFormattedTextFieldDataGuia.setEnabled(false);
        jFormattedTextFieldDataProntuario.setEnabled(false);
        jTCadastroNumeroDoProntuario.setEnabled(false);
        jTCadastroIdPacienteGuia.setEnabled(false);
    }
    
    public void habilitaDadosGuia() {
        //Habiita dados para cadastro
        jTCadastroNumeroDaGuia.setEnabled(true);
        jTCadastroCirurgia.setEnabled(true);
        jTCadastroLocalizacao.setEnabled(true);
        jTCadastroObservacao.setEnabled(true);
        jTCadastroStatusDaGuia.setEnabled(true);
        jTCadastroMedicoDaGuia.setEnabled(true);
        jFormattedTextFieldDataGuia.setEnabled(true);
        jFormattedTextFieldDataProntuario.setEnabled(true);
        jTCadastroNumeroDoProntuario.setEnabled(true);
    }
    
    public boolean validaGuia() {
        // Valida dados da guia
        if (!jTCadastroNumeroDaGuia.getText().equals("") && !jTCadastroIdPacienteGuia.getText().equals("") && !jTCadastroNumeroDoProntuario.getText().equals("")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Preencher campo NUMERO DA GUIA, ID_PACIENTE e NUMERO DO PRONTUARIO");
            return false;
        }
    }    
    
    public void cadastroGuia(){
        try {
            // Salva as guias:
            Guia guia = new Guia();
            
            guia.setNumero_guia_principal(Long.parseLong(jTCadastroNumeroDaGuia.getText()));
            guia.setCirugia(jTCadastroCirurgia.getText());
            guia.setLocalizacao(jTCadastroLocalizacao.getText());
            guia.setObservacao(jTCadastroObservacao.getText());
            guia.setStatus_da_guia(jTCadastroStatusDaGuia.getText());
            guia.setMedico_da_guia(jTCadastroMedicoDaGuia.getText());
            guia.setData_guia(jFormattedTextFieldDataGuia.getText());
            guia.setData_prontuario(jFormattedTextFieldDataProntuario.getText());
            guia.setNumero_do_prontuario(Long.parseLong(jTCadastroNumeroDoProntuario.getText()));
            guia.setId_paciente(Long.parseLong(jTCadastroIdPacienteGuia.getText()));
            
            GuiaDao dao = new GuiaDao();
            dao.adicionaGuia(guia);
            JOptionPane.showMessageDialog(null, "Guia cadastrada com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao cadastrar guia!" + ex);
        }
    }
    
    public void limpaCampoGuia() {
        //Limpa os campos da guia
        jTCadastroIdGuia.setText(null);
        jTCadastroNumeroDaGuia.setText(null);
        jTCadastroCirurgia.setText(null);
        jTCadastroLocalizacao.setText(null);
        jTCadastroObservacao.setText(null);
        jTCadastroStatusDaGuia.setText(null);
        jTCadastroMedicoDaGuia.setText(null);
        jFormattedTextFieldDataGuia.setText(null);
        jFormattedTextFieldDataProntuario.setText(null);
        jTCadastroNumeroDoProntuario.setText(null);
        //jTCadastroIdPacienteGuia.setText(null);
    }
    
    public void excluiGuia() {
    // Excluir guia
        int resp = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir este guia?", "Confirm"
                + "ação", JOptionPane.YES_NO_OPTION);
        
        if(resp == JOptionPane.YES_OPTION){
            
            try {
                GuiaDao dao = new GuiaDao();
                dao.removeGuia(guia.get(jTCadastroExibeGuia.getSelectedRow()));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir guia! " + ex);
            }                 
        }
    } 

    //CODIGO GERADO PELO NETBEANS - CODIGO DOS BOTÕES
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jPCadastroPaciente = new javax.swing.JPanel();
        jLCadastroIdPaciente = new javax.swing.JLabel();
        jLCadastroNome = new javax.swing.JLabel();
        jLCadastroMatricula = new javax.swing.JLabel();
        jLCadastroIdade = new javax.swing.JLabel();
        jTCadastroIdPaciente = new javax.swing.JTextField();
        jTCadastroNome = new javax.swing.JTextField();
        jTCadastroMatriculaNoConenio = new javax.swing.JTextField();
        jTCadastroIdade = new javax.swing.JTextField();
        jLabelConvenio = new javax.swing.JLabel();
        jTextFieldConvenio = new javax.swing.JTextField();
        jPCadastroGuia = new javax.swing.JPanel();
        jLCadastroIdGuia = new javax.swing.JLabel();
        jLCadastroNumeroGuia = new javax.swing.JLabel();
        jLCadastroCirurgia = new javax.swing.JLabel();
        jLCadastroLocalizacao = new javax.swing.JLabel();
        jLCadastroObservacao = new javax.swing.JLabel();
        jLCadastroStatusDaGuia = new javax.swing.JLabel();
        jLCadastroMedicoDaGuia = new javax.swing.JLabel();
        jLCadastroDataDaGuia = new javax.swing.JLabel();
        jLCadastroDataDoProntuario = new javax.swing.JLabel();
        jLCadastroNumeroDoProntuario = new javax.swing.JLabel();
        jLCadastraIdPacienteGuia = new javax.swing.JLabel();
        jTCadastroIdGuia = new javax.swing.JTextField();
        jTCadastroNumeroDaGuia = new javax.swing.JTextField();
        jTCadastroCirurgia = new javax.swing.JTextField();
        jTCadastroLocalizacao = new javax.swing.JTextField();
        jTCadastroObservacao = new javax.swing.JTextField();
        jTCadastroStatusDaGuia = new javax.swing.JTextField();
        jTCadastroMedicoDaGuia = new javax.swing.JTextField();
        jTCadastroNumeroDoProntuario = new javax.swing.JTextField();
        jTCadastroIdPacienteGuia = new javax.swing.JTextField();
        jFormattedTextFieldDataGuia = new javax.swing.JFormattedTextField();
        jFormattedTextFieldDataProntuario = new javax.swing.JFormattedTextField();
        jPCadastroBotoesPaciente = new javax.swing.JPanel();
        jBCadastroSalvaPaciente = new javax.swing.JButton();
        jBCadastroLimpaCampoPaciente = new javax.swing.JButton();
        jBCadastroAlteraPaciente = new javax.swing.JButton();
        jBCadastroExcluiPaciente = new javax.swing.JButton();
        jBCadastroNovoPaciente = new javax.swing.JButton();
        jPCadastroBotoesGuia = new javax.swing.JPanel();
        jBCadastroSalvaGuia = new javax.swing.JButton();
        jBCadastroLimpaCampoGuia = new javax.swing.JButton();
        jBCadastroExcluiGuia = new javax.swing.JButton();
        jBCadastroAlteraGuia = new javax.swing.JButton();
        jBCadastroNovaGuia = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTCadastroExibePaciente = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTCadastroExibeGuia = new javax.swing.JTable();
        jTCadastroPesquisaPaciente = new javax.swing.JTextField();
        jLCadastroPesquisaPaciente = new javax.swing.JLabel();
        jBCadastroPesquisaPaciente = new javax.swing.JButton();
        jBCadastroPesquisaGuia = new javax.swing.JButton();
        jBcadastroSair = new javax.swing.JButton();

        jLabel5.setText("jLabel5");

        jTextField5.setText("jTextField5");

        jTextField13.setText("jTextField13");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Controle de guias");

        jPCadastroPaciente.setBorder(javax.swing.BorderFactory.createTitledBorder("Cadastro Paciente"));

        jLCadastroIdPaciente.setText("Id paciiente: ");

        jLCadastroNome.setText("Nome: ");

        jLCadastroMatricula.setText("Matricula no convenio: ");

        jLCadastroIdade.setText("Idade: ");

        jTCadastroIdPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTCadastroIdPacienteActionPerformed(evt);
            }
        });

        jLabelConvenio.setText("Convênio");

        javax.swing.GroupLayout jPCadastroPacienteLayout = new javax.swing.GroupLayout(jPCadastroPaciente);
        jPCadastroPaciente.setLayout(jPCadastroPacienteLayout);
        jPCadastroPacienteLayout.setHorizontalGroup(
            jPCadastroPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCadastroPacienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPCadastroPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPCadastroPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPCadastroPacienteLayout.createSequentialGroup()
                            .addComponent(jLCadastroIdPaciente)
                            .addGap(66, 66, 66))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPCadastroPacienteLayout.createSequentialGroup()
                            .addComponent(jLCadastroMatricula)
                            .addGap(18, 18, 18)))
                    .addGroup(jPCadastroPacienteLayout.createSequentialGroup()
                        .addGroup(jPCadastroPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLCadastroNome)
                            .addComponent(jLCadastroIdade)
                            .addComponent(jLabelConvenio))
                        .addGap(84, 84, 84)))
                .addGroup(jPCadastroPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTCadastroIdPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTCadastroNome, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTCadastroIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTCadastroMatriculaNoConenio, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(267, Short.MAX_VALUE))
        );
        jPCadastroPacienteLayout.setVerticalGroup(
            jPCadastroPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCadastroPacienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPCadastroPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLCadastroIdPaciente)
                    .addComponent(jTCadastroIdPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPCadastroPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLCadastroNome)
                    .addComponent(jTCadastroNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPCadastroPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLCadastroMatricula)
                    .addComponent(jTCadastroMatriculaNoConenio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPCadastroPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLCadastroIdade)
                    .addComponent(jTCadastroIdade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPCadastroPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelConvenio)
                    .addComponent(jTextFieldConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPCadastroGuia.setBorder(javax.swing.BorderFactory.createTitledBorder("Cadastro Guias"));

        jLCadastroIdGuia.setText("Id guia: ");

        jLCadastroNumeroGuia.setText("Numero da guia: ");

        jLCadastroCirurgia.setText("Cirugia: ");

        jLCadastroLocalizacao.setText("Localização: ");

        jLCadastroObservacao.setText("Observação: ");

        jLCadastroStatusDaGuia.setText("Status da guia: ");

        jLCadastroMedicoDaGuia.setText("Médico da guia: ");

        jLCadastroDataDaGuia.setText("Data da guia: ");

        jLCadastroDataDoProntuario.setText("Data do prontuario: ");

        jLCadastroNumeroDoProntuario.setText("Numero do prontuario: ");

        jLCadastraIdPacienteGuia.setText("Id paciente: ");

        try {
            jFormattedTextFieldDataGuia.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            jFormattedTextFieldDataProntuario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPCadastroGuiaLayout = new javax.swing.GroupLayout(jPCadastroGuia);
        jPCadastroGuia.setLayout(jPCadastroGuiaLayout);
        jPCadastroGuiaLayout.setHorizontalGroup(
            jPCadastroGuiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCadastroGuiaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPCadastroGuiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLCadastroObservacao)
                    .addComponent(jLCadastroLocalizacao)
                    .addComponent(jLCadastroCirurgia)
                    .addComponent(jLCadastroNumeroGuia)
                    .addComponent(jLCadastroIdGuia)
                    .addComponent(jLCadastroStatusDaGuia))
                .addGap(34, 34, 34)
                .addGroup(jPCadastroGuiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPCadastroGuiaLayout.createSequentialGroup()
                        .addGroup(jPCadastroGuiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTCadastroIdGuia, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTCadastroNumeroDaGuia, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTCadastroLocalizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTCadastroObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTCadastroCirurgia, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addGroup(jPCadastroGuiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPCadastroGuiaLayout.createSequentialGroup()
                                .addGroup(jPCadastroGuiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLCadastraIdPacienteGuia, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLCadastroNumeroDoProntuario))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPCadastroGuiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTCadastroIdPacienteGuia, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTCadastroNumeroDoProntuario, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPCadastroGuiaLayout.createSequentialGroup()
                                .addGroup(jPCadastroGuiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLCadastroMedicoDaGuia)
                                    .addComponent(jLCadastroDataDaGuia)
                                    .addComponent(jLCadastroDataDoProntuario))
                                .addGap(18, 18, 18)
                                .addGroup(jPCadastroGuiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTCadastroMedicoDaGuia, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPCadastroGuiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jFormattedTextFieldDataProntuario, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jFormattedTextFieldDataGuia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))))))
                    .addGroup(jPCadastroGuiaLayout.createSequentialGroup()
                        .addComponent(jTCadastroStatusDaGuia, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPCadastroGuiaLayout.setVerticalGroup(
            jPCadastroGuiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCadastroGuiaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPCadastroGuiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPCadastroGuiaLayout.createSequentialGroup()
                        .addGroup(jPCadastroGuiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLCadastroIdGuia)
                            .addComponent(jTCadastroIdGuia))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPCadastroGuiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLCadastroNumeroGuia)
                            .addComponent(jTCadastroNumeroDaGuia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPCadastroGuiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLCadastroCirurgia)
                            .addComponent(jTCadastroCirurgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPCadastroGuiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLCadastroLocalizacao)
                            .addComponent(jTCadastroLocalizacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPCadastroGuiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLCadastroObservacao)
                            .addComponent(jTCadastroObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPCadastroGuiaLayout.createSequentialGroup()
                        .addGroup(jPCadastroGuiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLCadastroMedicoDaGuia)
                            .addComponent(jTCadastroMedicoDaGuia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPCadastroGuiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLCadastroDataDaGuia)
                            .addComponent(jFormattedTextFieldDataGuia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPCadastroGuiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLCadastroDataDoProntuario)
                            .addComponent(jFormattedTextFieldDataProntuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPCadastroGuiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLCadastroNumeroDoProntuario)
                            .addComponent(jTCadastroNumeroDoProntuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPCadastroGuiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLCadastraIdPacienteGuia)
                            .addComponent(jTCadastroIdPacienteGuia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPCadastroGuiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTCadastroStatusDaGuia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLCadastroStatusDaGuia))
                .addContainerGap())
        );

        jBCadastroSalvaPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imangens/user_add.png"))); // NOI18N
        jBCadastroSalvaPaciente.setText("Salva paciente");
        jBCadastroSalvaPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCadastroSalvaPacienteActionPerformed(evt);
            }
        });

        jBCadastroLimpaCampoPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imangens/user.png"))); // NOI18N
        jBCadastroLimpaCampoPaciente.setText("Limpa campo");
        jBCadastroLimpaCampoPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCadastroLimpaCampoPacienteActionPerformed(evt);
            }
        });

        jBCadastroAlteraPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imangens/user_edit.png"))); // NOI18N
        jBCadastroAlteraPaciente.setText("Altera paciente");
        jBCadastroAlteraPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCadastroAlteraPacienteActionPerformed(evt);
            }
        });

        jBCadastroExcluiPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imangens/user_delete.png"))); // NOI18N
        jBCadastroExcluiPaciente.setText("Exclui paciente");
        jBCadastroExcluiPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCadastroExcluiPacienteActionPerformed(evt);
            }
        });

        jBCadastroNovoPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imangens/add.png"))); // NOI18N
        jBCadastroNovoPaciente.setText("Novo Paciente");
        jBCadastroNovoPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCadastroNovoPacienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPCadastroBotoesPacienteLayout = new javax.swing.GroupLayout(jPCadastroBotoesPaciente);
        jPCadastroBotoesPaciente.setLayout(jPCadastroBotoesPacienteLayout);
        jPCadastroBotoesPacienteLayout.setHorizontalGroup(
            jPCadastroBotoesPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCadastroBotoesPacienteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBCadastroSalvaPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBCadastroExcluiPaciente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBCadastroAlteraPaciente)
                .addGap(10, 10, 10)
                .addComponent(jBCadastroLimpaCampoPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jBCadastroNovoPaciente)
                .addGap(44, 44, 44))
        );
        jPCadastroBotoesPacienteLayout.setVerticalGroup(
            jPCadastroBotoesPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPCadastroBotoesPacienteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPCadastroBotoesPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBCadastroSalvaPaciente)
                    .addComponent(jBCadastroLimpaCampoPaciente)
                    .addComponent(jBCadastroAlteraPaciente)
                    .addComponent(jBCadastroExcluiPaciente)
                    .addComponent(jBCadastroNovoPaciente))
                .addContainerGap())
        );

        jBCadastroSalvaGuia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imangens/table_add.png"))); // NOI18N
        jBCadastroSalvaGuia.setText("Salva guia");
        jBCadastroSalvaGuia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCadastroSalvaGuiaActionPerformed(evt);
            }
        });

        jBCadastroLimpaCampoGuia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imangens/table.png"))); // NOI18N
        jBCadastroLimpaCampoGuia.setText("Limpa campo guia");
        jBCadastroLimpaCampoGuia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCadastroLimpaCampoGuiaActionPerformed(evt);
            }
        });

        jBCadastroExcluiGuia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imangens/table_delete.png"))); // NOI18N
        jBCadastroExcluiGuia.setText("Exclui guia");
        jBCadastroExcluiGuia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCadastroExcluiGuiaActionPerformed(evt);
            }
        });

        jBCadastroAlteraGuia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imangens/table_edit.png"))); // NOI18N
        jBCadastroAlteraGuia.setText("Altera guia");
        jBCadastroAlteraGuia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCadastroAlteraGuiaActionPerformed(evt);
            }
        });

        jBCadastroNovaGuia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imangens/add.png"))); // NOI18N
        jBCadastroNovaGuia.setText("Nova guia");
        jBCadastroNovaGuia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCadastroNovaGuiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPCadastroBotoesGuiaLayout = new javax.swing.GroupLayout(jPCadastroBotoesGuia);
        jPCadastroBotoesGuia.setLayout(jPCadastroBotoesGuiaLayout);
        jPCadastroBotoesGuiaLayout.setHorizontalGroup(
            jPCadastroBotoesGuiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCadastroBotoesGuiaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBCadastroSalvaGuia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBCadastroExcluiGuia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBCadastroAlteraGuia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBCadastroLimpaCampoGuia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                .addComponent(jBCadastroNovaGuia)
                .addContainerGap())
        );
        jPCadastroBotoesGuiaLayout.setVerticalGroup(
            jPCadastroBotoesGuiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCadastroBotoesGuiaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPCadastroBotoesGuiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBCadastroSalvaGuia)
                    .addComponent(jBCadastroLimpaCampoGuia)
                    .addComponent(jBCadastroExcluiGuia)
                    .addComponent(jBCadastroAlteraGuia)
                    .addComponent(jBCadastroNovaGuia))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTCadastroExibePaciente.setModel(tmPaciente);
        jTCadastroExibePaciente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lsmPaciente = jTCadastroExibePaciente.getSelectionModel();
        lsmPaciente.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e){
                if (! e.getValueIsAdjusting()){
                    jTTabelaLinhaSelecionadaPaciente(jTCadastroExibePaciente);
                }
            }
        });
        jScrollPane1.setViewportView(jTCadastroExibePaciente);

        jTCadastroExibeGuia.setModel(tmGuia);
        jTCadastroExibeGuia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lsmGuia = jTCadastroExibeGuia.getSelectionModel();
        lsmGuia.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e){
                if (! e.getValueIsAdjusting()){
                    jTTabelaLinhaSelecionadaGuia(jTCadastroExibeGuia);
                }
            }
        });
        jScrollPane2.setViewportView(jTCadastroExibeGuia);

        jLCadastroPesquisaPaciente.setText("Pesquisa: ");

        jBCadastroPesquisaPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imangens/zoom.png"))); // NOI18N
        jBCadastroPesquisaPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCadastroPesquisaPacienteActionPerformed(evt);
            }
        });

        jBCadastroPesquisaGuia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imangens/table_go.png"))); // NOI18N
        jBCadastroPesquisaGuia.setText("Pesquisa guia do paciente");
        jBCadastroPesquisaGuia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCadastroPesquisaGuiaActionPerformed(evt);
            }
        });

        jBcadastroSair.setFont(new java.awt.Font("Arial Black", 0, 11)); // NOI18N
        jBcadastroSair.setForeground(new java.awt.Color(204, 0, 0));
        jBcadastroSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imangens/door_in.png"))); // NOI18N
        jBcadastroSair.setText("Sair");
        jBcadastroSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcadastroSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPCadastroBotoesGuia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPCadastroPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPCadastroBotoesPaciente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 710, Short.MAX_VALUE)
                        .addComponent(jPCadastroGuia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jBcadastroSair, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLCadastroPesquisaPaciente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTCadastroPesquisaPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                        .addComponent(jBCadastroPesquisaPaciente)
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jBCadastroPesquisaGuia)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLCadastroPesquisaPaciente)
                                .addComponent(jTCadastroPesquisaPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jBCadastroPesquisaPaciente)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBCadastroPesquisaGuia)
                        .addGap(5, 5, 5))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPCadastroPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPCadastroBotoesPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPCadastroGuia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPCadastroBotoesGuia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jBcadastroSair)))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1216, 571));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTCadastroIdPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTCadastroIdPacienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTCadastroIdPacienteActionPerformed

    private void jBCadastroNovaGuiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCadastroNovaGuiaActionPerformed
        habilitaDadosGuia();
    }//GEN-LAST:event_jBCadastroNovaGuiaActionPerformed

    private void jBCadastroLimpaCampoPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCadastroLimpaCampoPacienteActionPerformed
        limpaCampoPaciente();
        try {
            listarPaciente();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao limpar! = " + ex);
        }

    }//GEN-LAST:event_jBCadastroLimpaCampoPacienteActionPerformed

    private void jBCadastroLimpaCampoGuiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCadastroLimpaCampoGuiaActionPerformed
        limpaCampoGuia();
        try {
            listarGuia();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao limpar! = " + ex);
        }

    }//GEN-LAST:event_jBCadastroLimpaCampoGuiaActionPerformed

    private void jBCadastroSalvaPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCadastroSalvaPacienteActionPerformed
        if (validaPaciente()) {
            cadastroPaciente();
            desabilitaDadosPaciente();
            try {
                listarPaciente();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar! = " + ex);
            }
        }
    }//GEN-LAST:event_jBCadastroSalvaPacienteActionPerformed

    private void jBCadastroSalvaGuiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCadastroSalvaGuiaActionPerformed
        if (validaGuia()) {
            cadastroGuia();
            desabilitaDadosGuia();
            try {
                listarGuia();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Erro ao cadastrar! = " + ex);
            }
        }
    }//GEN-LAST:event_jBCadastroSalvaGuiaActionPerformed

    private void jBCadastroNovoPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCadastroNovoPacienteActionPerformed
        habilitaDadosPaciente();
    }//GEN-LAST:event_jBCadastroNovoPacienteActionPerformed

    private void jBCadastroPesquisaPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCadastroPesquisaPacienteActionPerformed
        try {
            listarPaciente();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar paciente! " + ex);
        }
    }//GEN-LAST:event_jBCadastroPesquisaPacienteActionPerformed

    private void jBCadastroPesquisaGuiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCadastroPesquisaGuiaActionPerformed
        try {
            listarGuia();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar  guia! " + ex);
        }
    }//GEN-LAST:event_jBCadastroPesquisaGuiaActionPerformed

    private void jBCadastroExcluiPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCadastroExcluiPacienteActionPerformed
        try {
            if (validacaoExcluirPaciente() == true){
                listarPaciente();
            }else{
                JOptionPane.showMessageDialog(null, "Paciente excluido com sucesso! ");
                listarPaciente();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro Validaçao Paciente! " + ex);
        }
    }//GEN-LAST:event_jBCadastroExcluiPacienteActionPerformed

    private void jBCadastroExcluiGuiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCadastroExcluiGuiaActionPerformed
            excluiGuia();
        try {
            listarGuia();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar guia! " + ex);
        }
    }//GEN-LAST:event_jBCadastroExcluiGuiaActionPerformed

    private void jBCadastroAlteraPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCadastroAlteraPacienteActionPerformed
        try {
            alteraPaciente();
            listarPaciente();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar paciente! " + ex);
        }
    }//GEN-LAST:event_jBCadastroAlteraPacienteActionPerformed

    private void jBCadastroAlteraGuiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCadastroAlteraGuiaActionPerformed
        try {
            alteraGuia();
            listarGuia();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar guia! " + ex);
        }
    }//GEN-LAST:event_jBCadastroAlteraGuiaActionPerformed

    private void jBcadastroSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcadastroSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jBcadastroSairActionPerformed

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
            java.util.logging.Logger.getLogger(JTCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JTCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JTCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JTCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JTCadastro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBCadastroAlteraGuia;
    private javax.swing.JButton jBCadastroAlteraPaciente;
    private javax.swing.JButton jBCadastroExcluiGuia;
    private javax.swing.JButton jBCadastroExcluiPaciente;
    private javax.swing.JButton jBCadastroLimpaCampoGuia;
    private javax.swing.JButton jBCadastroLimpaCampoPaciente;
    private javax.swing.JButton jBCadastroNovaGuia;
    private javax.swing.JButton jBCadastroNovoPaciente;
    private javax.swing.JButton jBCadastroPesquisaGuia;
    private javax.swing.JButton jBCadastroPesquisaPaciente;
    private javax.swing.JButton jBCadastroSalvaGuia;
    private javax.swing.JButton jBCadastroSalvaPaciente;
    private javax.swing.JButton jBcadastroSair;
    private javax.swing.JFormattedTextField jFormattedTextFieldDataGuia;
    private javax.swing.JFormattedTextField jFormattedTextFieldDataProntuario;
    private javax.swing.JLabel jLCadastraIdPacienteGuia;
    private javax.swing.JLabel jLCadastroCirurgia;
    private javax.swing.JLabel jLCadastroDataDaGuia;
    private javax.swing.JLabel jLCadastroDataDoProntuario;
    private javax.swing.JLabel jLCadastroIdGuia;
    private javax.swing.JLabel jLCadastroIdPaciente;
    private javax.swing.JLabel jLCadastroIdade;
    private javax.swing.JLabel jLCadastroLocalizacao;
    private javax.swing.JLabel jLCadastroMatricula;
    private javax.swing.JLabel jLCadastroMedicoDaGuia;
    private javax.swing.JLabel jLCadastroNome;
    private javax.swing.JLabel jLCadastroNumeroDoProntuario;
    private javax.swing.JLabel jLCadastroNumeroGuia;
    private javax.swing.JLabel jLCadastroObservacao;
    private javax.swing.JLabel jLCadastroPesquisaPaciente;
    private javax.swing.JLabel jLCadastroStatusDaGuia;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelConvenio;
    private javax.swing.JPanel jPCadastroBotoesGuia;
    private javax.swing.JPanel jPCadastroBotoesPaciente;
    private javax.swing.JPanel jPCadastroGuia;
    private javax.swing.JPanel jPCadastroPaciente;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTCadastroCirurgia;
    private javax.swing.JTable jTCadastroExibeGuia;
    private javax.swing.JTable jTCadastroExibePaciente;
    private javax.swing.JTextField jTCadastroIdGuia;
    private javax.swing.JTextField jTCadastroIdPaciente;
    private javax.swing.JTextField jTCadastroIdPacienteGuia;
    private javax.swing.JTextField jTCadastroIdade;
    private javax.swing.JTextField jTCadastroLocalizacao;
    private javax.swing.JTextField jTCadastroMatriculaNoConenio;
    private javax.swing.JTextField jTCadastroMedicoDaGuia;
    private javax.swing.JTextField jTCadastroNome;
    private javax.swing.JTextField jTCadastroNumeroDaGuia;
    private javax.swing.JTextField jTCadastroNumeroDoProntuario;
    private javax.swing.JTextField jTCadastroObservacao;
    private javax.swing.JTextField jTCadastroPesquisaPaciente;
    private javax.swing.JTextField jTCadastroStatusDaGuia;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextFieldConvenio;
    // End of variables declaration//GEN-END:variables

    
}
