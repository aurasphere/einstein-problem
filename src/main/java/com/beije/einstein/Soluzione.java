package com.beije.einstein;

public class Soluzione {
	
	private PadroneCasa[] padroni = new PadroneCasa[5];
	
	public Soluzione(PadroneCasa[] padroni) {
		this.padroni = padroni;
	}

	public PadroneCasa[] getPadroni() {
		return padroni;
	}

	public void setPadroni(PadroneCasa[] padroni) {
		this.padroni = padroni;
	}
	
}
