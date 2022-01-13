package org.petstore.persistence.impl;

import org.petstore.domain.Account;
import org.petstore.persistence.AccountDAO;
import org.petstore.persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountDAOImpl implements AccountDAO {
    private static final String GETACCOUNT_BY_USERNAME="SELECT SIGNON.USERNAME,ACCOUNT.EMAIL,ACCOUNT.FIRSTNAME,ACCOUNT.LASTNAME,ACCOUNT.STATUS,ACCOUNT.ADDR1 AS address1,ACCOUNT.ADDR2 AS address2,ACCOUNT.CITY,ACCOUNT.STATE,ACCOUNT.ZIP,ACCOUNT.COUNTRY,ACCOUNT.PHONE,PROFILE.LANGPREF AS languagePreference,PROFILE.FAVCATEGORY AS favouriteCategoryId,PROFILE.MYLISTOPT AS listOption,PROFILE.BANNEROPT AS bannerOption,BANNERDATA.BANNERNAME FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA WHERE ACCOUNT.USERID =? AND SIGNON.USERNAME = ACCOUNT.USERID AND PROFILE.USERID = ACCOUNT.USERID AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";

    private static final String GETACCOUNT_BY_USERNAMEANDPASSWORD="SELECT SIGNON.USERNAME,ACCOUNT.EMAIL,ACCOUNT.FIRSTNAME,ACCOUNT.LASTNAME,ACCOUNT.STATUS,ACCOUNT.ADDR1 AS address1,ACCOUNT.ADDR2 AS address2," +
            "    ACCOUNT.CITY,ACCOUNT.STATE,ACCOUNT.ZIP,ACCOUNT.COUNTRY,ACCOUNT.PHONE,PROFILE.LANGPREF AS languagePreference,PROFILE.FAVCATEGORY AS favouriteCategoryId," +
            "    PROFILE.MYLISTOPT AS listOption,PROFILE.BANNEROPT AS bannerOption,BANNERDATA.BANNERNAME FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA" +
            "    WHERE PROFILE.USERID=SIGNON.USERNAME AND ACCOUNT.USERID=SIGNON.USERNAME AND  SIGNON.USERNAME = ? AND SIGNON.PASSWORD = ? AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";

    private static final String UPDATE_ACCOUNT="UPDATE account SET" +
            "      EMAIL = ?," +
            "      FIRSTNAME = ?," +
            "      LASTNAME = ?," +
            "      STATUS = ?," +
            "      ADDR1 = ?," +
            "      ADDR2 = ?," +
            "      CITY = ?," +
            "      STATE = ?," +
            "      ZIP = ?," +
            "      COUNTRY = ?," +
            "      PHONE = ?" +
            "    WHERE USERID = ?";

    private static final String INSERT_ACCOUNT=" INSERT INTO ACCOUNT" +
            "      (EMAIL, FIRSTNAME, LASTNAME, STATUS, ADDR1, ADDR2, CITY, STATE, ZIP, COUNTRY, PHONE, USERID)" +
            "    VALUES" +
            "      (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String INSERT_PROFILE="" +
            "    INSERT INTO PROFILE (LANGPREF, FAVCATEGORY, USERID)" +
            "    VALUES (?, ?, ?)";

    private static final String INSERT_SIGNON="INSERT INTO SIGNON (PASSWORD,USERNAME)" +
            "    VALUES (?, ?)";

    private static final String UPDATE_PROFILE="    UPDATE PROFILE SET" +
            "      LANGPREF = ?," +
            "      FAVCATEGORY = ?" +
            "    WHERE USERID = ?";

    private static final String UPDATE_SIGNON="UPDATE SIGNON SET PASSWORD = ?" +
            "    WHERE USERNAME = ?";
    @Override
    public Account getAccountByUsername(String username) {
        Account account=null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(GETACCOUNT_BY_USERNAME);
            pStatement.setString(1,username);
            ResultSet res=pStatement.executeQuery();
            if (res.next()){
                account = new Account();
                account.setUsername(res.getString(1));
                account.setEmail(res.getString(2));
                account.setFirstName(res.getString(3));
                account.setLastName(res.getString(4));
                account.setStatus(res.getString(5));
                account.setAddress1(res.getString(6));
                account.setAddress2(res.getString(7));
                account.setCity(res.getString(8));
                account.setState(res.getString(9));
                account.setZip(res.getString(10));
                account.setCountry(res.getString(11));
                account.setPhone(res.getString(12));
                account.setLanguagePreference(res.getString(13));
                account.setFavouriteCategoryId(res.getString(14));
                account.setListOption(res.getBoolean(15));
                account.setBannerOption(res.getBoolean(16));
                account.setBannerName(res.getString(17));
            }
            DBUtil.closeResultSet(res);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return account;
    }


    @Override
    public Account getAccountByUsernameAndPassword(Account account) {
        Account account1=null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(GETACCOUNT_BY_USERNAMEANDPASSWORD);
            pStatement.setString(1,account.getUsername());
            pStatement.setString(2,account.getPassword());
            ResultSet res=pStatement.executeQuery();
            if (res.next()){
                account1 = new Account();
                account1.setUsername(res.getString(1));
                account1.setEmail(res.getString(2));
                account1.setFirstName(res.getString(3));
                account1.setLastName(res.getString(4));
                account1.setStatus(res.getString(5));
                account1.setAddress1(res.getString(6));
                account1.setAddress2(res.getString(7));
                account1.setCity(res.getString(8));
                account1.setState(res.getString(9));
                account1.setZip(res.getString(10));
                account1.setCountry(res.getString(11));
                account1.setPhone(res.getString(12));
                account1.setLanguagePreference(res.getString(13));
                account1.setFavouriteCategoryId(res.getString(14));
                account1.setListOption(res.getBoolean(15));
                account1.setBannerOption(res.getBoolean(16));
                account1.setBannerName(res.getString(17));
            }
            DBUtil.closeResultSet(res);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return account1;
    }

    @Override
    public void insertAccount(Account account) {
        try{
            Connection connection=DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(INSERT_ACCOUNT);

            preparedStatement.setString(1,account.getEmail());
            preparedStatement.setString(2,account.getFirstName());
            preparedStatement.setString(3,account.getLastName());
            preparedStatement.setString(4,account.getStatus());
            preparedStatement.setString(5,account.getAddress1());
            preparedStatement.setString(6,account.getAddress2());
            preparedStatement.setString(7,account.getCity());
            preparedStatement.setString(8,account.getState());
            preparedStatement.setString(9,account.getZip());
            preparedStatement.setString(10,account.getCountry());
            preparedStatement.setString(11,account.getPhone());
            preparedStatement.setString(12,account.getUsername());
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void insertProfile(Account account) {
        try{
            Connection connection=DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(INSERT_PROFILE);

            preparedStatement.setString(1,account.getLanguagePreference());
            preparedStatement.setString(2,account.getFavouriteCategoryId());
            preparedStatement.setString(3,account.getUsername());

            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void insertSignon(Account account) {
        try{
            Connection connection=DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(INSERT_SIGNON);

            preparedStatement.setString(1,account.getPassword());
            preparedStatement.setString(2,account.getUsername());

            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateAccount(Account account) {
        try{
            Connection connection=DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(UPDATE_ACCOUNT);

            preparedStatement.setString(1,account.getEmail());
            preparedStatement.setString(2,account.getFirstName());
            preparedStatement.setString(3,account.getLastName());
            preparedStatement.setString(4,account.getStatus());
            preparedStatement.setString(5,account.getAddress1());
            preparedStatement.setString(6,account.getAddress2());
            preparedStatement.setString(7,account.getCity());
            preparedStatement.setString(8,account.getState());
            preparedStatement.setString(9,account.getZip());
            preparedStatement.setString(10,account.getCountry());
            preparedStatement.setString(11,account.getPhone());
            preparedStatement.setString(12,account.getUsername());
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void updateProfile(Account account) {
        try{
            Connection connection=DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(UPDATE_PROFILE);

            preparedStatement.setString(1,account.getLanguagePreference());
            preparedStatement.setString(2,account.getFavouriteCategoryId());
            preparedStatement.setString(3,account.getUsername());

            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateSignon(Account account) {
        try{
            Connection connection=DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(UPDATE_SIGNON);

            preparedStatement.setString(1,account.getPassword());
            preparedStatement.setString(2,account.getUsername());

            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Account account = new Account();
        account.setUsername("2");
        account.setPassword("2");

        System.out.println(new AccountDAOImpl().getAccountByUsernameAndPassword(account).toString());
    }
}