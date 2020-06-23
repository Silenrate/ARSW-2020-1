package edu.eci.arsw.exams.moneylaunderingapi.service;

import edu.eci.arsw.exams.moneylaunderingapi.model.MoneyLauderingException;
import edu.eci.arsw.exams.moneylaunderingapi.model.SuspectAccount;

import java.util.List;

public interface MoneyLaunderingService {
    void updateAccountStatus(SuspectAccount suspectAccount) throws MoneyLauderingException;
    SuspectAccount getAccountStatus(String accountId) throws MoneyLauderingException;
    List<SuspectAccount> getSuspectAccounts() throws MoneyLauderingException;
    void addSuspectAccount(SuspectAccount suspectAccount) throws MoneyLauderingException;
}
