package org.petstore.persistence.impl;

import org.petstore.domain.Account;
import org.petstore.domain.CartItem;
import org.petstore.domain.CartItem;
import org.petstore.domain.Item;
import org.petstore.domain.CartItem;
import org.petstore.domain.Product;
import org.petstore.persistence.CartShopDAO;
import org.petstore.persistence.DBUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartShopDAOImpl implements CartShopDAO {
    private static final String SELECT_CARTSHOPItemS="SELECT ACCOUNT.USERID,I.ItemID,LISTPRICE,UNITCOST,CARTSHOP.QUANTITY,SUPPLIER AS supplierId,I.PRODUCTID AS productId,NAME AS NAME,DESCN AS description,CATEGORY AS categoryId,INVENTORY.QTY,ATTR1 AS attribute1,ATTR2 AS attribute2,ATTR3 AS attribute3,ATTR4 AS attribute4,ATTR5 AS attribute5 FROM Item I, PRODUCT P ,ACCOUNT,CARTSHOP ,INVENTORY WHERE P.PRODUCTID = I.PRODUCTID AND I.ItemID=CARTSHOP.ItemID AND ACCOUNT.USERID=? AND INVENTORY.ITEMID=CARTSHOP.ItemID";
    private  static final String INSERT_ITEMS = "INSERT INTO CARTSHOP (USERID, ITEMID, PRODUCTID, QUANTITY )" +
            "VALUES (?,?,?,?)";
    private static final String DELETE_ITEM = "DELETE FROM CARTSHOP WHERE USERID=? AND ITEMID=?";
    private static final String UPDATE_ITEM= "UPDATE CARTSHOP SET QUANTITY=? WHERE USERID=? AND ITEMID=?";
    private static final String DELETE_ALL="DELETE FROM CARTSHOP where USERID=?";
    @Override
    public List<CartItem> getCartShopItems(Account account) throws SQLException {
        List<CartItem> ListItems = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CARTSHOPItemS);
        preparedStatement.setString(1,account.getUsername());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
        {
            CartItem cartItem = new CartItem();
            Item item = new Item();
            item.setItemId((resultSet.getString(2)));
            BigDecimal bigDecimal =new BigDecimal(resultSet.getString(3));
            item.setListPrice(bigDecimal);
            BigDecimal decimal = new BigDecimal(resultSet.getString(4));
            item.setUnitCost(decimal);
            cartItem.setQuantity(Integer.parseInt(resultSet.getString(5)));
            item.setSupplierId(resultSet.getInt(6));

            Product product = new Product();
           product.setProductId(resultSet.getString(7));
            product.setName(resultSet.getString(8));
            product.setDescription(resultSet.getString(9));
            product.setCategoryId(resultSet.getString(10));
            item.setProduct(product);
           cartItem.setInStock(resultSet.getInt(11));
            item.setAttribute1(resultSet.getString(12));
            item.setAttribute2(resultSet.getString(13));
            item.setAttribute3(resultSet.getString(14));
            item.setAttribute4(resultSet.getString(15));
            item.setAttribute5(resultSet.getString(16));
            cartItem.setItem(item);
            ListItems.add(cartItem);
        }
        try {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListItems;
    }

    @Override
    public void insertIntoCartShop(Account account ,CartItem item) {

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ITEMS);

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, item.getItem().getItemId());
            preparedStatement.setString(3,item.getItem().getProduct().getProductId());
            preparedStatement.setInt(4,item.getQuantity());

            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void deleteItemFromCartShop(Account account, CartItem item) {
        try{
            Connection connection=DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(DELETE_ITEM);

            preparedStatement.setString(1,account.getUsername());
            preparedStatement.setString(2,item.getItem().getItemId());

            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateItemQuantity(Account account, CartItem item) {
        try{
            Connection connection=DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(UPDATE_ITEM);

            preparedStatement.setInt(1,item.getQuantity());
            preparedStatement.setString(2,account.getUsername());
            preparedStatement.setString(3,item.getItem().getItemId());
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll(Account account) {
        try{
            Connection connection=DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(DELETE_ALL);

            preparedStatement.setString(1,account.getUsername());

            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
