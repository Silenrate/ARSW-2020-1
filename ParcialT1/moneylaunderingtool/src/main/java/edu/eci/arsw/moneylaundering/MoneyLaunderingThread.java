package edu.eci.arsw.moneylaundering;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MoneyLaunderingThread extends Thread{

    private List<File> files;
    private AtomicInteger amountOfFilesProcessed;
    private TransactionAnalyzer transactionAnalyzer;
    private TransactionReader transactionReader;

    public MoneyLaunderingThread(AtomicInteger amountOfFilesProccesed, TransactionReader transactionReader, TransactionAnalyzer transactionAnalyzer){
        this.amountOfFilesProcessed = amountOfFilesProccesed;
        this.transactionAnalyzer = transactionAnalyzer;
        this.transactionReader = transactionReader;
        files = new ArrayList<>();
    }

    public void addFile(File file){
        files.add(file);
    }

    @Override
    public void run() {
        //System.out.println("Voy a empezar y mis archivos son: "+files);
        for (File file : files) {
            List<Transaction> transactions = transactionReader.readTransactionsFromFile(file);
            for (Transaction transaction : transactions) {
                synchronized (MoneyLaundering.monitor){
                    if (MoneyLaundering.isPaused){
                        try {
                            MoneyLaundering.monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                transactionAnalyzer.addTransaction(transaction);
            }
            //System.out.println("Archivo "+file+"finalizado");
            amountOfFilesProcessed.incrementAndGet();
        }
    }
}
