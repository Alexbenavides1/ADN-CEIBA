INSERT INTO afiliado (numero_identificacion,nombre,nivel) VALUES ('1067111111','Juan Perez','NIVEL_I');
INSERT INTO afiliado (numero_identificacion,nombre,nivel) VALUES ('1067222222','Alex Benavides','NIVEL_II');
INSERT INTO afiliado (numero_identificacion,nombre,nivel) VALUES ('1067333333','Pedro Lopez','NIVEL_III');
INSERT INTO afiliado (numero_identificacion,nombre,nivel) VALUES ('1067444444','Luis Jimenez','NIVEL_III');
INSERT INTO afiliado (numero_identificacion,nombre,nivel) VALUES ('1067555555','Andres Gonzales','NIVEL_II');
INSERT INTO afiliado (numero_identificacion,nombre,nivel) VALUES ('1067666666','Carlos Sanchez','NIVEL_I');
INSERT INTO afiliado (numero_identificacion,nombre,nivel) VALUES ('1067777777','Carlos Perez','NIVEL_I');
INSERT INTO afiliado (numero_identificacion,nombre,nivel) VALUES ('1067888888','Luis Sanchez','NIVEL_III');
INSERT INTO afiliado (numero_identificacion,nombre,nivel) VALUES ('1067999999','Andres Martinez','NIVEL_I');
INSERT INTO afiliado (numero_identificacion,nombre,nivel) VALUES ('1067000000','Julian Sanchez','NIVEL_II');

INSERT INTO procedimiento (codigo,nombre,valor) VALUES ('808081','Dermabrasion',300000);
INSERT INTO procedimiento (codigo,nombre,valor) VALUES ('808082','Exfoliacion por laser',400000);
INSERT INTO procedimiento (codigo,nombre,valor) VALUES ('808083','Exfoliacion quimica',500000);
INSERT INTO procedimiento (codigo,nombre,valor) VALUES ('808084','Microdermoabrasión',200000);
INSERT INTO procedimiento (codigo,nombre,valor) VALUES ('808085','Escleroterapia',285000);
INSERT INTO procedimiento (codigo,nombre,valor) VALUES ('808086','Electrolisis para eliminar el vello',390000);
INSERT INTO procedimiento (codigo,nombre,valor) VALUES ('808087','Inyecciones de relleno',630000);

INSERT INTO cita (id,fecha,jornada,identificacion_afiliado,codigo_procedimiento,valor_copago,estado) VALUES (1,'2022-06-13','M','1067111111','808081',34500,'PENDIENTE');


