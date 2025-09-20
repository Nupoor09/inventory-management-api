CREATE SEQUENCE inventory_quantity_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE Book (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE Location (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE inventory_quantity (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    book_id BIGINT,
    location_id BIGINT,
    quantity BIGINT
);


INSERT INTO Book (name) VALUES ('Harry Porter');
INSERT INTO Book (name) VALUES ('JAVA');

INSERT INTO Location (name) VALUES ('Fifty Avenue');

INSERT INTO Location (name) VALUES ('Austin');

INSERT INTO inventory_quantity(book_id,location_id,quantity) VALUES(1,1,3);
INSERT INTO inventory_quantity(book_id,location_id,quantity) VALUES(1,2,6);
