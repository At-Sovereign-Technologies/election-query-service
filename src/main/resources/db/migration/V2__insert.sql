INSERT INTO election (name, status, start_date, end_date) VALUES
('Elección Presidencial 2026', 'ACTIVE', NOW(), NOW() + INTERVAL '1 day'),
('Elección Senado 2026', 'UPCOMING', NOW() + INTERVAL '10 days', NOW() + INTERVAL '12 days');