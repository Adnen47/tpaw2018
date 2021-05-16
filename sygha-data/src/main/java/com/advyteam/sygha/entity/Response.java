package com.advyteam.sygha.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
//
@Document(collection = "ESTABLISHMENT")
public class Response {
    public List<Establishment> establishments;
    public int totalPages;
    public int pageNumber;
    public int pageSize;
    public Response(List<Establishment> establishments,int totalPages, int pageNumber,int pageSize){
        this.establishments = establishments;
        this.totalPages = totalPages;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }
}
