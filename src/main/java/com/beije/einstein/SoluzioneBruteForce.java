package com.beije.einstein;

public class SoluzioneBruteForce {

	public static void main(String[] args) {
		PadroneCasa[] soluzione = new PadroneCasa[5];
		for (int i = 0; i < 5; i++) {
			soluzione[i] = new PadroneCasa();
		}

		while (true) {
			PadroneCasa prev = null;
			PadroneCasa succ = null;
			for (int i = 0; i < 5; i++) {
				if (i > 0) {
					prev = soluzione[i - 1];
				}
				if (i < 4) {
					succ = soluzione[i + 1];
				}
				PadroneCasa corrente = soluzione[i];

				// Soluzione trovata.
				if (corrente.getAnimale() == Animale.PESCE) {
					System.out.println(corrente);
					return;
				}

				// Vincoli di problema:
				// 1.
				vincola(corrente, ColoreCasa.ROSSO, Nazionalita.INGLESE);

				// 2.
				vincola(corrente, Animale.CANE, Nazionalita.SVEDESE);

				// 3.
				vincola(corrente, Bevanda.TE, Nazionalita.DANESE);

				// 4.
				vincolaPosizioneSuccessiva(corrente, succ, ColoreCasa.VERDE, ColoreCasa.BIANCO);

				// 5.
				vincola(corrente, Bevanda.CAFFE, ColoreCasa.VERDE);

				// 6.
				vincola(corrente, Sigarette.PALL_MALL, Animale.UCCELLO);

				// 7.
				vincola(corrente, Sigarette.DUNHILL, ColoreCasa.GIALLO);

				// 8.
				if (corrente.getOrdineCasa() == 3) {
					corrente.setBevanda(Bevanda.LATTE);
				}

				// 9.
				if (corrente.getOrdineCasa() == 1) {
					corrente.setNazionalita(Nazionalita.NORVEGESE);
				}

				// 10.
				vincolaPosizioneVicina(prev, corrente, succ, Sigarette.BLENDS, Animale.GATTO);

				// 11.
				vincolaPosizioneVicina(prev, corrente, succ, Sigarette.DUNHILL, Animale.CAVALLO);

				// 12.
				vincola(corrente, Sigarette.BLUE_MASTER, Bevanda.BIRRA);

				// 13.
				vincola(corrente, Sigarette.PRINCE, Nazionalita.TEDESCO);

				// 14.
				vincolaPosizioneVicina(prev, corrente, succ, Nazionalita.NORVEGESE, ColoreCasa.BLU);

				// 15.
				vincolaPosizioneVicina(prev, corrente, succ, Sigarette.BLENDS, Bevanda.ACQUA);

				System.out.println(corrente);
			}

		}

	}

	private static void vincolaPosizioneVicina(PadroneCasa prev, PadroneCasa corrente, PadroneCasa succ,
			Nazionalita primo, ColoreCasa secondo) {
		if (prev == null) {
			// Precedente non esiste, prima casa.
			vincolaPosizioneSuccessiva(corrente, succ, primo, secondo);
		} else if (succ == null) {
			// Successivo non esiste, ultima casa.
			vincolaPosizioneSuccessiva(prev, corrente, primo, secondo);
		} else {
			// Casa in mezzo.
			vincolaPosizioneSuccessiva(prev, corrente, primo, secondo);
			vincolaPosizioneSuccessiva(corrente, succ, primo, secondo);
		}
	}
	
	private static void vincolaPosizioneVicina(PadroneCasa prev, PadroneCasa corrente, PadroneCasa succ,
			Sigarette primo, Bevanda secondo) {
		if (prev == null) {
			// Precedente non esiste, prima casa.
			vincolaPosizioneSuccessiva(corrente, succ, primo, secondo);
		} else if (succ == null) {
			// Successivo non esiste, ultima casa.
			vincolaPosizioneSuccessiva(prev, corrente, primo, secondo);
		} else {
			// Casa in mezzo.
			vincolaPosizioneSuccessiva(prev, corrente, primo, secondo);
			vincolaPosizioneSuccessiva(corrente, succ, primo, secondo);
		}
	}
	
	private static void vincolaPosizioneVicina(PadroneCasa prev, PadroneCasa corrente, PadroneCasa succ,
			Sigarette primo, Animale secondo) {
		if (prev == null) {
			// Precedente non esiste, prima casa.
			vincolaPosizioneSuccessivaSicura(corrente, succ, primo, secondo);
		} else if (succ == null) {
			// Successivo non esiste, ultima casa.
			vincolaPosizioneSuccessivaSicura(prev, corrente, primo, secondo);
		} else {
			// Casa in mezzo.
			vincolaPosizioneSuccessivaSicura(prev, corrente, primo, secondo);
			vincolaPosizioneSuccessivaSicura(corrente, succ, primo, secondo);
		}
	}

	private static void vincolaPosizioneSuccessivaSicura(PadroneCasa corrente, PadroneCasa succ, Sigarette primo,
			Animale secondo) {
		if (succ != null) {
			if (corrente.getSigarette() == primo) {
				succ.setAnimale(secondo);
			}
			if (succ.getAnimale() == secondo) {
				corrente.setSigarette(primo);
			}
		}
	}

	private static void vincolaPosizioneSuccessiva(PadroneCasa corrente, PadroneCasa succ, Nazionalita primo,
			ColoreCasa secondo) {
		if (succ != null) {
			if (corrente.getNazionalita() == primo) {
				succ.setColore(secondo);
			}
			if (succ.getColore() == secondo) {
				corrente.setNazionalita(primo);
			}
		}
	}
	
	private static void vincolaPosizioneSuccessiva(PadroneCasa corrente, PadroneCasa succ, ColoreCasa primo,
			ColoreCasa secondo) {
		if (succ != null) {
			if (corrente.getColore() == primo) {
				succ.setColore(secondo);
			}
			if (succ.getColore() == secondo) {
				corrente.setColore(primo);
			}
		}
	}
	
	private static void vincolaPosizioneSuccessiva(PadroneCasa corrente, PadroneCasa succ, Sigarette primo,
			Bevanda secondo) {
		if (succ != null) {
			if (corrente.getSigarette() == primo) {
				succ.setBevanda(secondo);
			}
			if (succ.getBevanda() == secondo) {
				corrente.setSigarette(primo);
			}
		}
	}

	public static void vincola(PadroneCasa p, ColoreCasa primo, Nazionalita secondo) {
		if (p.getColore() == primo) {
			p.setNazionalita(secondo);
		}
		if (p.getNazionalita() == secondo) {
			p.setColore(primo);
		}
	}

	public static void vincola(PadroneCasa p, Animale primo, Nazionalita secondo) {
		if (p.getAnimale() == primo) {
			p.setNazionalita(secondo);
		}
		if (p.getNazionalita() == secondo) {
			p.setAnimale(primo);
		}
	}

	public static void vincola(PadroneCasa p, Bevanda primo, Nazionalita secondo) {
		if (p.getBevanda() == primo) {
			p.setNazionalita(secondo);
		}
		if (p.getNazionalita() == secondo) {
			p.setBevanda(primo);
		}
	}

	public static void vincola(PadroneCasa p, Bevanda primo, ColoreCasa secondo) {
		if (p.getBevanda() == primo) {
			p.setColore(secondo);
		}
		if (p.getColore() == secondo) {
			p.setBevanda(primo);
		}
	}

	public static void vincola(PadroneCasa p, Sigarette primo, Animale secondo) {
		if (p.getSigarette() == primo) {
			p.setAnimale(secondo);
		}
		if (p.getAnimale() == secondo) {
			p.setSigarette(primo);
		}
	}

	public static void vincola(PadroneCasa p, Sigarette primo, ColoreCasa secondo) {
		if (p.getSigarette() == primo) {
			p.setColore(secondo);
		}
		if (p.getColore() == secondo) {
			p.setSigarette(primo);
		}
	}

	public static void vincola(PadroneCasa p, Sigarette primo, Bevanda secondo) {
		if (p.getSigarette() == primo) {
			p.setBevanda(secondo);
		}
		if (p.getBevanda() == secondo) {
			p.setSigarette(primo);
		}
	}

	public static void vincola(PadroneCasa p, Sigarette primo, Nazionalita secondo) {
		if (p.getSigarette() == primo) {
			p.setNazionalita(secondo);
		}
		if (p.getNazionalita() == secondo) {
			p.setSigarette(primo);
		}
	}

}
