package com.example.calculmentalliamvassetnathanbonnard;

public enum Operations {
    ADD("+"),
    MOINS("-"),
    MULTIPLIER("*"),
    DIVISER("/");
    private String symbole;


    Operations(String s) {
        this.symbole=s;
    }

    public String getSymbole() {
        return symbole;
    }
}
