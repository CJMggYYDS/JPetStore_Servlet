package org.petstore.persistence;

public interface LogDAO {
    void insertLog(String username, String logInfo);
}
