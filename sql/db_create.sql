
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
INSERT INTO subjects (name) VALUES ('Biology');
INSERT INTO subjects (name) VALUES ('Geography');
INSERT INTO subjects (name) VALUES ('Chemistry');

# DROP TABLE faculties;
CREATE TABLE IF NOT EXISTS faculties (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(40) UNIQUE,
    students_amount INT,
    state_funded_amount INT,
    subject1_id INT REFERENCES subjects(id) ON DELETE CASCADE,
    subject2_id INT REFERENCES subjects(id) ON DELETE CASCADE,
    subject3_id INT REFERENCES subjects(id) ON DELETE CASCADE
);

# INSERT INTO faculties (name, students_amount, state_funded_amount, subject1_id, subject2_id, subject3_id)
# VALUES ('Cybernetics', 100, 40,  2, 1, 4);
# INSERT INTO faculties (name, students_amount, state_funded_amount, subject1_id, subject2_id, subject3_id)
# VALUES ('Mechanic-mathematical', 80, 40,  1, 3, 4);

# DROP TABLE users;
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

# INSERT INTO users (login, password, first_name, last_name, email, role, city, region, institution)
# VALUES ('test', '1111', 'Ivan', 'Ivanov', 'test@test.com', 'ADMIN', 'Kyiv', 'Kyiv', 'School N100');
#
# INSERT INTO users (login, password, first_name, last_name, email, role, city, region, institution)
# VALUES ('user', '2222', 'Petro', 'Petrov', 'user@user.com', 'USER', 'Kyiv', 'Kyiv', 'School N100');

DROP TABLE submissions;
CREATE TABLE IF NOT EXISTS submissions (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    faculty_id INT REFERENCES faculties(id) ON DELETE CASCADE,
    user_id INT REFERENCES users(id) ON DELETE CASCADE,
    grade1      INT,
    grade2      INT,
    grade3      INT,
    sec_education_avg DOUBLE,
    checked BOOLEAN,
    finalization_status INT

);

