package util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.CellValue;

public class ExelUtil {

	/**
	 * M�todo usado para abrir um arquivo .XLS, transformar seu conte�do, c�lula
	 * por c�lula, em String e pass�-lo para um ArrayList
	 * 
	 * @param String com o caminho e o nome do arquivo .XLS que ser� convertido
	 * @return ArrayList<ArrayList<String>> com o conte�do do arquivo .XLS
	 * @throws BiffException
	 * @throws IOException
	 */
	public ArrayList<ArrayList<String>> xlsParaArrayList(String nomeArquivo)
			throws BiffException, IOException {
		Workbook workbook = Workbook.getWorkbook(new File(nomeArquivo));
		Sheet sheet = workbook.getSheet(0);

		int linhas = sheet.getRows();
		int colunas = sheet.getColumns();

		ArrayList<ArrayList<String>> linha = new ArrayList<ArrayList<String>>();

		for (int i = 0; i < linhas; i++) {
			ArrayList<String> coluna = new ArrayList<String>();
			for (int j = 0; j < colunas; j++) {
				coluna.add(sheet.getCell(j, i).getContents());
			}
			linha.add(coluna);
		}

		workbook.close();

		return linha;
	}

	/**
	 * M�todo usado para abrir um arquivo .XLS, transformar seu conte�do, c�lula
	 * por c�lula, em String e pass�-lo para um ArrayList
	 * 
	 * @param String com o caminho e o nome do arquivo .XLS que ser� criado
	 * @param ArrayList <ArrayList<String>> com o conte�do que o arquivo .XLS contera
	 * @throws BiffException
	 * @throws WriteException
	 */
	public void arrayListParaXls(String nomeArquivo,
			ArrayList<ArrayList<String>> tabela) throws IOException,
			WriteException {

		WritableWorkbook workbook = Workbook.createWorkbook(new File(
				nomeArquivo + ".xls"));

		WritableSheet sheet = workbook.createSheet("1", 0);

		for (int i = 0; i < tabela.size(); i++) {
			for (int j = 0; j < tabela.get(i).size(); j++) {
				Label label = new Label(j, i, tabela.get(i).get(j));
				sheet.addCell(label);
			}
		}

		workbook.write();
		workbook.close();
	}

}
