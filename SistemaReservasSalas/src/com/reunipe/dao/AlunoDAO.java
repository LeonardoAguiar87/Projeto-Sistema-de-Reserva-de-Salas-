package com.reunipe.dao;

import com.reunipe.model.Aluno;
import com.reunipe.database.ConexaoBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    
    // CREATE - Cadastrar aluno
    public boolean cadastrarAluno(Aluno aluno) {
        // Primeiro cadastra o usuário
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if (!usuarioDAO.cadastrarUsuario(aluno)) {
            return false;
        }
        
        // Depois cadastra como aluno
        String sql = "INSERT INTO Aluno (idUsuario, matricula, instituicaoEnsino) VALUES (?, ?, ?)";
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, aluno.getIdUsuario());
            stmt.setString(2, aluno.getMatricula());
            stmt.setString(3, aluno.getInstituicaoEnsino());
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar aluno: " + e.getMessage());
        }
        return false;
    }
    
    // READ - Buscar aluno por ID do usuário
    public Aluno buscarPorUsuarioId(int idUsuario) {
        String sql = "SELECT a.*, u.* FROM Aluno a INNER JOIN Usuario u ON a.idUsuario = u.idUsuario WHERE a.idUsuario = ?";
        Aluno aluno = null;
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                aluno = criarAlunoFromResultSet(rs);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao buscar aluno: " + e.getMessage());
        }
        return aluno;
    }
    
    // READ - Buscar aluno por matrícula
    public Aluno buscarPorMatricula(String matricula) {
        String sql = "SELECT a.*, u.* FROM Aluno a INNER JOIN Usuario u ON a.idUsuario = u.idUsuario WHERE a.matricula = ?";
        Aluno aluno = null;
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, matricula);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                aluno = criarAlunoFromResultSet(rs);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao buscar aluno por matrícula: " + e.getMessage());
        }
        return aluno;
    }
    
    // READ - Listar todos os alunos
    public List<Aluno> listarTodos() {
        String sql = "SELECT a.*, u.* FROM Aluno a INNER JOIN Usuario u ON a.idUsuario = u.idUsuario WHERE u.ativo = true";
        List<Aluno> alunos = new ArrayList<>();
        
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                alunos.add(criarAlunoFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao listar alunos: " + e.getMessage());
        }
        return alunos;
    }
    
    // Método auxiliar para criar objeto Aluno do ResultSet
    private Aluno criarAlunoFromResultSet(ResultSet rs) throws SQLException {
        Aluno aluno = new Aluno();
        
        // Dados do Usuario
        aluno.setIdUsuario(rs.getInt("idUsuario"));
        aluno.setNome(rs.getString("nome"));
        aluno.setSobrenome(rs.getString("sobrenome"));
        aluno.setEmail(rs.getString("email"));
        aluno.setCpf(rs.getString("cpf"));
        aluno.setTelefone(rs.getString("telefone"));
        aluno.setSenha(rs.getString("senha"));
        aluno.setDataCadastro(rs.getDate("dataCadastro").toLocalDate());
        aluno.setAtivo(rs.getBoolean("ativo"));
        
        // Dados específicos do Aluno
        aluno.setMatricula(rs.getString("matricula"));
        aluno.setInstituicaoEnsino(rs.getString("instituicaoEnsino"));
        
        return aluno;
    }
}