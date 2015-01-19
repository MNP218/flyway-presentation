CREATE SEQUENCE personSeq;
CREATE SEQUENCE friendshipSeq;

CREATE TABLE person
(
  id        BIGINT DEFAULT personSeq.nextval PRIMARY KEY,
  firstName VARCHAR,
  lastName  VARCHAR,
  gender    VARCHAR
);

CREATE TABLE friendship
(
  id              BIGINT DEFAULT friendshipSeq.nextval PRIMARY KEY,
  person1_id         BIGINT NOT NULL,
  person2_id         BIGINT NOT NULL,
  dateEstablished DATETIME
);

