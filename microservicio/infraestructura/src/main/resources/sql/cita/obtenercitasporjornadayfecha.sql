SELECT COUNT(id)
FROM cita
WHERE jornada = :jornada
AND fecha = :fecha
AND estado='PENDIENTE'