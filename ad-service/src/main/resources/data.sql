insert into fuel_type(id, type, tank_capacity) values
(1, 'Diesel', '30L'),
(2, 'Diesel', '40L'),
(3, 'Diesel', '50L'),
(4, 'Diesel', '60L'),
(5, 'Diesel', '70L'),
(6, 'Benzine', '30L'),
(7, 'Benzine', '40L'),
(8, 'Benzine', '50L'),
(9, 'Benzine', '60L'),
(10, 'Benzine', '70L');

insert into gearshift_type(id, type, number_of_gears) values
(1, 'Manuel', 4),
(2, 'Manuel', 5),
(3, 'Manuel', 6),
(4, 'Automatic', 8),
(5, 'Automatic', 10);

insert into car_brand(id, name, country) values
(1, 'BMW', 'Germany'),
(2, 'Audi', 'Germany'),
(3, 'Mercedes', 'Germany'),
(4, 'Volkswagen', 'Germany'),
(5, 'Opel', 'Germany'),
(6, 'Porsche ', 'Germany'),
(7, 'Fiat', 'Italy'),
(8, 'Ferrari', 'Italy'),
(9, 'Volvo', 'Sweden'),
(10, 'Reno', 'France'),
(11, 'Peugeot', 'France'),
(12, 'Citroen', 'France'),
(13, 'Toyota', 'Japan'),
(14, 'Nissan', 'Japan'),
(15, 'Honda', 'Japan');

insert into car_class(id, name) values
(1, 'Hatchback'),
(2, 'Crossover'),
(3, 'Coupe'),
(4, 'SUV'),
(5, 'Pickup'),
(6, 'Van'),
(7, 'City'),
(8, 'Sedan');

insert into car_model(id, name, car_brand_id, car_class_id) values
(1, 'X1', 1, 2), (2, 'Q5', 2, 2), (3, 'Civic', 15, 8),
(4, 'A3', 2, 7), (5, 'Yaris', 13, 7), (6, 'M3', 1, 3),
(7, 'R8', 2, 3), (8, 'GT-R', 14, 3), (9, '911', 6, 3),
(10, 'Juke', 14, 2), (11, 'Macan', 6, 2), (12, 'Q7', 2, 4),
(13, 'X6', 1, 4), (14, 'G-class', 3, 4), (15, 'Series 5', 1, 8),
(16, 'Series 7', 1, 8), (17, 'S-class', 3, 8), (18, 'E-class', 3, 8),
(19, 'Panamera', 6, 8), (20, 'M5', 1, 8), (21, 'Titan', 14, 5),
(22, 'Tacoma', 13, 5), (23, 'Odyssey', 15, 6), (24, 'V-class', 3, 6),
(25, 'NV-3500', 15, 6), (26, 'A1', 2, 1), (27, 'Series 1', 1, 1),
(28, 'Civic', 15, 1), (29, 'Clio', 10, 1), (30, '207', 11, 7);

insert into car(id, km_traveled, car_model_id, fuel_type_id, gearshift_type_id) values
(1, 15000, 1, 8, 4),
(2, 7000, 5, 5, 3),
(3, 150000, 15, 8, 4),
(4, 40000, 12, 9, 3),
(5, 13000, 3, 1, 5),
(6, 10000, 7, 5, 2),
(7, 120000, 29, 4, 2),
(8, 75000, 23, 3, 3),
(9, 3500, 22, 10, 1),
(10, 30000, 14, 8, 1);

insert into ad(id, agent,car_id, limited_distance, limited_km, cdw, seats, creation_date) values
(1, 1, 1, false, 0, false, 4, '2020-07-08'),
(2, 1, 3, false, 0, true, 2, '2020-01-08'),
(3, 1, 5, false, 0, true, 3, '2020-11-23'),
(4, 1, 4, true, 40, false, 7, '2020-07-13'),
(5, 1, 10, true, 20, false, 8, '2020-02-15'),
(6, 2, 8, true, 15, true, 4, '2020-03-01'),
(7, 2, 9, false, 0, true, 4, '2020-07-03'),
(8, 2, 7, false, 0, false, 4, '2020-04-21'),
(9, 2, 2, true, 150, true, 2, '2020-04-22'),
(10, 2, 6, true, 30, false, 4, '2020-12-01');
