package com.alhud.alhudastore.objectes;

public class staiton {
    String staiton_name;
    String address;
    String longitude;
    String latitude;
    String phone;
    String image;

    public staiton() {
    }

    public staiton(String staiton_name, String address, String longitude, String latitude, String phone, String image) {
        this.staiton_name = staiton_name;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.phone = phone;
        this.image = image;
    }

    public String getStaiton_name() {
        return staiton_name;
    }

    public void setStaiton_name(String staiton_name) {
        this.staiton_name = staiton_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
