package com.jisellemartins.cards.models;

public class Card {
    private int id;
    private int number;
    private String validity;
    private String codSecutiry;
    private String password;
    private String desc;
    private String key;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getCodSecutiry() {
        return codSecutiry;
    }

    public void setCodSecutiry(String codSecutiry) {
        this.codSecutiry = codSecutiry;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
