package br.com.saulocn.estudo.springbatch.model;

import java.time.LocalDateTime;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.saulocn.estudo.springbatch.model.mapper.LocalDateTimeAdapter;

@SuppressWarnings("restriction")
@XmlRootElement(name = "transactionRecord")
public class Transaction {
    private String username;
    private int userId;
    private LocalDateTime transactionDate;
    private double amount;

    public boolean isNegativeTransaction() {
        return isNegativeTransaction;
    }

    public void setNegativeTransaction(final boolean negativeTransaction) {
        isNegativeTransaction = negativeTransaction;
    }

    private boolean isNegativeTransaction;

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(final int userId) {
        this.userId = userId;
    }

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(final LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(final double amount) {
        this.amount = amount;
    }

    @Override public String toString() {
        final StringBuilder builder = new StringBuilder()//
                .append("Transaction [")//
                .append("username=\"")//
                .append(username + "\"")//
                .append(",userId=")//
                .append(userId)//
                .append(",transactionDate=")//
                .append(transactionDate)//
                .append(",amount=")//
                .append(amount)//
                .append("]");
        return builder.toString();
    }
}