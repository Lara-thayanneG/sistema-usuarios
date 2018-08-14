
package br.edu.ifpe.usuarios.dao;

import br.edu.ifpe.usuarios.util.exception.ErroSistema;
import java.util.List;

public interface CrudDao<E> {
    
    public void salvar(E entidade)throws ErroSistema;
    public void deletar(E entidade)throws ErroSistema;
    public List<E>listar()throws ErroSistema;
    
    
    
}
