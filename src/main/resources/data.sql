insert into users (name, weight, height, age, gender, bmr) values ('Raanan', 168, 70, 24, 'MALE', 0);
insert into users (name, weight, height, age, gender, bmr) values ('Ben', 150, 70, 24, 'MALE', 0);
insert into users (name, weight, height, age, gender, bmr) values ('Akiva', 200, 69, 26, 'MALE', 0);

INSERT INTO weekly_total (user_id, active_calories, activities_count, average_calories_per_day, total_miles) 
VALUES (1, 0, 0, 0, 0);

INSERT INTO weekly_total (user_id, active_calories, activities_count, average_calories_per_day, total_miles) 
VALUES (2, 0, 0, 0, 0); 

INSERT INTO weekly_total (user_id, active_calories, activities_count, average_calories_per_day, total_miles) 
VALUES (3, 0, 0, 0, 0);

INSERT INTO daily_total (weekly_total_id, day_of_week, activity_calories, miles, total_calories) 
VALUES (1,'MONDAY', 0, 0, 0);

INSERT INTO daily_total (weekly_total_id, day_of_week, activity_calories, miles, total_calories) 
VALUES (1, 'TUESDAY', 0, 0, 0);

INSERT INTO daily_total (weekly_total_id, day_of_week, activity_calories, miles, total_calories) 
VALUES (1, 'WEDNESDAY', 0, 0, 0);

INSERT INTO daily_total (weekly_total_id, day_of_week, activity_calories, miles, total_calories) 
VALUES (1, 'THURSDAY', 0, 0, 0);

INSERT INTO daily_total (weekly_total_id, day_of_week, activity_calories, miles, total_calories) 
VALUES (1, 'FRIDAY', 0, 0, 0);

INSERT INTO daily_total (weekly_total_id, day_of_week, activity_calories, miles, total_calories) 
VALUES (1, 'SATURDAY', 0, 0, 0);

INSERT INTO daily_total (weekly_total_id, day_of_week, activity_calories, miles, total_calories) 
VALUES (1, 'SUNDAY', 0, 0, 0);