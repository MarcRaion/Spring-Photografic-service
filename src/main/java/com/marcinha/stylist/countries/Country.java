package com.marcinha.stylist.countries;

import javax.persistence.*;

@Entity
@Table(name="Countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "country_next_id", allocationSize = 1)
    @Column(name ="country_id")
    private int id;

    @Column
    private String name;

    @Column(name="code")
    private String alpha2Code;

    @Column(name="native_name")
    private String nativeName;
    @Column
    private String capital;
    @Column
    private String region;
    @Column
    private String subregion;
    @Column
    private String currencies;

    public Country() {
    }

    public Country(String name, String alpha2Code, String nativeName,
                   String capital, String region, String subregion, String currencies) {
        this.name = name;
        this.alpha2Code = alpha2Code;
        this.nativeName = nativeName;
        this.capital = capital;
        this.region = region;
        this.subregion = subregion;
        this.currencies = currencies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public String getCurrencies() {
        return currencies;
    }

    public void setCurrencies(String currencies) {
        this.currencies = currencies;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", alpha2Code='" + alpha2Code + '\'' +
                ", nativeName='" + nativeName + '\'' +
                ", capital='" + capital + '\'' +
                ", region='" + region + '\'' +
                ", subregion='" + subregion + '\'' +
                ", currencies='" + currencies + '\'' +
                '}';
    }
}
