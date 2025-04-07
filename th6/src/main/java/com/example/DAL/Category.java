package com.example.DAL;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class Category {
    @Id
    private int CategoryID;
    @Column
    private String Name;
    @Column
    private String Description;
    @OneToMany (mappedBy = "catagory")
    private List<Vegetable> listVegetable;
    @Override
    public String toString() {
        return this.Name;
    }
}
