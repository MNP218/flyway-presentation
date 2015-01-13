package no.jpro.flywaydemo.domain;

import no.jpro.flywaydemo.AbstractJpaTest;
import no.jpro.flywaydemo.infrastructure.PersonJpaRepository;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonRepositoryTest extends AbstractJpaTest {
    @Test
    public void save_whenNewEntity_createsId() {
        PersonRepository repository = new PersonJpaRepository(entityManager());
        Person johnDoe = new Person("John", "Doe");
        assertThat(johnDoe.getId()).isNull();

        repository.save(johnDoe);
        assertThat(johnDoe.getId()).isNotNull();
    }

    @Test
    public void personByName_whenEntityExists_returnsEntity() {
        PersonRepository repository = new PersonJpaRepository(entityManager());
        Person johnDoe = new Person("John", "Doe");
        repository.save(johnDoe);

        Person savedPerson = repository.personByName("John", "Doe");
        assertThat(savedPerson.getId()).isEqualTo(johnDoe.getId());
    }

}
