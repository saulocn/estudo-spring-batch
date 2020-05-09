package br.com.saulocn.estudo.springbatch.model.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.saulocn.estudo.springbatch.model.Transaction;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class RecordFieldSetMapper  implements FieldSetMapper<Transaction> {
    public Transaction mapFieldSet(FieldSet fieldSet) throws BindException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        Transaction transaction = new Transaction();

        transaction.setUsername(fieldSet.readString("username"));
        transaction.setUserId(fieldSet.readInt(1));
        transaction.setAmount(fieldSet.readDouble(3));
        String dateString = fieldSet.readString(2);
        System.out.println(dateString);
        final LocalDateTime data = LocalDate.parse(dateString, formatter).atStartOfDay();
        transaction.setTransactionDate(data);
        System.out.println(transaction);
        return transaction;
    }
}
