package com.example.GUI;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import com.example.BLL.CategoryBLL;
import com.example.BLL.VegetableBLL;

public class VegetableForm extends JFrame implements ActionListener {
    VegetableBLL vegBLL = new VegetableBLL();
    CategoryBLL cateBLL = new CategoryBLL();
    int page = 1;
    VegetableForm() {
        init();
}
private void loadVegetable(int cateID)
    {
        List listVeg = cateBLL.getCategory(cateID).getListVegetable();
        Object[][] data = vegBLL.converVegetable(listVeg);
        String[] title = {"VegID", "Name", "Unit", "Amount", "Images", "Price"};
        DefaultTableModel model = new DefaultTableModel(data, title);
        jTable1.setModel(model);
    }
    private void loadCategory()
    {
        List listCate = cateBLL.loadCategory();
        Category[] newList = cateBLL.convertList1(listCate);
        CategoryModel model= new CategoryModel(newList);
        cbCategory = new JComboBox(model);
        cbCategory.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Category cate = (Category ) e.getItem();
                lbStatus.setText(cate.getCatagoryID() + " - " + cate.getName());
                int cateid = cate.getCatagoryID();
                loadVegetable(cateid);
            }
        });
        
    }

}