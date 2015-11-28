package com.lm.client;

import com.lm.Task.PrintTask;
import com.lm.Task.PrintTaskQueue;
import com.lm.exception.DownloadException;
import com.lm.model.Account;
import com.lm.model.PrintFile;
import com.lm.model.User;
import com.lm.model.request.CSProtocol;
import com.lm.util.Help;
import com.lm.exception.PrintException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiuMian on 2015/11/27.
 */
public class ClientAssistant {

    private List<PrintTask> tasks;

    private Client client;

    public ClientAssistant(Client client){
        this.client = client;
        tasks = new ArrayList<PrintTask>();
    }


    public void assignTask(CSProtocol protocol){
        List<PrintFile> files = protocol.getFiles();
        Account account = protocol.getAccount();
        User user = protocol.getUser();

        if(files != null){
            downloadFiles(files);
        }

        if(account != null){
            loadAccount(account);
        }

        if(checkPaid(user)){
            try {
                doPrint(tasks);
            } catch (PrintException e) {
                e.printStackTrace();
                System.out.println("文件打印失败");

            }
        }


    }

    private void downloadFiles(List<PrintFile> files){
        for(PrintFile file:files){
            try {
                downloadFile(file);
            } catch (DownloadException e) {
                //TODO 下载文件出错，记录下来
                e.printStackTrace();
            }
        }
    }

    private void downloadFile(PrintFile file) throws DownloadException {

        Help.downloadFile(file.getPath());
        tasks.add(new PrintTask(file));
    }

    private void loadAccount(Account account){

    }

    private void doPrint(List<PrintTask> tasks) throws PrintException {
        PrintTaskQueue taskQueue = PrintTaskQueue.getPrintTaskQueue();
        taskQueue.addAll(tasks);
        taskQueue.doPrint();
    }


    private boolean checkPaid(User user){
        return user != null && user.getIsPay() == 1 ? true:false;
    }



}
