package br.com.saulocn.estudo.springbatch.tasklets;

import java.util.ArrayList;
import java.util.List;

import br.com.saulocn.estudo.springbatch.model.Line;
import br.com.saulocn.estudo.springbatch.util.FileUtils;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class LinesReader implements Tasklet, StepExecutionListener {
    private List<Line> lines;
    private FileUtils fu;
    String filename = "input_bdays.csv";

    @Override
    public void beforeStep(StepExecution stepExecution) {
        lines = new ArrayList<>();
        fu = new FileUtils(filename);
        System.out.println("Inicializando leitura dos dados");
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution,
            ChunkContext chunkContext) throws Exception {
        Line line = fu.readLine();
        while (line != null) {
            lines.add(line);
            System.out.println("Leitura da linha: "+ line.toString());
            line = fu.readLine();
        }
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        fu.closeReader();
        stepExecution
                .getJobExecution()
                .getExecutionContext()
                .put("lines", this.lines);
        System.out.println("Finalizando leitura dos dados");
        return ExitStatus.COMPLETED;
    }
}
