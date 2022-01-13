package org.petstore.service;


import org.petstore.domain.Log;
import org.petstore.persistence.LogDAO;
import org.petstore.persistence.impl.LogDAOImpl;

public class LogService {
    Log log;
    private LogDAO logDAO;

    public LogService(){
        log = new Log();
        logDAO = new LogDAOImpl();
    }

    public String logInfo(Object ...s){
        return log.logInformation(s);
    }

    public void insertLogInfo(String username, String logInfo){
        logDAO.insertLog(username, logInfo);
    }
}
