package com.example.DAL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class Vegetable {
    @Id
    private int VegetableID;
    @Column
    private String VegetableName;
    @Column
    private String Unit;
    @Column
    private int Amount;
    @Column
    private String Image;
    @Column
    private Double Price;

    @ManyToOne
    @JoinColumn(name = "CatagoryID")
    private Category catagory;
}
