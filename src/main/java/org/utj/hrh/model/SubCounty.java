/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utj.hrh.model;

/**
 *
 * @author UTJ
 */
public class SubCounty {
    int DistrictID, CountyID,  active;
    String DistrictNom;

    public SubCounty() {
    }

    public int getDistrictID() {
        return DistrictID;
    }

    public void setDistrictID(int DistrictID) {
        this.DistrictID = DistrictID;
    }

    public int getCountyID() {
        return CountyID;
    }

    public void setCountyID(int CountyID) {
        this.CountyID = CountyID;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getDistrictNom() {
        return DistrictNom;
    }

    public void setDistrictNom(String DistrictNom) {
        this.DistrictNom = DistrictNom;
    }
    
    
}
