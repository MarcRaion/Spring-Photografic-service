package com.marcinha.stylist.countries.repository;


import com.marcinha.stylist.countries.Country;
import org.hibernate.Session;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;



@Repository
public class CountryRepository {

    EntityManager entityManager;

    public CountryRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public List<Country> findAll() {

        Session currentSession = entityManager.unwrap(Session.class);
        Query<Country> theQuery = currentSession.createQuery("from Country ", Country.class);

        List<Country> countries = theQuery.getResultList();
        return countries;
    }

    @Transactional
    public void save(Country country) {

        Session currentSession = entityManager.unwrap(Session.class);


        Query query = currentSession.createSQLQuery("INSERT INTO Countries" +
                "(country_id, name, code, native_name,capital, region, subregion, currencies) " +
                "Select COUNTRY_NEXT_ID.nextval,?,?,?,?,?,?,? FROM DUAL " +
                "WHERE NOT EXISTS (SELECT * FROM Countries WHERE name = ?)");
        query.setParameter(1, country.getName());
        query.setParameter(2, country.getAlpha2Code());
        query.setParameter(3, country.getNativeName());
        query.setParameter(4, country.getCapital());
        query.setParameter(5, country.getRegion());
        query.setParameter(6, country.getSubregion());
        query.setParameter(7, country.getCurrencies());
        query.setParameter(8, country.getName());

        query.executeUpdate();

    }
}
