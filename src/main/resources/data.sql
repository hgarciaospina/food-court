use powerup

INSERT INTO user (
    name,
    surname,
    dni_number,
    birthdate,
    phone,
    email,
    password,
    id_role
  )
VALUES
  (
    'Pedro',
    'Picapiedra',
     10001012390,
    '2000-05-18',
    '+573234567890',
    ' ',
    '1234',
     1
  );


INSERT INTO role (description, name) VALUES ('ROLE_ADMIN', 'ROLE_ADMIN');
INSERT INTO role (description, name) VALUES ('ROLE_EMPLOYEE', 'ROLE_EMPLOYEE');
INSERT INTO role (description, name) VALUES ('ROLE_CUSTOMER', 'ROLE_CUSTOMER');
INSERT INTO role (description, name) VALUES ('ROLE_OWNER', 'ROLE_OWNER');
