package com.example.user_service.entities;

import com.example.user_service.dtos.UserEntityDTO;

import javax.persistence.*;

@Entity
@Table(name = "user_details")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    public UserEntity() {

    }

    public UserEntity(UserEntityDTO userEntityDTO) {
        this.id = userEntityDTO.getId();
        this.name = userEntityDTO.getName();
        this.name = userEntityDTO.getPassword();
    }

    public Long getId() {
        return id;
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
}
