/*REQUIRES BD KYC CUSTOMER.sql and BD KYC Catalogs.sql*/

DROP TABLE IF EXISTS TRANSACTIONS;
DROP TABLE IF EXISTS TRANSACTION_STATUS;
DROP TABLE IF EXISTS SERVICE_CHARGE;
DROP TABLE IF EXISTS SERVICE_CHARGE_DETAIL;
DROP TABLE IF EXISTS PAYMENT;
DROP TABLE IF EXISTS PAYMENT_STATUS;

CREATE TABLE SERVICE_CHARGE(
    ID SERIAL,
    ID_SERVICE INTEGER,
    ID_CUSTOMER INTEGER
);

CREATE TABLE SERVICE_CHARGE_DETAIL(
	ID SERIAL,
	REFERENCE_PAYMENT VARCHAR(20),
	AMOUNT_CHARGE VARCHAR(10),
	PAID CHAR(1),
	DATE_CHARGE DATE
);

CREATE TABLE PAYMENT(
    FOLIO SERIAL,
    PAYMENT_SOURCE VARCHAR(50),
    AMOUNT VARCHAR(10),
    MOTIVE VARCHAR(50),
    ID_STATUS_PAYMENT INTEGER,
    ID_SERVICE_CHARGE INTEGER,
    DATE_PAYMENT DATE NOT NULL
);


CREATE TABLE TRANSACTIONS(
    ID SERIAL,
    SOURCE VARCHAR(20),
    DESTINATION VARCHAR(20),
    ID_STATUS INTEGER,
    ID_BANK INTEGER,
    ID_FOLIO_PAYMENT INTEGER,
    DATE_START TIMESTAMP,
    DATE_FINISH TIMESTAMP
);

CREATE TABLE TRANSACTION_STATUS(
    ID SERIAL,
    DESCRIPTION VARCHAR(10)
);

CREATE TABLE PAYMENT_STATUS(
    ID SERIAL,
    DESCRIPTION VARCHAR(15)
);

ALTER TABLE PAYMENT ADD PRIMARY KEY(FOLIO);
ALTER TABLE PAYMENT_STATUS ADD PRIMARY KEY (ID);
ALTER TABLE TRANSACTIONS ADD PRIMARY KEY(ID);
ALTER TABLE TRANSACTION_STATUS ADD PRIMARY KEY(ID);
ALTER TABLE SERVICE_CHARGE ADD PRIMARY KEY(ID);
ALTER TABLE SERVICE_CHARGE_DETAIL ADD PRIMARY KEY(ID);

ALTER TABLE PAYMENT ADD CONSTRAINT FK_ID_STATUS_PAYMENT
FOREIGN KEY (ID_STATUS_PAYMENT) REFERENCES PAYMENT_STATUS(ID) ON DELETE CASCADE;

ALTER TABLE TRANSACTIONS ADD CONSTRAINT FK_ID_STATUS
FOREIGN KEY (ID_STATUS) REFERENCES TRANSACTION_STATUS(ID) ON DELETE CASCADE;

ALTER TABLE TRANSACTIONS ADD CONSTRAINT FK_ID_BANK
FOREIGN KEY (ID_BANK) REFERENCES AUTHORIZED_BANKS(ID) ON DELETE CASCADE;

ALTER TABLE TRANSACTIONS ADD CONSTRAINT FK_ID_FOLIO_PAYMENT
FOREIGN KEY (ID_FOLIO_PAYMENT) REFERENCES PAYMENT(FOLIO) ON DELETE CASCADE;

ALTER TABLE SERVICE_CHARGE ADD CONSTRAINT FK_ID_SERVICE
FOREIGN KEY (ID_SERVICE) REFERENCES SERVICES(ID) ON DELETE CASCADE;

ALTER TABLE SERVICE_CHARGE ADD CONSTRAINT FK_ID_CUSTOMER
FOREIGN KEY (ID_CUSTOMER) REFERENCES CUSTOMER(ID) ON DELETE CASCADE;

ALTER TABLE SERVICE_CHARGE_DETAIL ADD CONSTRAINT FK_ID_SERVICE_CHARGE
FOREIGN KEY (ID) REFERENCES SERVICE_CHARGE(ID) ON DELETE CASCADE;

ALTER TABLE PAYMENT ADD CONSTRAINT FK_ID_SERVICE_CHARGE_PAYMENT
FOREIGN KEY (ID_SERVICE_CHARGE) REFERENCES SERVICE_CHARGE(ID) ON DELETE CASCADE;

INSERT INTO PAYMENT_STATUS(DESCRIPTION) VALUES('REJECTED');
INSERT INTO PAYMENT_STATUS(DESCRIPTION) VALUES('PAID');
INSERT INTO PAYMENT_STATUS(DESCRIPTION) VALUES('ONGOING');

INSERT INTO TRANSACTION_STATUS(DESCRIPTION) VALUES('SUCCESS');
INSERT INTO TRANSACTION_STATUS(DESCRIPTION) VALUES('APPROVED');
INSERT INTO TRANSACTION_STATUS(DESCRIPTION) VALUES('PENDING');
INSERT INTO TRANSACTION_STATUS(DESCRIPTION) VALUES('SEND');
INSERT INTO TRANSACTION_STATUS(DESCRIPTION) VALUES('DECLINED');
INSERT INTO TRANSACTION_STATUS(DESCRIPTION) VALUES('FAILED');
