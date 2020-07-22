/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.northwind.jpa.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author raihan
 */
@Entity
@Table(name = "shippers")
@NamedQueries({
    @NamedQuery(name = "Shippers.findAll", query = "SELECT c FROM Shippers c")})
public class Shippers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "shipperid")
    private String shipperID;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "companyname")
    private String companyName;
    
    @Size(max = 30)
    @Column(name = "phone")
    private String Phone;
    
    public Shippers(){
        
    }
    
    public Shippers(String shipperID){
        this.shipperID = shipperID;
    }
    
    public Shippers(String shipperID, String companyName){
        this.shipperID = shipperID;
        this.companyName = companyName;
    }
    
    public String getShipperID(){
        return shipperID;
    }
    
    public void setShipperID(String ShipperID) {
        this.shipperID = shipperID;
    }
    
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shipperID != null ? shipperID.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Shippers)) {
            return false;
        }
        Shippers other = (Shippers) object;
        if ((this.shipperID == null && other.shipperID != null) || (this.shipperID != null && !this.shipperID.equals(other.shipperID))) {
            return false;
        }
        return true;
    }
}