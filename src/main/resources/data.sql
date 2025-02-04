insert into users (name, weight, height, age, gender) values ('Raanan', 168, 70, 24, 'MALE');
insert into users (name, weight, height, age, gender) values ('Ben', 150, 70, 24, 'MALE');
insert into users (name, weight, height, age, gender) values ('Akiva', 200, 69, 26, 'MALE');

INSERT INTO weekly_total (user_id, active_calories, activities_count, average_calories_per_day, total_miles, bmr) 
VALUES (1, 0, 0, 0, 0, 0);

INSERT INTO weekly_total (user_id, active_calories, activities_count, average_calories_per_day, total_miles, bmr) 
VALUES (2, 0, 0, 0, 0, 0); 

INSERT INTO weekly_total (user_id, active_calories, activities_count, average_calories_per_day, total_miles, bmr) 
VALUES (3, 0, 0, 0, 0, 0);

INSERT INTO daily_total (id, weekly_total_id, date, day_of_week, activity_calories, miles, total_calories) 
VALUES (1, 1, '2024-01-01', 'MONDAY', 0, 0, 0);

INSERT INTO daily_total (id, weekly_total_id, date, day_of_week, activity_calories, miles, total_calories) 
VALUES (2, 1, '2024-01-02', 'TUESDAY', 0, 0, 0);

INSERT INTO daily_total (id, weekly_total_id, date, day_of_week, activity_calories, miles, total_calories) 
VALUES (3, 1, '2024-01-03', 'WEDNESDAY', 0, 0, 0);

INSERT INTO daily_total (id, weekly_total_id, date, day_of_week, activity_calories, miles, total_calories) 
VALUES (4, 1, '2024-01-04', 'THURSDAY', 0, 0, 0);

INSERT INTO daily_total (id, weekly_total_id, date, day_of_week, activity_calories, miles, total_calories) 
VALUES (5, 1, '2024-01-05', 'FRIDAY', 0, 0, 0);

INSERT INTO daily_total (id, weekly_total_id, date, day_of_week, activity_calories, miles, total_calories) 
VALUES (6, 1, '2024-01-06', 'SATURDAY', 0, 0, 0);

INSERT INTO daily_total (id, weekly_total_id, date, day_of_week, activity_calories, miles, total_calories) 
VALUES (7, 1, '2024-01-07', 'SUNDAY', 0, 0, 0);

insert into activities (calories_burned, distance, duration, met, daily_total_id) values (0, 0, 0, 0, 1);