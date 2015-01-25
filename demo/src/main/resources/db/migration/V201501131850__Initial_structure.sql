CREATE SEQUENCE personSeq;
CREATE SEQUENCE companySeq;

CREATE TABLE person
(
  id         BIGINT DEFAULT personSeq.nextval PRIMARY KEY,
  company_id BIGINT,
  firstName  VARCHAR,
  lastName   VARCHAR,
  gender     VARCHAR
);

CREATE TABLE company
(
  id   BIGINT DEFAULT companySeq.nextval PRIMARY KEY,
  name VARCHAR NOT NULL
);

