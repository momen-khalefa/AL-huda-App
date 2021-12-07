package com.alhud.alhudastore;

public class cards {
    String car_id;
    int card_no;
    int card_total;
    float card_balance;
    float charge_in_nis;
    float charge_in_lit;

    public cards(String car_id,int card_no,int card_total,float card_balance,float charge_in_nis,float charge_in_lit){
        this.car_id=car_id;
        this.card_no=card_no;
        this.card_total=card_total;
        this.card_balance=card_balance;
        this.charge_in_nis=charge_in_nis;
        this.charge_in_lit=charge_in_lit;
    };

    public void setCar_id(String car_id) {
        this.car_id = car_id;
    }

    public String getCar_id() {
        return car_id;
    }

    public void setCard_no(int card_no) {
        this.card_no = card_no;
    }

    public int getCard_no() {
        return card_no;
    }

    public int getCard_total() {
        return card_total;
    }

    public void setCard_total(int card_total) {
        this.card_total = card_total;
    }

    public void setCard_balance(float card_balance) {
        this.card_balance = card_balance;
    }

    public float getCard_balance() {
        return card_balance;
    }

    public void setCharge_in_nis(float charge_in_nis) {
        this.charge_in_nis = charge_in_nis;
    }

    public float getCharge_in_nis() {
        return charge_in_nis;
    }

    public void setCharge_in_lit(float charge_in_lit) {
        this.charge_in_lit = charge_in_lit;
    }

    public float getCharge_in_lit() {
        return charge_in_lit;
    }
}
