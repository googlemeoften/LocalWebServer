package com.lm.Task;

import com.lm.model.PrintFile;
import com.lm.exception.PrintException;

import java.util.concurrent.Callable;

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

        //TODO 调用打印机，得到返回值，如false 直接抛异常

        if(false){
            throw new PrintException("打印失败！文件名："+file.getFilename());
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
