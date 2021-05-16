package com.advyteam.sygha.DTO;

import com.advyteam.sygha.entity.Company;

import java.util.List;


public class GroupDTO {

    private String code;
    private String label;
    private String address;
    private List<Company> companies;

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
