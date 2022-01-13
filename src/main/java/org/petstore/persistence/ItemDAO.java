package org.petstore.persistence;

import org.petstore.domain.Item;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ItemDAO {
    void updateInventoryQuantity(Map<String, Object> param);

    int getInventoryQuantity(String itemId);

    List<Item> getItemListByProduct(String productId) throws SQLException, ClassNotFoundException;

    Item getItem(String itemId);
}
