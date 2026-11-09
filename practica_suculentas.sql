-- 1. Base de datos
CREATE DATABASE IF NOT EXISTS practica
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE practica;

-- 2. Tabla suculenta
DROP TABLE IF EXISTS suculenta;

CREATE TABLE suculenta (
  id_suculenta      INT UNSIGNED NOT NULL AUTO_INCREMENT,
  nombre_comun      VARCHAR(100) NOT NULL,
  nombre_cientifico VARCHAR(150) NOT NULL,
  familia           VARCHAR(80)  NOT NULL,
  color_principal   VARCHAR(50)  NOT NULL,
  altura_cm         DECIMAL(6,2) NOT NULL,
  precio_estimado   DECIMAL(10,2) NOT NULL,
  nivel_riego       ENUM('Bajo','Medio','Alto') NOT NULL,
  exposicion_solar  VARCHAR(50)  NOT NULL,
  ruta_imagen       VARCHAR(255) NOT NULL,
  PRIMARY KEY (id_suculenta),
  CONSTRAINT chk_altura CHECK (altura_cm > 0),
  CONSTRAINT chk_precio CHECK (precio_estimado >= 0)
) ENGINE=InnoDB;

-- 3. Datos de ejemplo
INSERT INTO suculenta
  (nombre_comun, nombre_cientifico, familia, color_principal, altura_cm, precio_estimado, nivel_riego, exposicion_solar, ruta_imagen)
VALUES
  ('Aloe vera',          'Aloe barbadensis miller',    'Asphodelaceae', 'Verde grisáceo',   45.00, 12.50, 'Bajo',  'Sol directo',     'img/suculentas/aloe_vera.jpg'),
  ('Rosa de alabastro',  'Echeveria elegans',          'Crassulaceae',  'Verde azulado',     8.00,  4.75, 'Bajo',  'Sol directo',     'img/suculentas/echeveria_elegans.jpg'),
  ('Árbol de jade',      'Crassula ovata',             'Crassulaceae',  'Verde brillante',  60.00, 18.00, 'Bajo',  'Semisombra',      'img/suculentas/crassula_ovata.jpg'),
  ('Collar de perlas',   'Senecio rowleyanus',         'Asteraceae',    'Verde',            30.00,  9.90, 'Medio', 'Luz indirecta',   'img/suculentas/senecio_rowleyanus.jpg'),
  ('Planta cebra',       'Haworthiopsis attenuata',    'Asphodelaceae', 'Verde con blanco', 12.00,  6.50, 'Bajo',  'Luz indirecta',   'img/suculentas/haworthia_attenuata.jpg'),
  ('Cola de burro',      'Sedum morganianum',          'Crassulaceae',  'Verde claro',      40.00, 11.20, 'Medio', 'Semisombra',      'img/suculentas/sedum_morganianum.jpg'),
  ('Lengua de suegra',   'Dracaena trifasciata',       'Asparagaceae',  'Verde con amarillo',70.00, 15.00, 'Bajo',  'Luz indirecta',   'img/suculentas/dracaena_trifasciata.jpg'),
  ('Kalanchoe',          'Kalanchoe blossfeldiana',    'Crassulaceae',  'Rojo',             25.00,  7.80, 'Medio', 'Luz indirecta',   'img/suculentas/kalanchoe_blossfeldiana.jpg'),
  ('Siempreviva',        'Sempervivum tectorum',       'Crassulaceae',  'Verde rojizo',      6.00,  3.90, 'Bajo',  'Sol directo',     'img/suculentas/sempervivum_tectorum.jpg'),
  ('Piedras vivas',      'Lithops karasmontana',       'Aizoaceae',     'Beige',             3.00,  8.40, 'Bajo',  'Sol directo',     'img/suculentas/lithops_karasmontana.jpg');

-- 4. Usuario con permisos sobre la base practica
DROP USER IF EXISTS 'usuario_practica'@'localhost';
CREATE USER 'usuario_practica'@'localhost' IDENTIFIED BY 'la_Clave';
GRANT ALL PRIVILEGES ON practica.* TO 'usuario_practica'@'localhost';
FLUSH PRIVILEGES;

-- 5. Verificación
SELECT * FROM suculenta;
SHOW GRANTS FOR 'usuario_practica'@'localhost';
