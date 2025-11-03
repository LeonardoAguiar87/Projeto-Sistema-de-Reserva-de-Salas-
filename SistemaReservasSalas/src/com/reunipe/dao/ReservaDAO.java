package com.reunipe.dao;

import com.reunipe.model.Reserva;
import com.reunipe.enums.StatusReserva;
import com.reunipe.database.ConexaoBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    
    // CREATE - Criar reserva
    public boolean criarReserva(Reserva reserva) {
        String sql = "INSERT INTO Reserva (idSala, idUsuario, turma, tipoSala, dataReserva, horaInicio, horaFim, finalidade, status, maxParticipantes) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, reserva.getIdSala());
            stmt.setInt(2, reserva.getIdUsuario());
            stmt.setString(3, reserva.getTurma());
            stmt.setString(4, reserva.getTipoSala());
            stmt.setDate(5, Date.valueOf(reserva.getDataReserva()));
            stmt.setTime(6, Time.valueOf(reserva.getHoraInicio()));
            stmt.setTime(7, Time.valueOf(reserva.getHoraFim()));
            stmt.setString(8, reserva.getFinalidade());
            stmt.setString(9, reserva.getStatus().toString());
            stmt.setInt(10, reserva.getMaxParticipantes());
            
            int linhasAfetadas = stmt.executeUpdate();
            
            if (linhasAfetadas > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        reserva.setIdReserva(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao criar reserva: " + e.getMessage());
        }
        return false;
    }
    
    // READ - Buscar reserva por ID
    public Reserva buscarPorId(int idReserva) {
        String sql = "SELECT * FROM Reserva WHERE idReserva = ?";
        Reserva reserva = null;
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idReserva);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                reserva = criarReservaFromResultSet(rs);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao buscar reserva: " + e.getMessage());
        }
        return reserva;
    }
    
    // READ - Listar reservas por usuário
    public List<Reserva> listarPorUsuario(int idUsuario) {
        String sql = "SELECT * FROM Reserva WHERE idUsuario = ? ORDER BY dataReserva DESC, horaInicio DESC";
        List<Reserva> reservas = new ArrayList<>();
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                reservas.add(criarReservaFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao listar reservas do usuário: " + e.getMessage());
        }
        return reservas;
    }
    
    // UPDATE - Cancelar reserva
    public boolean cancelarReserva(int idReserva) {
        String sql = "UPDATE Reserva SET status = 'CANCELADA' WHERE idReserva = ?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idReserva);
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println("Erro ao cancelar reserva: " + e.getMessage());
        }
        return false;
    }
    
    // Método auxiliar para criar objeto Reserva do ResultSet
    private Reserva criarReservaFromResultSet(ResultSet rs) throws SQLException {
        Reserva reserva = new Reserva();
        
        reserva.setIdReserva(rs.getInt("idReserva"));
        reserva.setIdSala(rs.getInt("idSala"));
        reserva.setIdUsuario(rs.getInt("idUsuario"));
        reserva.setTurma(rs.getString("turma"));
        reserva.setTipoSala(rs.getString("tipoSala"));
        reserva.setDataReserva(rs.getDate("dataReserva").toLocalDate());
        reserva.setHoraInicio(rs.getTime("horaInicio").toLocalTime());
        reserva.setHoraFim(rs.getTime("horaFim").toLocalTime());
        reserva.setFinalidade(rs.getString("finalidade"));
        reserva.setStatus(StatusReserva.valueOf(rs.getString("status")));
        reserva.setMaxParticipantes(rs.getInt("maxParticipantes"));
        
        return reserva;
    }
}