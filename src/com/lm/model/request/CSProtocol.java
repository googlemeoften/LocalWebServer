package com.lm.model.request;

import com.lm.model.Account;
import com.lm.model.PrintFile;
import com.lm.model.User;

import java.io.Serializable;
import java.util.List;



/**
 * Created by Cbillow on 15/11/27.
 */
public class CSProtocol implements Serializable{


    private List<PrintFile> files;

    private Account account;

    private User user;

//    private JSONObject json;

    public CSProtocol() {
    }

    public List<PrintFile> getFiles() {
        return files;
    }

    public Account getAccount() {
        return account;
    }

    public User getUser() {
        return user;
    }



}
