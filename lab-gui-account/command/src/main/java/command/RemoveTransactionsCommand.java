package command;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Account;
import model.Transaction;

public class RemoveTransactionsCommand implements Command{

    private ObservableList<Transaction> transactionsToRemove;

    private Account account;
    public RemoveTransactionsCommand(ObservableList<Transaction> transactionsToRemove, Account account) {
        this.transactionsToRemove = FXCollections.observableArrayList(transactionsToRemove);
        this.account = account;
    }

    @Override
    public void execute() {
        for (Transaction transaction: transactionsToRemove) {
            account.removeTransaction(transaction);
        }
    }

    @Override
    public String getName() {
        return transactionsToRemove.size() + " transactions removed.";
    }

    @Override
    public void undo() {
        for (Transaction transaction: transactionsToRemove) {
            account.addTransaction(transaction);
        }
    }

    @Override
    public void redo() {
        execute();
    }
}
