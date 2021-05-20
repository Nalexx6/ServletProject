CREATE TABLE IF NOT EXISTS subjects (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) UNIQUE
);

CREATE TABLE IF NOT EXISTS faculties (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) UNIQUE,
    subject1_id INT REFERENCES subjects(id) ON DELETE CASCADE,
    subject2_id INT REFERENCES subjects(id) ON DELETE CASCADE,
    subject3_id INT REFERENCES subjects(id) ON DELETE CASCADE,
    weight1 INT,
    weight2 INT,
    weight3 INT
);


DROP TABLE users;
CREATE TABLE IF NOT EXISTS users (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    first_name  VARCHAR(20),
    last_name   VARCHAR(20),
    email       VARCHAR(30),
    role        BOOLEAN,
    city        VARCHAR(20),
    region      VARCHAR(20),
    institution VARCHAR(30)

);

INSERT INTO users (first_name, last_name, email, role, city, region, institution)
VALUES ('Ivan', 'Ivanov', 'test@test.com', 1, 'Kyiv', 'Kyiv', 'School N100');

# DROP TABLE submissions;
CREATE TABLE IF NOT EXISTS submissions (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    faculty_id INT REFERENCES faculties(id) ON DELETE CASCADE,
    user_id INT REFERENCES users(id) ON DELETE CASCADE,
    user_grade1      INT,
    user_grade2      INT,
    user_grade3      INT,
    sec_education_avg DOUBLE

);

