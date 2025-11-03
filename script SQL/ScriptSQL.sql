-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema db_ReuniPe
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `db_ReuniPe`;
CREATE SCHEMA IF NOT EXISTS `db_ReuniPe` DEFAULT CHARACTER SET utf8;
USE `db_ReuniPe`;

-- -----------------------------------------------------
-- Table `db_ReuniPe`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_ReuniPe`.`Usuario` (
  `idUsuario` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `sobrenome` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `cpf` CHAR(11) NOT NULL,
  `telefone` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(255) NOT NULL,
  `dataCadastro` DATE NOT NULL,
  `ativo` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`idUsuario`),
  UNIQUE INDEX `cpf_UNIQUE` (`cpf`),
  UNIQUE INDEX `email_UNIQUE` (`email`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `db_ReuniPe`.`Aluno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_ReuniPe`.`Aluno` (
  `idAluno` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idUsuario` INT UNSIGNED NOT NULL,
  `matricula` VARCHAR(45) NOT NULL,
  `instituicaoEnsino` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idAluno`),
  INDEX `fk_usuario_aluno_idx` (`idUsuario`),
  UNIQUE INDEX `matricula_UNIQUE` (`matricula`),
  CONSTRAINT `fk_usuario_aluno`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `db_ReuniPe`.`Usuario` (`idUsuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `db_ReuniPe`.`Professor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_ReuniPe`.`Professor` (
  `idProfessor` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idUsuario` INT UNSIGNED NOT NULL,
  `CNDB` VARCHAR(45) NOT NULL,
  `instituicaoEnsino` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idProfessor`),
  UNIQUE INDEX `CNDB_UNIQUE` (`CNDB`),
  INDEX `fk_usuario_professor_idx` (`idUsuario`),
  CONSTRAINT `fk_usuario_professor`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `db_ReuniPe`.`Usuario` (`idUsuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `db_ReuniPe`.`Administrador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_ReuniPe`.`Administrador` (
  `idAdmin` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idUsuario` INT UNSIGNED NOT NULL,
  `departamento` VARCHAR(45) NOT NULL,
  `superUsuario` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`idAdmin`),
  INDEX `fk_usuario_admin_idx` (`idUsuario`),
  CONSTRAINT `fk_usuario_admin`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `db_ReuniPe`.`Usuario` (`idUsuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `db_ReuniPe`.`Sala`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_ReuniPe`.`Sala` (
  `idSala` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `numero` INT UNSIGNED NOT NULL,
  `andar` INT UNSIGNED NOT NULL,
  `campus` VARCHAR(45) NOT NULL,
  `tipo` ENUM('NORMAL', 'LABORATORIO') NOT NULL DEFAULT 'NORMAL',
  `capacidade` INT UNSIGNED NOT NULL,
  `ativo` TINYINT NOT NULL DEFAULT 1,
  `bloqueada` TINYINT NOT NULL DEFAULT 0, -- ✅ Adicionado para substituir bloqueioSala
  `motivoBloqueio` VARCHAR(100) NULL,     -- ✅ Adicionado para substituir bloqueioSala
  PRIMARY KEY (`idSala`),
  UNIQUE INDEX `sala_unica` (`numero`, `andar`, `campus`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `db_ReuniPe`.`SalaLab`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_ReuniPe`.`SalaLab` (
  `idSalaLab` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idSala` INT UNSIGNED NOT NULL,
  `tipoLaboratorio` VARCHAR(45) NOT NULL,
  `equipamentos` TEXT NULL,
  `softwareInstalado` TEXT NULL,
  PRIMARY KEY (`idSalaLab`),
  INDEX `fk_sala_laboratorio_idx` (`idSala`),
  CONSTRAINT `fk_sala_laboratorio`
    FOREIGN KEY (`idSala`)
    REFERENCES `db_ReuniPe`.`Sala` (`idSala`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `db_ReuniPe`.`Reserva`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_ReuniPe`.`Reserva` (
  `idReserva` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idSala` INT UNSIGNED NOT NULL,
  `idUsuario` INT UNSIGNED NOT NULL,
  `turma` VARCHAR(45) NOT NULL,
  `tipoSala` VARCHAR(45) NOT NULL,
  `dataReserva` DATE NOT NULL,
  `horaInicio` TIME NOT NULL,
  `horaFim` TIME NOT NULL,
  `finalidade` VARCHAR(100) NOT NULL,
  `status` ENUM('PENDENTE', 'CONFIRMADA', 'CANCELADA', 'FINALIZADA') NOT NULL DEFAULT 'PENDENTE',
  `maxParticipantes` INT UNSIGNED NOT NULL DEFAULT 1,
  `participantesAtuais` INT UNSIGNED NOT NULL DEFAULT 1, -- ✅ Adicionado para substituir participanteReserva
  `logAlteracoes` TEXT NULL, -- ✅ Adicionado para substituir logReserva (JSON ou texto)
  `dataCriacao` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idReserva`),
  INDEX `fk_sala_idx` (`idSala`),
  INDEX `fk_Usuario_idx` (`idUsuario`),
  INDEX `idx_data_reserva` (`dataReserva`),
  CONSTRAINT `fk_sala_reserva`
    FOREIGN KEY (`idSala`)
    REFERENCES `db_ReuniPe`.`Sala` (`idSala`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Usuario_reserva`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `db_ReuniPe`.`Usuario` (`idUsuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- DADOS INICIAIS PARA TESTE
INSERT INTO Sala (numero, andar, campus, capacidade, ativo, tipo, bloqueada) 
VALUES 
(101, 1, 'Campus I', 30, 1, 'NORMAL', 0),
(201, 2, 'Campus I', 25, 1, 'NORMAL', 0),
(301, 3, 'Campus I', 20, 1, 'LABORATORIO', 0);

-- Inserir usuários de teste
INSERT INTO Usuario (nome, sobrenome, email, cpf, telefone, senha, dataCadastro, ativo) VALUES
('Joao', 'Silva', 'joao.silva@email.com', '12345678900', '11999999999', 'senha123', CURDATE(), 1),
('Maria', 'Santos', 'maria.santos@email.com', '98765432100', '11888888888', 'senha456', CURDATE(), 1),
('Admin', 'Sistema', 'admin@sistema.com', '11122233344', '11777777777', 'admin123', CURDATE(), 1);

-- Inserir como Aluno, Professor e Administrador
INSERT INTO Aluno (idUsuario, matricula, instituicaoEnsino) VALUES
(1, '20230001', 'Unibra');

INSERT INTO Professor (idUsuario, CNDB, instituicaoEnsino) VALUES
(2, 'CNDB12345', 'Unibra');

INSERT INTO Administrador (idUsuario, departamento, superUsuario) VALUES
(3, 'TI', 1);