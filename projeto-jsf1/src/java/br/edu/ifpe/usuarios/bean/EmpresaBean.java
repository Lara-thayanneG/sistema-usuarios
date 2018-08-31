package br.edu.ifpe.usuarios.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import br.edu.ifpe.usuarios.dao.EmpresaDao;
import br.edu.ifpe.usuarios.entidades.Empresa;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class EmpresaBean {
    
    private EmpresaDao empresaDao = new EmpresaDao();
    private Empresa empresa = new Empresa(null,"","","");
    private List<Empresa> empresas = new ArrayList<>();


public void adicionar(){
    empresaDao.salvar(empresa);
    empresa = new Empresa(null, "","","");
}

public void listar(){
    empresas = empresaDao.listar();
}

 public void remover(Empresa empresavar){
     this.empresaDao.deletar(empresavar);
 }

    public EmpresaDao getEmpresaDao() {
        return empresaDao;
    }

    public void setEmpresaDao(EmpresaDao empresaDao) {
        this.empresaDao = empresaDao;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }
 
}
