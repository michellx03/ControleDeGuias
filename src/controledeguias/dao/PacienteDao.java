package controledeguias.dao;

import controledeguias.bancodedados.CriaConexao;
import controledeguias.tabelasbanco.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDao {

    private Connection conexao;//Cria conexao com o banco

    //Contrutor
    public PacienteDao() throws SQLException {
        this.conexao = CriaConexao.getConexao();//Pucha a conexao com o banco
    }

    //Metodo para adicionar pacientes
    public void adicionaPaciente(Paciente paciente) throws SQLException {
        //Prepara a conexao
        String sql = "insert into paciente(nome, matricula_no_convenio, idade, convenio) values (?, ?, ?, ?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);

        //Seta valores
        stmt.setString(1, paciente.getNome());
        stmt.setLong(2, paciente.getMatricula_no_convenio());
        stmt.setLong(3, paciente.getIdade());
        stmt.setString(4, paciente.getConvenio());

        //Executa o codigo
        stmt.execute();
        stmt.close();
    }

    //Metodo para Pesquizar
    public List<Paciente> getListaP(String nome) throws SQLException {
        //Prepara a conexao sql
        String sql = "select * from paciente where nome like ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, nome);
        ResultSet rs = stmt.executeQuery();

        //Cria um ArrayList da tabela paciente
        List<Paciente> listaPaciente = new ArrayList<Paciente>();

        //Exibi os pacientes
        while (rs.next()) {
            Paciente paciente = new Paciente();

            paciente.setId_paciente(rs.getLong("id_paciente"));
            paciente.setNome(rs.getString("nome"));
            paciente.setMatricula_no_convenio(rs.getLong("matricula_no_convenio"));
            paciente.setIdade(rs.getLong("idade"));
            paciente.setConvenio(rs.getString("convenio"));

            listaPaciente.add(paciente);
        }
        //fecha e retorna a lista
        rs.close();
        stmt.close();
        return listaPaciente;
    }

    //Metodo para Alterar
    public void alteraPaciente(Paciente paciente) throws SQLException {
        //Prepara a conexao
        String sql = "update paciente set nome=?, matricula_no_convenio=?, convenio=?, idade=? where id_paciente=?";
        PreparedStatement stmt = conexao.prepareStatement(sql);

        //Seta valores
        stmt.setString(1, paciente.getNome());
        stmt.setLong(2, paciente.getMatricula_no_convenio());
        stmt.setString(3, paciente.getConvenio());
        stmt.setLong(4, paciente.getIdade());
        stmt.setLong(5, paciente.getId_paciente());

        //Executa o codigo e fecha
        stmt.execute();
        stmt.close();
    }

    //Metodo para remover
    public void removePaciente(Paciente paciente) throws SQLException {
        //Prepara a conexao
        String sql = "delete from paciente where id_paciente=?";
        PreparedStatement stmt = conexao.prepareStatement(sql);

        //Seta o valor
        stmt.setLong(1, paciente.getId_paciente());

        //Executa o codigo e fecha
        stmt.execute();
        stmt.close();

    }
}
