package com.lm.client;

import com.lm.Task.PrintTask;
import com.lm.Task.PrintTaskQueue;
import com.lm.model.Account;
import com.lm.model.PrintFile;
import com.lm.model.User;
import com.lm.model.request.CSProtocol;
import com.lm.util.PrintException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiuMian on 2015/11/27.
 */
public class ClientAssistant {

    private static ClientAssistant clientAssistant = null;
    private List<PrintTask> tasks;

    private ClientAssistant(){
        tasks = new ArrayList<PrintTask>();
    }

    public static ClientAssistant getClientAssistant(){
        if(clientAssistant == null){
            clientAssistant = new ClientAssistant();
        }
        return clientAssistant;
    }

    public void parseCSProtocol(CSProtocol protocol){
        List<PrintFile> files = protocol.getFiles();
        Account account = protocol.getAccount();
        User user = protocol.getUser();



    }

    private void downloadFiles(List<PrintFile> files){

    }

    private void downloadFile(PrintFile file){

        //TODO обтьнд╪Ч

        tasks.add(new PrintTask(file));
    }

    private void loadAccount(Account account){

    }

    private void doPrint(List<PrintTask> tasks) throws PrintException {
        PrintTaskQueue taskQueue = PrintTaskQueue.getPrintTaskQueue();
        taskQueue.addAll(tasks);
        taskQueue.doPrint();
    }


    private int checkPaid(User user){

        return user.getIsPay();
    }













//    public List<PrintFile> getNotPrintFiles(){
//        List<PrintFile> notPrintFiles = new ArrayList<PrintFile>();
//        for(PrintTask task:tasks){
//            if(!task.getFile().isPrinted()){
//                notPrintFiles.add(task.getFile());
//            }
//        }
//        return notPrintFiles;
//    }
//
//    public List<PrintFile> getPrintFiles(){
//        List<PrintFile> printedFiles = new ArrayList<PrintFile>();
//        for(PrintTask task:tasks){
//            if(task.getFile().isPrinted()){
//                printedFiles.add(task.getFile());
//            }
//        }
//        return printedFiles;
//    }


}
