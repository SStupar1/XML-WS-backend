insert into bundle(id, publisher_id, status) values
(1, 1, 'PENDING'), (2, 1, 'APPROVED'), (3, 1,'PENDING');

insert into reservation (id, from_date, to_date, from_time, to_time, customer_id, ad_id, status, simple_user, bundle_id) values
(1, '2021-01-15', '2021-01-18', '15:32', '12:33', 1, 1, 'DROPPED', true, null),
(2, '2021-01-13', '2021-01-15', '20:00', '20:00', 1, 5, 'DROPPED', true, null),
(3, '2021-02-13', '2021-02-15', '20:00', '20:00', 1, 4, 'DROPPED', false, null),
(4, '2021-02-16', '2021-02-19', '20:00', '20:00', 1, 1, 'APPROVED', true, null),
(5, '2021-02-21', '2021-02-21', '08:00', '20:00', 2, 1, 'APPROVED', false, null),
(6, '2021-03-01', '2021-03-15', '20:00', '20:00', 2, 2, 'APPROVED', true, null),
(7, '2021-03-13', '2021-03-21', '20:00', '20:00', 2, 3, 'PENDING', false, 3),
(8, '2021-04-13', '2021-05-15', '20:00', '20:00', 2, 1, 'PENDING', false, 3),
(9, '2021-03-19', '2021-03-21', '20:00', '20:00', 2, 2, 'APPROVED', true, 2),
(10, '2021-03-22', '2021-03-23', '20:00', '20:00', 2, 3, 'APPROVED', true, 2),
(11, '2021-05-13', '2021-05-15', '20:00', '20:00', 1, 1, 'PENDING', true, 1),
(12, '2021-06-13', '2021-06-16', '20:00', '20:00', 1, 2, 'PENDING', true, 1);

insert into discount(id, discount) values
(1, 0),(2, 10),(3, 20), (4, 30), (5, 40), (6, 50), (7, 60), (8, 70), (9, 80), (10, 90);

insert into pricelist(id, price_per_day, price_per_kilometer, price_cdw, discount_id) values
(1, 10, 1, 0, 1),
(2, 15, 2, 10, 3);

