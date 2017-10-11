package session1.td;

import hierarchie.SemiAnneauUnitaireEuclidien;

public class Outils {
	public static <T extends SemiAnneauUnitaireEuclidien<T>> T produitRusse(T x, T y, T base) {
		T zero = x.zero();
		T resultat = zero;

		while (!y.equals(zero)) {
			T reste = y.modulo(base);
			if (!reste.equals(zero)) {
				resultat = resultat.somme(x.produit(reste));
			}
			x = x.produit(base);
			y = y.div(base);

		}
		return resultat;
	}
}
