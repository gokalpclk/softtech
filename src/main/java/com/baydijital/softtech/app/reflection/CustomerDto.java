package com.baydijital.softtech.app.reflection;

import lombok.Data;

/**
 * @author Gokalp on 8/9/22
 */

class CustomerDto {
    public Long id;
    private String name;
    private String surname;

    public CustomerDto(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFullName(){
        return this.name + " " + this.surname;
    }
}
