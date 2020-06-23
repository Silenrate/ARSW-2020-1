
package edu.eci.arsw.exams.moneylaunderingapi.model;

public class MoneyLauderingException extends Exception {

    public MoneyLauderingException(String msg) {
        super(msg);
    }

    public MoneyLauderingException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
