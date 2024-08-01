UPDATE users
SET password='$2a$12$dqwh84gLBqxJhDpgBh9h6O3fPHGLTtWPT74DefxdEUKuqdSRj5Zy2'
WHERE username = 'testUser';
INSERT INTO users (id, username, password, role)
SELECT 00001, 'testUser', '$2a$12$dqwh84gLBqxJhDpgBh9h6O3fPHGLTtWPT74DefxdEUKuqdSRj5Zy2', 'USER'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username='testUser');

UPDATE users
SET password='$2a$12$dqwh84gLBqxJhDpgBh9h6O3fPHGLTtWPT74DefxdEUKuqdSRj5Zy2'
WHERE username = 'testAdmin';
INSERT INTO users (id, username, password, role)
SELECT 00002, 'testAdmin', '$2a$12$dqwh84gLBqxJhDpgBh9h6O3fPHGLTtWPT74DefxdEUKuqdSRj5Zy2', 'ADMIN'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username='testAdmin');

UPDATE users
SET password='$2a$12$dqwh84gLBqxJhDpgBh9h6O3fPHGLTtWPT74DefxdEUKuqdSRj5Zy2'
WHERE username = 'testShelter';
INSERT INTO users (id, username, password, role)
SELECT 00003, 'testShelter', '$2a$12$dqwh84gLBqxJhDpgBh9h6O3fPHGLTtWPT74DefxdEUKuqdSRj5Zy2', 'SHELTER'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username='testShelter');