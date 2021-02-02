package com.sincronizacao.receita.SincronizacaoReceita;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.sincronizacao.receita.SincronizacaoReceita.Services.ReceitaService;

@SpringBootApplication
public class SincronizacaoReceitaApplication {

	/**
	 * 
	 * @param args
	 * @throws CsvDataTypeMismatchException
	 * @throws CsvRequiredFieldEmptyException
	 * @throws IOException
	 * 
	 * Passar o caminho do arquivo csv pelos parametros (args), exemplo: C:\pasta\contas.csv
	 */
	public static void main(String[] args) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		SpringApplication.run(SincronizacaoReceitaApplication.class, args);
		ReceitaService receitaService = new ReceitaService();
		System.out.println("----------------------------------------------------");
		System.out.println("Iniciando processamento");
		
		receitaService.atualizarConta(args[0]);   
	}

}
