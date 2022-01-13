package org.petstore.persistence.impl;

import org.petstore.domain.Item;
import org.petstore.domain.Product;
import org.petstore.persistence.DBUtil;
import org.petstore.persistence.ItemDAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemDAOImpl implements ItemDAO {
    private static final String UPDATE_INVENTORY_QUANTITY="UPDATE INVENTORY SET QTY = QTY - ? WHERE ITEMID = ?";
    private static final String GETINVENTORYQUANTITY ="SELECT QTY AS value FROM INVENTORY WHERE ITEMID=?";
    private static final String GETITEMLISTBYPRODUCT = "SELECT I.ITEMID,LISTPRICE,UNITCOST,SUPPLIER AS supplierId,I.PRODUCTID AS productId,NAME AS NAME,DESCN AS description,CATEGORY AS categoryId,STATUS,ATTR1 AS attribute1,ATTR2 AS attribute2,ATTR3 AS attribute3,ATTR4 AS attribute4,ATTR5 AS attribute5 FROM ITEM I, PRODUCT P WHERE P.PRODUCTID = I.PRODUCTID AND I.PRODUCTID =?";//联表查询
    private static final String GETITEM = "SELECT I.ITEMID,LISTPRICE,UNITCOST,SUPPLIER AS supplierId,I.PRODUCTID AS \"product.productId\",NAME AS \"product.name\",DESCN AS \"product.description\",CATEGORY AS \"product.categoryId\",STATUS,ATTR1 AS attribute1,ATTR2 AS attribute2,ATTR3 AS attribute3,ATTR4 AS attribute4,ATTR5 AS attribute5,QTY AS quantity FROM ITEM I, INVENTORY V, PRODUCT P WHERE P.PRODUCTID = I.PRODUCTID AND I.ITEMID = V.ITEMID AND I.ITEMID =?";//链表查询

    @Override
    public void updateInventoryQuantity(Map<String, Object> param) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(UPDATE_INVENTORY_QUANTITY);
            String itemId = param.keySet().iterator().next();
            Integer increment = (Integer)param.get(itemId);
            pStatement.setInt(1, increment);
            pStatement.setString(2, itemId);
            pStatement.executeUpdate();
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getInventoryQuantity(String itemId) {
        int i=-1;
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GETINVENTORYQUANTITY);
            preparedStatement.setString(1,itemId);
            ResultSet result = preparedStatement.executeQuery();
            if(result.next())
            {
                i = Integer.parseInt(result.getString(1));
            }
            DBUtil.closeResultSet(result);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public List<Item> getItemListByProduct(String productId) throws SQLException, ClassNotFoundException {
        List<Item> items = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GETITEMLISTBYPRODUCT);
        preparedStatement.setString(1,productId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
        {
            Item item = new Item();
            item.setItemId(resultSet.getString(1));
            BigDecimal bigDecimal =new BigDecimal(resultSet.getString(2));
            item.setListPrice(bigDecimal);
            BigDecimal decimal = new BigDecimal(resultSet.getString(3));
            item.setUnitCost(decimal);
            item.setSupplierId(Integer.parseInt(resultSet.getString(4)));
            Product product=new Product();
            product.setProductId(resultSet.getString(5));
            product.setName(resultSet.getString(6));
            product.setDescription(resultSet.getString(7));
            product.setCategoryId(resultSet.getString(8));
            item.setProduct(product);
            item.setStatus(resultSet.getString(9));
            item.setAttribute1(resultSet.getString(10));
            items.add(item);
        }
        try {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public Item getItem(String itemId) {
        Item item = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GETITEM);
            preparedStatement.setString(1,itemId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                item = new Item();
                item.setItemId(resultSet.getString(1));
                BigDecimal bigDecimal =new BigDecimal(resultSet.getString(2));
                item.setListPrice(bigDecimal);
                BigDecimal decimal = new BigDecimal(resultSet.getString(3));
                item.setUnitCost(decimal);
                item.setSupplierId(Integer.parseInt(resultSet.getString(4)));
                Product product=new Product();
                product.setProductId(resultSet.getString(5));
                product.setName(resultSet.getString(6));
                product.setDescription(resultSet.getString(7));
                product.setCategoryId(resultSet.getString(8));
                item.setProduct(product);
                item.setStatus(resultSet.getString(9));
                item.setAttribute1(resultSet.getString(10));
                item.setAttribute2(resultSet.getString(11));
                item.setAttribute3(resultSet.getString(12));
                item.setAttribute4(resultSet.getString(13));
                item.setAttribute5(resultSet.getString(14));
                item.setQuantity(Integer.parseInt(resultSet.getString(15)));
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return item;
    }

}
