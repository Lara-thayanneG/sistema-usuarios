package br.edu.ifpe.usuarios.entidades;
/**
 *
 * @author Edjânio
 */
public class Aluno {
    private int matricula;
    private String cpf;
    private String nome;
    private String data_nascimento;
    private String endereco;
    private String curso;
    
    
    public Aluno(int matricula, String cpf, String nome, String data_nascimento, String endereco, String curso){
        this.matricula=matricula;
        this.cpf=cpf;
        this.nome=nome;
        this.data_nascimento=data_nascimento;
        this.endereco=endereco;
        this.curso=curso;   
    }
    public int getMatricula(){
        return matricula;
    }
    
    public void setMatricula(int matricula){
        this.matricula = matricula;
    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
    
   @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.matricula;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Aluno other = (Aluno) obj;
        if (this.matricula != other.matricula) {
            return false;
        }
        return true;
    } 
}
