package no.jpro.flywaydemo.domain;

import no.jpro.flywaydemo.AbstractJpaTest;
import no.jpro.flywaydemo.infrastructure.CompanyJpaRepository;
import no.jpro.flywaydemo.infrastructure.PersonJpaRepository;
import org.junit.Test;

import static java.util.Arrays.asList;
import static no.jpro.flywaydemo.domain.Gender.Female;
import static no.jpro.flywaydemo.domain.Gender.Male;
import static org.assertj.core.api.Assertions.assertThat;

public class CompanyRepositoryTest extends AbstractJpaTest {
    @Test
    public void save_whenNewEntity_createsId() {
        CompanyRepository repository = new CompanyJpaRepository(entityManager());
        Company jPro = new Company("jPro");
        assertThat(jPro.id()).isNull();

        repository.save(jPro);
        assertThat(jPro.id()).isNotNull();
    }

    @Test
    public void companyByName_whenEntityExists_returnsEntity() {
        CompanyRepository repository = new CompanyJpaRepository(entityManager());
        Company jPro = new Company("jPro");
        repository.save(jPro);

        Company savedCompany = repository.companyByName("jPro");
        assertThat(savedCompany.id()).isEqualTo(jPro.id());
        assertThat(savedCompany.name()).isEqualTo("jPro");
    }

    @Test
    public void employeesRelation_mapsCorrectly() {
        CompanyRepository companyRepository = new CompanyJpaRepository(entityManager());
        Company jPro = new Company("jPro");
        companyRepository.save(jPro);

        Person frode = new Person("Frode", "Rystad", Male);
        Person john = new Person("Jane", "Doe", Female);

        PersonRepository personRepository = new PersonJpaRepository(entityManager());
        personRepository.save(frode);
        personRepository.save(john);

        jPro.addEmployee(frode);
        jPro.addEmployee(john);
        companyRepository.save(jPro);

        clearCache();

        Company companyFromDb = companyRepository.companyByName("jPro");
        assertThat(companyFromDb.employees()).containsAll(asList(frode, john));

        companyFromDb.removeEmployee(john);
        companyRepository.save(companyFromDb);

        clearCache();

        companyFromDb = companyRepository.companyByName("jPro");
        assertThat(companyFromDb.employees()).containsAll(asList(frode));
    }
}
