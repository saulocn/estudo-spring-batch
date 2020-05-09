package br.com.saulocn.estudo.springbatch.chunks;

import br.com.saulocn.estudo.springbatch.model.Line;
import br.com.saulocn.estudo.springbatch.util.FileUtils;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;

public class LineReader implements ItemReader<Line>, StepExecutionListener {
    private FileUtils fu;
    String filename = "input_bdays.csv";

    @Override
    public Line read() throws Exception {
        Line line = fu.readLine();
        if (line != null) {
            System.out.println("Leitura da linha: " + line.toString());
        }
        return line;
    }

    @Override public void beforeStep(StepExecution stepExecution) {
        fu = new FileUtils(filename);
        System.out.println("Inicilizando leitura das linhas");
    }

    @Override public ExitStatus afterStep(StepExecution stepExecution) {
        fu.closeReader();
        System.out.println("Finalizando leitura das linhas");
        return ExitStatus.COMPLETED;
    }
}