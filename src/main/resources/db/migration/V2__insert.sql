INSERT INTO election (name, status, start_date, end_date) VALUES
('Elección Presidencial 2026', 'ACTIVE', NOW(), NOW() + INTERVAL '1 day'),
('Elección Senado 2026', 'UPCOMING', NOW() + INTERVAL '10 days', NOW() + INTERVAL '12 days'),
('Elección Alcaldía Bogotá 2027', 'UPCOMING', NOW() + INTERVAL '30 days', NOW() + INTERVAL '32 days'),
('Elección Gobernación Antioquia 2027', 'UPCOMING', NOW() + INTERVAL '40 days', NOW() + INTERVAL '42 days'),
('Consulta Popular Nacional', 'FINISHED', NOW() - INTERVAL '20 days', NOW() - INTERVAL '18 days');
