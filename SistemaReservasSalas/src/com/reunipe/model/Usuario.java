package com.reunipe.model;

import java.time.LocalDate;

public abstract class Usuario {
    protected int idUsuario;
    protected String nome;
    protected String sobrenome;
    protected String email;
    protected String cpf;
    protected String telefone;
    protected String senha;
    protected LocalDate dataCadastro;
    protected boolean ativo;
    
    // Construtor
    public Usuario(String nome, String sobrenome, String email, String cpf, 
                   String telefone, String senha) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
        this.senha = senha;
        this.dataCadastro = LocalDate.now();
        this.ativo = true;
    }
    
    // Construtor vazio
    public Usuario() {}
    
    // Métodos abstratos (serão implementados pelas subclasses)
    public abstract boolean login(String email, String senha);
    public abstract void logout();
    
    // Métodos concretos
    public boolean cadastrar() {
        // Lógica de cadastro no banco
        System.out.println("Usuário cadastrado: " + this.nome);
        return true;
    }
    
    public boolean alterarSenha(String novaSenha) {
        this.senha = novaSenha;
        System.out.println("Senha alterada para usuário: " + this.nome);
        return true;
    }
    
    // Getters e Setters
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getSobrenome() { return sobrenome; }
    public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    
    public LocalDate getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDate dataCadastro) { this.dataCadastro = dataCadastro; }
    
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
    
    @Override
    public String toString() {
        return "Usuario [id=" + idUsuario + ", nome=" + nome + " " + sobrenome + 
               ", email=" + email + ", ativo=" + ativo + "]";
    }
}