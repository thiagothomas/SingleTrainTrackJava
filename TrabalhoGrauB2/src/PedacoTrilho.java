public class PedacoTrilho {
	private Trem trem;
	private Estacao estacao;
	private PedacoTrilho next;
	private PedacoTrilho previous;

	public PedacoTrilho() {
		trem = null;
		estacao = null;
		next = null;
		previous = null;
	}

	public PedacoTrilho(Trem trem, Estacao estacao, PedacoTrilho next, PedacoTrilho previous) {
		this.trem = trem;
		this.estacao = estacao;
		this.next = next;
		this.previous = previous;
	}

	public Trem getTrem() {
		return trem;
	}

	public void setTrem(Trem trem) {
		this.trem = trem;
	}

	public Estacao getEstacao() {
		return estacao;
	}

	public PedacoTrilho getNext() {
		return next;
	}

	public void setNext(PedacoTrilho next) {
		this.next = next;
	}

	public PedacoTrilho getPrevious() {
		return previous;
	}

	public void setPrevious(PedacoTrilho previous) {
		this.previous = previous;
	}

	public void trilhoParaDesvioTrensEsquerda() {
		next.getEstacao().getDesvioAntes().setTrem(trem);
		trem = null;
	}

	public void estacaoParaDesvioTrensVindoEsquerda() {
		estacao.getDesvioDepois().setTrem(trem);
		trem = null;
	}

	public void trilhoParaDesvioTrensDireita() {
		previous.getEstacao().getDesvioDepois().setTrem(trem);
		trem = null;
	}

	public void estacaoParaDesvioTrensVindoDireita() {
		estacao.getDesvioAntes().setTrem(trem);
		trem = null;
	}

	public void desvioParaTrilhoTrensVindoEsquerda() {
		next.setTrem(estacao.getDesvioDepois().getTrem());
		estacao.getDesvioDepois().setTrem(null);
	}

	public void desvioParaTrilhoTrensVindoDireita() {
		previous.setTrem(estacao.getDesvioAntes().getTrem());
		estacao.getDesvioAntes().setTrem(null);
	}

	public void desvioParaEstacaoTrensVindoEsquerda() {
		trem = estacao.getDesvioAntes().getTrem();
		estacao.getDesvioAntes().setTrem(null);
	}

	public void desvioParaEstacaoTrensVindoDireita() {
		trem = estacao.getDesvioDepois().getTrem();
		estacao.getDesvioDepois().setTrem(null);
	}

	public void andaTrilhoTrensEsquerda() {
		next.setTrem(trem);
		trem = null;
	}

	public void andaTrilhoTrensDireita() {
		previous.setTrem(trem);
		trem = null;
	}

	public void chegaPonto() {
		trem = null;
	}

	public String toString() {
		String s = "[";
		if(trem != null && estacao != null) {
			s += estacao;
		}
		else if(trem != null)
			s += trem;
		else if(estacao != null)
			s += estacao;
		else
			s += " ";
		return s+"]";
	}
}