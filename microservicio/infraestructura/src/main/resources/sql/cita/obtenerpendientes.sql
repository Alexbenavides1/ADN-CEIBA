SELECT c.id,a.numero_identificacion AS identificacion_afiliado,a.nombre AS nombre_afiliado,p.codigo AS codigo_procedimiento,
p.nombre AS nombre_procedimiento,c.fecha,c.jornada,valor_copago,estado
FROM cita AS c
INNER JOIN afiliado AS a ON a.numero_identificacion=c.identificacion_afiliado
INNER JOIN procedimiento AS p ON p.codigo=c.codigo_procedimiento
WHERE estado = 'PENDIENTE'
ORDER BY c.fecha,c.jornada ASC