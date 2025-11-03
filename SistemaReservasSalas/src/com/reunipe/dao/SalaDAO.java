package com.reunipe.dao;

import com.reunipe.model.Sala;
import com.reunipe.enums.TipoSala;
import com.reunipe.database.ConexaoBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalaDAO {
    
    public boolean cadastrarSala(Sala sala) {
        String sql = "INSERT INTO Sala (numero, andar, campus, capacidade, ativo, tipo) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, sala.getNumero());
            stmt.setInt(2, sala.getAndar());
            stmt.setString(3, sala.getCampus());
            stmt.setInt(4, sala.getCapacidade());
            stmt.setBoolean(5, sala.isAtiva());
            stmt.setString(6, sala.getTipo().toString());
            
            int linhasAfetadas = stmt.executeUpdate();
            
            if (linhasAfetadas > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        sala.setIdSala(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar sala: " + e.getMessage());
        }
        return false;
    }
    
    public Sala buscarPorId(int idSala) {
        String sql = "SELECT * FROM Sala WHERE idSala = ?";
        Sala sala = null;
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idSala);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                sala = criarSalaFromResultSet(rs);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao buscar sala: " + e.getMessage());
        }
        return sala;
    }
    
    public List<Sala> listarSalasDisponiveis() {
        String sql = "SELECT * FROM Sala WHERE ativo = true";
        List<Sala> salas = new ArrayList<>();
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                salas.add(criarSalaFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao listar salas: " + e.getMessage());
        }
        return salas;
    }
    
    public boolean verificarDisponibilidade(int idSala, String dataReserva, String horaInicio, String horaFim) {
        String sql = "SELECT COUNT(*) as count FROM Reserva " +
                    "WHERE idSala = ? AND dataReserva = ? AND status IN ('PENDENTE', 'CONFIRMADA') " +
                    "AND ((? BETWEEN horaInicio AND horaFim) OR (? BETWEEN horaInicio AND horaFim) " +
                    "OR (horaInicio BETWEEN ? AND ?))";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idSala);
            stmt.setDate(2, Date.valueOf(dataReserva));
            stmt.setTime(3, Time.valueOf(horaInicio + ":00"));
            stmt.setTime(4, Time.valueOf(horaFim + ":00"));
            stmt.setTime(5, Time.valueOf(horaInicio + ":00"));
            stmt.setTime(6, Time.valueOf(horaFim + ":00"));
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("count") == 0;
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao verificar disponibilidade: " + e.getMessage());
        }
        return false;
    }
    
    private Sala criarSalaFromResultSet(ResultSet rs) throws SQLException {
        Sala sala = new Sala();
        
        sala.setIdSala(rs.getInt("idSala"));
        sala.setNumero(rs.getInt("numero"));
        sala.setAndar(rs.getInt("andar"));
        sala.setCampus(rs.getString("campus"));
        sala.setCapacidade(rs.getInt("capacidade"));
        sala.setAtiva(rs.getBoolean("ativo"));
        sala.setTipo(TipoSala.valueOf(rs.getString("tipo")));
        
        return sala;
    }
}