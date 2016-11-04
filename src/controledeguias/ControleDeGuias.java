package controledeguias;

import controledeguias.dao.GuiaDao;
import controledeguias.dao.PacienteDao;
import controledeguias.forms.JTCadastro;
import controledeguias.tabelasbanco.Guia;
import controledeguias.tabelasbanco.Paciente;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControleDeGuias {

    public static void main(String[] args) throws SQLException {
        
        JTCadastro t = new JTCadastro();
        t.setVisible(true);
        
        //REMOVE GUIA
        /*
         Guia guia = new Guia();
        
         guia.setId_guia(Long.valueOf(2));
        
         GuiaDao dao = new GuiaDao();
         dao.removeGuia(guia);
         */

        //--------------------------------------------------------------------------
        //REMOVE PACIENTE
        /*
         Paciente paciente = new Paciente();
       
         paciente.setId_paciente(Long.valueOf(5));
         
         PacienteDao dao = new PacienteDao();
         dao.removePaciente(paciente);
         */
        //-------------------------------------------------------------------------
        //ALTERA GUIA
        /*
         Guia guia = new Guia();
        
         guia.setNumero_guia_principal(Long.parseLong("333333"));
         guia.setCirugia("Septoplastia");
         guia.setLocalizacao("ADM");
         guia.setObservacao("Falta Tudo");
         guia.setStatus_da_guia("Parada");
         guia.setMedico_da_guia("Sirlei");
         guia.setData_guia("11/11/2015");
         guia.setData_prontuario("12/12/2015");
         guia.setNumero_do_prontuario(Long.parseLong("333333"));
         guia.setId_paciente(Long.parseLong("2"));
         guia.setId_guia(Long.valueOf(2));
        
         GuiaDao dao = new GuiaDao();
         dao.alteraGuia(guia);
         */
        //-------------------------------------------------------------------------
        //ALTERA PACIENTE
        /*
         Paciente paciente = new Paciente();
        
         paciente.setNome("Teste altera");
         paciente.setMatricula_no_convenio(Long.parseLong("2313212"));
         paciente.setIdade(Long.parseLong("22"));
         paciente.setId_paciente(Long.valueOf(5));
         
         PacienteDao dao = new PacienteDao();
         dao.alteraPaciente(paciente);
         */
        //------------------------------------------------------------------------       
        //PESQUIZA DE PACIENTE + TABELA DE GUIAS
        /*
         PacienteDao dao = new PacienteDao();
         GuiaDao daog = new GuiaDao();
        
         List<Paciente> listaPaciente = dao.getListaP();
         List<Guia> listaGuia = daog.getListaG(); 
        
         Paciente paciente = new Paciente();
         Guia guia = new Guia();
        
         for(int i=0; i<listaPaciente.size(); i++ ){    
         System.out.println("Cirurgia: " + listaGuia.get(i).getCirugia() + " " + "Nome: " 
         + listaPaciente.get(i).getNome());;
         }
         */
        //------------------------------------------------------------------------
        /*PESQUIZA GUIA
         GuiaDao dao = new GuiaDao();
        
         List<Guia> listaguia = dao.getListaG();
        
         for(Guia guia : listaguia){
         System.out.println("Id: " + guia.getId_guia());
         System.out.println("Numero da guia principal: " + guia.getNumero_guia_principal());
         System.out.println("Cirurgia: " + guia.getCirugia());
         System.out.println("Localização: " + guia.getLocalizacao());
         System.out.println("Observação: " + guia.getObservacao());
         System.out.println("Status da guia: " + guia.getStatus_da_guia());
         System.out.println("Medico da guia: " + guia.getMedico_da_guia());
         System.out.println("Data da guia: " + guia.getData_guia());
         System.out.println("Data do prontuario: " + guia.getData_prontuario());
         System.out.println("Numero do prontuario: " + guia.getNumero_do_prontuario());
         System.out.println("Id do paciente: " + guia.getId_paciente());
         System.out.println("------------------------------------");
         }
         */
        //----------------------------------------------------------------------
        /*PESQUIZA PACIENTE
         PacienteDao dao = new PacienteDao();
        
         List<Paciente> listaPaciente = dao.getListaP();
        
         for(Paciente paciente : listaPaciente){
         System.out.println("Id Paciente: " + paciente.getNome());
         System.out.println("Nome: " + paciente.getNome());
         System.out.println("Matricula no convenio: " + paciente.getMatricula_no_convenio());
         System.out.println("Idade: " + paciente.getIdade());
         }
         */
        //-----------------------------------------------------------------------
        /*ADICIONA GUIA
         //Inclui dados no banco para a guia
         Guia guia = new Guia();
         guia.setNumero_guia_principal(Long.parseLong("8888888"));
         guia.setCirugia("Postectomia");
         guia.setLocalizacao("ADM");
         guia.setObservacao("Isto e um teste com BD e JAVA");
         guia.setStatus_da_guia("Aguardando");
         guia.setMedico_da_guia("Lawrence");
         guia.setData_guia("19/02/2016");
         guia.setData_prontuario("18/02/2016");
         guia.setNumero_do_prontuario(Long.parseLong("8888889"));
         guia.setId_paciente(Long.parseLong("6"));
        
         GuiaDao dao = new GuiaDao();
         dao.adicionaGuia(guia);
         System.out.println("Gravado com sucesso!");
         */
        //------------------------------------------------------------------------
        /* ADICIONA PACIENTE
         //Inclui dados no banco para o paciente
         Paciente paciente = new Paciente();
         paciente.setNome("Teste no java");
         paciente.setMatricula_no_convenio(Long.parseLong("8888888"));
         paciente.setIdade(Long.parseLong("31"));
         
         PacienteDao dao = new PacienteDao();
         dao.adicionaPaciente(paciente);
         System.out.println("Gravado com sucesso!");
         */
    }
}
