package com.lm.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by LiuMian on 2015/11/27.
 */
public class PrintTaskQueue extends ArrayBlockingQueue<PrintTask>{

    private List<PrintTask> printSucces;
    private List<PrintTask> printFail;
    private static PrintTaskQueue printTasks = null;
    private ExecutorService executor;


    private PrintTaskQueue(int capacity) {
        super(capacity);
        executor = Executors.newSingleThreadExecutor();
        printFail = new ArrayList<PrintTask>();
        printSucces = new ArrayList<PrintTask>();
    }

    public static PrintTaskQueue getPrintTaskQueue(){
        if(printTasks == null)
            printTasks = new PrintTaskQueue(20);
        return printTasks;
    }

    public boolean addTask(PrintTask task){
        return printTasks.offer(task);
    }

    public boolean addAllTask(Collection<Callable<PrintTask>> printTasks){
        return printTasks.addAll(printTasks);
    }


    public boolean doPrint(){
        while (this.size() != 0){
            PrintTask task = this.poll();
            Future<Integer> future = executor.submit(task);
            try {
                future.get();
                task.getFile().setIsPrinted(true);
                //TODO 哪些文件未打印成功  要返回给调用者

            } catch (InterruptedException e) {
                e.printStackTrace();
                this.add(task);
                return false;
            } catch (ExecutionException e) {
                e.printStackTrace();
                this.add(task);
                return false;
            }
        }
        return true;
    }

}
