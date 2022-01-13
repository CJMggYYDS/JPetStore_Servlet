package org.petstore.persistence.impl;

import org.petstore.domain.Category;
import org.petstore.persistence.CategoryDAO;
import org.petstore.persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO
{
    private static final String SELECT_ALL="select * from category";
    private static final String SELECT_BY_ID="select name,descn from category where catid=?";

    public List<Category> getCategoryList()
    {
        List<Category> categoryList=new ArrayList<>();
        try{
            Connection connection= DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(SELECT_ALL);
            ResultSet res=preparedStatement.executeQuery();
            while(res.next())
            {
                Category category=new Category();
                category.setCategoryId(res.getString(1));
                category.setName(res.getString(2));
                category.setDescription(res.getString(3));
                categoryList.add(category);
            }
            DBUtil.closeResultSet(res);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);

        }catch (Exception e){
            e.printStackTrace();
        }
        return categoryList;
    }

    public Category getCategory(String categoryID)
    {
        Category category=null;
        try{
            Connection connection=DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setString(1,categoryID);
            ResultSet res=preparedStatement.executeQuery();
            if(res.next())
            {
                category=new Category();
                category.setCategoryId(categoryID);
                category.setName(res.getString(1));
                category.setDescription(res.getString(2));
            }
            DBUtil.closeResultSet(res);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);

        }catch (Exception e){
            e.printStackTrace();
        }
        return category;
    }
}
