-- MySQL dump 10.13  Distrib 8.0.39, for Linux (x86_64)
--
-- Host: localhost    Database: app_revistas
-- ------------------------------------------------------
-- Server version	8.0.39-0ubuntu0.24.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ARCHIVOS_REVISTA`
--

DROP TABLE IF EXISTS `ARCHIVOS_REVISTA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ARCHIVOS_REVISTA` (
  `id_archivo` bigint NOT NULL AUTO_INCREMENT,
  `archivo` mediumblob,
  PRIMARY KEY (`id_archivo`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `anuncios`
--

DROP TABLE IF EXISTS `anuncios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `anuncios` (
  `id_anuncio` bigint NOT NULL AUTO_INCREMENT,
  `nombre_usuario` varchar(20) NOT NULL,
  `tipo_anuncio` enum('texto','imagen','video','mixto') NOT NULL,
  `fecha_creacion` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_anuncio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `anuncios_multimedia`
--

DROP TABLE IF EXISTS `anuncios_multimedia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `anuncios_multimedia` (
  `id_anuncio` bigint NOT NULL,
  `imagen` mediumblob,
  `video` mediumblob,
  PRIMARY KEY (`id_anuncio`),
  CONSTRAINT `anuncios_multimedia_ibfk_1` FOREIGN KEY (`id_anuncio`) REFERENCES `anuncios` (`id_anuncio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `anuncios_texto`
--

DROP TABLE IF EXISTS `anuncios_texto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `anuncios_texto` (
  `id_anuncio` bigint NOT NULL,
  `texto` varchar(255) NOT NULL,
  PRIMARY KEY (`id_anuncio`),
  CONSTRAINT `anuncios_texto_ibfk_1` FOREIGN KEY (`id_anuncio`) REFERENCES `anuncios` (`id_anuncio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `carteras_digitales`
--

DROP TABLE IF EXISTS `carteras_digitales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carteras_digitales` (
  `usuario_representante` varchar(15) NOT NULL,
  `saldo_disponible` decimal(10,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`usuario_representante`),
  CONSTRAINT `carteras_digitales_ibfk_1` FOREIGN KEY (`usuario_representante`) REFERENCES `usuarios` (`nombre_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `categoria_etiqueta`
--

DROP TABLE IF EXISTS `categoria_etiqueta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria_etiqueta` (
  `id_categoria` bigint NOT NULL,
  `id_etiqueta` bigint NOT NULL,
  PRIMARY KEY (`id_categoria`,`id_etiqueta`),
  KEY `id_etiqueta` (`id_etiqueta`),
  CONSTRAINT `categoria_etiqueta_ibfk_1` FOREIGN KEY (`id_categoria`) REFERENCES `categorias` (`id_categoria`) ON DELETE CASCADE,
  CONSTRAINT `categoria_etiqueta_ibfk_2` FOREIGN KEY (`id_etiqueta`) REFERENCES `etiquetas` (`id_etiqueta`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
  `id_categoria` bigint NOT NULL AUTO_INCREMENT,
  `nombre_categoria` varchar(50) NOT NULL,
  PRIMARY KEY (`id_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `comentarios`
--

DROP TABLE IF EXISTS `comentarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comentarios` (
  `id_comentario` bigint NOT NULL AUTO_INCREMENT,
  `comentario` text,
  `nombre_usuario` varchar(20) DEFAULT NULL,
  `id_revista` bigint DEFAULT NULL,
  `fecha_comentario` date DEFAULT NULL,
  PRIMARY KEY (`id_comentario`),
  KEY `id_revista` (`id_revista`),
  CONSTRAINT `comentarios_ibfk_1` FOREIGN KEY (`id_revista`) REFERENCES `revistas` (`id_revista`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `etiquetas`
--

DROP TABLE IF EXISTS `etiquetas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `etiquetas` (
  `id_etiqueta` bigint NOT NULL AUTO_INCREMENT,
  `nombre_etiqueta` varchar(100) NOT NULL,
  PRIMARY KEY (`id_etiqueta`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fotos_usuario`
--

DROP TABLE IF EXISTS `fotos_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fotos_usuario` (
  `id_foto` bigint NOT NULL AUTO_INCREMENT,
  `foto_usuario` varchar(15) DEFAULT NULL,
  `foto` mediumblob,
  PRIMARY KEY (`id_foto`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `likes_revistas`
--

DROP TABLE IF EXISTS `likes_revistas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `likes_revistas` (
  `id_revista` bigint NOT NULL,
  `numero_likes` int DEFAULT '0',
  `nombre_usuario` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_revista`),
  CONSTRAINT `likes_revistas_ibfk_1` FOREIGN KEY (`id_revista`) REFERENCES `revistas` (`id_revista`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `preferencias_usuario`
--

DROP TABLE IF EXISTS `preferencias_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `preferencias_usuario` (
  `id_preferencia` bigint NOT NULL AUTO_INCREMENT,
  `nombre_usuario` varchar(15) DEFAULT NULL,
  `tipo_preferencia` enum('HOBBIE','GUSTO','TEMA_PREFERENCIA') NOT NULL,
  `valor_preferencia` varchar(20) NOT NULL,
  PRIMARY KEY (`id_preferencia`),
  KEY `nombre_usuario` (`nombre_usuario`),
  CONSTRAINT `preferencias_usuario_ibfk_1` FOREIGN KEY (`nombre_usuario`) REFERENCES `usuarios` (`nombre_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `revista_categoria`
--

DROP TABLE IF EXISTS `revista_categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `revista_categoria` (
  `id_revista` bigint NOT NULL,
  `id_categoria` bigint NOT NULL,
  PRIMARY KEY (`id_revista`,`id_categoria`),
  KEY `id_categoria` (`id_categoria`),
  CONSTRAINT `revista_categoria_ibfk_1` FOREIGN KEY (`id_revista`) REFERENCES `revistas` (`id_revista`),
  CONSTRAINT `revista_categoria_ibfk_2` FOREIGN KEY (`id_categoria`) REFERENCES `categorias` (`id_categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `revista_etiqueta`
--

DROP TABLE IF EXISTS `revista_etiqueta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `revista_etiqueta` (
  `id_revista` bigint NOT NULL,
  `id_etiqueta` bigint NOT NULL,
  PRIMARY KEY (`id_revista`,`id_etiqueta`),
  KEY `id_etiqueta` (`id_etiqueta`),
  CONSTRAINT `revista_etiqueta_ibfk_1` FOREIGN KEY (`id_revista`) REFERENCES `revistas` (`id_revista`),
  CONSTRAINT `revista_etiqueta_ibfk_2` FOREIGN KEY (`id_etiqueta`) REFERENCES `etiquetas` (`id_etiqueta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `revistas`
--

DROP TABLE IF EXISTS `revistas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `revistas` (
  `id_revista` bigint NOT NULL AUTO_INCREMENT,
  `id_archivo` bigint NOT NULL,
  `id_categoria` bigint NOT NULL,
  `titulo_revista` varchar(50) NOT NULL,
  `nombre_autor` varchar(20) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `fecha_creacion` date NOT NULL,
  `costo_mantenimiento` decimal(10,2) NOT NULL,
  `estado_revista` enum('ACTIVA','INACTIVA','EN_ESPERA') NOT NULL,
  `revista_comentable` tinyint(1) NOT NULL,
  `revista_likeable` tinyint(1) NOT NULL,
  `numero_likes` int DEFAULT '0',
  `acepta_suscripciones` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id_revista`),
  KEY `id_archivo` (`id_archivo`),
  KEY `id_categoria` (`id_categoria`),
  CONSTRAINT `revistas_ibfk_2` FOREIGN KEY (`id_categoria`) REFERENCES `categorias` (`id_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `suscripciones`
--

DROP TABLE IF EXISTS `suscripciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `suscripciones` (
  `id_suscripcion` bigint NOT NULL AUTO_INCREMENT,
  `nombre_usuario` varchar(20) DEFAULT NULL,
  `id_revista` bigint DEFAULT NULL,
  `fecha_suscripcion` date NOT NULL,
  `aprecio_suscripcion` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id_suscripcion`),
  KEY `nombre_usuario` (`nombre_usuario`),
  KEY `id_revista` (`id_revista`),
  CONSTRAINT `suscripciones_ibfk_1` FOREIGN KEY (`nombre_usuario`) REFERENCES `usuarios` (`nombre_usuario`),
  CONSTRAINT `suscripciones_ibfk_2` FOREIGN KEY (`id_revista`) REFERENCES `revistas` (`id_revista`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `nombre_usuario` varchar(15) NOT NULL,
  `password_usuario` varchar(200) NOT NULL,
  `rol_usuario` enum('COMPRADOR','SUSCRIPTOR','EDITOR','ADMINISTRADOR') NOT NULL,
  `nombre_pila` varchar(100) NOT NULL,
  `descripcion_usuario` text,
  `id_foto` bigint DEFAULT NULL,
  PRIMARY KEY (`nombre_usuario`),
  UNIQUE KEY `nombre_usuario` (`nombre_usuario`),
  KEY `id_foto` (`id_foto`),
  CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`id_foto`) REFERENCES `fotos_usuario` (`id_foto`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-19 13:07:02
