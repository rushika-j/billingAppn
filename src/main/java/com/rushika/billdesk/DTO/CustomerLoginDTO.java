package com.rushika.billdesk.DTO;



public class CustomerLoginDTO {
    private String email;
    private String password;

    public CustomerLoginDTO() {
    }

    public CustomerLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
