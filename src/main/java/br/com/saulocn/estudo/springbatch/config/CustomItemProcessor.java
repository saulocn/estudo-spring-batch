package br.com.saulocn.estudo.springbatch.config;

import br.com.saulocn.estudo.springbatch.model.Transaction;
import org.springframework.batch.item.ItemProcessor;

public class CustomItemProcessor implements ItemProcessor<Transaction, Transaction> {

    public Transaction process(Transaction item) {
        System.out.println("Processando...");
        if(item.getAmount()<0){
            item.setNegativeTransaction(true);
        }
        return item;
    }
}