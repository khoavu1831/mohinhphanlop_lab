package com.example.DAL;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class VegetableDAL {
    
    Session session;
    
    public VegetableDAL()
    {
        session = HibernateUtils.getSessionFactory().openSession();
    }
    public Vegetable getVegetable(int vegetableID)
    {
        Vegetable obj;
        session.beginTransaction();
        obj = session.get(Vegetable.class, vegetableID);
        session.getTransaction().commit();
        return obj;
        
    }
    public List getVegetableInCategory(int categoryID)
    {
        List list;
        session.beginTransaction();
        Query q = session.createQuery("FROM Vegetable WHERE CatagoryID = :categoryID");
        q.setParameter("categoryID", categoryID);
        list = q.list();
        session.getTransaction().commit();
        return list;
    }

