-- Step 1: Create the Database
CREATE DATABASE fitness_app;

-- Use the database
\c fitness_app;

-- Step 2: Create Tables

-- Admin Table
CREATE TABLE Admin (
                       admin_id SERIAL PRIMARY KEY,
                       first_name VARCHAR(50),
                       last_name VARCHAR(50),
                       email VARCHAR(100),
                       password VARCHAR(100)
);

-- User Table
CREATE TABLE "User" (
                        user_id SERIAL PRIMARY KEY,
                        first_name VARCHAR(50),
                        last_name VARCHAR(50),
                        email VARCHAR(100),
                        password VARCHAR(100),
                        age INT,
                        fitness_level VARCHAR(20) CHECK (fitness_level IN ('BEGINNER', 'INTERMEDIATE', 'ADVANCED')),
                        goal VARCHAR(255),
                        created_at TIMESTAMP DEFAULT NOW()
);

-- Trainer Table
CREATE TABLE Trainer (
                         trainer_id SERIAL PRIMARY KEY,
                         first_name VARCHAR(50),
                         last_name VARCHAR(50),
                         specialization VARCHAR(100),
                         experience_level VARCHAR(50),
                         email VARCHAR(100),
                         password VARCHAR(100),
                         verified BOOLEAN,
                         created_at TIMESTAMP DEFAULT NOW()
);

-- Training Table
CREATE TABLE Training (
                          training_id SERIAL PRIMARY KEY,
                          user_id INT REFERENCES "User"(user_id),
                          workout_name VARCHAR(100),
                          activity_type VARCHAR(100),
                          created_at TIMESTAMP DEFAULT NOW(),
                          scheduled_at TIMESTAMP
);

-- Segments Table
CREATE TABLE Segments (
                          segment_id SERIAL PRIMARY KEY,
                          training_id INT REFERENCES Training(training_id),
                          segment_name VARCHAR(100),
                          duration_type VARCHAR(10) CHECK (duration_type IN ('MINUTES', 'HOURS')),
                          duration_value INT,
                          intensity VARCHAR(50)
);

-- Feedback Table
CREATE TABLE Feedback (
                          feedback_id SERIAL PRIMARY KEY,
                          training_id INT REFERENCES Training(training_id),
                          feedback_text TEXT,
                          feeling VARCHAR(10) CHECK (feeling IN ('POSITIVE', 'NEUTRAL', 'NEGATIVE')),
                          rating_value INT
);

-- Complaint Table
CREATE TABLE Complaint (
                           complaint_id SERIAL PRIMARY KEY,
                           trainer_id INT REFERENCES Trainer(trainer_id),
                           admin_id INT REFERENCES Admin(admin_id),
                           complaint_text TEXT,
                           status VARCHAR(10) CHECK (status IN ('OPEN', 'RESOLVED', 'CLOSED')),
                           created_at TIMESTAMP DEFAULT NOW(),
                           resolved_at TIMESTAMP
);

-- Rating Table
CREATE TABLE Rating (
                        rating_id SERIAL PRIMARY KEY,
                        user_id INT REFERENCES "User"(user_id),
                        trainer_id INT REFERENCES Trainer(trainer_id),
                        rating_value INT,
                        comment TEXT,
                        created_at TIMESTAMP DEFAULT NOW()
);

-- Message Table
CREATE TABLE Message (
                         message_id SERIAL PRIMARY KEY,
                         user_id INT REFERENCES "User"(user_id),
                         trainer_id INT REFERENCES Trainer(trainer_id),
                         message_text TEXT,
                         sent_at TIMESTAMP DEFAULT NOW()
);

-- Step 3: Insert Sample Data

-- Insert into Admin Table
INSERT INTO Admin (first_name, last_name, email, password)
VALUES
    ('John', 'Doe', 'john.doe@admin.com', 'adminpass123'),
    ('Jane', 'Smith', 'jane.smith@admin.com', 'adminpass456');

-- Insert into User Table
INSERT INTO "User" (first_name, last_name, email, password, age, fitness_level, goal, created_at)
VALUES
    ('Michael', 'Jordan', 'mjordan@gmail.com', 'userpass123', 32, 'ADVANCED', 'Lose Weight', NOW()),
    ('Serena', 'Williams', 'swilliams@gmail.com', 'userpass456', 29, 'INTERMEDIATE', 'Increase Stamina', NOW()),
    ('Usain', 'Bolt', 'usainbolt@gmail.com', 'userpass789', 35, 'BEGINNER', 'Get Fit', NOW());

-- Insert into Trainer Table
INSERT INTO Trainer (first_name, last_name, specialization, experience_level, email, password, verified, created_at)
VALUES
    ('Mike', 'Tyson', 'Boxing', 'Expert', 'mike.tyson@trainer.com', 'trainerpass123', TRUE, NOW()),
    ('Tom', 'Brady', 'Football', 'Intermediate', 'tom.brady@trainer.com', 'trainerpass456', TRUE, NOW());

-- Insert into Training Table
INSERT INTO Training (user_id, workout_name, activity_type, created_at, scheduled_at)
VALUES
    (1, 'Cardio Session', 'Running', NOW(), NOW() + INTERVAL '1 day'),
    (2, 'Strength Training', 'Weightlifting', NOW(), NOW() + INTERVAL '2 day'),
    (3, 'Speed Training', 'Sprinting', NOW(), NOW() + INTERVAL '3 day');

-- Insert into Segments Table
INSERT INTO Segments (training_id, segment_name, duration_type, duration_value, intensity)
VALUES
    (1, 'Warm-up', 'MINUTES', 10, 'Medium'),
    (1, 'Main Running', 'MINUTES', 30, 'High'),
    (2, 'Deadlift', 'MINUTES', 20, 'High'),
    (3, 'Sprints', 'MINUTES', 15, 'Very High');

-- Insert into Feedback Table
INSERT INTO Feedback (training_id, feedback_text, feeling, rating_value)
VALUES
    (1, 'Great session, felt energized!', 'POSITIVE', 5),
    (2, 'Good workout but could be more challenging.', 'NEUTRAL', 3),
    (3, 'Too exhausting, needs better pacing.', 'NEGATIVE', 2);

-- Insert into Complaint Table
INSERT INTO Complaint (trainer_id, admin_id, complaint_text, status, created_at)
VALUES
    (1, 1, 'Trainer was late for the session.', 'OPEN', NOW()),
    (2, 2, 'Trainer did not explain the exercises properly.', 'RESOLVED', NOW() - INTERVAL '1 day');

-- Insert into Rating Table
INSERT INTO Rating (user_id, trainer_id, rating_value, comment, created_at)
VALUES
    (1, 1, 5, 'Trainer was amazing! Very knowledgeable.', NOW()),
    (2, 2, 3, 'Trainer was good, but not as engaging.', NOW()),
    (3, 1, 4, 'Great session, but trainer should focus more on form.', NOW());

-- Insert into Message Table
INSERT INTO Message (user_id, trainer_id, message_text, sent_at)
VALUES
    (1, 1, 'Can we focus on endurance in the next session?', NOW()),
    (2, 2, 'What time should I arrive for the session tomorrow?', NOW()),
    (3, 1, 'Can we discuss my progress at the next session?', NOW());
