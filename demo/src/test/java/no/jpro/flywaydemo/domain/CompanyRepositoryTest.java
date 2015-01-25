package no.jpro.flywaydemo.domain;

import no.jpro.flywaydemo.AbstractJpaTest;
import no.jpro.flywaydemo.infrastructure.CompanyJpaRepository;
import no.jpro.flywaydemo.infrastructure.PersonJpaRepository;
import org.junit.Test;

import java.util.Arrays;

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

        Person frodeR = new Person("Frode", "Rystad");
        Person rune = new Person("Rune", "Steinseth");

        PersonRepository personRepository = new PersonJpaRepository(entityManager());
        personRepository.save(frodeR);
        personRepository.save(rune);

        jPro.addEmployee(frodeR);
        jPro.addEmployee(rune);
        companyRepository.save(jPro);

        clearCache();

        Company companyFromDb = companyRepository.companyByName("jPro");
        assertThat(companyFromDb.employees()).containsAll(Arrays.asList(frodeR, rune));

        companyFromDb.removeEmployee(frodeR);
        companyRepository.save(companyFromDb);

        clearCache();

        companyFromDb = companyRepository.companyByName("jPro");
        assertThat(companyFromDb.employees()).containsAll(Arrays.asList(rune));
    }
}
