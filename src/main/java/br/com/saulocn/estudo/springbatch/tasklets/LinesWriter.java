package br.com.saulocn.estudo.springbatch.tasklets;

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

public class LinesWriter implements Tasklet, StepExecutionListener {
    private List<Line> lines;
    private FileUtils fu;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        ExecutionContext executionContext = stepExecution
                .getJobExecution()
                .getExecutionContext();
        this.lines = (List<Line>) executionContext.get("lines");
        fu = new FileUtils("saida.csv");
        System.out.println("Inicializando escrita das linhas");
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution,
            ChunkContext chunkContext) throws Exception {
        for (Line line : lines) {
            fu.writeLine(line);
            System.out.println("Escrevendo a linha: "+line.toString());
        }
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        fu.closeWriter();
        System.out.println("Finalizando escrita das linhas");
        return ExitStatus.COMPLETED;
    }
}
