package com.example.ServletProject.model.entity;

import java.util.Map;

public class Faculty{

    private String name;
    private int stateFundedAmount;
    private int feePayedAmount;

    private Map<String, Double> subjectWeights;

    public Faculty(String name, int stateFundedAmount, int feePayedAmount){
        this.name = name;
        this.stateFundedAmount = stateFundedAmount;
        this.feePayedAmount = feePayedAmount;
    }

    public int getFeePayedAmount() {
        return feePayedAmount;
    }

    public void setFeePayedAmount(int feePayedAmount) {
        this.feePayedAmount = feePayedAmount;
    }

    public int getStateFundedAmount() {
        return stateFundedAmount;
    }

    public void setStateFundedAmount(int stateFundedAmount) {
        this.stateFundedAmount = stateFundedAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
