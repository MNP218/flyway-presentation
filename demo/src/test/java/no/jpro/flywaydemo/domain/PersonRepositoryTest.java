package no.jpro.flywaydemo.domain;

import no.jpro.flywaydemo.AbstractJpaTest;
import no.jpro.flywaydemo.infrastructure.PersonJpaRepository;
import org.junit.Test;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonRepositoryTest extends AbstractJpaTest {
    @Test
    public void verifyMapping() {
        EntityManager entityManager = entityManager();
        PersonRepository repository = new PersonJpaRepository(entityManager);
        Person johnDoe = new Person("John", "Doe");
        repository.save(johnDoe);
        assertThat(johnDoe.getId()).isNotNull();

        Person savedPerson = repository.personByName("John", "Doe");
        assertThat(savedPerson.getId()).isEqualTo(johnDoe.getId());
    }

}
