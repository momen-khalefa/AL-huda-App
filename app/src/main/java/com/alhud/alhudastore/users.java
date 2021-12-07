package com.alhud.alhudastore;


import android.widget.Toast;

public class users {
    String email;
    String name;
    String password;
    String phone ;
public  users(String name, String password,String email ,String phone ){
    this.name=name;
    this.password=password;
    this.email=email;
    this.phone=phone;
}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPassword(String password) {
    if(authenticate(password))
        this.password = password;
    else
        return;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }
    public static boolean authenticate(String password)
    {
        if ((password.length() > 6) &&
                (password.length() < 16) &&
                (password.matches("[0-9]")))
            return true;
        else
            return false;
    }
    public static boolean isValidEmail(String emailId) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (emailId.toString().isEmpty()) {
            Toast.makeText(null, "enter email address", Toast.LENGTH_SHORT).show();
            return false;
        } else if (emailId.toString().trim().matches(emailPattern)) {
                Toast.makeText(null, "valid email address", Toast.LENGTH_SHORT).show();
                return true;
            } else {
                Toast.makeText(null, "Invalid email address", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

    }


