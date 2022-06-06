create table procedimiento (
 codigo varchar(20) not null,
 nombre varchar(100) not null,
 valor DECIMAL(10,2) not null,
 primary key (codigo)
);
create table afiliado (
 numero_identificacion varchar(50) not null,
 nombre varchar(255) not null,
 nivel varchar(20) not null,
 primary key (numero_identificacion)
);

create table cita(
id int(11) not null auto_increment,
fecha date not null,
jornada varchar(1) not null,
identificacion_afiliado varchar(50) not null,
codigo_procedimiento varchar(20) not null,
valor_copago DECIMAL(10,2) not null,
estado varchar(20) not null,
primary key (id)
);

ALTER TABLE procedimiento
ADD CONSTRAINT codigo_uq
UNIQUE (codigo);

ALTER TABLE afiliado
ADD CONSTRAINT documento_uq
UNIQUE (numero_identificacion);

ALTER TABLE cita
ADD CONSTRAINT procedimiento_fk
  FOREIGN KEY (codigo_procedimiento)
  REFERENCES procedimiento (codigo)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE cita
ADD CONSTRAINT identificacion_fk
   FOREIGN KEY (identificacion_afiliado)
   REFERENCES afiliado (numero_identificacion)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

