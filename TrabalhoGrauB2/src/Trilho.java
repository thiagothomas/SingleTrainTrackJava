public class Trilho {
	private PedacoTrilho head;
	private PedacoTrilho tail;

	public Trilho(int quantEstacoes){
		head = tail = null;
		insert(new PedacoTrilho());
		for(int i=0; i<quantEstacoes; i++) {
			for(int j=0; j<20; j++)
				insert(new PedacoTrilho());
			insert(new PedacoTrilho(null, new Estacao(), null, null));
		}
		for(int j=0; j<20; j++)
			insert(new PedacoTrilho());
		insert(new PedacoTrilho(null, null, null, null));

		maisTrem();
	}

	public boolean isEmpty() {
		return head == null;
	}

	public void insert(PedacoTrilho element) throws NullPointerException{
		if(element == null) throw new NullPointerException();

		if (isEmpty()) {
			head = tail = element;
		}
		else {
			tail.setNext(element);
			element.setPrevious(tail);
			tail = element;
		}
	}

	public String toString() {
		StringBuilder s = new StringBuilder("[PA]");
		int count = 0;
		PedacoTrilho current = head.getNext();
		while (current != tail) {
			count++;
			s.append(current.toString());
			current = current.getNext();
			if(count % 21 == 0) s.append("\n");
		}
		s.append("[PB]");
		return s.toString();
	}

	public void maisTrem() {
		Trem t1 = new Trem();
		Trem t2 = new Trem();

		head.getNext().setTrem(t1);
		tail.getPrevious().setTrem(t2);
	}

	public void anda() {
		PedacoTrilho curr = head;

		boolean tremEsquerdaAndou;

		while(curr != tail) {
			tremEsquerdaAndou = false;
			if(curr == null) break;
			if(curr.getEstacao() != null) {
				if(curr.getEstacao().getDesvioAntes().getTrem() != null) {
					if(curr.getEstacao().getDesvioAntes().getTrem().getIdentidade() % 2 != 0) {
						if(podeSairDesvioParaEstacao(curr, "ANTES")) {
							tremEsquerdaAndou = true;
							curr.desvioParaEstacaoTrensVindoEsquerda();
							curr.getTrem().sorteioQuantPessoasSobemDescem(curr.getEstacao());
						}
					} else {
						if(podeSairDesvioParaTrilho(curr, "ANTES")) curr.desvioParaTrilhoTrensVindoDireita();
					}
				}
				if(curr.getEstacao().getDesvioDepois().getTrem() != null) {
					if(curr.getEstacao().getDesvioDepois().getTrem().getIdentidade() % 2 != 0) {
						if(podeSairDesvioParaTrilho(curr, "DEPOIS")) {
							tremEsquerdaAndou = true;
							curr.desvioParaTrilhoTrensVindoEsquerda();
						}
					} else {
						if(podeSairDesvioParaEstacao(curr, "DEPOIS")) {
							curr.desvioParaEstacaoTrensVindoDireita();
							curr.getTrem().sorteioQuantPessoasSobemDescem(curr.getEstacao());
						}
					}
				}
				if(curr.getTrem() != null) {
					if(!curr.getTrem().temPessoas()) {
						if(curr.getTrem().getIdentidade() % 2 != 0) {
							if(podeSairEstacao(curr)) {
								curr.andaTrilhoTrensEsquerda();
								tremEsquerdaAndou = true;
							}
							else curr.estacaoParaDesvioTrensVindoEsquerda();
						} else {
							if(podeSairEstacao(curr)) curr.andaTrilhoTrensDireita();
							else curr.estacaoParaDesvioTrensVindoDireita();
						}
					} else {
						curr.getTrem().descemSobemPessoas();
					}
				}
			}
			else if(curr.getTrem() != null) {
				if(curr.getTrem().getIdentidade() % 2 != 0) {
					if(curr.getNext() == null) {
						curr.chegaPonto();
						break;
					}
					if(curr.getNext().getEstacao() != null) {
						if(podeEntrarTrilhoParaEstacao(curr)) {
							curr.andaTrilhoTrensEsquerda();
							curr = curr.getNext();
							curr.getTrem().sorteioQuantPessoasSobemDescem(curr.getEstacao());
							tremEsquerdaAndou = false;
							if(curr.getEstacao().getDesvioAntes().getTrem() != null) {
								if(curr.getEstacao().getDesvioAntes().getTrem().getIdentidade() % 2 != 0) {
									if(podeSairDesvioParaEstacao(curr, "ANTES")) {
										tremEsquerdaAndou = true;
										curr.desvioParaEstacaoTrensVindoEsquerda();
									}
								} else {
									if(podeSairDesvioParaTrilho(curr, "ANTES")) curr.desvioParaTrilhoTrensVindoDireita();
								}
							}
							if(curr.getEstacao().getDesvioDepois().getTrem() != null) {
								if(curr.getEstacao().getDesvioDepois().getTrem().getIdentidade() % 2 != 0) {
									if(podeSairDesvioParaTrilho(curr, "DEPOIS")) {
										tremEsquerdaAndou = true;
										curr.desvioParaTrilhoTrensVindoEsquerda();
									}
								}
							}
						} else {
							Trem aux = null;
							if(curr.getNext().getEstacao().getDesvioAntes().getTrem() != null) aux = curr.getNext().getEstacao().getDesvioAntes().getTrem();
							curr.trilhoParaDesvioTrensEsquerda();
							curr.setTrem(aux);
							curr = curr.getNext();
							if(curr.getEstacao().getDesvioDepois().getTrem() != null) {
								if(curr.getEstacao().getDesvioDepois().getTrem().getIdentidade() % 2 != 0) {
									if(podeSairDesvioParaTrilho(curr, "DEPOIS")) {
										tremEsquerdaAndou = true;
										curr.desvioParaTrilhoTrensVindoEsquerda();
									}
								} else {
									if(podeSairDesvioParaEstacao(curr, "DEPOIS")) curr.desvioParaEstacaoTrensVindoDireita();
								}
							}
							if(curr.getTrem() != null) {
								if(curr.getTrem().getIdentidade() % 2 != 0) {
									if(podeSairEstacao(curr)) {
										curr.andaTrilhoTrensEsquerda();
										tremEsquerdaAndou = true;
									}
									else curr.estacaoParaDesvioTrensVindoEsquerda();
								} else {
									if(podeSairEstacao(curr) && !curr.getTrem().temPessoas()) curr.andaTrilhoTrensDireita();
									else if(!curr.getTrem().temPessoas()) curr.estacaoParaDesvioTrensVindoDireita();
								}
							}
						}
					} else {
						curr.andaTrilhoTrensEsquerda();
						tremEsquerdaAndou = true;
					}
				} else {
					if(curr.getPrevious() == null) {
						curr.chegaPonto();
						break;
					}
					if(curr.getPrevious().getEstacao() != null) {
						if(podeEntrarTrilhoParaEstacao(curr)) {
							curr.andaTrilhoTrensDireita();
							curr.getPrevious().getTrem().sorteioQuantPessoasSobemDescem(curr.getPrevious().getEstacao());
						} else {
							curr.trilhoParaDesvioTrensDireita();
						}
					} else {
						curr.andaTrilhoTrensDireita();
					}
				}
			}
			head.setTrem(null);
			tail.setTrem(null);
			if(tremEsquerdaAndou && curr.getNext() != tail) curr = curr.getNext().getNext();
			else curr = curr.getNext();
			if(curr == null) break;
		}
	}

	public boolean podeSairEstacao(PedacoTrilho localTrem) {
		PedacoTrilho current;
		if(localTrem.getTrem().getIdentidade() % 2 != 0) {
			current = localTrem.getNext();
			while(current.getEstacao() == null) {
				if(current.getTrem() != null && current.getTrem().getIdentidade() % 2 == 0) return false;
				current = current.getNext();
				if(current == null) break;
			}
		}
		else {
			current = localTrem.getPrevious();
			while(current.getEstacao() == null) {
				if(current.getTrem() != null && current.getTrem().getIdentidade() % 2 != 0) return false;
				current = current.getPrevious();
				if(current == null) break;
			}
		}
		return true;
	}

	public boolean podeSairDesvioParaEstacao(PedacoTrilho localTrem, String antesOuDepois) {
		if(antesOuDepois.equals("ANTES")) {
			return localTrem.getTrem() == null || localTrem.getTrem().getIdentidade() % 2 != 0;
		}
		else {
			return localTrem.getTrem() == null || localTrem.getTrem().getIdentidade() % 2 == 0;
		}
	}

	public boolean podeSairDesvioParaTrilho(PedacoTrilho localTrem, String antesOuDepois) {
		PedacoTrilho current;
		if(antesOuDepois.equals("DEPOIS")) {
			current = localTrem.getNext();
			while(current.getEstacao() == null) {
				if(current.getTrem() != null && current.getTrem().getIdentidade() % 2 == 0) return false;
				current = current.getNext();
				if(current == null) break;
			}
		} else {
			current = localTrem.getPrevious();
			while(current.getEstacao() == null) {
				if(current.getTrem() != null && current.getTrem().getIdentidade() % 2 != 0) return false;
				current = current.getPrevious();
				if(current == null) break;
			}
		}
		return true;
	}

	public boolean podeEntrarTrilhoParaEstacao(PedacoTrilho localTrem) {
		Trem t = localTrem.getTrem();
		if(t.getIdentidade() % 2 != 0) {
			if(localTrem.getNext().getTrem() == null) return true;
			else return localTrem.getNext().getTrem().getIdentidade() % 2 != 0;
		}
		else {
			if(localTrem.getPrevious().getTrem() == null) return true;
			else return localTrem.getPrevious().getTrem().getIdentidade() % 2 == 0;
		}
	}

	public boolean trilhoVazio() {
		PedacoTrilho current = head;
		head.setTrem(null);
		tail.setTrem(null);
		while(current != null) {
			if(current.getTrem() != null) return false;
			current = current.getNext();
		}
		return true;
	}
}
