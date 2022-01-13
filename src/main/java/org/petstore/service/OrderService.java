package org.petstore.service;

import org.petstore.domain.*;
import org.petstore.persistence.*;
import org.petstore.persistence.impl.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {
    private ItemDAO itemDAO;
    private OrderDAO orderDAO;
    private SequenceDAO sequenceDAO;
    private LineItemDAO lineItemDAO;
    private CartShopDAO cartShopDAO;
    public OrderService(){
        itemDAO = new ItemDAOImpl();
        orderDAO = new OrderDAOImpl();
        sequenceDAO = new SequenceDAOImpl();
        lineItemDAO = new LineItemDAOImpl();
        cartShopDAO = new CartShopDAOImpl();
    }

    public void insertOrder(Order order)
    {
        order.setOrderId(getNextId("ordernum"));
        for(int i=0;i<order.getLineItems().size();i++)
        {
            LineItem lineItem=order.getLineItems().get(i);
            String itemId=lineItem.getItemId();
            Integer increment=lineItem.getQuantity();
            Map<String,Object>param=new HashMap<>(2);
            param.put("itemId",itemId);
            param.put("increment",increment);
            itemDAO.updateInventoryQuantity(param);
        }
        orderDAO.insertOrder(order);
        orderDAO.insertOrderStatus(order);
        for(int i=0;i<order.getLineItems().size();i++)
        {
            LineItem lineItem=order.getLineItems().get(i);
            lineItem.setOrderId(order.getOrderId());
            lineItemDAO.insertLineItem(lineItem);
        }
    }

    public Order getOrder(int orderId)
    {
        Order order= orderDAO.getOrder(orderId);
        order.setLineItems(lineItemDAO.getLineItemsByOrderId(orderId));
        for(int i=0;i<order.getLineItems().size();i++)
        {
            LineItem lineItem=order.getLineItems().get(i);
            Item item=itemDAO.getItem(lineItem.getItemId());
            item.setQuantity(itemDAO.getInventoryQuantity(lineItem.getItemId()));
            lineItem.setItem(item);
        }
        return order;
    }

    public List<Order> getOrderByUsername(String username)
    {
        return orderDAO.getOrdersByUsername(username);
    }

    public int getNextId(String name) {
        Sequence sequence = new Sequence(name, -1);
        sequence = sequenceDAO.getSequence(sequence);
        if (sequence == null) {
            throw new RuntimeException("Error: A null sequence was returned from the database (could not get next " + name
                    + " sequence).");
        }
        Sequence parameterObject = new Sequence(name, sequence.getNextId() + 1);
        sequenceDAO.updateSequence(parameterObject);
        return sequence.getNextId();
    }
    public List<CartItem> getCartShopItems(Account account) throws SQLException {
        return cartShopDAO.getCartShopItems(account);
    }
    public void insertIntoCartShop(Account account,CartItem cartItem)
    {
       cartShopDAO.insertIntoCartShop(account,cartItem);
    }
    public void deleteItemFromCartShop(Account account,CartItem cartItem)
    {
        cartShopDAO.deleteItemFromCartShop(account,cartItem);
    }
    public void updateItemQuantity(Account account,CartItem cartItem)
    {
        cartShopDAO.updateItemQuantity(account,cartItem);
    }
    public void deleteAll(Account account)
    {
        cartShopDAO.deleteAll(account);
    }
}
