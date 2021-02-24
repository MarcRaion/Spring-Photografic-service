package com.marcinha.stylist.countries.controller;

import com.marcinha.stylist.countries.Country;
import com.marcinha.stylist.countries.service.CountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {

    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/list")
    public List<Country> list(){
        return countryService.list();
    }
}