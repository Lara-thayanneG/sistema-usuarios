package br.edu.ifpe.usuarios.util;

import br.edu.ifpe.usuarios.util.exception.ErroSistema;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;

public class Conexoes {
    private static Connection conexao;
    private static final String URL_CONEXAO = "jdbc:mysql://localhost/sistema-usuario"; 
    private static final String USUARIO = "root"; 
    private static final String SENHA = "root"; 

    public static Connection getConexao() throws ErroSistema {
        if(conexao == null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conexao = DriverManager.getConnection(URL_CONEXAO, USUARIO, SENHA);
            } catch (SQLException ex) {
                throw new ErroSistema("Não foi possível conectar ao banco de dados", ex);
                //Logger.getLogger(Conexoes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                throw new ErroSistema("O Driver do banco de dados não foi encontrado", ex);
                //Logger.getLogger(Conexoes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return conexao;
    }
    
    public static void fecharConexao() throws ErroSistema{
        if (conexao != null){
            try {
                conexao.close();
                conexao = null;
            } catch (SQLException ex) {
                throw new ErroSistema("Erro ao fechar a conexão com o banco de dados", ex);
               // Logger.getLogger(Conexoes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
