package session1.td;

import session1.td.Nat;

public class NatParInt implements Nat {
	
	public static FabriqueNaturels<Nat> FAB = new NatParInt(0);
	
	private int val;

	public NatParInt(int val){
		if(val < 0)
			throw new IllegalArgumentException("Pas de Nat à patir d'un int négatif.");
		this.val = val;
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
	public Nat zero() {
		return this.creerNatAvecValeur(0);
	}

	@Override
	public Nat somme(Nat x) {
		return this.creerNatAvecValeur(this.val() + x.val());
	}

	@Override
	public Nat un() {
		return this.creerNatAvecValeur(1);
	}

	@Override
	public Nat produit(Nat x) {
		return this.creerNatAvecValeur(this.val() * x.val());
	}


	@Override
	public Nat modulo(Nat x) {
		return this.creerNatAvecValeur(this.val()%x.val());
	}

	@Override
	public Nat div(Nat x) {
		return this.creerNatAvecValeur(this.val()/x.val());
	}
	
	@Override
	public String toString() {
		return Integer.toString(this.val());
	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Nat))
			return false;
		Nat x = (Nat)obj;
		return this.val() == x.val();
	}


	

}