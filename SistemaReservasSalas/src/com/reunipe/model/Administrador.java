package com.reunipe.model;

public class Administrador extends Usuario {
    private String departamento;
    private boolean superUsuario;
    
    public Administrador(String nome, String sobrenome, String email, String cpf,
                        String telefone, String senha, String departamento, boolean superUsuario) {
        super(nome, sobrenome, email, cpf, telefone, senha);
        this.departamento = departamento;
        this.superUsuario = superUsuario;
    }
    
    public Administrador() {
        super();
    }
    
    @Override
    public boolean login(String email, String senha) {
        boolean loginSucesso = this.email.equals(email) && this.senha.equals(senha);
        if (loginSucesso) {
            System.out.println("Administrador " + this.nome + " logou com sucesso!");
        }
        return loginSucesso;
    }
    
    @Override
    public void logout() {
        System.out.println("Administrador " + this.nome + " fez logout");
    }
    
    // Métodos específicos do Administrador
    public boolean gerenciarSalas() {
        System.out.println("Gerenciando salas...");
        return true;
    }
    
    public boolean gerenciarUsuario() {
        System.out.println("Gerenciando usuários...");
        return true;
    }
    
    public boolean gerenciarReservas() {
        System.out.println("Gerenciando reservas...");
        return true;
    }
    
    public boolean bloquearSalas(int idSala, String dataInicio, String dataFim, String motivo) {
        System.out.println("Bloqueando sala " + idSala + " de " + dataInicio + " até " + dataFim);
        return true;
    }
    
    public Object reservarParaUsuario(int idSala, int idUsuario, String dataReserva, 
                                     String horaInicio, String horaFim, String turma,
                                     String turno, String tipoEstudo, String finalidade) {
        System.out.println("Reservando sala " + idSala + " para usuário " + idUsuario);
        return new Object(); // Retornaria a reserva criada
    }
    
    public boolean cancelarQualquerReserva(int idReserva, int idUsuario) {
        System.out.println("Cancelando reserva " + idReserva + " do usuário " + idUsuario);
        return true;
    }
    
    // Getters e Setters específicos
    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
    
    public boolean isSuperUsuario() { return superUsuario; }
    public void setSuperUsuario(boolean superUsuario) { this.superUsuario = superUsuario; }
    
    @Override
    public String toString() {
        return super.toString() + " [Departamento: " + departamento + ", SuperUsuário: " + superUsuario + "]";
    }
}