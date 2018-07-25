package br.edu.ifpe.usuarios.dao;
import br.edu.ifpe.usuarios.conexao.Conexoes;
import br.edu.ifpe.usuarios.entidades.Aluno;
import br.edu.ifpe.usuarios.util.exception.ErroSistema;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlunoDao implements CrudDao<Aluno>{

    @Override   
    public void salvar(Aluno aluno) throws ErroSistema {
    try{
        Connection conexao = Conexoes.getConexao();
        PreparedStatement ps;
        ps = conexao.prepareCall("Insert into alunos (cpf,nome, data_nascimento, endereco, curso) values (?, ?, ?,? )");
        ps.setString(1, aluno.getCpf());
        ps.setString(2, aluno.getNome());
        ps.setDate(3, new Date(aluno.getData_nascimento().getTime));
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
    public List<Aluno> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public static void main (String args[]){
        Aluno aluno = new Aluno(1,"0000000000","Juarez Rodrigues", "1992-10-09", "Rua Esquecida", "Desenvolvimento");
        AlunoDao objeto = new AlunoDao();
        objeto.salvar(aluno);
    }
}
