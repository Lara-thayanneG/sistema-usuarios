package br.edu.ifpe.usuarios.entidades;

public class Empresa {
    private int codigo;
    private String cnpj;
    private String nome;
    private String endereco;
    
    public Empresa(int codigo, String cnpj, String nome, String endereco){
        this.codigo=codigo;
        this.cnpj=cnpj;
        this.nome=nome;
        this.endereco=endereco;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
}
