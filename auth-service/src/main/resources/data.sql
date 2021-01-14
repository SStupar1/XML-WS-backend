insert into permission (name) values
('CREATE_AD'), ('VIEW_AD'), ('UPDATE_AD'), ('DELETE_AD'), ('POST_COMMENT'),
('READ_COMMENT'),  ('DENY_COMMENT'), ('APPROVE_COMMENT'), ('POST_RATE'), ('READ_RATE'),
('UPDATE_RATE'), ('CREATE_REQUEST'), ('LOGIN'), ('RECEIVE_MESSAGE'), ('REGISTER'),
('RENT_A_CAR'), ('SEARCH'), ('SEND_MESSAGE'), ('UPLOAD_PHOTO'), ('DELETE_RATE'),
('CREATE_AGENT'), ('READ_REQUEST'), ('APPROVE_REQUEST'), ('VIEW_IMAGE'), ('CRUD_CAR_BRAND'),
('CRUD_CAR_MODEL'), ('CRUD_CAR_CLASS'), ('CRUD_FUEL_TYPE'), ('CRUD_GEARSHIFT_TYPE'), ('APPROVE_AGENT'),
('DENY_AGENT'), ('CHANGE_PERMISSION');

insert into authority (name) values ('ROLE_ADMIN'), ('ROLE_AGENT'), ('ROLE_SIMPLE_USER'),
                                    ('ROLE_REVIEWER_USER'), ('ROLE_MESSAGE_USER'), ('ROLE_RENT_USER'), ('ROLE_COMMENT_USER'),
                                    ('ROLE_REQUEST'), ('ROLE_AD_USER');

insert into authorities_permissions (authority_id, permission_id) values
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (1, 10), (1, 11), (1, 12), (1, 13), (1, 14),
(1, 15), (1, 16), (1, 17), (1, 18), (1, 19), (1, 21), (1, 22), (1, 23), (1, 25), (1, 26), (1, 27), (1, 28), (1, 29), (1, 30), (1, 31), (1, 32),
(2, 1), (2, 2), (2, 3), (2, 4), (2, 19), (2, 22), (2, 23), (2, 24), (2, 12),
(3, 13), (3, 15), (3, 17), (3, 2), (3, 6), (3, 10), (3, 24), (3, 14),
(4, 9), (4, 10), (4, 11), (4, 20), (4, 24),
(5, 14), (5, 18), (5, 24),
(6, 16), (6, 12), (6, 24),
(7, 5), (7, 6), (7, 7), (7, 8), (7, 24),
(8, 22), (8, 24),
(9, 1);

-- admin@gmail.com -> Admin123!!!
-- agent@gmail.com -> Agent123!!!
-- agent1@gmail.com -> Agent123!!!
-- customer@gmail.com -> Customer123!!!
-- customer1@gmail.com -> Customer123!!!
insert into user_entity (id, username, password, has_signed_in, last_password_reset_date, user_role) values
(1, 'admin@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', false, '2020-01-01 10:10:11', 2),
(2, 'agent@gmail.com', '$2a$10$zQU7XEdDSMvxt13Xkjs3X.CCY64edvCS0ZXcgqPtU8FhSYVUhtnau', false, '2020-01-01 10:10:12', 1),
(3, 'agent1@gmail.com', '$2a$10$zQU7XEdDSMvxt13Xkjs3X.CCY64edvCS0ZXcgqPtU8FhSYVUhtnau', false, '2020-01-01 10:10:13', 1),
(4, 'customer@gmail.com', '$2a$10$UJEbOrAMWN/bh8tEPHt.Z.fD2RX.T0e0MXNuZEFCEFTNAjHkdAVju', false, '2020-01-01 10:10:14', 0),
(5, 'customer2@gmail.com', '$2a$10$UJEbOrAMWN/bh8tEPHt.Z.fD2RX.T0e0MXNuZEFCEFTNAjHkdAVju', false, '2020-01-01 10:10:15', 0);

insert into user_authority (user_id, authority_id) values
(1, 1),
(2, 2),
(2, 3),
(2, 4),
(2, 5),
(2, 7),
(3, 2),
(3, 3),
(3, 4),
(3, 5),
(3, 7),
(4, 3),
(4, 5),
(4, 6),
(4, 7);

insert into admin (id, first_name, last_name, user_id) values
(1, 'Ms', 'Misoni', 1);

insert into agent (id, bank_account_number, date_founded, name, pib, user_id, address, simple_user_id) values
(1, 'DE72 3702 0500 0009 7097 00', '2020-02-25', 'Marko Kraljevic',  '123-45-6789', 2, 'Svetislava Kasapinovica 3', null),
(2, '0500 0009 3702 FE22 7097 00', '2020-02-25', 'Dragan Topalovic', '321-54-9876', 3, 'Knez Mihailova 15', null);

insert into simple_user (id, address, first_name, last_name, request_status, ssn, user_id, deleted, num_of_ads) values
(1, 'Pionirska 26', 'Somi', 'Misoni', 'CONFIRMED', '1547854896523', 4, false, 0),
(2, 'Njegoseva 55', 'Didi', 'Mimica-Kostovic', 'CONFIRMED', '1547858576523', 5, true, 0);

