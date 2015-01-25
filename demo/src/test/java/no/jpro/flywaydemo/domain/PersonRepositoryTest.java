package no.jpro.flywaydemo.domain;

import no.jpro.flywaydemo.AbstractJpaTest;
import no.jpro.flywaydemo.infrastructure.CompanyJpaRepository;
import no.jpro.flywaydemo.infrastructure.PersonJpaRepository;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonRepositoryTest extends AbstractJpaTest {
    @Test
    public void save_whenNewEntity_createsId() {
        PersonRepository repository = new PersonJpaRepository(entityManager());
        Person johnDoe = new Person("John", "Doe");
        assertThat(johnDoe.id()).isNull();

        repository.save(johnDoe);
        assertThat(johnDoe.id()).isNotNull();
    }

    @Test
    public void personByName_whenEntityExists_returnsEntity() {
        PersonRepository repository = new PersonJpaRepository(entityManager());
        Person johnDoe = new Person("John", "Doe");
        repository.save(johnDoe);

        Person savedPerson = repository.personByName("John", "Doe");
        assertThat(savedPerson.id()).isEqualTo(johnDoe.id());
        assertThat(savedPerson.firstName()).isEqualTo("John");
        assertThat(savedPerson.lastName()).isEqualTo("Doe");
    }

    @Test
    public void company_mapsCorrectly() {
        CompanyRepository companyRepository = new CompanyJpaRepository(entityManager());
        Company jPro = new Company("jPro");
        companyRepository.save(jPro);

        PersonRepository personRepository = new PersonJpaRepository(entityManager());
        Person johnDoe = new Person("John", "Doe");
        johnDoe.setCompany(jPro);
        personRepository.save(johnDoe);

        clearCache();

        Person personFromDb = personRepository.personByName("John", "Doe");
        assertThat(personFromDb.company()).isNotNull();
    }

}
