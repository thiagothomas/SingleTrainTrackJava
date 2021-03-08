import java.util.HashMap;

public class Trem {
	private static int id = 0;
	private final int identidade;
	private int numPessoasNoTrem;
	private int quantPessoasSobem;
	private int quantPessoasDescem;
	private int tempo;
	public static HashMap<Estacao, Integer> pessoasDesceramEstacao = new HashMap<>();
	public static HashMap<Estacao, Integer> pessoasSubiramEstacao = new HashMap<>();

	public Trem() {
		this.identidade = ++id;
		this.numPessoasNoTrem = (int)(Math.random() * 40 + 10);
	}

	public int getIdentidade() {
		return this.identidade;
	}

	public void sorteioQuantPessoasSobemDescem(Estacao e) {
		int sobem = (int)(Math.random() * 11);
		int descem = (int)(Math.random() * 11);

		while(true) {
			if(((sobem + descem) % 2 != 0) && (descem <= numPessoasNoTrem)) {
				sobem = (int)(Math.random() * 11);
				descem = (int)(Math.random() * 11);
			} else {
				break;
			}
		}

		this.quantPessoasDescem = descem;
		this.quantPessoasSobem = sobem;
		this.numPessoasNoTrem = this.numPessoasNoTrem - descem + sobem;

		tempo = (sobem + descem)/2;
		if(pessoasDesceramEstacao.containsKey(e)) pessoasDesceramEstacao.put(e, pessoasDesceramEstacao.get(e) + descem);
		else pessoasDesceramEstacao.put(e, descem);

		if(pessoasSubiramEstacao.containsKey(e)) pessoasSubiramEstacao.put(e, pessoasSubiramEstacao.get(e) + sobem);
		else pessoasSubiramEstacao.put(e, sobem);
	}

	public boolean temPessoas() {
		if(tempo == -1) return false;

		return quantPessoasDescem != 0 || quantPessoasSobem != 0;
	}

	public void descemSobemPessoas() {
		if(tempo == 0)
			tempo = -1;
		if(quantPessoasDescem > 0) {
			if(quantPessoasDescem == 1) {
				quantPessoasDescem--;
				quantPessoasSobem--;
			}
			else quantPessoasDescem -= 2;
		}
		else if(quantPessoasSobem > 0) {
			if(quantPessoasSobem == 1) quantPessoasSobem--;
			else quantPessoasSobem -= 2;
		}
	}

	public String toString() {
		if(identidade % 2 != 0)
			return "\u001B[31m" + "T"+identidade+"\u001B[0m";
		else return "\u001B[36m" + "T"+identidade+"\u001B[0m";
	}
}
