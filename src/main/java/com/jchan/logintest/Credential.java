/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jchan.logintest;

import com.jchan.jtableutils.JDataColumn;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Mr Jacky
 */
@Entity
public class Credential {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    
    @Column(unique=true)
    @JDataColumn(index=0)
    private String userName;
    
    @JDataColumn(index=1)
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String toString() {
        return id + ", " + userName + ", " + password;
    }
}
