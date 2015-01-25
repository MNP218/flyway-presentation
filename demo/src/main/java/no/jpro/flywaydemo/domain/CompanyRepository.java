package no.jpro.flywaydemo.domain;

public interface CompanyRepository {
    Company save(Company person);

    Company companyByName(String name);
}
