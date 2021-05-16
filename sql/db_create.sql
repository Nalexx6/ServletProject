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

CREATE TABLE IF NOT EXISTS applicants (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(20),
    last_name  VARCHAR(20),
    email      VARCHAR(30),
    subject1_id INT REFERENCES subjects(id) ON DELETE CASCADE,
    subject2_id INT REFERENCES subjects(id) ON DELETE CASCADE,
    subject3_id INT REFERENCES subjects(id) ON DELETE CASCADE,
    grade1 INT,
    grade2 INT,
    grade3 INT,
    faculty1_id INT REFERENCES faculties(id) ON DELETE CASCADE,
    faculty2_id INT REFERENCES faculties(id) ON DELETE CASCADE,
    faculty3_id INT REFERENCES faculties(id) ON DELETE CASCADE

);

CREATE TABLE IF NOT EXISTS submissions (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    faculty_id INT REFERENCES faculties(id) ON DELETE CASCADE,
    applicant_id INT REFERENCES applicants(id) ON DELETE CASCADE

);

