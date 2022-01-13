package org.petstore.persistence;

import org.petstore.domain.Account;
import org.petstore.domain.CartItem;
import org.petstore.domain.Item;

import java.sql.SQLException;
import java.util.List;

public interface CartShopDAO {
    List<CartItem> getCartShopItems(Account account) throws SQLException;
    void insertIntoCartShop(Account account,CartItem item);
    void deleteItemFromCartShop(Account account ,CartItem item);
    void updateItemQuantity(Account account , CartItem item);
    void deleteAll(Account account);
}
