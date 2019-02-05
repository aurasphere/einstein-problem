package com.beije.einstein;

import com.beije.einstein.ColoreCasa;

public class PadroneCasa {

	private int ordineCasa;

	private ColoreCasa colore;

	private Animale animale;

	private Bevanda bevanda;

	private Sigarette sigarette;

	private Nazionalita nazionalita;

	public PadroneCasa(int ordine, int colore, int animale, int bevanda, int sigarette, int nazionalita) {
		this.ordineCasa = ordine;
		this.colore = ColoreCasa.values()[colore];
		this.animale = Animale.values()[animale];
		this.bevanda = Bevanda.values()[bevanda];
		this.sigarette = Sigarette.values()[sigarette];
		this.nazionalita = Nazionalita.values()[nazionalita];
	}

	public ColoreCasa getColore() {
		return colore;
	}

	public void setColore(ColoreCasa colore) {
		this.colore = colore;
	}

	public Animale getAnimale() {
		return animale;
	}

	public void setAnimale(Animale animale) {
		this.animale = animale;
	}

	public Bevanda getBevanda() {
		return bevanda;
	}

	public void setBevanda(Bevanda bevanda) {
		this.bevanda = bevanda;
	}

	public Sigarette getSigarette() {
		return sigarette;
	}

	public void setSigarette(Sigarette sigarette) {
		this.sigarette = sigarette;
	}

	public Nazionalita getNazionalita() {
		return nazionalita;
	}

	public void setNazionalita(Nazionalita nazionalita) {
		this.nazionalita = nazionalita;
	}

	public int getOrdineCasa() {
		return ordineCasa;
	}

	public void setOrdineCasa(int ordineCasa) {
		this.ordineCasa = ordineCasa;
	}

	@Override
	public String toString() {
		return "PadroneCasa [ordineCasa=" + ordineCasa + ", colore=" + colore + ", animale=" + animale + ", bevanda="
				+ bevanda + ", sigarette=" + sigarette + ", nazionalita=" + nazionalita + "]";
	}

}