SET session_replication_role = replica;

insert into RESERVATION (id, negotiated_price, client_id, trip_id)
values (nextval('reservation_seq'), 150.99, 1, 1);
insert into RESERVATION (id, negotiated_price, client_id, trip_id)
values (nextval('reservation_seq'), 500.00, 2, 2);
insert into RESERVATION (id, negotiated_price, client_id, trip_id)
values (nextval('reservation_seq'), 240.99, 3, 3);
insert into RESERVATION (id, negotiated_price, client_id, trip_id)
values (nextval('reservation_seq'), 200.99, 4, 4);

insert into CLIENT (id, first_name, last_name, id_number, reservation_id)
values (nextval('client_seq'), 'Jan', 'Nowak', 'CDR097432', 1);
insert into CLIENT (id, first_name, last_name, id_number, reservation_id)
values (nextval('client_seq'), 'Piotr', 'Malysz', 'POE321234', 2);
insert into CLIENT (id, first_name, last_name, id_number, reservation_id)
values (nextval('client_seq'), 'Patryk', 'Koscielny', 'ERT345678', 3);
insert into CLIENT (id, first_name, last_name, id_number, reservation_id)
values (nextval('client_seq'), 'Maciej', 'Iluzyjny', 'PWE923456', 4);
insert into CLIENT (id, first_name, last_name, id_number, reservation_id)
values (nextval('client_seq'), 'Tomek', 'Borowski', 'OIU765343', null);

insert into TRIP (id, date_of_departure, date_of_return, suggested_price, destination_id, guide_id)
values (nextval('trip_seq'), '2018-04-30', '2018-05-10', 250.50, 1, 1),
       (nextval('trip_seq'), '2017-02-10', '2017-02-20', 870.99, 2, 2),
       (nextval('trip_seq'), '2020-01-30', '2020-02-03', 320.00, 3, 2),
       (nextval('trip_seq'), '2019-09-19', '2019-09-29', 250.50, 4, 1);


insert into TRIP_RESERVATION (trip_id, reservation_id)
values (1, 1),
       (2, 2),
       (3, 3),
       (4, 4);

insert into DESTINATION (id, destination_name, trip_id)
values (nextval('destination_seq'), 'Crete', 1),
       (nextval('destination_seq'), 'Thailand', 2),
       (nextval('destination_seq'), 'Philippines', 3),
       (nextval('destination_seq'), 'Crete', 4);

insert into TRAVEL_GUIDE (id, name)
values (nextval('guide_seq'), 'Bartek Pospiech'),
        (nextval('guide_seq'), 'Jan Pospieszalski');

insert into TRAVEL_GUIDE_TRIPS (guide_id, trips_id)
values (1, 1),
       (2, 2),
       (2, 3),
       (1, 4);

SET session_replication_role = DEFAULT;

commit;