-- MySQL Script generated by MySQL Workbench
-- 11/05/16 05:28:32
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema sgpo1
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sgpo1
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sgpo1` DEFAULT CHARACTER SET utf8 ;
USE `sgpo1` ;

-- -----------------------------------------------------
-- Table `sgpo1`.`universidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpo1`.`universidade` (
  `iduniversidade` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `cgc` VARCHAR(45) NOT NULL,
  `obs` TEXT NULL,
  PRIMARY KEY (`iduniversidade`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sgpo1`.`curso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpo1`.`curso` (
  `idcurso` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(15) NOT NULL,
  `obs` TEXT NULL,
  `universidade_iduniversidade` INT(11) NOT NULL,
  PRIMARY KEY (`idcurso`),
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC),
  INDEX `fk_curso_universidade1_idx` (`universidade_iduniversidade` ASC),
  CONSTRAINT `fk_curso_universidade1`
    FOREIGN KEY (`universidade_iduniversidade`)
    REFERENCES `sgpo1`.`universidade` (`iduniversidade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sgpo1`.`turma`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpo1`.`turma` (
  `idturma` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(15) NOT NULL,
  `sala` VARCHAR(10) NOT NULL,
  `horario` VARCHAR(45) NOT NULL,
  `carga_horaria` VARCHAR(45) NOT NULL,
  `obs` TEXT NULL,
  `curso_idcurso` INT(11) NOT NULL,
  `universidade_iduniversidade` INT(11) NOT NULL,
  PRIMARY KEY (`idturma`),
  INDEX `fk_turma_curso1_idx` (`curso_idcurso` ASC),
  INDEX `fk_turma_universidade1_idx` (`universidade_iduniversidade` ASC),
  CONSTRAINT `fk_turma_curso1`
    FOREIGN KEY (`curso_idcurso`)
    REFERENCES `sgpo1`.`curso` (`idcurso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_turma_universidade1`
    FOREIGN KEY (`universidade_iduniversidade`)
    REFERENCES `sgpo1`.`universidade` (`iduniversidade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sgpo1`.`aluno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpo1`.`aluno` (
  `idaluno` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(60) NOT NULL,
  `sexo` VARCHAR(45) NOT NULL,
  `data_nascimento` VARCHAR(45) NOT NULL,
  `naturalidade` VARCHAR(45) NOT NULL,
  `morada` VARCHAR(50) NOT NULL,
  `ano_escolar` INT(1) NOT NULL,
  `ano_lectivo` INT(4) NOT NULL,
  `telefone` INT(11) NULL,
  `telefone_alter` INT(11) NULL,
  `email` VARCHAR(60) NULL,
  `obs` TEXT NULL DEFAULT NULL,
  `turma_idturma` INT(11) NOT NULL,
  `universidade_iduniversidade` INT(11) NOT NULL,
  `curso_idcurso` INT(11) NOT NULL,
  PRIMARY KEY (`idaluno`),
  INDEX `fk_aluno_turma1_idx` (`turma_idturma` ASC),
  INDEX `fk_aluno_universidade1_idx` (`universidade_iduniversidade` ASC),
  INDEX `fk_aluno_curso1_idx` (`curso_idcurso` ASC),
  CONSTRAINT `fk_aluno_turma1`
    FOREIGN KEY (`turma_idturma`)
    REFERENCES `sgpo1`.`turma` (`idturma`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_aluno_universidade1`
    FOREIGN KEY (`universidade_iduniversidade`)
    REFERENCES `sgpo1`.`universidade` (`iduniversidade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_aluno_curso1`
    FOREIGN KEY (`curso_idcurso`)
    REFERENCES `sgpo1`.`curso` (`idcurso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sgpo1`.`disciplina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpo1`.`disciplina` (
  `iddisciplina` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `obs` TEXT(100) NULL,
  `curso_idcurso` INT(11) NOT NULL,
  PRIMARY KEY (`iddisciplina`),
  INDEX `fk_disciplina_curso1_idx` (`curso_idcurso` ASC),
  CONSTRAINT `fk_disciplina_curso1`
    FOREIGN KEY (`curso_idcurso`)
    REFERENCES `sgpo1`.`curso` (`idcurso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sgpo1`.`prova`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpo1`.`prova` (
  `idprova` INT(11) NOT NULL AUTO_INCREMENT,
  `nota` INT(11) NOT NULL,
  `obs` TEXT NULL DEFAULT NULL,
  `disciplina_iddisciplina` INT(11) NOT NULL,
  `aluno_idaluno` INT(11) NOT NULL,
  PRIMARY KEY (`idprova`, `aluno_idaluno`),
  INDEX `fk_prova_disciplina1_idx` (`disciplina_iddisciplina` ASC),
  INDEX `fk_prova_aluno1_idx` (`aluno_idaluno` ASC),
  CONSTRAINT `fk_prova_disciplina1`
    FOREIGN KEY (`disciplina_iddisciplina`)
    REFERENCES `sgpo1`.`disciplina` (`iddisciplina`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_prova_aluno1`
    FOREIGN KEY (`aluno_idaluno`)
    REFERENCES `sgpo1`.`aluno` (`idaluno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sgpo1`.`trabalho`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpo1`.`trabalho` (
  `idtrabalho` INT(11) NOT NULL AUTO_INCREMENT,
  `nota` FLOAT(2) NOT NULL,
  `obs` TEXT NULL DEFAULT NULL,
  `disciplina_iddisciplina` INT(11) NOT NULL,
  `aluno_idaluno` INT(11) NOT NULL,
  PRIMARY KEY (`idtrabalho`, `aluno_idaluno`),
  INDEX `fk_trabalho_disciplina1_idx` (`disciplina_iddisciplina` ASC),
  INDEX `fk_trabalho_aluno1_idx` (`aluno_idaluno` ASC),
  CONSTRAINT `fk_trabalho_disciplina1`
    FOREIGN KEY (`disciplina_iddisciplina`)
    REFERENCES `sgpo1`.`disciplina` (`iddisciplina`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_trabalho_aluno1`
    FOREIGN KEY (`aluno_idaluno`)
    REFERENCES `sgpo1`.`aluno` (`idaluno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sgpo1`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpo1`.`usuario` (
  `nome` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NULL,
  `endereco` VARCHAR(45) NULL,
  `nome_login` VARCHAR(10) NOT NULL,
  `senha` VARCHAR(11) NOT NULL,
  `obs` TEXT NULL DEFAULT NULL)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sgpo1`.`Nota`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpo1`.`Nota` (
  `idnota` INT NOT NULL AUTO_INCREMENT,
  `mac` INT(2) NOT NULL,
  `media` INT(2) NOT NULL,
  `obs` TEXT NULL,
  `disciplina_iddisciplina` INT(11) NOT NULL,
  `prova_idprova` INT(11) NOT NULL,
  `prova_aluno_idaluno` INT(11) NOT NULL,
  `trabalho_idtrabalho` INT(11) NOT NULL,
  `trabalho_aluno_idaluno` INT(11) NOT NULL,
  PRIMARY KEY (`idnota`, `disciplina_iddisciplina`, `prova_idprova`, `prova_aluno_idaluno`, `trabalho_idtrabalho`, `trabalho_aluno_idaluno`),
  INDEX `fk_Nota_prova1_idx` (`prova_idprova` ASC, `prova_aluno_idaluno` ASC),
  INDEX `fk_Nota_trabalho1_idx` (`trabalho_idtrabalho` ASC, `trabalho_aluno_idaluno` ASC),
  CONSTRAINT `fk_Nota_disciplina1`
    FOREIGN KEY (`disciplina_iddisciplina`)
    REFERENCES `sgpo1`.`disciplina` (`iddisciplina`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Nota_prova1`
    FOREIGN KEY (`prova_idprova` , `prova_aluno_idaluno`)
    REFERENCES `sgpo1`.`prova` (`idprova` , `aluno_idaluno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Nota_trabalho1`
    FOREIGN KEY (`trabalho_idtrabalho` , `trabalho_aluno_idaluno`)
    REFERENCES `sgpo1`.`trabalho` (`idtrabalho` , `aluno_idaluno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;

use sgpo1;
insert into usuario(nome, email, endereco, nome_login, senha, obs) 
values('Admin','','', 'admin', 'admin',''); 