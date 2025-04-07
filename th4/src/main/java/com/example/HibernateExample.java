package com.example;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction; // Correct import
import org.hibernate.cfg.Configuration;

public class HibernateExample {
    private static SessionFactory factory;

    public static void main(String[] args) {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object");
            throw new ExceptionInInitializerError(ex);
        }
        HibernateExample hb = new HibernateExample();
        hb.listDepartment();
    }

    public void listDepartment() {
        Session session = factory.openSession();
        Transaction tx = null; // Use org.hibernate.Transaction
        try {
            tx = session.beginTransaction(); // No casting needed
            List employees = session.createQuery("FROM Department").list();
            for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
                Department dp = (Department) iterator.next();
                System.out.println(" Name: " + dp.getName());
                System.out.println(" StartDate: " + dp.getStartDate());
                System.out.println(" Budget: " + dp.getBudget());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}