package com.reunipe.model;

import com.reunipe.enums.StatusReserva;
import java.time.LocalDate;
import java.time.LocalTime;

public class Reserva {
    private int idReserva;
    private int idSala;
    private int idUsuario;
    private String turma;
    private String tipoSala;
    private LocalDate dataReserva;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private String finalidade;
    private StatusReserva status;
    private int maxParticipantes;
    
    public Reserva(int idSala, int idUsuario, String turma, String tipoSala,
                   LocalDate dataReserva, LocalTime horaInicio, LocalTime horaFim,
                   String finalidade, int maxParticipantes) {
        this.idSala = idSala;
        this.idUsuario = idUsuario;
        this.turma = turma;
        this.tipoSala = tipoSala;
        this.dataReserva = dataReserva;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.finalidade = finalidade;
        this.maxParticipantes = maxParticipantes;
        this.status = StatusReserva.PENDENTE;
    }
    
    public Reserva() {}
    
    // Métodos
    public Object criarReserva(int idSala, int idUsuario, LocalDate dataReserva,
                              LocalTime horaInicio, LocalTime horaFim, String turma,
                              String turno, String tipoEstudo, String finalidade) {
        System.out.println("Criando reserva para sala " + idSala + " do usuário " + idUsuario);
        // Lógica para criar reserva no banco
        return this; // Retorna a própria reserva criada
    }
    
    public boolean cancelarReserva(int idReserva, int idUsuario) {
        System.out.println("Cancelando reserva " + idReserva + " do usuário " + idUsuario);
        this.status = StatusReserva.CANCELADA;
        return true;
    }
    
    // Getters e Setters
    public int getIdReserva() { return idReserva; }
    public void setIdReserva(int idReserva) { this.idReserva = idReserva; }
    
    public int getIdSala() { return idSala; }
    public void setIdSala(int idSala) { this.idSala = idSala; }
    
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    
    public String getTurma() { return turma; }
    public void setTurma(String turma) { this.turma = turma; }
    
    public String getTipoSala() { return tipoSala; }
    public void setTipoSala(String tipoSala) { this.tipoSala = tipoSala; }
    
    public LocalDate getDataReserva() { return dataReserva; }
    public void setDataReserva(LocalDate dataReserva) { this.dataReserva = dataReserva; }
    
    public LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }
    
    public LocalTime getHoraFim() { return horaFim; }
    public void setHoraFim(LocalTime horaFim) { this.horaFim = horaFim; }
    
    public String getFinalidade() { return finalidade; }
    public void setFinalidade(String finalidade) { this.finalidade = finalidade; }
    
    public StatusReserva getStatus() { return status; }
    public void setStatus(StatusReserva status) { this.status = status; }
    
    public int getMaxParticipantes() { return maxParticipantes; }
    public void setMaxParticipantes(int maxParticipantes) { this.maxParticipantes = maxParticipantes; }
    
    @Override
    public String toString() {
        return "Reserva [id=" + idReserva + ", sala=" + idSala + ", usuário=" + idUsuario + 
               ", data=" + dataReserva + ", status=" + status + "]";
    }
}