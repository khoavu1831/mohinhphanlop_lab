package com.example.GUI;

import com.example.BLL.CategoryBLL;

public class CategoryForm extends javax.swing.JFrame {

    private CategoryBLL cateBLL;
    public CategoryForm() {
        initComponents();
        cateBLL = new CategoryBLL();
        loadCategoryTable();
    }

    public void loadCategoryTable()
    {
        List listCate = cateBLL.loadCategory();
        Object[][] datamodel;
        datamodel = cateBLL.convertList(listCate);
        String[] title = {"TT", "Name", "Description", "Count of Vegetable"};
        DefaultTableModel model = new DefaultTableModel(datamodel, title);
        jTableCategory.setModel(model);
    }
