INSERT INTO patient (name, birth_date, email, gender, blood_group, created_at)
VALUES
('Rahul Sharma', '1995-05-12', 'rahul.sharma@gmail.com', 'Male', 'O_POSITIVE', NOW()),
('Priya Patil', '1998-08-22', 'priya.patil@gmail.com', 'Female', 'A_POSITIVE', NOW()),
('Amit Verma', '1992-11-18', 'amit.verma@gmail.com', 'Male', 'B_POSITIVE', NOW()),
('Sneha Joshi', '2000-03-15', 'sneha.joshi@gmail.com', 'Female', 'AB_POSITIVE', NOW()),
('Rohan Deshmukh', '1997-09-08', 'rohan.deshmukh@gmail.com', 'Male', 'O_NEGATIVE', NOW());

INSERT INTO doctor (id, name, specialization, email)
VALUES
(1, 'Dr. Rajesh Sharma', 'Cardiologist', 'rajesh.sharma@hospital.com'),
(2, 'Dr. Priya Patil', 'Neurologist', 'priya.patil@hospital.com'),
(3, 'Dr. Amit Verma', 'Orthopedic Surgeon', 'amit.verma@hospital.com'),
(4, 'Dr. Sneha Joshi', 'Dermatologist', 'sneha.joshi@hospital.com'),
(5, 'Dr. Rohan Deshmukh', 'Pediatrician', 'rohan.deshmukh@hospital.com'),
(6, 'Dr. Neha Kulkarni', 'Gynecologist', 'neha.kulkarni@hospital.com');