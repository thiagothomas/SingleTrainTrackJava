import java.io.*;
import java.util.HashMap;

public class Teste {
	public static void main(String[] args) throws IOException {
		InputStreamReader ir = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(ir);
		Trilho t = new Trilho((int)(Math.random()*21+10));
		String line = "";
		int count = 0;
		int time = 571;

		while(!line.equals("pare") && !t.trilhoVazio()) {

			line = in.readLine();
			count++;
			time--;
			if(count % 30 == 0 && time > 0) {
				t.maisTrem();
				count = 0;
			}

			System.out.println(t);
			t.anda();
		}
		System.out.println(t);

		HashMap<Estacao, Integer> desceramAux = Trem.pessoasDesceramEstacao;
		HashMap<Estacao, Integer> subiramAux = Trem.pessoasSubiramEstacao;

		int[] desceram = new int[Trem.pessoasDesceramEstacao.size()+1];
		int[] subiram = new int[Trem.pessoasSubiramEstacao.size()+1];

		for(Estacao e : desceramAux.keySet()) {
			desceram[e.getIdentidade()] = desceramAux.get(e);
		}

		for(Estacao e : subiramAux.keySet()) {
			subiram[e.getIdentidade()] = subiramAux.get(e);
		}

		FileWriter fw = new FileWriter("estacoes.txt");
		BufferedWriter bw = new BufferedWriter(fw);

		bw.write("----- RELATÓRIO SUBIDA E DESCIDA PASSAGEIROS NAS ESTAÇÕES -----\n");
		for(int i=1; i< desceram.length; i++) {
			if(i > 1) bw.write("\n");
			bw.write("* ESTAÇÃO " + i+ ":\n");
			bw.write("- Desceram " + desceram[i] + " passageiros\n");
			bw.write("- Subiram " + subiram[i] + " passageiros\n");
		}
		bw.close();
	}
}
