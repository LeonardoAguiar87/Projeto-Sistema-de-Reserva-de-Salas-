package com.reunipe.model;

public class Aluno extends Usuario {
    private String matricula;
    private String instituicaoEnsino;
    
    public Aluno(String nome, String sobrenome, String email, String cpf,
                 String telefone, String senha, String matricula, String instituicaoEnsino) {
        super(nome, sobrenome, email, cpf, telefone, senha);
        this.matricula = matricula;
        this.instituicaoEnsino = instituicaoEnsino;
    }
    
    public Aluno() {
        super();
    }
    
    @Override
    public boolean login(String email, String senha) {
        boolean loginSucesso = this.email.equals(email) && this.senha.equals(senha);
        if (loginSucesso) {
            System.out.println("Aluno " + this.nome + " logou com sucesso!");
        }
        return loginSucesso;
    }
    
    @Override
    public void logout() {
        System.out.println("Aluno " + this.nome + " fez logout");
    }
    
    // Métodos específicos do Aluno
    public boolean criarReserva(int idSala, String dataReserva, String horaInicio, 
                               String horaFim, String turma, String finalidade) {
        System.out.println("Aluno criando reserva para sala " + idSala);
        // Lógica para criar reserva
        return true;
    }
    
    public boolean cancelarReserva(int idReserva) {
        System.out.println("Aluno cancelando reserva " + idReserva);
        // Lógica para cancelar reserva
        return true;
    }
    
    public boolean participarReserva(int idReserva) {
        System.out.println("Aluno participando da reserva " + idReserva);
        return true;
    }
    
    public boolean sairReserva(int idReserva) {
        System.out.println("Aluno saindo da reserva " + idReserva);
        return true;
    }
    
    public Object[] minhasReservas() {
        System.out.println("Buscando reservas do aluno...");
        return new Object[0]; // Retornaria lista de reservas
    }
    
    public Object[] detalhesReserva(int idReserva) {
        System.out.println("Detalhes da reserva " + idReserva);
        return new Object[0]; // Retornaria detalhes da reserva
    }
    
    // Getters e Setters específicos
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    
    public String getInstituicaoEnsino() { return instituicaoEnsino; }
    public void setInstituicaoEnsino(String instituicaoEnsino) { 
        this.instituicaoEnsino = instituicaoEnsino; 
    }
    
    @Override
    public String toString() {
        return super.toString() + " [Matrícula: " + matricula + ", Instituição: " + instituicaoEnsino + "]";
    }
}