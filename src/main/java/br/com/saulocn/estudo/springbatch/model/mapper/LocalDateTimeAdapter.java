package br.com.saulocn.estudo.springbatch.model.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public LocalDateTime unmarshal(String data) throws Exception {
        return LocalDateTime.parse(data, DATE_TIME_FORMATTER);
    }

    public String marshal(LocalDateTime data) throws Exception {
        return DATE_TIME_FORMATTER.format(data);
    }
}
