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
    public void salvar(Empresa empresa){
        try {
            Connection conexao = Conexoes.getConexao();
             PreparedStatement ps;
            if(empresa.getCodigo() == null){
               ps = conexao.prepareStatement("insert into empresas_parceiras (cnpj_empresa, nome_empresa, endereco_empresa) values (?, ?, ?)");
            } else {
                ps = conexao.prepareStatement("update empresa set cnpj_empresa=?, nome_empresa=?, endereco_empresa=? where codigo_empresa=?");
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
        
    }

    @Override
    public void deletar(Empresa empresa){
        Connection conexao = Conexoes.getConexao();
        try {
            PreparedStatement ps;
            ps = conexao.prepareStatement("delete from empresas_parceiras where codigo_empresa=?");
            ps.setInt(1, empresa.getCodigo());
            ps.execute();
            Conexoes.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Empresa> listar(){
        Connection conexao = Conexoes.getConexao();
        try {
            PreparedStatement ps = conexao.prepareStatement("select * from empresas_parceiras");
            ResultSet resultSet = ps.executeQuery();
            List<Empresa> empresas = new ArrayList<>();
            while(resultSet.next()){
                Empresa empresa = new Empresa(null,"","","");
                empresa.setCodigo(resultSet.getInt("codigo_empresa"));
                empresa.setCnpj(resultSet.getString("cnpj_empresa"));
                empresa.setNome(resultSet.getString("nome_empresa"));
                empresa.setEndereco(resultSet.getString("endereco_empresa"));
                empresas.add(empresa);
            }
            return empresas;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        
    }
    
    
    
}
