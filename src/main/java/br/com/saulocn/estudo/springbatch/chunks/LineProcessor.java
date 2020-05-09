package br.com.saulocn.estudo.springbatch.chunks;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import br.com.saulocn.estudo.springbatch.model.Line;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;

public class LineProcessor implements
        ItemProcessor<Line, Line>, StepExecutionListener {


    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("Inicializando processamento das linhas");
    }

    @Override
    public Line process(Line line) throws Exception {
        long age = ChronoUnit.YEARS
                .between(line.getBirthDay(), LocalDate.now());
        System.out.println("Calculado a idade de "+age +" para: " +line.toString());
        line.setAge(age);
        return line;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("Finalizando processamento das linhas");
        return ExitStatus.COMPLETED;
    }
}