package com.sincronizacao.receita.SincronizacaoReceita.Services;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.sincronizacao.receita.SincronizacaoReceita.models.CsvFile;

@Component
public class ReceitaService {

	private List<CsvFile> readerFile(String pathFile)
			throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		Reader reader = Files.newBufferedReader(Paths.get(pathFile));
		CSVReader csvReader = new CSVReader(reader);
		List<String[]> records = csvReader.readAll();
		List<CsvFile> listReturn = new ArrayList<>();
		for (int i = 1; i < records.size(); i++) {
			String[] record = records.get(i).length > 1 ? records.get(i) : records.get(i)[0].split(";");;
			records.get(i)[0].split(";");
			listReturn.add(new CsvFile(record[0], record[1], record[2], record[3], "Processado"));
		}
		return listReturn;
	}

	public void atualizarConta(String pathFile) {
		try {

			List<CsvFile> listLinhasCsv = readerFile(pathFile);

			String[] cabecalho = { "agencia", "conta", "saldo", "status", "resultado" };
			List<String[]> linhas = new ArrayList<>();

			for (CsvFile obj : listLinhasCsv) {
				linhas.add(new String[] { obj.getAgencia(), obj.getConta(), obj.getSaldo(), obj.getStatus(),
						"Processado" });
			}

			String pathFileResult = pathFile.substring(0, pathFile.length() - 4) + "Resultado.csv";

			Writer writer = Files.newBufferedWriter(Paths.get(pathFileResult));
			CSVWriter csvWriter = new CSVWriter(writer);

			csvWriter.writeNext(cabecalho);
			csvWriter.writeAll(linhas);

			csvWriter.flush();
			writer.close();
			System.out.println("Processamento finalizado, arquivo criado em: " + pathFileResult);

		} catch (Exception e) {
			System.out.println("Problema no processamento do arquivo");
		}finally {
			System.out.println("----------------------------------------------------");
		};

	}

}
