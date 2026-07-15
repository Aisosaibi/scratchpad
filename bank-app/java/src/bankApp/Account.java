package bankApp;


public class Account {
    private String name;
    private int balance;
    private final String pin;
    private int number;

    public Account(){
        this("defaultName", "1234");
    }
    public Account(String name, String pin) {
        this.name = name;
        this.pin = pin;
    }

        public int getAccountNumber() {
        return number;
    }

    void setAccountNumber(int number){ //no modifier = package-private
        this.number = number;
    }

    public int checkBalance(String pin) {
        if (!this.pin.equals(pin)) throw new InvalidPinException("Invalid pin!");
        return balance;
    }

    public void deposit(int amount) {
        boolean validAmount = amount > 0;
        if (!validAmount) throw new InvalidAmountException("Invalid Amount") ;
        balance = balance + amount;
    }

    public void withdraw(int amount, String pin) {
        boolean validAmount = amount > 0;
        boolean sufficientFunds = amount <= balance;
        boolean securePin = this.pin.equals(pin);
        if (!securePin) throw new InvalidPinException("Invalid pin!");
        if (!validAmount) throw new InvalidAmountException("Invalid Amount");
        if (!sufficientFunds) throw new InsufficientFundsException("Insufficient Funds");
        balance = balance - amount;
    }


}
