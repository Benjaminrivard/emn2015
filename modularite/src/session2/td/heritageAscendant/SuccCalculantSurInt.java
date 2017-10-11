package session2.td.heritageAscendant;

import session1.td.Nat;

public class SuccCalculantSurInt extends EtatSucc {

	public SuccCalculantSurInt(Nat predecesseur) {
		super(predecesseur);
	}

	@Override
	public Nat creerZero() {
		return new ZeroCalculantSurInt();
	}

	@Override
	public Nat creerSuccesseur(Nat predecesseur) {
		return new SuccCalculantSurInt(predecesseur);
	}

	// Code copié-collé

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
		return this.creerNatAvecValeur(this.val() % x.val());
	}

	@Override
	public Nat div(Nat x) {
		return this.creerNatAvecValeur(this.val() / x.val());
	}

	@Override
	public String toString() {
		return Integer.toString(this.val());
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Nat))
			return false;
		Nat x = (Nat) obj;
		return this.val() == x.val();
	}

}
