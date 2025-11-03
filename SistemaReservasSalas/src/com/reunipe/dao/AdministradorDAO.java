package com.reunipe.dao;

import com.reunipe.model.Administrador;
import com.reunipe.database.ConexaoBD;
import java.sql.*;

public class AdministradorDAO {
    
    // CREATE - Cadastrar administrador
    public boolean cadastrarAdministrador(Administrador admin) {
        // Primeiro cadastra o usuário
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if (!usuarioDAO.cadastrarUsuario(admin)) {
            return false;
        }
        
        // Depois cadastra como administrador
        String sql = "INSERT INTO Administrador (idUsuario, departamento, superUsuario) VALUES (?, ?, ?)";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, admin.getIdUsuario());
            stmt.setString(2, admin.getDepartamento());
            stmt.setBoolean(3, admin.isSuperUsuario());
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar administrador: " + e.getMessage());
        }
        return false;
    }
    
    // READ - Buscar administrador por ID do usuário
    public Administrador buscarPorUsuarioId(int idUsuario) {
        String sql = "SELECT a.*, u.* FROM Administrador a INNER JOIN Usuario u ON a.idUsuario = u.idUsuario WHERE a.idUsuario = ?";
        Administrador admin = null;
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                admin = criarAdministradorFromResultSet(rs);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao buscar administrador: " + e.getMessage());
        }
        return admin;
    }
    
    // Método auxiliar para criar objeto Administrador do ResultSet
    private Administrador criarAdministradorFromResultSet(ResultSet rs) throws SQLException {
        Administrador admin = new Administrador();
        
        // Dados do Usuario
        admin.setIdUsuario(rs.getInt("idUsuario"));
        admin.setNome(rs.getString("nome"));
        admin.setSobrenome(rs.getString("sobrenome"));
        admin.setEmail(rs.getString("email"));
        admin.setCpf(rs.getString("cpf"));
        admin.setTelefone(rs.getString("telefone"));
        admin.setSenha(rs.getString("senha"));
        admin.setDataCadastro(rs.getDate("dataCadastro").toLocalDate());
        admin.setAtivo(rs.getBoolean("ativo"));
        
        // Dados específicos do Administrador
        admin.setDepartamento(rs.getString("departamento"));
        admin.setSuperUsuario(rs.getBoolean("superUsuario"));
        
        return admin;
    }
}