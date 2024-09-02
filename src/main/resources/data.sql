INSERT INTO images (file_name)
VALUES('0003.jpg')
ON CONFLICT (file_name) DO NOTHING;

INSERT INTO images (file_name)
VALUES('0002.png')
ON CONFLICT (file_name) DO NOTHING;

INSERT INTO images (file_name)
VALUES('0001.png')
ON CONFLICT (file_name) DO NOTHING;

INSERT INTO images (file_name)
VALUES('0004.jpg')
ON CONFLICT (file_name) DO NOTHING;

INSERT INTO images (file_name)
VALUES('0005.jpg')
ON CONFLICT (file_name) DO NOTHING;

INSERT INTO images (file_name)
VALUES('0006.jpg')
ON CONFLICT (file_name) DO NOTHING;

INSERT INTO images (file_name)
VALUES('0007.jpg')
ON CONFLICT (file_name) DO NOTHING;

INSERT INTO images (file_name)
VALUES('0008.jpg')
ON CONFLICT (file_name) DO NOTHING;

UPDATE users
SET password='$2a$12$dqwh84gLBqxJhDpgBh9h6O3fPHGLTtWPT74DefxdEUKuqdSRj5Zy2',
    user_photo_file_name='0001.png', id=0001
WHERE username = 'testUser';
INSERT INTO users (id, username, password, role, user_photo_file_name)
SELECT 0001, 'testUser', '$2a$12$dqwh84gLBqxJhDpgBh9h6O3fPHGLTtWPT74DefxdEUKuqdSRj5Zy2', 'USER', '0001.png'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username='testUser');

UPDATE users
SET password='$2a$12$dqwh84gLBqxJhDpgBh9h6O3fPHGLTtWPT74DefxdEUKuqdSRj5Zy2',
    user_photo_file_name='0002.png', id=0002
WHERE username = 'testAdmin';
INSERT INTO users (id, username, password, role, user_photo_file_name)
SELECT 0002, 'testAdmin', '$2a$12$dqwh84gLBqxJhDpgBh9h6O3fPHGLTtWPT74DefxdEUKuqdSRj5Zy2', 'ADMIN', '0002.png'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username='testAdmin');

UPDATE users
SET password='$2a$12$dqwh84gLBqxJhDpgBh9h6O3fPHGLTtWPT74DefxdEUKuqdSRj5Zy2',
speciality='Reptiles', user_photo_file_name='0003.jpg', id=1313
WHERE username = 'testShelter';
INSERT INTO users (id, username, password, role, speciality, user_photo_file_name)
SELECT 1313, 'testShelter', '$2a$12$dqwh84gLBqxJhDpgBh9h6O3fPHGLTtWPT74DefxdEUKuqdSRj5Zy2', 'SHELTER', 'Reptiles', '0003.jpg'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username='testShelter');

UPDATE animals
SET name='Ammit', species_category='Reptiles', common_species_name='Royal python',
    scientific_species_name='Python Regius', animal_photo_file_name='0003.jpg', age='9',
    sex='F', warning=false, shelter_id=1313,
    description='Ammit is a very friendly and outgoing python. She loves to hang out with you and cuddle. Eats well, sheds well, great for beginners and experts alike!'
WHERE id = '1111';
INSERT INTO animals (id, name, species_category, common_species_name, scientific_species_name, animal_photo_file_name,
                     age, sex, warning, shelter_id, description)
SELECT 1111, 'Ammit', 'Reptiles', 'Royal python', 'Python Regius', '0003.jpg', 9, 'F', false, 1313,
       'Ammit is a very friendly and outgoing python. She loves to hang out with you and cuddle. Eats well, sheds well, great for beginners and experts alike!'
WHERE NOT EXISTS (SELECT 1 FROM animals WHERE id='1111')
ON CONFLICT (animal_photo_file_name) DO NOTHING;

UPDATE animals
SET name='Atl', species_category='Reptiles', common_species_name='Red-tailed boa',
    scientific_species_name='Boa Constrictor', animal_photo_file_name='0002.png', age='16',
    sex='M', warning=true, warning_explanation='BIG and can bite', shelter_id=1313,
    description='Atl is a great snake, and if you have experience with boas, then dont let the warning dissuade you! But for a beginner, he might be a bit much. Atl is middle-aged, 2 and a half meters long and very inquisitive. He loves to play with water and we recommend a good large enclose with a pool for him. He has a lot of scars from poor care in his past, but he is still beautiful!'
WHERE id = '2222';
INSERT INTO animals (id, name, species_category, common_species_name, scientific_species_name, animal_photo_file_name,
                     age, sex, warning, warning_explanation, shelter_id, description)
SELECT 2222, 'Atl', 'Reptiles', 'Red-tailed boa', 'Boa Constrictor', '0002.png', 16, 'M', true, 'BIG and can bite', 1313,
    'Atl is a great snake, and if you have experience with boas, then dont let the warning dissuade you! But for a beginner, he might be a bit much. Atl is middle-aged, 2 and a half meters long and very inquisitive. He loves to play with water and we recommend a good large enclose with a pool for him. He has a lot of scars from poor care in his past, but he is still beautiful!'
WHERE NOT EXISTS (SELECT 1 FROM animals WHERE id='2222')
ON CONFLICT (animal_photo_file_name) DO NOTHING;

UPDATE animals
SET name='Chad', species_category='Birds', common_species_name='Zebra finch',
    scientific_species_name='Taeniopygia Castanotis', animal_photo_file_name='0001.png', age=0,
    sex='M', warning=false, shelter_id=1313,
    description='Chad is very large (for a tiny little finch species) and very sweet. He gets along great with any other bird he meets and is very chill. His age is unknown.'
WHERE id = '3333';
INSERT INTO animals (id, name, species_category, common_species_name, scientific_species_name, animal_photo_file_name,
                     age, sex, warning, shelter_id, description)
SELECT 3333, 'Chad', 'Birds', 'Zebra finch', 'Taeniopygia Castanotis', '0001.png', 0, 'M', false, 1313,
       'Chad is very large (for a tiny little finch species) and very sweet. He gets along great with any other bird he meets and is very chill. His age is unknown.'
WHERE NOT EXISTS (SELECT 1 FROM animals WHERE id='3333')
ON CONFLICT (animal_photo_file_name) DO NOTHING;

UPDATE animals
SET name='Hooligan', species_category='Birds', common_species_name='Zebra finch',
    scientific_species_name='Taeniopygia Castanotis', animal_photo_file_name='0004.jpg', age=0,
    sex='F', warning=true, warning_explanation='Tiny menace', shelter_id=1313,
    description='Hooligan is a tiny girl with a big brain and evil intent.'
WHERE id = '4444';
INSERT INTO animals (id, name, species_category, common_species_name, scientific_species_name, animal_photo_file_name,
                     age, sex, warning, warning_explanation, shelter_id, description)
SELECT 4444, 'Hooligan', 'Birds', 'Zebra finch', 'Taeniopygia Castanotis', '0004.jpg', 0, 'F', true, 'Tiny menace', 1313,
       'Hooligan is a tiny girl with a big brain and evil intent.'
WHERE NOT EXISTS (SELECT 1 FROM animals WHERE id='4444')
ON CONFLICT (animal_photo_file_name) DO NOTHING;

UPDATE animals
SET name='4 finches', species_category='Birds', common_species_name='Zebra finch',
    scientific_species_name='Taeniopygia Castanotis', animal_photo_file_name='0005.jpg', age=0,
    sex='X', warning=false, shelter_id=1313,
    description='Four finches! Thats more than one! They are Peep, Beep, Meep and Jessica!'
WHERE id = '5555';
INSERT INTO animals (id, name, species_category, common_species_name, scientific_species_name, animal_photo_file_name,
                     age, sex, warning, shelter_id, description)
SELECT 5555, '4 finches', 'Birds', 'Zebra finch', 'Taeniopygia Castanotis', '0005.jpg', 0, 'F', false, 1313,
       'Four finches! Thats more than one! They are Peep, Beep, Meep and Jessica!'
WHERE NOT EXISTS (SELECT 1 FROM animals WHERE id='5555')
ON CONFLICT (animal_photo_file_name) DO NOTHING;

UPDATE animals
SET name='Basil', species_category='Mammals', common_species_name='Ferret',
    scientific_species_name='Mustela Furo', animal_photo_file_name='0006.jpg', age=4,
    sex='M', warning=false, shelter_id=1313,
    description='Basil is an absolute delight and loves people! Very sweet, and very playful. Will happily hang out with people, cats and dogs.'
WHERE id = '6666';
INSERT INTO animals (id, name, species_category, common_species_name, scientific_species_name, animal_photo_file_name,
                     age, sex, warning, shelter_id, description)
SELECT 6666, 'Basil', 'Mammals', 'Ferret', 'Mustela Furo', '0006.jpg', 4, 'M', false, 1313,
       'Basil is an absolute delight and loves people! Very sweet, and very playful. Will happily hang out with people, cats and dogs.'
WHERE NOT EXISTS (SELECT 1 FROM animals WHERE id='6666')
ON CONFLICT (animal_photo_file_name) DO NOTHING;

UPDATE animals
SET name='Sizzle', species_category='Mammals', common_species_name='Cat',
    scientific_species_name='Felis cattus', animal_photo_file_name='0007.jpg', age=7,
    sex='F', warning=true, warning_explanation='Hates you', shelter_id=1313,
    description='Sizzle has a lot of bad experiences with people. As a result, she defaults to hating people until they prove themselves to her, which WILL take a while. She doesnt like visitors, she doesnt like children and she absolutely despises other cats. Once she warms up to you however, she is an absolutely wonderful cat. If youre patient, dont mind her being your only cat and wont take it personally, Sizzle might be the right cat for you!'
WHERE id = '7777';
INSERT INTO animals (id, name, species_category, common_species_name, scientific_species_name, animal_photo_file_name,
                     age, sex, warning, warning_explanation, shelter_id, description)
SELECT 7777, 'Sizzle', 'Mammals', 'Cat', 'Felis cattus', '0007.jpg', 7, 'F', true, 'Hates you', 1313,
       'Sizzle has a lot of bad experiences with people. As a result, she defaults to hating people until they prove themselves to her, which WILL take a while. She doesnt like visitors, she doesnt like children and she absolutely despises other cats. Once she warms up to you however, she is an absolutely wonderful cat. If youre patient, dont mind her being your only cat and wont take it personally, Sizzle might be the right cat for you!'
WHERE NOT EXISTS (SELECT 1 FROM animals WHERE id='7777')
ON CONFLICT (animal_photo_file_name) DO NOTHING;

UPDATE animals
SET name='Vermin', species_category='Mammals', common_species_name='Common African rat',
    scientific_species_name='Mastomys natalensis', animal_photo_file_name='0008.jpg', age=0,
    sex='X', warning=true, warning_explanation='Not domesticated', shelter_id=1313,
    description='Large quantity of common African rats available. Males and females. Various ages, ranging from 0 to 3 years old. Theyre a somewhat bitey species and not a good pet for people that like to hold their rodents, but are fun to watch and have big personalities.'
WHERE id = '8888';
INSERT INTO animals (id, name, species_category, common_species_name, scientific_species_name, animal_photo_file_name,
                     age, sex, warning, warning_explanation, shelter_id, description)
SELECT 8888, 'Vermin', 'Mammals', 'Common African rat', 'Mastomys natalensis', '0008.jpg', 0, 'X', true, 'Not domesticated', 1313,
       'Large quantity of common African rats available. Males and females. Various ages, ranging from 0 to 3 years old. Theyre a somewhat bitey species and not a good pet for people that like to hold their rodents, but are fun to watch and have big personalities. They do best in large groups, so will only be placed in groups of 4 or more rats.'
WHERE NOT EXISTS (SELECT 1 FROM animals WHERE id='8888')
ON CONFLICT (animal_photo_file_name) DO NOTHING;
