package controledeguias.dao;

import controledeguias.bancodedados.CriaConexao;
import controledeguias.tabelasbanco.Guia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuiaDao {

    private Connection conexao;//Cria conexao com o banco

    //Consturto
    public GuiaDao() throws SQLException {
        this.conexao = CriaConexao.getConexao();//Pucha a conexao com o banco
    }

    //Metodo para adicionar pacientes
    public void adicionaGuia(Guia guia) throws SQLException {
        //Prepara a conexao
        String sql = "insert into guia(numero_guia_principal, cirurgia, localizacao, observacao, status_da_guia, medico_da_guia, data_da_guia, data_do_prontuario, numero_do_prontuario, id_paciente)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);

        //Seta valores
        stmt.setLong(1, guia.getNumero_guia_principal());
        stmt.setString(2, guia.getCirugia());
        stmt.setString(3, guia.getLocalizacao());
        stmt.setString(4, guia.getObservacao());
        stmt.setString(5, guia.getStatus_da_guia());
        stmt.setString(6, guia.getMedico_da_guia());
        stmt.setString(7, guia.getData_guia());
        stmt.setString(8, guia.getData_prontuario());
        stmt.setLong(9, guia.getNumero_do_prontuario());
        stmt.setLong(10, guia.getId_paciente());

        //Executa o codigo
        stmt.execute();
        stmt.close();        
    }

    //Metodo para Pesquizar
    public List<Guia> getListaG(String numero) throws SQLException {
        //Prepara a conexao sql
        String sql = "select * from guia where id_paciente like ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, numero);
        ResultSet rs = stmt.executeQuery();

        //Cria um ArrayList da tabela Guia
        List<Guia> listaGuia = new ArrayList<Guia>();

        //Exibi os dados da guia
        while (rs.next()) {
            Guia guia = new Guia();
            
            guia.setId_guia(rs.getLong("id_guia"));
            guia.setNumero_guia_principal(rs.getLong("numero_guia_principal"));
            guia.setCirugia(rs.getString("cirurgia"));
            guia.setLocalizacao(rs.getString("localizacao"));
            guia.setObservacao(rs.getString("observacao"));
            guia.setStatus_da_guia(rs.getString("status_da_guia"));
            guia.setMedico_da_guia(rs.getString("medico_da_guia"));
            guia.setData_guia(rs.getString("data_da_guia"));
            guia.setData_prontuario(rs.getString("data_do_prontuario"));
            guia.setNumero_do_prontuario(rs.getLong("numero_do_prontuario"));
            guia.setId_paciente(rs.getLong("id_paciente"));
            
            listaGuia.add(guia);
        }
        //fecha e retorna a lista
        rs.close();
        stmt.close();
        return listaGuia;
    }

    //Metodo de Alterar de guia
    public void alteraGuia(Guia guia) throws SQLException {
        //Prepara a conexao
        String sql = "update guia set numero_guia_principal=?, cirurgia=?, localizacao=?, observacao=?,"
                + " status_da_guia=?, medico_da_guia=?, data_da_guia=?, data_do_prontuario=?, numero_do_prontuario=?,"
                + "id_paciente=? where id_guia=?";
        PreparedStatement stmt = conexao.prepareStatement(sql);

        //Seta valores
        stmt.setLong(1, guia.getNumero_guia_principal());
        stmt.setString(2, guia.getCirugia());
        stmt.setString(3, guia.getLocalizacao());
        stmt.setString(4, guia.getObservacao());
        stmt.setString(5, guia.getStatus_da_guia());
        stmt.setString(6, guia.getMedico_da_guia());
        stmt.setString(7, guia.getData_guia());
        stmt.setString(8, guia.getData_prontuario());
        stmt.setLong(9, guia.getNumero_do_prontuario());
        stmt.setLong(10, guia.getId_paciente());
        stmt.setLong(11, guia.getId_guia());

        //Executa o codigo e fecha
        stmt.execute();
        stmt.close();
    }

    //Metodo para remover
    public void removeGuia(Guia guia) throws SQLException {
        //Prepara a conexao
        String sql = "delete from guia where id_guia=?";
        PreparedStatement stmt = conexao.prepareStatement(sql);

        //Seta o valor
        stmt.setLong(1, guia.getId_guia());

        //Executa o codigo e fecha
        stmt.execute();
        stmt.close();
    }
}
