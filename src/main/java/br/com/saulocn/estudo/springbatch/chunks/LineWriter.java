package br.com.saulocn.estudo.springbatch.chunks;

import java.util.List;

import br.com.saulocn.estudo.springbatch.model.Line;
import br.com.saulocn.estudo.springbatch.util.FileUtils;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;

public class LineWriter  implements
        ItemWriter<Line>, StepExecutionListener {


    private String filename= "saida_chunk.csv";
    private FileUtils fu;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        fu = new FileUtils(filename);
        System.out.println("Inicializando escrita das linhas... ");
    }

    @Override
    public void write(List<? extends Line> lines) throws Exception {
        for (Line line : lines) {
            fu.writeLine(line);
            System.out.println("Escrevendo linha: "+line.toString());
        }
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        fu.closeWriter();
        System.out.println("Finalizando escrita das linhas... ");
        return ExitStatus.COMPLETED;
    }
}