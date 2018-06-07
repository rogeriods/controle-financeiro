-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 06-Jun-2018 às 21:57
-- Versão do servidor: 10.1.32-MariaDB
-- PHP Version: 7.2.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rdsolutions`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `ctlf_despesas`
--

CREATE TABLE `ctlf_despesas` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_tipo_despesa` int(11) NOT NULL,
  `data` date NOT NULL,
  `data_vencimento` date NOT NULL,
  `data_pagamento` date DEFAULT NULL,
  `descricao` varchar(50) DEFAULT NULL,
  `valor` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `ctlf_despesas`
--

INSERT INTO `ctlf_despesas` (`id`, `id_usuario`, `id_tipo_despesa`, `data`, `data_vencimento`, `data_pagamento`, `descricao`, `valor`) VALUES
(1, 1, 2, '2016-07-22', '2016-07-27', NULL, 'Portal Vila Rica', '450.00'),
(2, 1, 7, '2016-07-22', '2016-07-27', NULL, 'Montana Parcela 60/60', '732.29'),
(5, 1, 3, '2016-07-22', '2016-07-22', NULL, 'Studio ALpha', '10.10'),
(6, 1, 33, '2016-07-22', '2016-07-30', NULL, 'teste', '14.50'),
(7, 1, 38, '2016-07-22', '2016-07-22', NULL, 'Ultra Som', '150.00');

-- --------------------------------------------------------

--
-- Estrutura da tabela `ctlf_receitas`
--

CREATE TABLE `ctlf_receitas` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_tipo_receita` int(11) NOT NULL,
  `data` date NOT NULL,
  `data_recebimento` date NOT NULL,
  `descricao` varchar(50) DEFAULT NULL,
  `valor` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `ctlf_tipos_despesas`
--

CREATE TABLE `ctlf_tipos_despesas` (
  `id` int(11) NOT NULL,
  `descricao` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `ctlf_tipos_despesas`
--

INSERT INTO `ctlf_tipos_despesas` (`id`, `descricao`) VALUES
(1, 'Aluguel'),
(2, 'Condomínio'),
(3, 'Prestação da Casa'),
(4, 'Seguro da Casa'),
(5, 'Diarista'),
(6, 'Mensalista'),
(7, 'Prestação do Carro'),
(8, 'Seguro do Carro'),
(9, 'Estacionamento'),
(10, 'Seguro Saúde'),
(11, 'Plano de Saúde'),
(12, 'Colégio'),
(13, 'Faculdade'),
(14, 'Curso'),
(15, 'IPTU'),
(16, 'IPVA'),
(17, 'Seguro de Vida'),
(18, 'Luz'),
(19, 'Água'),
(20, 'Telefone'),
(21, 'Telefone Celular'),
(22, 'Gás'),
(23, 'Mensalidade TV'),
(24, 'Internet'),
(25, 'Metrô'),
(26, 'Ônibus'),
(27, 'Combustível'),
(28, 'Estacionamento'),
(29, 'Supermercado'),
(30, 'Feira'),
(31, 'Padaria'),
(32, 'Medicamentos'),
(33, 'Cabeleireiro'),
(34, 'Manicure'),
(35, 'Esteticista'),
(36, 'Academia'),
(37, 'Clube'),
(38, 'Médico'),
(39, 'Dentista'),
(40, 'Hospital'),
(41, 'Carro'),
(42, 'Casa'),
(43, 'Material Escolar'),
(44, 'Uniforme'),
(45, 'Viagens'),
(46, 'Cinema/Teatro'),
(47, 'Restaurantes/Bares'),
(48, 'Locadora DVD'),
(49, 'Roupas'),
(50, 'Calçados'),
(51, 'Acessórios'),
(52, 'Presentes'),
(53, 'Outros');

-- --------------------------------------------------------

--
-- Estrutura da tabela `ctlf_tipos_receitas`
--

CREATE TABLE `ctlf_tipos_receitas` (
  `id` int(11) NOT NULL,
  `descricao` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `ctlf_tipos_receitas`
--

INSERT INTO `ctlf_tipos_receitas` (`id`, `descricao`) VALUES
(1, 'Salário'),
(2, 'Aluguel'),
(3, 'Pensão'),
(4, 'Horas Extras'),
(5, '13º Salário'),
(6, 'Férias'),
(7, 'Ações'),
(8, 'Tesouro Direto'),
(9, 'Renda Fixa'),
(10, 'Previdência Privada'),
(11, 'Outros');

-- --------------------------------------------------------

--
-- Estrutura da tabela `ctlf_usuarios`
--

CREATE TABLE `ctlf_usuarios` (
  `id` int(11) NOT NULL,
  `nome` varchar(60) NOT NULL,
  `email` varchar(80) NOT NULL,
  `senha` varchar(80) NOT NULL,
  `data` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `ctlf_usuarios`
--

INSERT INTO `ctlf_usuarios` (`id`, `nome`, `email`, `senha`, `data`) VALUES
(1, 'Administrador', 'admin@admin.com.br', 'd033e22ae348aeb5660fc2140aec35850c4da997', '2016-07-18 16:43:27');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ctlf_despesas`
--
ALTER TABLE `ctlf_despesas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_desp_usuario_idx` (`id_usuario`),
  ADD KEY `fk_desp_tipo_despesa_idx` (`id_tipo_despesa`);

--
-- Indexes for table `ctlf_receitas`
--
ALTER TABLE `ctlf_receitas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_usuario_idx` (`id_usuario`),
  ADD KEY `fk_tipo_receita_idx` (`id_tipo_receita`);

--
-- Indexes for table `ctlf_tipos_despesas`
--
ALTER TABLE `ctlf_tipos_despesas`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ctlf_tipos_receitas`
--
ALTER TABLE `ctlf_tipos_receitas`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ctlf_usuarios`
--
ALTER TABLE `ctlf_usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email_UNIQUE` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ctlf_despesas`
--
ALTER TABLE `ctlf_despesas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `ctlf_receitas`
--
ALTER TABLE `ctlf_receitas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ctlf_tipos_despesas`
--
ALTER TABLE `ctlf_tipos_despesas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- AUTO_INCREMENT for table `ctlf_tipos_receitas`
--
ALTER TABLE `ctlf_tipos_receitas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `ctlf_usuarios`
--
ALTER TABLE `ctlf_usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `ctlf_despesas`
--
ALTER TABLE `ctlf_despesas`
  ADD CONSTRAINT `fk_desp_tipo_despesa` FOREIGN KEY (`id_tipo_despesa`) REFERENCES `ctlf_tipos_despesas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_desp_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `ctlf_usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `ctlf_receitas`
--
ALTER TABLE `ctlf_receitas`
  ADD CONSTRAINT `fk_rece_tipo_receita` FOREIGN KEY (`id_tipo_receita`) REFERENCES `ctlf_tipos_receitas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_rece_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `ctlf_usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
