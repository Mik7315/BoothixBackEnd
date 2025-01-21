CREATE TABLE users(
   id_user  INT,
   username VARCHAR(50) NOT NULL,
   role     VARCHAR(50) NOT NULL,

   PRIMARY KEY(id_user),
   UNIQUE(username)
);

CREATE TABLE clients(
   id_client        INT,
   type             VARCHAR(50) NOT NULL,
   first_name       VARCHAR(50),
   last_name        VARCHAR(50),
   denomination     VARCHAR(50),
   vat_number       VARCHAR(50),
   bce_number       VARCHAR(50),
   phone_number     VARCHAR(50) NOT NULL,
   email            VARCHAR(50) NOT NULL,
   street           VARCHAR(50) NOT NULL,
   house_number     VARCHAR(50) NOT NULL,
   box              VARCHAR(50),
   city             VARCHAR(50) NOT NULL,
   zip_code         VARCHAR(50) NOT NULL,
   country          VARCHAR(50) NOT NULL,
   created_by       INT NOT NULL,
   created_date     TIMESTAMP NOT NULL,
   modified_by      INT,
   modified_date    TIMESTAMP,

   PRIMARY KEY(id_client),
   UNIQUE(denomination),
   UNIQUE(vat_number),
   UNIQUE(bce_number),
   UNIQUE(email)
);

CREATE TABLE options(
   id_option    INT,
   name         VARCHAR(50) NOT NULL,
   description  VARCHAR(50) NOT NULL,
   price        DECIMAL(15,2) NOT NULL,

   PRIMARY KEY(id_option)
);

CREATE TABLE devices(
   id_device    INT,
   name         VARCHAR(50) NOT NULL,
   description  VARCHAR(50) NOT NULL,
   type         VARCHAR(50) NOT NULL,

   PRIMARY KEY(id_device)
);

CREATE TABLE formulas(
   id_formula   INT,
   name         VARCHAR(50) NOT NULL,
   description  VARCHAR(50) NOT NULL,
   price        DECIMAL(15,2) NOT NULL,
   id_device    INT NOT NULL,

   PRIMARY KEY(id_formula),
   FOREIGN KEY(id_device) REFERENCES devices(id_device)
);

CREATE TABLE reservations(
   id_reservation       INT,
   phone_number         VARCHAR(50),
   street               VARCHAR(50),
   house_number         VARCHAR(50),
   box                  VARCHAR(50),
   city                 VARCHAR(50),
   zip_code             VARCHAR(50),
   country              VARCHAR(50),
   status               VARCHAR(50),
   event_date           TIMESTAMP NOT NULL,
   installation_date    TIMESTAMP,
   pick_up_date         TIMESTAMP,
   delivery_cost        DECIMAL(15,2),
   discount             DECIMAL(15,2),
   total_price          DECIMAL(15,2),
   deposit              DECIMAL(15,2),
   comment              VARCHAR(500),
   gallery_link         VARCHAR(500),
   id_device            INT NOT NULL,
   id_client            INT NOT NULL,
   created_by           INT NOT NULL,
   created_date         TIMESTAMP NOT NULL,
   modified_by          INT,
   modified_date        TIMESTAMP,

   PRIMARY KEY(id_reservation),
   FOREIGN KEY(id_device) REFERENCES devices(id_device),
   FOREIGN KEY(id_client) REFERENCES clients(id_client)
);

CREATE TABLE options_reservations(
   id_reservation   INT,
   id_option        INT,

   PRIMARY KEY(id_reservation, id_option),
   FOREIGN KEY(id_reservation) REFERENCES reservations(id_reservation),
   FOREIGN KEY(id_option) REFERENCES options(id_option)
);

CREATE TABLE formulas_reservations(
   id_reservation   INT,
   id_formula       INT,

   PRIMARY KEY(id_reservation, id_formula),
   FOREIGN KEY(id_reservation) REFERENCES reservations(id_reservation),
   FOREIGN KEY(id_formula) REFERENCES formulas(id_formula)
);
