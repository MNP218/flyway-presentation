package no.jpro.flywaydemo.domain;

public interface PersonRepository {
    Person save(Person person);

    Person personByName(String firstName, String lastName);

    void clearCache();
}
