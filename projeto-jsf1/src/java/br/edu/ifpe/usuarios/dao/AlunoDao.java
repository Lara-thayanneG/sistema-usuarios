package br.edu.ifpe.usuarios.dao;
import br.edu.ifpe.usuarios.conexao.Conexoes;
import br.edu.ifpe.usuarios.entidades.Aluno;
import br.edu.ifpe.usuarios.util.exception.ErroSistema;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlunoDao implements CrudDao<Aluno>{

    @Override   
    public void salvar(Aluno aluno) throws ErroSistema {
    try{
        Connection conexao = Conexoes.getConexao();
        PreparedStatement ps;
        if(aluno.getMatricula()== null){
        ps = conexao.prepareStatement("insert into alunos (cpf_aluno, nome_aluno, data_nascimento_aluno, endereco_aluno, curso_aluno) values (?, ?, ?, ?, ? )");
        } else{
            ps = conexao.prepareStatement("update alunos set cpf_aluno=?, nome_aluno=?, data_nascimento_aluno=?, endereco_aluno=?, curso_aluno=? where matricula_aluno=?");
            ps.setInt(6, aluno.getMatricula());
        }
        ps.setString(1, aluno.getCpf());
        ps.setString(2, aluno.getNome());
        ps.setString(3, aluno.getData_nascimento());
        ps.setString(4, aluno.getEndereco());
        ps.setString(5,aluno.getCurso());
        ps.execute();
        Conexoes.fecharConexao();
    }catch (SQLException ex) {
            Logger.getLogger(AlunoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletar(Aluno aluno) throws ErroSistema {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Aluno> listar() throws ErroSistema {
         Connection conexao = Conexoes.getConexao();
        try {
            PreparedStatement ps = conexao.prepareStatement("Select * from alunos");
            ResultSet resultSet = ps.executeQuery();
            List<Aluno> alunos = new ArrayList<>();
            while(resultSet.next()){
                Aluno aluno = new Aluno(0,"","","","","");
                aluno.setMatricula(resultSet.getInt("matricula"));
                aluno.setCpf(resultSet.getString("cpf"));
                aluno.setNome(resultSet.getString("nome"));
                aluno.setData_nascimento(resultSet.getString("data_nascimento"));
                aluno.setEndereco(resultSet.getString("endereco"));
                aluno.setCurso(resultSet.getString("curso"));
                alunos.add(aluno);
            }
            return alunos;
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public static void main (String [] args) throws ErroSistema{
        AlunoDao objeto = new AlunoDao();
        Aluno alunoobjeto = new Aluno(null, "0000000044", "Julião", "09/10/1992", "Rua Esquecida", "Redes de Computadores");
        objeto.salvar(alunoobjeto);
    }
}
