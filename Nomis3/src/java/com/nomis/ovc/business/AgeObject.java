/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.business;

import java.io.Serializable;

/**
 *
 * @author smomoh
 */
public class AgeObject implements Serializable
{
    private int age;
    private int ageUnit;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAgeUnit() {
        return ageUnit;
    }

    public void setAgeUnit(int ageUnit) {
        this.ageUnit = ageUnit;
    }
    
}
