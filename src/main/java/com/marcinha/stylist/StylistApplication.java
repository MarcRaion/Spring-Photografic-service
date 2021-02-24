package com.marcinha.stylist;

import com.marcinha.stylist.countries.Country;
import com.marcinha.stylist.countries.service.CountryService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;


@SpringBootApplication
@EnableJpaRepositories
public class StylistApplication {

    private CountryService service;

    public StylistApplication(CountryService service) {
        this.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(StylistApplication.class, args);
    }

    @PostConstruct
    public void saveCountriesAfterStartup() throws Exception {
        {

            HttpResponse<String> response = Unirest.get("https://restcountries-v1.p.rapidapi.com/all")
                    .header("x-rapidapi-key", "4d228b8634msh503300a269b1687p1cb835jsnabd2c9cf9dc3")
                    .header("x-rapidapi-host", "restcountries-v1.p.rapidapi.com")
                    .asString();

            JSONParser parser = new JSONParser();
            Object object = parser.parse(response.getBody());
            JSONArray array = (JSONArray) object;

            for (Object o : array) {
                JSONObject jsonObject = (JSONObject) o;

                Country country = new Country(jsonObject.get("name").toString(),
                        jsonObject.get("alpha2Code").toString(),
                        jsonObject.get("nativeName").toString(),
                        jsonObject.get("capital").toString(),
                        jsonObject.get("region").toString(),
                        jsonObject.get("subregion").toString(),
                        jsonObject.get("currencies").toString()
                );

                service.save(country);
            }
            System.out.println("done");
        }
    }
}
