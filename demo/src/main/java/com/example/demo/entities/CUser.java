package com.example.demo.entities;

import javax.persistence.*;

@Entity
@Table //(name = "user_details")
public class CUser {

    @Id
    @GeneratedValue
    private Long id;

    // @Column(name = "name")
    private String name;

    // @Column(name = "password")
    private String password;

    public CUser() {
    }

    public CUser(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public CUser(String name, String password) {
        this.name = name;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CUser)) return false;

        CUser cUser = (CUser) o;

        if (!getId().equals(cUser.getId())) return false;
        if (!getName().equals(cUser.getName())) return false;
        return getPassword().equals(cUser.getPassword());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getPassword().hashCode();
        return result;
    }
}
