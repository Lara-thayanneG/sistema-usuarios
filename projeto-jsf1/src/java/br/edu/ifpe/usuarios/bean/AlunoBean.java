
package br.edu.ifpe.usuarios.bean;

import br.edu.ifpe.usuarios.entidades.Aluno;
import br.edu.ifpe.usuarios.dao.AlunoDao;
import br.edu.ifpe.usuarios.util.exception.ErroSistema;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped
public class AlunoBean {
    
    private Aluno aluno = new Aluno(null,"","","","","");
    
    public void adicionar() throws ErroSistema{
        new AlunoDao().salvar(aluno);
        aluno = new Aluno(null,"","","","","");
        
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    
    
    
}
