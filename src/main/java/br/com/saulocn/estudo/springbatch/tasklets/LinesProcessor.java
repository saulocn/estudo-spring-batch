package br.com.saulocn.estudo.springbatch.tasklets;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import br.com.saulocn.estudo.springbatch.tasklets.model.Line;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

public class LinesProcessor implements Tasklet, StepExecutionListener {


    private List<Line> lines;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        ExecutionContext executionContext = stepExecution
                .getJobExecution()
                .getExecutionContext();
        this.lines = (List<Line>) executionContext.get("lines");
        System.out.println("Inicializando processamento de linhas");
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution,
            ChunkContext chunkContext) throws Exception {
        for (Line line : lines) {
            long age = ChronoUnit.YEARS.between(
                    line.getBirthDay(),
                    LocalDate.now());
            System.out.println("Calculado a idade de "+age +" para: " +line.toString());
            line.setAge(age);
        }
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("Finalizando processamento de linhas");
        return ExitStatus.COMPLETED;
    }
}