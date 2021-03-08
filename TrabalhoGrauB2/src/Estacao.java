public class Estacao {
	private final int identidade;
	private static int id = 0;
	private final Desvio desvioAntes;
	private final Desvio desvioDepois;

	public Estacao() {
		this.identidade = ++id;
		this.desvioAntes = new Desvio();
		this.desvioDepois = new Desvio();
	}

	public int getIdentidade() {
		return identidade;
	}

	public Desvio getDesvioAntes() {
		return desvioAntes;
	}

	public Desvio getDesvioDepois() {
		return desvioDepois;
	}

	public String toString() {
		return "{" + desvioAntes + "}[E" + identidade + "]{" + desvioDepois + "}";
	}
}
