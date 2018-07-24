  
package br.edu.ifpe.usuarios.bean;

import br.edu.ifpe.usuarios.dao.UsuarioDAO;
import br.edu.ifpe.usuarios.entidade.Usuario;
import br.edu.ifpe.usuarios.util.exception.ErroSistema;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class UsuarioBean {
    private Usuario usuario;
    private List<Usuario> usuarios = new ArrayList<>();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    public void adicionar(){
        try {
            usuarioDAO.salvar(usuario);  
            usuario = new Usuario();
            adicionarMensagem("Salvo", "Carro salvo com sucesso", FacesMessage.SEVERITY_INFO);
        } catch (ErroSistema ex) {
            adicionarMensagem(ex.getMessage(), ex.getCause().getMessage(), FacesMessage.SEVERITY_ERROR);
            //Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void listar(){
        try {
            usuarios = usuarioDAO.buscar();
            if(usuarios == null || usuarios.size() == 0){
                adicionarMensagem("Nunhum dado encontrado", "Sua busca não retornou nenhum carro", FacesMessage.SEVERITY_WARN);
            }
        } catch (ErroSistema ex) {
            adicionarMensagem(ex.getMessage(), ex.getCause().getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void deletar(Usuario usr){
        try {
            usuarioDAO.deletar(usr.getId());
            adicionarMensagem("Deletado", "Carro deletado com sucesso", FacesMessage.SEVERITY_INFO);
        } catch (ErroSistema ex) {
            adicionarMensagem(ex.getMessage(), ex.getCause().getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void editar(Usuario usr){
        usuario = usr;
    }
    
    public void adicionarMensagem(String sumario, String detalhe, FacesMessage.Severity tipoErro){
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(tipoErro, sumario, detalhe);
        context.addMessage(null, message);
        
        
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    private Object usuarioDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
