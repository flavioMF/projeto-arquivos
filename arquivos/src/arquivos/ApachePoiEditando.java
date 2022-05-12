package arquivos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ApachePoiEditando {
	
	public static void main(String[] args) throws Exception {
		
		File file = new File("C:\\workspace-curso-java\\arquivos\\src\\arquivos\\arquivo.excel.xls");
		
		FileInputStream entrada = new FileInputStream(file);
		
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(entrada);/*Prepara a entrada do arquivo Xls do excel*/
		HSSFSheet planilha = hssfWorkbook.getSheetAt(0); /*Pegando a planilha*/
		
		Iterator<Row> linhaIterator = planilha.iterator();
		
		while(linhaIterator.hasNext()) {/*Enaquanto tiver linha*/
			Row linha = linhaIterator.next();/*Dados da pessoa na linha*/
			
		int numerosCelulas = linha.getPhysicalNumberOfCells();/*Quantidade de celula*/
		
		Cell cell = linha.createCell(numerosCelulas);/*Cria nova celula na linha*/
		cell.setCellValue("5.487,20");
	  }
		
		entrada.close();
		
		FileOutputStream saida = new FileOutputStream(file);
		hssfWorkbook.write(saida);
		saida.flush();
		saida.close();
		
		
		System.out.println("Planilha atualizada");
				
		
	
		
	}

}