package br.com.registros.Planilha;

import jxl.Workbook;
import jxl.Sheet;
import jxl.Cell;
import jxl.read.biff.BiffException;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Planilha {
	private Workbook pasta;
	private Sheet planilha;
	private Cell nome;
	private Cell Descricao;
	private Cell Entrega;
	private Cell Hora;
	private Cell Status;
	
	private Object objetos[][] = new Object[5][5];
	
	
	public void conecta(File a) throws IOException, BiffException{
		try {
		pasta = Workbook.getWorkbook(a);
		planilha = pasta.getSheet(0);
		}catch(IOException e) {
			JOptionPane.showMessageDialog(null, "Arquivo não encontrado!");
		}
	}		
		
	public Object[][] localiza(String a){
	
		int i = planilha.getRows()-1;
		int j = 0;
		
		for(int k=0;k<5;k++) {
			for(int l=0;l<5;l++) {
				objetos[k][l] = "";
			}
		}

		while(i >= 0) {
			
			nome = planilha.getCell(0, i);
			
			if(nome.getContents().contains(a)) {
				
				Descricao = planilha.getCell(nome.getColumn()+1, nome.getRow());
				Entrega = planilha.getCell(nome.getColumn()+2, nome.getRow());
				Hora = planilha.getCell(nome.getColumn()+3, nome.getRow());
				Status = planilha.getCell(nome.getColumn()+4, nome.getRow());				
				
				if(j<5) {
				objetos[j][0] = nome.getContents();
				objetos[j][1] = Descricao.getContents();
				objetos[j][2] = Entrega.getContents();
				objetos[j][3] = Hora.getContents();
				objetos[j][4] = Status.getContents();
				j++;
				
				}else{
					JOptionPane.showMessageDialog(null, "Resultados Omitidos");
					break;
				}
			}
			i--;
		}
		return objetos;

	}
}