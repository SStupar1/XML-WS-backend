insert into bundle(id, publisher_id) values
(1, 1), (2, 2);

insert into reservation (id, from_date, to_date, from_time, to_time, customer_id, ad_id, status, simple_user, bundle_id) values
(1, '2021-01-15', '2021-01-18', '15:32', '12:33', 1, 1, 'PENDING', true, 1),
(2, '2021-01-13', '2021-01-15', '20:00', '20:00', 1, 2, 'PENDING', true, 1),
(3, '2021-02-13', '2021-02-15', '20:00', '20:00', 1, 3, 'PENDING', true, 1),
(4, '2021-02-16', '2021-02-19', '20:00', '20:00', 1, 4, 'PENDING', true, null),
(5, '2021-02-21', '2021-02-21', '08:00', '20:00', 2, 1, 'PENDING', false, 2),
(6, '2021-03-01', '2021-03-15', '20:00', '20:00', 2, 2, 'PENDING', false, 2),
(7, '2021-03-13', '2021-03-21', '20:00', '20:00', 2, 3, 'PENDING', false, null),
(8, '2021-03-19', '2021-03-21', '20:00', '20:00', 2, 6, 'PENDING', true, null),
(9, '2021-03-22', '2021-03-23', '20:00', '20:00', 2, 7, 'PENDING', true, null),
(10, '2021-04-13', '2021-05-15', '20:00', '20:00', 2, 4, 'PENDING', false, null);

