package session1.td;

public class Zero implements Nat {

	public static final FabriqueNaturels<Nat> FAB = new Zero();

	
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
	public Nat creerNatAvecRepresentation(String repDecimale) {
		return this.creerNatAvecValeur(Integer.parseInt(repDecimale));
	}
	
	@Override
	public Nat creerNatAvecValeur(int val) {
		if(val == 0)
			return this.creerZero();
		return this.creerSuccesseur(this.creerNatAvecValeur(val -1));
	}

	@Override
	public Nat creerZero() {
		return this;
	}

	@Override
	public Nat creerSuccesseur(Nat predecesseur) {
		return new Succ(predecesseur);
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
