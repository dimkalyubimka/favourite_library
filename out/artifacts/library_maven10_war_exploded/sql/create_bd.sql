DROP SCHEMA IF EXISTS public CASCADE;
CREATE SCHEMA public;

CREATE TABLE users
(
    id           SERIAL NOT NULL
        CONSTRAINT user_pkey
        PRIMARY KEY,
    name         VARCHAR(45)            NOT NULL,
    lastname     VARCHAR(45)            NOT NULL,
    email        VARCHAR(45)            NOT NULL,
    passwordhash VARCHAR(45)            NOT NULL,
    role         VARCHAR(45)            NOT NULL
);

CREATE UNIQUE INDEX user_id_uindex
    ON users (id);

CREATE UNIQUE INDEX user_email_uindex
    ON users (email);

CREATE TABLE user_order
(
    id        SERIAL NOT NULL
        CONSTRAINT order_pkey
        PRIMARY KEY,
    bookid INTEGER                NOT NULL,
    userid    INTEGER                NOT NULL
        CONSTRAINT order_user_id_fk
        REFERENCES users
        ON UPDATE CASCADE ON DELETE CASCADE,
    status    VARCHAR(45)            NOT NULL
);

CREATE UNIQUE INDEX order_id_uindex
    ON user_order (id);

CREATE TABLE book_instance
(
    id        SERIAL NOT NULL
        CONSTRAINT book_pkey
        PRIMARY KEY,
    bookid INTEGER                NOT NULL
);

CREATE UNIQUE INDEX book_id_uindex
    ON book_instance (id);

CREATE TABLE book_order
(
    id      SERIAL NOT NULL
        CONSTRAINT book_order_pkey
        PRIMARY KEY,
    book_instanceid  INTEGER                NOT NULL
        CONSTRAINT book_order_book_id_fk
        REFERENCES book_instance
        ON UPDATE CASCADE ON DELETE CASCADE,
    user_orderid INTEGER                NOT NULL
        CONSTRAINT book_order_order_id_fk
        REFERENCES user_order
        ON UPDATE CASCADE ON DELETE CASCADE,
    option  VARCHAR(45)            NOT NULL
);

CREATE UNIQUE INDEX book_order_id_uindex
    ON book_order (id);

CREATE TABLE book
(
    id     SERIAL NOT NULL
        CONSTRAINT edition_pkey
        PRIMARY KEY,
    author VARCHAR(100),
    book_name VARCHAR(100),
    date_of_publication DATE         NOT NULL,
    cost                VARCHAR(200) DEFAULT 0,
    publication         VARCHAR(100) NOT NULL
);

CREATE UNIQUE INDEX edition_id_uindex
    ON book (id);

ALTER TABLE user_order
    ADD CONSTRAINT order_edition_id_fk
        FOREIGN KEY (bookid) REFERENCES book
            ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE book_instance
    ADD CONSTRAINT book_edition_id_fk
        FOREIGN KEY (bookid) REFERENCES book
            ON UPDATE CASCADE ON DELETE CASCADE;


INSERT INTO book(book_name, author,  cost, date_of_publication, publication) VALUES ('Professional Assembly Language','Richard Blum','500','1980-10-12','Ryefield');
INSERT INTO book(book_name, author,  cost, date_of_publication, publication) VALUES ('Дон Кіхот','Мігель де Сервантес Сааведра','300','2017-10-12','Фоліо');
INSERT INTO book(book_name, author,  cost, date_of_publication, publication) VALUES ('JavaScript для дітей. Веселий вступ до програмування','Нік Морган','700','2018-10-12','Видавництво Старого Лева');
INSERT INTO book(book_name, author,  cost, date_of_publication, publication) VALUES ('Я вибрав свободу','Віктор Кравченко','500','2002-10-12','Солоскип');
INSERT INTO book(book_name, author,  cost, date_of_publication, publication) VALUES ('Бетмен. Останній лицар на землі','Скот Снайдер','1200','2002-10-12','Рідна мова');
INSERT INTO book(book_name, author,  cost, date_of_publication, publication) VALUES ('Найбагатший чоловік у Вавилоні','Джордж Клейсон','500','2002-10-12','Наш Формат');
INSERT INTO book(book_name, author,  cost, date_of_publication, publication) VALUES ('Чистий код: створення, аналіз, рефакторинг','Роберт Мартін','500','2002-10-12','Фабула');
INSERT INTO book(book_name, author,  cost, date_of_publication, publication) VALUES (' Head First. Java','БЕРТ БЕЙТС, КЕТІ СЬЄРРА','500','2002-10-12','Фабула');
INSERT INTO book(book_name, author,  cost, date_of_publication, publication) VALUES ('Стен Лі. Життя як комікс','Ліел Лейбовіц','500','2002-10-12','Фабула');
INSERT INTO book(book_name, author,  cost, date_of_publication, publication) VALUES ('Великий Гетсбі','Френсіс Скотт Фіцджеральд','500','2002-10-12','Фабула');


INSERT INTO book_instance (bookid) VALUES (1);
INSERT INTO book_instance (bookid) VALUES (1);
INSERT INTO book_instance (bookid) VALUES (1);
INSERT INTO book_instance (bookid) VALUES (1);
INSERT INTO book_instance (bookid) VALUES (2);
INSERT INTO book_instance (bookid) VALUES (2);
INSERT INTO book_instance (bookid) VALUES (2);
INSERT INTO book_instance (bookid) VALUES (2);
INSERT INTO book_instance (bookid) VALUES (2);
INSERT INTO book_instance (bookid) VALUES (4);
INSERT INTO book_instance (bookid) VALUES (4);
INSERT INTO book_instance (bookid) VALUES (4);
INSERT INTO book_instance (bookid) VALUES (3);
INSERT INTO book_instance (bookid) VALUES (3);
INSERT INTO book_instance (bookid) VALUES (3);
INSERT INTO book_instance (bookid) VALUES (3);
INSERT INTO book_instance (bookid) VALUES (5);
INSERT INTO book_instance (bookid) VALUES (5);
INSERT INTO book_instance (bookid) VALUES (5);
INSERT INTO book_instance (bookid) VALUES (5);
INSERT INTO book_instance (bookid) VALUES (6);
INSERT INTO book_instance (bookid) VALUES (6);
INSERT INTO book_instance (bookid) VALUES (6);
INSERT INTO book_instance (bookid) VALUES (6);
INSERT INTO book_instance (bookid) VALUES (7);
INSERT INTO book_instance (bookid) VALUES (7);
INSERT INTO book_instance (bookid) VALUES (7);
INSERT INTO book_instance (bookid) VALUES (7);
INSERT INTO book_instance (bookid) VALUES (7);
INSERT INTO book_instance (bookid) VALUES (8);
INSERT INTO book_instance (bookid) VALUES (8);
INSERT INTO book_instance (bookid) VALUES (8);
INSERT INTO book_instance (bookid) VALUES (8);
INSERT INTO book_instance (bookid) VALUES (8);
INSERT INTO book_instance (bookid) VALUES (8);
INSERT INTO book_instance (bookid) VALUES (9);
INSERT INTO book_instance (bookid) VALUES (9);
INSERT INTO book_instance (bookid) VALUES (9);
INSERT INTO book_instance (bookid) VALUES (9);
INSERT INTO book_instance (bookid) VALUES (10);
INSERT INTO book_instance (bookid) VALUES (10);
INSERT INTO book_instance (bookid) VALUES (10);
INSERT INTO book_instance (bookid) VALUES (10);

INSERT INTO users (name, lastname, email, passwordhash, role)
VALUES ('Олександр', 'Ніколаєнко', 'olegsandr@gamil.com', '123', 'USER');
INSERT INTO users (name, lastname, email, passwordhash, role)
VALUES ('Андрій', 'Леонов', 'andrewet@gmail.com', '222', 'USER');
INSERT INTO users (name, lastname, email, passwordhash, role)
VALUES ('Микола', 'Терещенко', 'ivan20yo@gmail.com', '321', 'USER');
INSERT INTO users (name, lastname, email, passwordhash, role)
VALUES ('Галина', 'Степанівна', 'egorcreedfun@gmail.com', '1955', 'LIBRARIAN');
INSERT INTO users (name, lastname, email, passwordhash, role)
VALUES ('Дмитро', 'Сисадмін', 'gopak@gmail.com', 'AbCd', 'SYS_ADMIN');

INSERT INTO user_order (bookid, userid, status) VALUES (1, 1, 'NEW');
INSERT INTO user_order (bookid, userid, status) VALUES (2, 2, 'IN PROGRESS');
INSERT INTO user_order (bookid, userid, status) VALUES (3, 3, 'CLOSED');
INSERT INTO user_order (bookid, userid, status) VALUES (4, 4, 'NEW');
INSERT INTO user_order (bookid, userid, status) VALUES (3, 1, 'IN PROGRESS');

INSERT INTO book_order (book_instanceid, user_orderid, option) VALUES (4, 1, 'SUBSCRIPTION');
INSERT INTO book_order (book_instanceid, user_orderid, option) VALUES (6, 2, 'SUBSCRIPTION');
INSERT INTO book_order (book_instanceid, user_orderid, option) VALUES (11, 3, 'READING_ROOM');
INSERT INTO book_order (book_instanceid, user_orderid, option) VALUES (10, 4, 'READING_ROOM');