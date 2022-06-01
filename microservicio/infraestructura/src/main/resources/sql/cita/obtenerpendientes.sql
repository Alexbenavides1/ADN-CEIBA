SELECT id,fecha,jornada,identificacion_afiliado,codigo_procedimiento,valor_copago,estado
FROM cita
WHERE estado = 'PENDIENTE'