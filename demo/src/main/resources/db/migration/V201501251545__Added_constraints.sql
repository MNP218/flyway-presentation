ALTER TABLE company ADD CONSTRAINT company_name_unique UNIQUE (name);

ALTER TABLE person ADD CONSTRAINT person_name_unique UNIQUE (firstname, lastname);
ALTER TABLE person ADD FOREIGN KEY (company_id) REFERENCES company (id);
