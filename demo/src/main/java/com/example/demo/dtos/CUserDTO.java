package com.example.demo.dtos;

public class CUserDTO {
    private Long id;
    private String name;
    private String password;

    public CUserDTO() {
    }

    public CUserDTO(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public CUserDTO(String name, String password) {
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

    /*
    We may not need this code. I think.

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
    */

}
