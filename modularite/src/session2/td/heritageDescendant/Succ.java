package session2.td.heritageDescendant;

import session1.td.Nat;
import session2.td.heritageAscendant.NatInductif;

public class Succ extends AlgebreNatParInt implements NatInductif {

	private Nat predecesseur;

	public Succ(Nat predecesseur) {
		this.predecesseur = predecesseur;
	}

	@Override
	public int val() {
		return 1 + this.predecesseur().val();
	}

	@Override
	public boolean estNul() {
		return false;
	}

	@Override
	public Nat predecesseur() {
		return this.predecesseur;
	}
	
	// Précondition : i >=0
	@Override
	public int chiffre(int i) {
		if(i < this.taille()){
			return Character.getNumericValue(Integer.toString(this.val()).charAt(this.taille() - 1 - i));
		}
		return 0;
	}

	@Override
	public int taille() {
		return Integer.toString(this.val()).length();
	}
	
	@Override
	public Nat creerZero() {
		return new Zero();
	}

	@Override
	public Nat creerSuccesseur(Nat predecesseur) {
		return new Succ(predecesseur);
	}
}
