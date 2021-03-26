package com.usersystem.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public final class Database {

    // A table representing the persons in the database
    private final static List<DatabaseEntry> persons = new LinkedList<>();
    private static Database instance;

    private Database() {}

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public static DatabaseEntry getPersonByID(UUID id) {
        for (DatabaseEntry i : persons) {
            if (i.getId().equals(id)) {
                return i;
            }
        }
        return null;
    }

    public static DatabaseEntry getPersonByName(String username) {
        for (DatabaseEntry i : persons) {
            if (i.getUsername().equals(username)) {
                return i;
            }
        }
        return null;
    }

    public static boolean addPerson(DatabaseEntry personToAdd) {
        for (DatabaseEntry i : persons) {
            if (personToAdd.equals(i)) {
                return false;
            }
        }
        return persons.add(personToAdd);
    }

    public static boolean modifyPerson(DatabaseEntry originalPerson, DatabaseEntry newPerson) {
        int index = persons.indexOf(originalPerson);
        if (index == -1) return false;
        persons.set(index, newPerson);
        return true;
    }

    public static boolean deletePerson(DatabaseEntry personToDelete) {
        return persons.remove(personToDelete);
    }

}
