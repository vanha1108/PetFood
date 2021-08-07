use petfoodonline;

CREATE TABLE customer
(
    id          bigint NOT NULL PRIMARY KEY auto_increment,
    codecustomer VARCHAR(100) NOT NULL,
    accountid bigint NOT NULL,
    fullname   VARCHAR(100) NULL,
    phonenumber VARCHAR(20) NULL,
    address     TEXT NULL,
    level       int NULL,
    totalmoney  double NULL
);

CREATE TABLE account
(
    id           bigint       NOT NULL PRIMARY KEY auto_increment,
    username     VARCHAR(50)  NOT NULL,
    password     VARCHAR(100) NOT NULL,
    roleid       bigint       NOT NULL,
    status       int          NOT NULL,
    createddate  TIMESTAMP NULL,
    modifieddate TIMESTAMP NULL,
    createdby    VARCHAR(100) NULL,
    modifiedby   VARCHAR(100) NULL
);

CREATE TABLE role
(
    id   bigint       NOT NULL PRIMARY KEY auto_increment,
    name VARCHAR(255) NOT NULL,
    code VARCHAR(255) NOT NULL
);

CREATE TABLE product
(
    id           bigint       NOT NULL PRIMARY KEY auto_increment,
    codeproduct       VARCHAR(255) NOT NULL,
    nameproduct         TEXT NULL,
    image        LONGTEXT NULL,
    description  TEXT NULL,
    categoryid   bigint       NOT NULL,
    producerid   bigint       NOT NULL,
    amount       bigint NULL,
    importprice  double       NOT NULL,
    price        double       NOT NULL,
    createddate  TIMESTAMP NULL,
    modifieddate TIMESTAMP NULL,
    createdby    VARCHAR(255) NULL,
    modifiedby   VARCHAR(255) NULL
);

CREATE TABLE category
(
    id   bigint       NOT NULL PRIMARY KEY auto_increment,
    codecategory VARCHAR(255) NOT NULL,
    namecategory TEXT NULL
);

CREATE TABLE producer
(
    id          bigint       NOT NULL PRIMARY KEY auto_increment,
    codeproducer       VARCHAR(255) NOT NULL,
    nameproducer        TEXT NULL,
    phonenumber VARCHAR(150) NULL
);

CREATE TABLE bill
(
    id           bigint       NOT NULL PRIMARY KEY auto_increment,
    codebill         VARCHAR(100) NOT NULL,
    note         TEXT NULL,
    customerid   bigint       NOT NULL,
    accountid    bigint       NOT NULL,
    totalprice   double       NOT NULL,
    createddate  TIMESTAMP NULL,
    modifieddate TIMESTAMP NULL,
    createdby    VARCHAR(255) NULL,
    modifiedby   VARCHAR(255) NULL
);

CREATE TABLE productofbill
(
    id     bigint      NOT NULL PRIMARY KEY auto_increment,
    billid bigint      NOT NULL,
    code   VARCHAR(50) NOT NULL,
    name   TEXT NULL,
    amount int         NOT NULL,
    price  double      NOT NULL
);

ALTER TABLE account
    ADD CONSTRAINT fk_account_role FOREIGN KEY (roleid) REFERENCES role (id);
ALTER TABLE product
    ADD CONSTRAINT fk_product_producer FOREIGN KEY (producerid) REFERENCES producer (id);
ALTER TABLE bill
    ADD CONSTRAINT fk_bill_customer FOREIGN KEY (customerid) REFERENCES customer (id);
ALTER TABLE bill
    ADD CONSTRAINT fk_bill_account FOREIGN KEY (accountid) REFERENCES account (id);
ALTER TABLE productofbill
    ADD CONSTRAINT fk_productofbill_bill FOREIGN KEY (billid) REFERENCES bill (id);
ALTER TABLE product
    ADD CONSTRAINT fk_product_category FOREIGN KEY (categoryid) REFERENCES category (id);

