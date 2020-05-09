package br.com.saulocn.estudo.springbatch.config;

import br.com.saulocn.estudo.springbatch.model.Transaction;
import org.springframework.batch.item.ItemProcessor;

public class CustomItemProcessor implements ItemProcessor<Transaction, Transaction> {

    public Transaction process(Transaction item) {
        return item;
    }
}