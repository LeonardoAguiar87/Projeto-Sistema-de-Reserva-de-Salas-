package com.reunipe.dao;

import com.reunipe.model.Professor;
import com.reunipe.database.ConexaoBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {
    
    // CREATE - Cadastrar professor
    public boolean cadastrarProfessor(Professor professor) {
        // Primeiro cadastra o usuário
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if (!usuarioDAO.cadastrarUsuario(professor)) {
            return false;
        }
        
        // Depois cadastra como professor
        String sql = "INSERT INTO Professor (idUsuario, CNDB, instituicaoEnsino) VALUES (?, ?, ?)";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, professor.getIdUsuario());
            stmt.setString(2, professor.getCndb());
            stmt.setString(3, professor.getInstituicaoEnsino());
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar professor: " + e.getMessage());
        }
        return false;
    }
    
    // READ - Buscar professor por ID do usuário
    public Professor buscarPorUsuarioId(int idUsuario) {
        String sql = "SELECT p.*, u.* FROM Professor p INNER JOIN Usuario u ON p.idUsuario = u.idUsuario WHERE p.idUsuario = ?";
        Professor professor = null;
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                professor = criarProfessorFromResultSet(rs);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao buscar professor: " + e.getMessage());
        }
        return professor;
    }
    
    // Método auxiliar para criar objeto Professor do ResultSet
    private Professor criarProfessorFromResultSet(ResultSet rs) throws SQLException {
        Professor professor = new Professor();
        
        // Dados do Usuario
        professor.setIdUsuario(rs.getInt("idUsuario"));
        professor.setNome(rs.getString("nome"));
        professor.setSobrenome(rs.getString("sobrenome"));
        professor.setEmail(rs.getString("email"));
        professor.setCpf(rs.getString("cpf"));
        professor.setTelefone(rs.getString("telefone"));
        professor.setSenha(rs.getString("senha"));
        professor.setDataCadastro(rs.getDate("dataCadastro").toLocalDate());
        professor.setAtivo(rs.getBoolean("ativo"));
        
        // Dados específicos do Professor
        professor.setCndb(rs.getString("CNDB"));
        professor.setInstituicaoEnsino(rs.getString("instituicaoEnsino"));
        
        return professor;
    }
}