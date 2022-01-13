package org.petstore.service;

import org.petstore.domain.Category;
import org.petstore.domain.Item;
import org.petstore.domain.Product;
import org.petstore.persistence.impl.CategoryDAOImpl;
import org.petstore.persistence.impl.ItemDAOImpl;
import org.petstore.persistence.impl.ProductDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class CatelogService {
    public List<Category> getCategoryList() {
        return new CategoryDAOImpl().getCategoryList();
    }

    public Category getCategory(String categoryId) {
        return new CategoryDAOImpl().getCategory(categoryId);
    }

    public Product getProduct(String productId) {
        return new ProductDAOImpl().getProduct(productId);
    }

    public List<Product> getProductListByCategory(String categoryId) {
        return new ProductDAOImpl().getProductListByCategory(categoryId);
    }

    // TODO enable using more than one keyword
    public List<Product> searchProductList(String keyword) {
        return new ProductDAOImpl().searchProductList("%" + keyword.toLowerCase() + "%");
    }

    public List<Item> getItemListByProduct(String productId) {
        try {
            return new ItemDAOImpl().getItemListByProduct(productId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Item getItem(String itemId) {
        return new ItemDAOImpl().getItem(itemId);
    }

    public boolean isItemInStock(String itemId) {
        try {
            return new ItemDAOImpl().getInventoryQuantity(itemId) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
      return false;
    }
    public static void main(String []agrs)
    {
        try {
            System.out.println(new CatelogService().searchProductList("a"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}