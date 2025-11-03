package com.reunipe.main;

import com.reunipe.model.*;
import com.reunipe.dao.*;
import com.reunipe.database.ConexaoBD;
import com.reunipe.enums.TipoSala;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE RESERVA DE SALAS - ReuniPe ===");
        
        ConexaoBD.testarConexao();
        
        System.out.println("\n--- TESTANDO SISTEMA COM DADOS EXISTENTES ---");
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        AlunoDAO alunoDAO = new AlunoDAO();
        ProfessorDAO professorDAO = new ProfessorDAO();
        SalaDAO salaDAO = new SalaDAO();
        ReservaDAO reservaDAO = new ReservaDAO();
        
        // USAR USUÁRIOS JÁ EXISTENTES
        System.out.println("\n1. BUSCANDO USUARIOS EXISTENTES:");
        
        // Buscar aluno João pelo CPF
        Usuario usuarioJoao = usuarioDAO.buscarPorEmail("joao.silva@email.com");
        if (usuarioJoao != null) {
            System.out.println("Aluno encontrado: " + usuarioJoao.getNome() + " (ID: " + usuarioJoao.getIdUsuario() + ")");
        } else {
            System.out.println("Aluno João não encontrado");
        }
        
        // Buscar professora Maria pelo CPF  
        Usuario usuarioMaria = usuarioDAO.buscarPorEmail("maria.santos@email.com");
        if (usuarioMaria != null) {
            System.out.println("Professora encontrada: " + usuarioMaria.getNome() + " (ID: " + usuarioMaria.getIdUsuario() + ")");
        } else {
            System.out.println("Professora Maria não encontrada");
        }
        
        System.out.println("\n2. TESTANDO SALAS:");
        
        // Listar salas existentes
        List<Sala> salas = salaDAO.listarSalasDisponiveis();
        if (salas.isEmpty()) {
            System.out.println("NENHUMA SALA CADASTRADA - vamos criar uma...");
            
            // Tentar criar uma sala
            Sala novaSala = new Sala(102, 1, "Campus I", 25, TipoSala.NORMAL);
            if (salaDAO.cadastrarSala(novaSala)) {
                System.out.println("SALA CRIADA COM SUCESSO! ID: " + novaSala.getIdSala());
                salas.add(novaSala);
            } else {
                System.out.println("ERRO AO CRIAR SALA - criando manualmente no banco...");
            }
        } else {
            System.out.println("Salas encontradas:");
            for (Sala sala : salas) {
                System.out.println("Sala " + sala.getNumero() + " - Capacidade: " + sala.getCapacidade());
            }
        }
        
        // Se temos salas, testar reserva
        if (!salas.isEmpty() && usuarioJoao != null) {
            Sala primeiraSala = salas.get(0);
            
            System.out.println("\n3. TESTANDO RESERVA:");
            
            // Verificar disponibilidade
            boolean disponivel = salaDAO.verificarDisponibilidade(primeiraSala.getIdSala(), "2024-01-20", "14:00", "16:00");
            System.out.println("Sala " + primeiraSala.getNumero() + " disponível: " + disponivel);
            
            if (disponivel) {
                // Criar reserva
                Reserva reserva = new Reserva(
                    primeiraSala.getIdSala(), 
                    usuarioJoao.getIdUsuario(), 
                    "EngSoft-A", 
                    "NORMAL",
                    LocalDate.of(2024, 1, 20), 
                    LocalTime.of(14, 0), 
                    LocalTime.of(16, 0),
                    "Estudo em grupo - Java", 
                    5
                );
                
                if (reservaDAO.criarReserva(reserva)) {
                    System.out.println("RESERVA CRIADA COM SUCESSO! ID: " + reserva.getIdReserva());
                    
                    // Listar reservas do aluno
                    System.out.println("\n4. RESERVAS DO ALUNO:");
                    List<Reserva> reservasAluno = reservaDAO.listarPorUsuario(usuarioJoao.getIdUsuario());
                    for (Reserva r : reservasAluno) {
                        System.out.println("Reserva ID: " + r.getIdReserva() + 
                                         " - Sala: " + r.getIdSala() + 
                                         " - Data: " + r.getDataReserva() +
                                         " - Status: " + r.getStatus());
                    }
                } else {
                    System.out.println("ERRO AO CRIAR RESERVA");
                }
            }
        }
        
        System.out.println("\n5. TODOS OS USUARIOS CADASTRADOS:");
        List<Usuario> usuarios = usuarioDAO.listarTodos();
        for (Usuario u : usuarios) {
            System.out.println(u.getNome() + " " + u.getSobrenome() + " - " + u.getEmail() + " - Ativo: " + u.isAtivo());
        }
        
        System.out.println("\n=== SISTEMA TESTADO! ===");
    }
}