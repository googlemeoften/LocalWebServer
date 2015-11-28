package com.lm.Task;

import com.lm.model.PrintFile;
import com.lm.util.PrintException;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Created by LiuMian on 2015/11/27.
 */
public class PrintTask implements Callable<Integer>{

    private PrintFile file;

    public PrintTask(PrintFile file){
        this.file = file;
    }

    public boolean doPrint() throws PrintException{
        boolean printResult = false;

        //TODO ���ô�ӡ�����õ�����ֵ����false ֱ�����쳣

        if(false){
            throw new PrintException("��ӡʧ�ܣ��ļ�����"+file.getFilename());
        }
        return printResult;
    }


    @Override
    public Integer call() throws Exception {
        doPrint();
        return 0;
    }

    public PrintFile getFile(){
        return file;
    }
}
