package com.usersystem.demo;

import java.util.LinkedList;
import java.util.List;

public class OperationObject {

    enum OperationType {
        register, login, modify, delete
    }

    OperationType type;
    List<String> data;

    public OperationObject(OperationType type, List<String> data) {
        this.type = type;
        this.data = data;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}

