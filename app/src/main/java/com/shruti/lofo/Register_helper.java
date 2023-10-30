package com.shruti.lofo;

public class Register_helper {
    String name, email, phone, password;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Register_helper(String name, String email, String username, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
}
