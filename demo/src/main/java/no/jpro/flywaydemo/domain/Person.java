package no.jpro.flywaydemo.domain;

import javax.persistence.*;

@Entity
public class Person {
    @Id
    @SequenceGenerator(name="personSeq", sequenceName="personSeq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="personSeq")
    private Long personId;
    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    private Person() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getId() {
        return personId;
    }
}
