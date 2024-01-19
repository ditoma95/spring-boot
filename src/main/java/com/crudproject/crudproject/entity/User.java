/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.crudproject.crudproject.entity;


import com.crudproject.crudproject.entity.Role;
import java.util.ArrayList;
import java.util.List;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "users")
public class User implements Serializable {
    
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id ;
//    @OneToMany

    @ManyToMany
    @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    
    private List<Role> roles = new ArrayList<>();
    
    @Basic
    @Column(name = "username", length = 50)
    private String username;
    
    @Basic
    @Column(name = "password", length = 50)
    private String password;
    
    @Basic
    @Column(name = "enable", length = 50)
    // @ColumnDefault("false")
    private Boolean enable;
    
    @Basic
    @Column(name = "nom", length=50)
    private String nom;

    @Basic
    @Column(name = "prenom", length=50)
    private String prenom;

    @Basic
    @Column(name = "email", length=50)
    private String email;

    @Basic
    @Column(name = "telephone", length=50)
    private String telephone;
    
    public User() {
        super();
    }

    public User(String username, String password, Boolean enable, String nom, String prenom, String email, String telephone) {
        this.username = username;
        this.password = password;
        this.enable = enable;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
    }

    public User(String username, String password, Boolean enable, String nom, String prenom, String email, String telephone, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.enable = enable;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.roles = roles;
    }
    
     public List<Role> getRoles() {
        return roles;
    }


    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

   
    
    

    
    
}
