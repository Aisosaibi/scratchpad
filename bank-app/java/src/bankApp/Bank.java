package bankApp;
//import java.util.Map;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private String name;
    private List<Account> accounts = new ArrayList<>();
    // Why not ArrayList<> accounts = new ArrayList<>();
    private int nextAccountNumber = 1001;

    public Account registerCustomer(String name, String initPin, String rePin) {
        boolean pinsMatch = initPin.equals(rePin);
        if (!pinsMatch) throw new InvalidPinException("Both pins must be the same");

        Account newAccount = new Account(name, initPin);
        newAccount.setAccountNumber(nextAccountNumber);
        nextAccountNumber++;
        accounts.add(newAccount);
        return newAccount;
    }

    public Account findAccount(int number) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == number) return account;
        }
        return null;
    }
}
