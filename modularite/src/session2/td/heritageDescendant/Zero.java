package session2.td.heritageDescendant;

import session1.td.Nat;
import session2.td.heritageAscendant.NatInductif;

public class Zero extends AlgebreNatParInt implements NatInductif {

	@Override
	public int val() {
		return 0;
	}

	@Override
	public boolean estNul() {
		return true;
	}

	@Override
	public Nat predecesseur() {
		throw new UnsupportedOperationException("Pas de prédécesseur.");
	}
	
	@Override
	public int chiffre(int i) {
		return 0;
	}

	@Override
	public int taille() {
		return 1;
	}
	@Override
	public Nat creerZero() {
		return this;
	}

	@Override
	public Nat creerSuccesseur(Nat predecesseur) {
		return new Succ(predecesseur);
	}
	
}
