public class Desvio {
	private Trem trem;

	public Desvio() {
		this.trem = null;
	}

	public Trem getTrem() {
		return trem;
	}

	public void setTrem(Trem trem) {
		this.trem = trem;
	}

	public String toString() {
		if(trem != null)
			return trem + "";
		else
			return "D";
	}
}
