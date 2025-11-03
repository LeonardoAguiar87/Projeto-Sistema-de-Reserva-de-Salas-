package com.reunipe.dao;

import com.reunipe.model.Usuario;
import com.reunipe.database.ConexaoBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    
    public boolean cadastrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuario (nome, sobrenome, email, cpf, telefone, senha, dataCadastro, ativo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSobrenome());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getCpf());
            stmt.setString(5, usuario.getTelefone());
            stmt.setString(6, usuario.getSenha());
            stmt.setDate(7, Date.valueOf(usuario.getDataCadastro()));
            stmt.setBoolean(8, usuario.isAtivo());
            
            int linhasAfetadas = stmt.executeUpdate();
            
            if (linhasAfetadas > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        usuario.setIdUsuario(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar usuario: " + e.getMessage());
        }
        return false;
    }
    
    public Usuario buscarPorId(int idUsuario) {
        String sql = "SELECT * FROM Usuario WHERE idUsuario = ?";
        Usuario usuario = null;
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                usuario = criarUsuarioFromResultSet(rs);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuario: " + e.getMessage());
        }
        return usuario;
    }
    
    public Usuario buscarPorEmail(String email) {
        String sql = "SELECT * FROM Usuario WHERE email = ?";
        Usuario usuario = null;
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                usuario = criarUsuarioFromResultSet(rs);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuario por email: " + e.getMessage());
        }
        return usuario;
    }
    
    public List<Usuario> listarTodos() {
        String sql = "SELECT * FROM Usuario WHERE ativo = true";
        List<Usuario> usuarios = new ArrayList<>();
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                usuarios.add(criarUsuarioFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao listar usuarios: " + e.getMessage());
        }
        return usuarios;
    }
    
    public boolean atualizarUsuario(Usuario usuario) {
        String sql = "UPDATE Usuario SET nome = ?, sobrenome = ?, email = ?, telefone = ?, senha = ? WHERE idUsuario = ?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSobrenome());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getTelefone());
            stmt.setString(5, usuario.getSenha());
            stmt.setInt(6, usuario.getIdUsuario());
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar usuario: " + e.getMessage());
        }
        return false;
    }
    
    public boolean desativarUsuario(int idUsuario) {
        String sql = "UPDATE Usuario SET ativo = false WHERE idUsuario = ?";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idUsuario);
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println("Erro ao desativar usuario: " + e.getMessage());
        }
        return false;
    }
    
    private Usuario criarUsuarioFromResultSet(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario() {
            @Override
            public boolean login(String email, String senha) {
                return this.getEmail().equals(email) && this.getSenha().equals(senha);
            }
            
            @Override
            public void logout() {
                System.out.println("Usuario fez logout");
            }
        };
        
        usuario.setIdUsuario(rs.getInt("idUsuario"));
        usuario.setNome(rs.getString("nome"));
        usuario.setSobrenome(rs.getString("sobrenome"));
        usuario.setEmail(rs.getString("email"));
        usuario.setCpf(rs.getString("cpf"));
        usuario.setTelefone(rs.getString("telefone"));
        usuario.setSenha(rs.getString("senha"));
        usuario.setDataCadastro(rs.getDate("dataCadastro").toLocalDate());
        usuario.setAtivo(rs.getBoolean("ativo"));
        
        return usuario;
    }
    
    public boolean verificarLogin(String email, String senha) {
        Usuario usuario = buscarPorEmail(email);
        return usuario != null && usuario.getSenha().equals(senha) && usuario.isAtivo();
    }
}