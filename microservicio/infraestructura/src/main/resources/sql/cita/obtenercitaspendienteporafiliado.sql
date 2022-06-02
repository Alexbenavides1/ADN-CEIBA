SELECT COUNT(id)
FROM cita
WHERE identificacion_afiliado = :identificacion_afiliado
AND estado= 'PENDIENTE'