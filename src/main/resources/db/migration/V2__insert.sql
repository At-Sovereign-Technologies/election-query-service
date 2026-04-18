INSERT INTO election (id, name, status, start_date, end_date) VALUES
(1, 'Elección Presidencial 2026', 'ACTIVE', NOW(), NOW() + INTERVAL '1 day'),
(2, 'Elección Senado 2026', 'UPCOMING', NOW() + INTERVAL '10 days', NOW() + INTERVAL '12 days'),
(3, 'Elección Alcaldía Bogotá 2027', 'UPCOMING', NOW() + INTERVAL '30 days', NOW() + INTERVAL '32 days'),
(4, 'Elección Gobernación Antioquia 2027', 'UPCOMING', NOW() + INTERVAL '40 days', NOW() + INTERVAL '42 days'),
(5, 'Consulta Popular Nacional', 'FINISHED', NOW() - INTERVAL '20 days', NOW() - INTERVAL '18 days');
