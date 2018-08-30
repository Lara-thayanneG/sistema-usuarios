
package br.edu.ifpe.usuarios.bean;

import br.edu.ifpe.usuarios.entidades.Aluno;
import br.edu.ifpe.usuarios.dao.AlunoDao;
import br.edu.ifpe.usuarios.util.exception.ErroSistema;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped
public class AlunoBean {
    
    private Aluno aluno = new Aluno(null,"","","","","");
    AlunoDao alunoDao = new AlunoDao();
    private List<Aluno> alunos = new ArrayList<>();

    public void adicionar(){
        new AlunoDao().salvar(aluno);
        aluno = new Aluno(null,"","","","","");
        new AlunoBean().listar();
    }
    
    public void listar(){
        alunos = alunoDao.listar();
    }
    
    public void remover(Aluno alunovar){
        alunoDao.deletar(alunovar);
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    
    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }
    
    
}
