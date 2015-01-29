package no.jpro.flywaydemo.domain;

import javax.persistence.*;

@Entity
public class Person {
    @Id
    @SequenceGenerator(name = "personSeq", sequenceName = "personSeq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personSeq")
    private Long id;

    private String firstName;

    private String lastName;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne
    private Company company;

    public Person(String firstName, String lastName, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public Person() {
    }

    public Long id() {
        return id;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public Company company() {
        return company;
    }

    public Gender gender() {
        return gender;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return !(id != null ? !id.equals(person.id) : person.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
