package com.rushika.billdesk.entity;



import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String role = "CUSTOMER";

    @Column(unique = true)
    private String email;

    private String name;
    private String phone;
    private String address;
    private String password;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "customer")
    private List<Invoice> invoices;

    public Customer() {}

    public Customer(String email, String name, String phone, String address, String password, LocalDateTime createdAt,String role) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.createdAt = createdAt;
        this.role=role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }}