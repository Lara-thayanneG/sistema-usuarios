
package br.edu.ifpe.usuarios.dao;

import br.edu.ifpe.usuarios.conexao.Conexoes;
import br.edu.ifpe.usuarios.entidades.Escola;
import br.edu.ifpe.usuarios.util.exception.ErroSistema;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EscolaDao implements CrudDao<Escola>{

    @Override
    public void salvar(Escola escola){
        try{
        Connection conexao = Conexoes.getConexao();
        PreparedStatement ps;
        if(escola.getCodigo() == 0){
            ps = conexao.prepareStatement("insert into escolas (nome_escola, endereco_escola) values (?, ?");
        }else {
            ps = conexao.prepareStatement("update escolas set nome=?, endereco=? where codigo_escola=?");
            ps.setInt(3, escola.getCodigo());
        }
        ps.setString(1, escola.getNome());
        ps.setString(2, escola.getEndereco());
        ps.execute();
        Conexoes.fecharConexao();
         } catch (SQLException ex) {
            Logger.getLogger(EmpresaDao.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletar(Escola escola){
        Connection conexao = Conexoes.getConexao();
        try {
            PreparedStatement ps;
            ps = conexao.prepareStatement("delete from escolas where=codigo_escola=?");
            ps.setInt(1, escola.getCodigo());
            ps.execute();
            Conexoes.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(EscolaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public List<Escola> listar(){
        Connection conexao = Conexoes.getConexao();
        try {
            PreparedStatement ps = conexao.prepareStatement("select * from escola");
            ResultSet resultSet = ps.executeQuery();
            List<Escola> escolas = new ArrayList<>();
            while(resultSet.next()){
                Escola escola = new Escola(0,"","");
                escola.setCodigo(resultSet.getInt("codigo_escola"));
                escola.setNome(resultSet.getString("nome_escola"));
                escola.setEndereco(resultSet.getString("endereco_escola"));
                escolas.add(escola);
            }
            return escolas;
        } catch (SQLException ex) {
            Logger.getLogger(EscolaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
       
    }
    
}
