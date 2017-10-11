package session2.td.heritageAscendant;

import session1.td.Nat;

public abstract class EtatZero implements NatInductif {

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
}
