package com.jisellemartins.cards.models;

public class Card {
    private int id;
    private long number;
    private String validity;
    private String codSecutiry;
    private String password;
    private String descricao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
