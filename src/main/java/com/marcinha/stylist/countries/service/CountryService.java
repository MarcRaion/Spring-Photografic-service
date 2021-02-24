package com.marcinha.stylist.countries.service;


import com.marcinha.stylist.countries.Country;
import com.marcinha.stylist.countries.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> list(){
        return countryRepository.findAll();
    }
    public void save(Country country){
        countryRepository.save(country);
    }

}
