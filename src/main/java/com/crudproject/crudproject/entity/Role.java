/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.crudproject.crudproject.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long role_id;
	
	@Basic
    @Column(name = "name", length = 50)
	private String name;
	
	
	
	public Role(Long role_id) {
		super();
		this.role_id = role_id;
	}

	public Role() {
		super();
	}

	public Role(String name) {
		super();
		this.name = name;
	}

        public Long getRole_id() {
            return role_id;
        }

        public void setRole_id(Long role_id) {
            this.role_id = role_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
	
	
}
