CREATE SEQUENCE personSeq;
CREATE SEQUENCE friendshipSeq;

CREATE TABLE person
(
  personId  BIGINT DEFAULT personSeq.nextval PRIMARY KEY,
  firstName VARCHAR,
  lastName  VARCHAR,
  gender    VARCHAR
);

CREATE TABLE friendship
(
  friendship_id   BIGINT DEFAULT friendshipSeq.nextval PRIMARY KEY,
  person1         INT,
  person2         INT,
  dateEstablished DATETIME
);

