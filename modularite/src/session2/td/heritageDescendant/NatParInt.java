package session2.td.heritageDescendant;

import session1.td.Nat;

public class NatParInt extends AlgebreNatParInt implements Nat {
	
	private int val;

	public NatParInt(int val){
		if(val < 0)
			throw new IllegalArgumentException("Pas de Nat à patir d'un int négatif.");
		this.val = val;
	}

	@Override
	public int val() {
		return this.val;
	}

	@Override
	public boolean estNul() {
	return this.val() == 0;
	}

	@Override
	public Nat predecesseur() {
		if(this.estNul())
			throw new UnsupportedOperationException("Pas de prédécesseur.");
		return this.creerNatAvecValeur(this.val() - 1);
	}

	@Override
	public int chiffre(int i) {
		return (i == 0) ? this.val()%10 : (new NatParInt(this.val()/10)).chiffre(i-1);
	}

	@Override
	public int taille() {
		return (this.val() < 10) ? 1 : 1 + (new NatParInt(this.val() / 10)).taille();
	}
	
	@Override
	public Nat creerNatAvecValeur(int val) {
		return new NatParInt(val);
	}

	@Override
	public Nat creerZero() {
		return this.creerNatAvecValeur(0);
	}

	@Override
	public Nat creerSuccesseur(Nat predecesseur) {
		return this.creerNatAvecValeur(predecesseur.val() + 1);
	}
	
	@Override
	public Nat creerNatAvecRepresentation(String repDecimale) {
		return this.creerNatAvecValeur(Integer.parseInt(repDecimale));
	}
}