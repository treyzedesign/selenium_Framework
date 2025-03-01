package org.example.pom.objects;

import org.example.pom.utils.JacksonUtils;

import java.io.IOException;

public class Products {
    private int id;
    private String name;

    public Products(){}

    public Products(int id) throws IOException {
        Products[] products = JacksonUtils.deserializeJson("Products.json", Products[].class);

        for (Products product: products) {
             if(product.id == id){
                 this.id = id;
                 this.name = product.name;
             }
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
