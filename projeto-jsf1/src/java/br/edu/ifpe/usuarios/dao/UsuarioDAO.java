package br.edu.ifpe.usuarios.dao;

import br.edu.ifpe.usuarios.entidade.Usuario;
import br.edu.ifpe.usuarios.conexao.Conexoes;
import br.edu.ifpe.usuarios.util.exception.ErroSistema;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO {
    public void salvar(Usuario usuario) throws ErroSistema{
        try {
            Connection conexao = Conexoes.getConexao();
            PreparedStatement ps;
            if(usuario.getId() == null){
                ps = conexao.prepareStatement("");    
            }else{
                ps = conexao.prepareStatement("update usuario set login=?, senha=? where id=?");
                ps.setInt(3, usuario.getId());
                
            }
            ps.setString(1, usuario.getLogin());
            ps.setString(2, usuario.getSenha());
            ps.execute();
            Conexoes.fecharConexao();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar salvar", ex);
           // Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deletar(Integer idUsuario) throws ErroSistema{
        try {
            Connection conexao = Conexoes.getConexao();
            PreparedStatement ps = conexao.prepareStatement("delete from usuario where id = ?");
            ps.setInt(1, idUsuario);
            ps.execute();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao deletar o carro", ex);
            
            //Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Usuario> buscar() throws ErroSistema{
        try {
            Connection conexao = Conexoes.getConexao();
            PreparedStatement ps = conexao.prepareStatement("select * from usuario");
            ResultSet resultSet = ps.executeQuery();
            List<Usuario> usuarios = new ArrayList<>();
            while(resultSet.next()){
                Usuario usuario = new Usuario();
                usuario.setId(resultSet.getInt("id"));
                usuario.setLogin(resultSet.getString("Login"));
                usuario.setSenha(resultSet.getString("Senha"));
                usuarios.add(usuario);
            }
            Conexoes.fecharConexao();
            return usuarios;
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao buscar os usuários", ex);
            //Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
