package no.jpro.flywaydemo.infrastructure;

import no.jpro.flywaydemo.domain.Person;
import no.jpro.flywaydemo.domain.PersonRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class PersonJpaRepository implements PersonRepository {
    private final EntityManager entityManager;

    public PersonJpaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Person save(Person person) {
        entityManager.persist(person);
        return person;
    }

    @Override
    public Person personByName(String firstName, String lastName) {
        TypedQuery<Person> query =
                entityManager.createQuery("select p from Person p where p.firstName=:firstName and p.lastName=:lastName", Person.class);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        return query.getSingleResult();
    }

    @Override
    public void clearCache() {
        entityManager.flush();
        entityManager.clear();
    }
}
