package br.com.saulocn.estudo.springbatch.tasklets;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.saulocn.estudo.springbatch.tasklets.model.Line;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class FileUtils {
    private String fileName;
    private CSVReader csvReader;
    private CSVWriter csvWriter;
    private FileReader fileReader;
    private FileWriter fileWriter;
    private File file;

    public FileUtils(String fileName) {
        this.fileName = fileName;
    }

    public Line readLine() throws Exception {
        if (csvReader == null)
            initReader();
        String[] line = csvReader.readNext();
        if (line == null)
            return null;
        return new Line(
                line[0],
                LocalDate.parse(
                        line[1],
                        DateTimeFormatter.ofPattern("MM/dd/yyyy")));
    }

    public void writeLine(Line line) throws Exception {
        if (csvWriter == null)
            initWriter();
        String[] lineStr = new String[2];
        lineStr[0] = line.getName();
        lineStr[1] = line
                .getAge()
                .toString();
        csvWriter.writeNext(lineStr);
    }


    private void initReader() throws Exception {
        ClassLoader classLoader = this
                .getClass()
                .getClassLoader();
        if (file == null) file = new File(classLoader
                .getResource(fileName)
                .getFile());
        if (fileReader == null) fileReader = new FileReader(file);
        if (csvReader == null) csvReader = new CSVReader(fileReader);
    }



    private void initWriter() throws Exception {
        if (file == null) {
            file = new File(fileName);
            file.createNewFile();
        }
        if (fileWriter == null) fileWriter = new FileWriter(file, true);
        if (csvWriter == null) csvWriter = new CSVWriter(fileWriter);
    }

    public void closeWriter() {
        try {
            csvWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Erro ao fechar o writer CSV");
            e.printStackTrace();
        }
    }

    public void closeReader() {
        try {
            csvReader.close();
            fileReader.close();
        } catch (IOException e) {
            System.out.println("Erro ao fechar o leitor CSV");
            e.printStackTrace();
        }
    }
}
