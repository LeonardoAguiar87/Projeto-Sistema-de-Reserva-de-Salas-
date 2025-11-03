package com.reunipe.model;

public class Professor extends Usuario {
    private String cndb;
    private String instituicaoEnsino;
    
    public Professor(String nome, String sobrenome, String email, String cpf,
                    String telefone, String senha, String cndb, String instituicaoEnsino) {
        super(nome, sobrenome, email, cpf, telefone, senha);
        this.cndb = cndb;
        this.instituicaoEnsino = instituicaoEnsino;
    }
    
    public Professor() {
        super();
    }
    
    @Override
    public boolean login(String email, String senha) {
        boolean loginSucesso = this.email.equals(email) && this.senha.equals(senha);
        if (loginSucesso) {
            System.out.println("Professor " + this.nome + " logou com sucesso!");
        }
        return loginSucesso;
    }
    
    @Override
    public void logout() {
        System.out.println("Professor " + this.nome + " fez logout");
    }
    
    // Métodos específicos do Professor
    public boolean criarReserva(int idSala, String dataReserva, String horaInicio, 
                               String horaFim, String turma, String finalidade) {
        System.out.println("Professor criando reserva para sala " + idSala);
        return true;
    }
    
    public boolean cancelarReserva(int idReserva) {
        System.out.println("Professor cancelando reserva " + idReserva);
        return true;
    }
    
    public boolean participarReserva(int idReserva) {
        System.out.println("Professor participando da reserva " + idReserva);
        return true;
    }
    
    public boolean sairReserva(int idReserva) {
        System.out.println("Professor saindo da reserva " + idReserva);
        return true;
    }
    
    public Object[] minhasReservas() {
        System.out.println("Buscando reservas do professor...");
        return new Object[0];
    }
    
    public Object[] detalhesReserva(int idReserva) {
        System.out.println("Detalhes da reserva " + idReserva);
        return new Object[0];
    }
    
    // Getters e Setters específicos
    public String getCndb() { return cndb; }
    public void setCndb(String cndb) { this.cndb = cndb; }
    
    public String getInstituicaoEnsino() { return instituicaoEnsino; }
    public void setInstituicaoEnsino(String instituicaoEnsino) { 
        this.instituicaoEnsino = instituicaoEnsino; 
    }
    
    @Override
    public String toString() {
        return super.toString() + " [CNDB: " + cndb + ", Instituição: " + instituicaoEnsino + "]";
    }
}