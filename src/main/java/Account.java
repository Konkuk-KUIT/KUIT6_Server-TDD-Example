public class Account {

    private Balance balance;

    private Account(Balance balance) {
        this.balance = balance;
    }

    public static Account createEmptyAccount() {
        return new Account(new Balance(0));
    }

    public static Account createWithBalance(NaturalNumber initialBalance) {
        return new Account(new Balance(initialBalance.getNumber()));
    }

    public int getBalance() {
        return balance.getBalance();
    }

    public void deposit(NaturalNumber i) {
        this.balance = balance.add(i);
    }

    public void withdraw(NaturalNumber i) {

        this.balance = balance.sub(i);
    }
}
