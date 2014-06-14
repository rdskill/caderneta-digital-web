package util;

import java.util.ArrayList;

import bean.Nota;

public class Util {
	
	public static ArrayList<Nota> OrganizarNotas(ArrayList<Nota> ln) {
		ArrayList<Nota> novaOrdemNotas = new ArrayList<Nota>();
		ArrayList<Integer> posicaoNotaExame = new ArrayList<Integer>();
		
		for (int i = 0; i < ln.size(); i++) {
			if (ln.get(i).getIdTipoNota().getTipoNota().equals("Exame")) {
				posicaoNotaExame.add(i);
			} else {
				novaOrdemNotas.add(ln.get(i));
			}
		}
		
		for (Integer i : posicaoNotaExame) {
			novaOrdemNotas.add(ln.get(i));
		}
		
		return novaOrdemNotas;
	}

}
