package controledeguias.bancodedados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CriaConexao {  
        
    //Caminho do banco de dados
    private static final String url = "jdbc:mysql://localhost:3306/controledeguias";
    private static final String usuario = "root";
    private static final String senha = "root";
    
    //Metodo para obter uma conexao
    public static Connection getConexao() throws SQLException{
        
        try {
            Class.forName("com.mysql.jdbc.Driver");// código a ser executado.
            System.out.println("Banco de dados conectado com sucesso");
        } catch (ClassNotFoundException e){// tratamento da exceção.
            e.printStackTrace();//Imprime na saída de erro padrão.
            System.out.println("Banco de dados nao conectou com sucesso");
        }
       
        return DriverManager.getConnection(url, usuario, senha);
    }
}
/*
Try catch e pra tratamento de exceções, entenda try como um "TENTAR EXECUTAR UM TRECHO DE CODIGO", 
e se ele nao conseguir executar esse trecho de codigo, ele vai pro catch. Catch e onde as exceções sao 
tratadas.
*/

//Shift + Alt + F = Identa o codigo;
