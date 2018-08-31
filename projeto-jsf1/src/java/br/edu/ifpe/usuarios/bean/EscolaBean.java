
package br.edu.ifpe.usuarios.bean;
import br.edu.ifpe.usuarios.dao.EscolaDao;
import br.edu.ifpe.usuarios.entidades.Escola;
import java.util.ArrayList;
import java.util.List;

public class EscolaBean {
private EscolaDao escolaDao = new EscolaDao();
private Escola escola = new Escola(null, "","");
private List<Escola> escolas = new ArrayList<>();


public void adicionar(){
    escolaDao.salvar(escola);
    escola = new Escola(null, "", "");
}

public void listar(){
    escolas = escolaDao.listar();
}

public void remover(Escola escolavar){
    escolaDao.deletar(escolavar);
    
}

    public EscolaDao getEscolaDao() {
        return escolaDao;
    }

    public void setEscolaDao(EscolaDao escolaDao) {
        this.escolaDao = escolaDao;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    public List<Escola> getEscolas() {
        return escolas;
    }

    public void setEscolas(List<Escola> escolas) {
        this.escolas = escolas;
    }

}