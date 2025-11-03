package com.reunipe.model;

import com.reunipe.enums.TipoSala;

public class Sala {
    private int idSala;
    private int numero;
    private int andar;
    private String campus;
    private int capacidade;
    private boolean ativa;
    private TipoSala tipo;
    
    public Sala(int numero, int andar, String campus, int capacidade, TipoSala tipo) {
        this.numero = numero;
        this.andar = andar;
        this.campus = campus;
        this.capacidade = capacidade;
        this.tipo = tipo;
        this.ativa = true;
    }
    
    public Sala() {}
    
    // Métodos
    public boolean salaDisponivel(String dataReserva, String horaInicio, String horaFim) {
        System.out.println("Verificando disponibilidade da sala " + this.numero + 
                          " para " + dataReserva + " das " + horaInicio + " às " + horaFim);
        // Lógica para verificar disponibilidade no banco
        return true; // Simulando que está disponível
    }
    
    // Getters e Setters
    public int getIdSala() { return idSala; }
    public void setIdSala(int idSala) { this.idSala = idSala; }
    
    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }
    
    public int getAndar() { return andar; }
    public void setAndar(int andar) { this.andar = andar; }
    
    public String getCampus() { return campus; }
    public void setCampus(String campus) { this.campus = campus; }
    
    public int getCapacidade() { return capacidade; }
    public void setCapacidade(int capacidade) { this.capacidade = capacidade; }
    
    public boolean isAtiva() { return ativa; }
    public void setAtiva(boolean ativa) { this.ativa = ativa; }
    
    public TipoSala getTipo() { return tipo; }
    public void setTipo(TipoSala tipo) { this.tipo = tipo; }
    
    @Override
    public String toString() {
        return "Sala [id=" + idSala + ", número=" + numero + ", andar=" + andar + 
               ", campus=" + campus + ", capacidade=" + capacidade + ", tipo=" + tipo + "]";
    }
}