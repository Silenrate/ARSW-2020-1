package edu.eci.arsw.exams.moneylaunderingapi.service;

import edu.eci.arsw.exams.moneylaunderingapi.model.MoneyLauderingException;
import edu.eci.arsw.exams.moneylaunderingapi.model.SuspectAccount;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class MoneyLaunderingServiceStub implements MoneyLaunderingService {

    List<SuspectAccount> suspectAccounts = new CopyOnWriteArrayList<>();

    public MoneyLaunderingServiceStub() {
        suspectAccounts.add(new SuspectAccount("1234", 2));
        suspectAccounts.add(new SuspectAccount("5678", 7));
    }

    @Override
    public void updateAccountStatus(SuspectAccount suspectAccount) throws MoneyLauderingException {
        SuspectAccount target = null;
        for (SuspectAccount suspect : suspectAccounts) {
            if (suspect.getAccountId().equals(suspectAccount.getAccountId())) {
                target = suspect;
            }
        }
        System.out.println(target);
        if (target == null) {
            throw new MoneyLauderingException("No existe un usuario con ese Id");
        }
        target.updateAmountOfSmallTransactions(suspectAccount.getAmountOfSmallTransactions());
    }

    @Override
    public SuspectAccount getAccountStatus(String accountId) throws MoneyLauderingException {
        SuspectAccount suspect = null;
        for (SuspectAccount suspectAccount : suspectAccounts) {
            if (suspectAccount.getAccountId().equals(accountId)) {
                suspect = suspectAccount;
            }
        }
        if (suspect == null) {
            throw new MoneyLauderingException("No existe un usuario con ese Id");
        }
        return suspect;
    }

    @Override
    public List<SuspectAccount> getSuspectAccounts() throws MoneyLauderingException {
        return suspectAccounts;
    }

    @Override
    public void addSuspectAccount(SuspectAccount suspectAccount) throws MoneyLauderingException {
        for (SuspectAccount account : suspectAccounts) {
            if (suspectAccount.getAccountId().equals(account.getAccountId())) {
                throw new MoneyLauderingException("ESa cuenta ya existe, se debe realizar un PUT con el mismo numero de transaccaciones (estas le serán sumadas))");
            }
        }
        suspectAccounts.add(suspectAccount);
    }
}
