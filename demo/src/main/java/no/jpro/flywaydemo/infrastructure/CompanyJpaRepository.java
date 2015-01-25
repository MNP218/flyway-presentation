package no.jpro.flywaydemo.infrastructure;

import no.jpro.flywaydemo.domain.Company;
import no.jpro.flywaydemo.domain.CompanyRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class CompanyJpaRepository implements CompanyRepository {
    private final EntityManager entityManager;

    public CompanyJpaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Company save(Company company) {
        entityManager.persist(company);
        return company;
    }

    @Override
    public Company companyByName(String name) {
        TypedQuery<Company> query =
                entityManager.createQuery("select c from Company c where c.name=:name", Company.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }
}
