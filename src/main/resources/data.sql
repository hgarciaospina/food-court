use powerup

INSERT INTO powerup.`user` (
            birthdate,
            dni_number,
            email,
            name,
            password,
            phone,
            surname,
            id_role)
VALUES('1999-01-29', '1002000002', 'pedro@gmail.com', 'Pedro', '$2a$10$YnKZP0bLf1A26OAFJpbNMuwDtJgIY3rewapA1yZmQ7k1ePGZkoZmy', '+573209876565', 'Picapiedra', 2);

#El password sin codificar es: Pedro1999*

INSERT INTO role (description, name) VALUES ('Administrator role', 'ROLE_ADMINISTRATOR');
INSERT INTO role (description, name) VALUES ('Owner role', 'ROLE_EMPLOYEE');
INSERT INTO role (description, name) VALUES ('Employee role', 'ROLE_EMPLOYEE');
INSERT INTO role (description, name) VALUES ('Customer role', 'CUSTOMER_ROLE');