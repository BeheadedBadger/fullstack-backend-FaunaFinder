UPDATE users
SET password='$2a$12$dqwh84gLBqxJhDpgBh9h6O3fPHGLTtWPT74DefxdEUKuqdSRj5Zy2'
WHERE username = 'testUser';
INSERT INTO users (username, password, role)
SELECT 'testUser', '$2a$12$dqwh84gLBqxJhDpgBh9h6O3fPHGLTtWPT74DefxdEUKuqdSRj5Zy2', 1
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username='testUser');
/*
UPDATE authorities
SET authority='ROLE_USER'
WHERE username = 'testUser';
INSERT INTO authorities (username, authority)
SELECT 'testUser', 'ROLE_USER'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username='testUser');
*/

UPDATE users
SET password='$2a$12$dqwh84gLBqxJhDpgBh9h6O3fPHGLTtWPT74DefxdEUKuqdSRj5Zy2'
WHERE username = 'testUser2';
INSERT INTO users (username, password)
SELECT 'testUser2', '$2a$12$dqwh84gLBqxJhDpgBh9h6O3fPHGLTtWPT74DefxdEUKuqdSRj5Zy2'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username='testUser2');
/*UPDATE authorities
SET authority='ROLE_USER'
WHERE username = 'testUser2';
INSERT INTO authorities (username, authority)
SELECT 'testUser2', 'ROLE_USER'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username='testUser2');
*/
/*
INSERT INTO users (username, password)
VALUES ('testUser', '$2a$12$dqwh84gLBqxJhDpgBh9h6O3fPHGLTtWPT74DefxdEUKuqdSRj5Zy2')
ON DUPLICATE KEY UPDATE username = testUser;
INSERT INTO authorities (username, authority) VALUES ('testUser', 'ROLE_USER');

DELETE FROM users WHERE username = 'testShelter';
DELETE FROM authorities WHERE username = 'testShelter';
INSERT INTO shelters (username, password, phone_number)
VALUES ('testShelter', '$2a$12$WjuCAjqDWOhtKQvgfA0kFuwrzQgCMiX/776/zw.5OADiQSnM9goqq', '07102340234');
INSERT INTO authorities (username, authority) VALUES ('testShelter', 'ROLE_SHELTER');

DELETE FROM users WHERE username = 'testAdmin';
DELETE FROM authorities WHERE username = 'testAdmin';
INSERT INTO users (username, password)
VALUES ('testAdmin', '$2a$12$xk0/AzqqAbjJC.l024XZweJXrTCKt0h1OS4AP8mYOerLNoB3oyJu6');
INSERT INTO authorities (username, authority) VALUES ('testAdmin', 'ROLE_ADMIN');*/