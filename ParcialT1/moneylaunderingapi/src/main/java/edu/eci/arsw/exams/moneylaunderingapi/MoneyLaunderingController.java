package edu.eci.arsw.exams.moneylaunderingapi;


import edu.eci.arsw.exams.moneylaunderingapi.model.MoneyLauderingException;
import edu.eci.arsw.exams.moneylaunderingapi.model.SuspectAccount;
import edu.eci.arsw.exams.moneylaunderingapi.service.MoneyLaunderingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestMapping(value = "/fraud-bank-accounts")
@RestController
public class MoneyLaunderingController {

    @Autowired
    MoneyLaunderingService moneyLaunderingService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> offendingAccounts() {
        try {
            return new ResponseEntity<>(moneyLaunderingService.getSuspectAccounts(), HttpStatus.ACCEPTED);
        } catch (MoneyLauderingException e) {
            Logger.getLogger(MoneyLaunderingController.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{accountId}", method = RequestMethod.GET)
    public ResponseEntity<?> accountStatus(@PathVariable String accountId) {
        try {
            return new ResponseEntity<>(moneyLaunderingService.getAccountStatus(accountId), HttpStatus.ACCEPTED);
        } catch (MoneyLauderingException e) {
            Logger.getLogger(MoneyLaunderingController.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addSuspect(@RequestBody SuspectAccount suspectAccount) {
        try {
            moneyLaunderingService.addSuspectAccount(suspectAccount);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (MoneyLauderingException e) {
            Logger.getLogger(MoneyLaunderingController.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{accountId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateSuspect(@RequestBody SuspectAccount suspectAccount) {
        try {
            moneyLaunderingService.updateAccountStatus(suspectAccount);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (MoneyLauderingException e) {
            Logger.getLogger(MoneyLaunderingController.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
