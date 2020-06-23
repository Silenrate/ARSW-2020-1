package edu.eci.arsw.moneylaundering;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MoneyLaundering {
    private static TransactionAnalyzer transactionAnalyzer;
    private static TransactionReader transactionReader;
    private static int amountOfFilesTotal;
    private static AtomicInteger amountOfFilesProcessed;
    private static List<MoneyLaunderingThread> moneyThreads;
    private static int numberOfThreads;
    public static boolean isPaused;
    public static final Object monitor=new Object();

    public MoneyLaundering() {
        amountOfFilesProcessed = new AtomicInteger();
        transactionAnalyzer = new TransactionAnalyzer();
        transactionReader = new TransactionReader();
        amountOfFilesProcessed = new AtomicInteger();
        moneyThreads = new ArrayList<>();
        numberOfThreads = 5;
        isPaused = false;
    }

    public List<String> getOffendingAccounts() {
        return transactionAnalyzer.listOffendingAccounts();
    }

    private static List<File> getTransactionFileList() {
        List<File> csvFiles = new ArrayList<>();
        try (Stream<Path> csvFilePaths = Files.walk(Paths.get("src/main/resources/")).filter(path -> path.getFileName().toString().endsWith(".csv"))) {
            csvFiles = csvFilePaths.map(Path::toFile).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvFiles;
    }

    public static void main(String[] args) throws InterruptedException {
        MoneyLaundering moneyLaundering = new MoneyLaundering();
        amountOfFilesProcessed.set(0);
        moneyThreads = IntStream.rangeClosed(0,numberOfThreads-1).
                mapToObj(i -> new MoneyLaunderingThread(amountOfFilesProcessed,transactionReader,transactionAnalyzer))
                .collect(Collectors.toList());
        int amount = 0;
        List<File> transactionFiles = getTransactionFileList();
        amountOfFilesTotal = transactionFiles.size();

        for(int i=0;i<amountOfFilesTotal;i++){
            moneyThreads.get(amount).addFile(transactionFiles.get(i));
            amount++;
            amount %= numberOfThreads;
        }

        moneyThreads.forEach(t->t.start());

        while (amountOfFilesProcessed.get()<amountOfFilesTotal) {
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            if(isPaused){
                isPaused=false;
                System.out.println("Is now Running");
                synchronized (monitor){
                    monitor.notifyAll();
                }
            }
            else{
                System.out.println("Is now Paused");
                isPaused=true;
                Thread.sleep(1);
                String message = "Processed %d out of %d files.\nFound %d suspect accounts.";
                List<String> offendingAccounts = moneyLaundering.getOffendingAccounts();
                message = String.format(message, moneyLaundering.amountOfFilesProcessed.get(), moneyLaundering.amountOfFilesTotal, offendingAccounts.size());
                System.out.println(message);
            }

            if(line.contains("exit"))
                break;
        }
        System.out.println("Finished");
        String message = "Processed %d out of %d files.\nFound %d suspect accounts:\n%s";
        List<String> offendingAccounts = moneyLaundering.getOffendingAccounts();
        String suspectAccounts = offendingAccounts.stream().reduce("", (s1, s2) -> s1 + "\n" + s2);
        message = String.format(message, moneyLaundering.amountOfFilesProcessed.get(), moneyLaundering.amountOfFilesTotal, offendingAccounts.size(), suspectAccounts);
        System.out.println(message);

    }


}
