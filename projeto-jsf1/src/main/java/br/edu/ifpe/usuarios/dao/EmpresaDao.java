/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.usuarios.dao;

import br.edu.ifpe.usuarios.entidades.Empresa;
import java.util.ArrayList;
import java.util.List;
import br.edu.ifpe.usuarios.util.exception.ErroSistema;
import br.edu.ifpe.usuarios.conexao.Conexoes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmpresaDao implements CrudDao<Empresa>{

    @Override
    public void salvar(Empresa empresa) throws ErroSistema {
        try {
            Connection conexao = Conexoes.getConexao();
             PreparedStatement ps;
            if(empresa.getCodigo() == 0){
               ps = conexao.prepareStatement("insert into empresa (cnpj, nome, endereco) values (?, ?, ?)");
            } else {
                ps = conexao.prepareStatement("update empresa set cnpj=?, nome=?, endereco=? where codigo=?");
                ps.setInt(4, empresa.getCodigo());
            }
            ps.setString(1, empresa.getCnpj());
            ps.setString(2, empresa.getNome());
            ps.setString(3, empresa.getEndereco());
            ps.execute();
            Conexoes.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletar(Empresa empresa) throws ErroSistema {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Empresa> listar() throws ErroSistema {
        Connection conexao = Conexoes.getConexao();
        try {
            PreparedStatement ps = conexao.prepareStatement("select * from empresas");
            ResultSet resultSet = ps.executeQuery();
            List<Empresa> empresas = new ArrayList<>();
            while(resultSet.next()){
                Empresa empresa = new Empresa(0,"","","");
                empresa.setCodigo(resultSet.getInt("codigo"));
                empresa.setCnpj(resultSet.getString("cnpj"));
                empresa.setNome(resultSet.getString("nome"));
                empresa.setEndereco(resultSet.getString("endereco"));
                empresas.add(empresa);
            }
            return empresas;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
