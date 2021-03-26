package com.usersystem.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.crypto.Data;
import java.util.Objects;
import java.util.UUID;

@Entity
public class DatabaseEntry {
    @Id
    @Column(name = "id")
    UUID id;

    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;

    public DatabaseEntry() {}

    public DatabaseEntry(UUID id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public DatabaseEntry(String username, String password) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatabaseEntry that = (DatabaseEntry) o;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(password, that.password);
    }
}
