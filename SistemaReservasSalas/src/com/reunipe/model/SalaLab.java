package com.reunipe.model;

public class SalaLab extends Sala {
    private String tipoLaboratorio;
    private String equipamentos;
    private String softwareInstalado;
    
    public SalaLab(int numero, int andar, String campus, int capacidade, 
                   String tipoLaboratorio, String equipamentos, String softwareInstalado) {
        super(numero, andar, campus, capacidade, com.reunipe.enums.TipoSala.LABORATORIO);
        this.tipoLaboratorio = tipoLaboratorio;
        this.equipamentos = equipamentos;
        this.softwareInstalado = softwareInstalado;
    }
    
    public SalaLab() {
        super();
    }
    
    // Métodos específicos do laboratório
    @Override
    public boolean salaDisponivel(String dataReserva, String horaInicio, String horaFim) {
        System.out.println("Verificando disponibilidade do laboratório " + getNumero() + 
                          " (" + tipoLaboratorio + ") para " + dataReserva);
        // Lógica específica para laboratório
        return super.salaDisponivel(dataReserva, horaInicio, horaFim);
    }
    
    // Getters e Setters específicos
    public String getTipoLaboratorio() { return tipoLaboratorio; }
    public void setTipoLaboratorio(String tipoLaboratorio) { this.tipoLaboratorio = tipoLaboratorio; }
    
    public String getEquipamentos() { return equipamentos; }
    public void setEquipamentos(String equipamentos) { this.equipamentos = equipamentos; }
    
    public String getSoftwareInstalado() { return softwareInstalado; }
    public void setSoftwareInstalado(String softwareInstalado) { this.softwareInstalado = softwareInstalado; }
    
    @Override
    public String toString() {
        return super.toString() + " [Tipo Laboratório: " + tipoLaboratorio + 
               ", Equipamentos: " + equipamentos + "]";
    }
}