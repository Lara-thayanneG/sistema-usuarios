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
    public void salvar(Aluno aluno){
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
    }


    @Override
    public void deletar(Aluno aluno){
        try {
            Connection conexao = Conexoes.getConexao();
            PreparedStatement ps;
            ps = conexao.prepareStatement("delete from alunos where matricula_aluno=?");
            ps.setInt(1, aluno.getMatricula());
            ps.execute();
            Conexoes.fecharConexao();
            //Logger.getLogger(AlunoDao.class.getName()).log(Level.SEVERE, null, ex);
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Aluno> listar(){
         Connection conexao = Conexoes.getConexao();
        try {
            PreparedStatement ps = conexao.prepareStatement("Select * from alunos");
            ResultSet resultSet = ps.executeQuery();
            List<Aluno> alunos = new ArrayList<>();
            while(resultSet.next()){
                Aluno aluno = new Aluno(null,"","","","","");
                aluno.setMatricula(resultSet.getInt("matricula_aluno"));
                aluno.setCpf(resultSet.getString("cpf_aluno"));
                aluno.setNome(resultSet.getString("nome_aluno"));
                aluno.setData_nascimento(resultSet.getString("data_nascimento_aluno"));
                aluno.setEndereco(resultSet.getString("endereco_aluno"));
                aluno.setCurso(resultSet.getString("curso_aluno"));
                alunos.add(aluno);
            }
            return alunos;
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public static void main (String [] args) throws ErroSistema{
        //AlunoDao objeto = new AlunoDao();
        //Aluno alunoobjeto = new Aluno(1, "0000000033", "jarilândio", "09/10/1992", "Rua Esquecida", "Redes de Computadores");
        //objeto.salvar(alunoobjeto);
        //objeto.deletar(alunoobjeto);
    }
}
