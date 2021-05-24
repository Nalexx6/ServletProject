
DROP TABLE subjects;
CREATE TABLE IF NOT EXISTS subjects (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) UNIQUE
);

INSERT INTO subjects (name) VALUES ('Math');
INSERT INTO subjects (name) VALUES ('English');
INSERT INTO subjects (name) VALUES ('Physics');
INSERT INTO subjects (name) VALUES ('Ukrainian Language');
INSERT INTO subjects (name) VALUES ('History');

DROP TABLE faculties;
CREATE TABLE IF NOT EXISTS faculties (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) UNIQUE,
    studentsAmount INT,
    stateFundedAmount INT,
    subject1_id INT REFERENCES subjects(id) ON DELETE CASCADE,
    subject2_id INT REFERENCES subjects(id) ON DELETE CASCADE,
    subject3_id INT REFERENCES subjects(id) ON DELETE CASCADE,
    weight1 INT,
    weight2 INT,
    weight3 INT
);

INSERT INTO faculties (name, studentsAmount, stateFundedAmount, subject1_id, subject2_id, subject3_id, weight1, weight2, weight3)
VALUES ('Cybernetics', 100, 40,  1, 2, 4, 0.6, 0.2, 0.2);

DROP TABLE users;
CREATE TABLE IF NOT EXISTS users (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    login       VARCHAR(20) UNIQUE,
    password    VARCHAR(20),
    first_name  VARCHAR(20),
    last_name   VARCHAR(20),
    email       VARCHAR(30),
    role        VARCHAR(10),
    city        VARCHAR(20),
    region      VARCHAR(20),
    institution VARCHAR(30)

);

INSERT INTO users (login, password, first_name, last_name, email, role, city, region, institution)
VALUES ('test', '1111', 'Ivan', 'Ivanov', 'test@test.com', 'ADMIN', 'Kyiv', 'Kyiv', 'School N100');

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

