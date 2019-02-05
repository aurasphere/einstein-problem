package com.beije.einstein;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SoluzioneRandom {

	public static void main(String[] args) {
		PadroneCasa[][] soluzioni = new PadroneCasa[5 ^ 6][5];
		int contatore = 0;
		soluzioni[contatore] = new PadroneCasa[5];
		List<int[]> allPermutazioni = calcolaPermutazioniBase();
		List<int[][]> permutazioniCompatibili = estraiPermutazioniCompatibili(allPermutazioni);
		List<int[][]> permutaPermutazioni = permutaPermutazioni(permutazioniCompatibili);

		System.out.println(allPermutazioni.size());
	}

	private static List<int[][]> permutaPermutazioni(List<int[][]> permutazioniBase) {
		List<int[][]> permutazioniPermutate = new ArrayList<int[][]>();
		for (int i = 0; i < permutazioniBase.size(); i++) {
			permutazioniPermutate.addAll(permutaSoluzione(permutazioniBase.get(i)));
		}
		return permutazioniBase;
	}

	private static List<int[][]> permutaSoluzione(int[][] soluzioneCorrente) {
		List<int[][]> permutazioniSoluzione = new ArrayList<int[][]>();

		for (int i = 0; i < soluzioneCorrente.length; i++) {
			for (int j = 0; j < soluzioneCorrente.length; j++) {
				if (soluzioneCorrente[i].equals(soluzioneCorrente[j])) {
					continue;
				}
				for (int k = 0; k < soluzioneCorrente.length; k++) {
					if (soluzioneCorrente[k].equals(soluzioneCorrente[i])
							|| soluzioneCorrente[k].equals(soluzioneCorrente[j])) {
						continue;
					}
					for (int l = 0; l < soluzioneCorrente.length; l++) {
						if (soluzioneCorrente[l].equals(soluzioneCorrente[i])
								|| soluzioneCorrente[l].equals(soluzioneCorrente[j])
								|| soluzioneCorrente[l].equals(soluzioneCorrente[k])) {
							continue;
						}
						for (int m = 0; m < soluzioneCorrente.length; m++) {
							if (soluzioneCorrente[m].equals(soluzioneCorrente[i])
									|| soluzioneCorrente[m].equals(soluzioneCorrente[j])
									|| soluzioneCorrente[m].equals(soluzioneCorrente[k])
									|| soluzioneCorrente[m].equals(soluzioneCorrente[l])) {
								continue;
							}
							int[][] permutazioneValida = { soluzioneCorrente[i], soluzioneCorrente[j],
									soluzioneCorrente[k], soluzioneCorrente[l], soluzioneCorrente[m] };
							permutazioniSoluzione.add(permutazioneValida);
						}
					}
				}
			}
		}
		return permutazioniSoluzione;
	}

	private static List<int[][]> estraiPermutazioniCompatibili(List<int[]> permutazioniBase) {
		List<int[][]> permutazioniCompatibili = new ArrayList<int[][]>();

		// Cicla le soluzioni.
		for (int i = 0; i < permutazioniBase.size() - 4; i++) {
			for (int j = i + 1; j < permutazioniBase.size() - 3; j++) {
				for (int k = j + 1; k < permutazioniBase.size() - 2; k++) {
					for (int l = k + 1; l < permutazioniBase.size() - 1; l++) {
						for (int m = l + 1; m < permutazioniBase.size(); m++) {

							int[][] permutazioneCorrente = { permutazioniBase.get(i), permutazioniBase.get(j),
									permutazioniBase.get(k), permutazioniBase.get(l), permutazioniBase.get(m) };
							if (permutazioniCompatibili(permutazioneCorrente)) {
								permutazioniCompatibili.add(permutazioneCorrente);
							}

						}
					}
				}
			}
		}

		return permutazioniCompatibili;
	}

	private static boolean permutazioniCompatibili(int[][] permutazioni) {
		for (int i = 0; i < permutazioni.length - 1; i++) {
			for (int j = i + 1; j < permutazioni.length; j++) {
				if (permutazioni[i][0] == permutazioni[j][0] || permutazioni[i][1] == permutazioni[j][1]
						|| permutazioni[i][2] == permutazioni[j][2] || permutazioni[i][3] == permutazioni[j][3]
						|| permutazioni[i][4] == permutazioni[j][4]) {
					return false;
				}
			}
		}
		return true;
	}

	private static List<int[]> calcolaPermutazioniBase() {
		int[] permutazioneCorrente = new int[5];
		List<int[]> allPermutazioni = new ArrayList<int[]>();
		for (int i = 0; i < 5; i++) {
			permutazioneCorrente[0] = i;
			for (int j = 0; j < 5; j++) {
				if (j == i) {
					continue;
				}
				permutazioneCorrente[1] = j;
				for (int k = 0; k < 5; k++) {
					if (k == i || k == j) {
						continue;
					}
					permutazioneCorrente[2] = k;
					for (int l = 0; l < 5; l++) {
						if (l == i || l == j || l == k) {
							continue;
						}
						permutazioneCorrente[3] = l;
						for (int m = 0; m < 5; m++) {
							if (m == i || m == j || m == k || m == l) {
								continue;
							}
							permutazioneCorrente[4] = m;
							allPermutazioni.add(permutazioneCorrente);
						}
					}
				}
			}
		}
		return allPermutazioni;
	}
}
