INSERT INTO oauth_client_details (client_id, client_secret, web_server_redirect_uri, scope, access_token_validity, refresh_token_validity, resource_ids, authorized_grant_types, additional_information)
VALUES ('mobile', '{bcrypt}$2a$10$g2XaOBhSgYBx7GtmJmyaK.06Ckr2h2hGVOuhYR6FxNqG9aMHj90kK', 'http://localhost:8080/login', 'READ,WRITE', '3600', '10000', 'inventory,payment', 'authorization_code,password,refresh_token,implicit', '{}');

INSERT INTO permission (NAME) VALUES
('create_officer'),  /* 1 */
('read_officer'),   /* 2 */
('update_officer'),  /* 3 */
('delete_officer'),  /* 4 */

('create_driver'),   /* 5 */
('read_driver'),    /* 6 */
('update_driver'),   /* 7 */
('delete_driver'),  /* 8 */

('create_fine'),     /* 9 */
('read_fine'),   /* 10 */
('update_fine'),    /* 11 */
('delete_fine'),    /* 12 */

('create_offence'),  /* 13 */
('read_offence'),    /* 14 */
('update_offence'),  /* 15 */
('delete_offence');  /* 16 */

INSERT INTO role (NAME) VALUES
('ROLE_admin'),('ROLE_officer'),('ROLE_driver');

INSERT INTO permission_role (PERMISSION_ID, ROLE_ID) VALUES

/* Admin */
(1,1), /*create_officer -> admin */
(2,1), /* read_officer -> admin */
(3,1), /* update_officer -> admin */
(4,1), /* delete_officer -> admin */

(5,1), /*create_driver -> admin */
(6,1), /*read_driver -> admin */
(7,1), /*update_driver -> admin */
(8,1), /*delete_driver -> admin */

(9,1), /*create_fine -> admin */
(10,1), /*read_fine -> admin */
(11,1), /*update_fine -> admin */
(12,1), /*delete_fine -> admin */

(13,1), /*create_offence -> admin */
(14,1), /*read_offence -> admin */
(15,1), /*update_offence -> admin */
(16,1), /*delete_offence -> admin */

/* Officer */
(2,2),  /* read_officer -> officer */
(3,2),  /* update_officer -> officer */

(6,2),  /* read_driver -> officer */

(9,2),  /* create_fine -> officer */
(10,2),  /* read_fine -> officer */
(11,2),  /* update_fine -> officer */
(12,2),  /* delete_fine -> officer */

/* Driver */
(6,3),  /* read_driver -> driver */

(10,3); /* read_fine -> driver */


insert into user (id, username,password, email, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked) VALUES ('1', 'janitha','{bcrypt}$2a$10$e/0VdrRKm7DLdPcLrMOcSeAeJXSGPoX/RpgW64fUdrCPYncGoQenS', 'janitha@gmail.com', '1', '1', '1', '1');
insert into  user (id, username,password, email, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked) VALUES ('2', 'sampath', '{bcrypt}$2a$10$9HqPZoFGegzFMlWX2n31me8fI.hELA9PbnRjeNuQg6wzQFrYZcEDu','sampath@gmail.com', '1', '1', '1', '1');
insert into  user (id, username,password, email, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked) VALUES ('3', 'bandara', '{bcrypt}$2a$10$feZdn1oIXr.hSct6ZNr0vuW6iVQy5Pz1u3GZy0IdNa54Wn6ddmAmy','bandara@gmail.com', '1', '1', '1', '1');
/*
passowrds:
janitha - janitha@1234
sampath - sampath@1234
bandara - bandara@1234
*/


INSERT INTO role_user (ROLE_ID, USER_ID)
VALUES
(1, 1) /* janitha-admin */,
(2, 2) /* sampath-officer */ ,
(3, 3) /* bandara-driver */ ;