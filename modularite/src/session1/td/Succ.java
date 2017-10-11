package session1.td;

public class Succ implements Nat {

	public static FabriqueNaturels<Nat> FAB = new Succ(Zero.FAB.creerZero());

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
	public Nat creerNatAvecValeur(int val) {
		if (val == 0)
			return this.creerZero();
		return this.creerSuccesseur(this.creerNatAvecValeur(val - 1));
	}

	@Override
	public Nat creerZero() {
		return new Zero();
	}

	@Override
	public Nat creerSuccesseur(Nat predecesseur) {
		return new Succ(predecesseur);
	}

	@Override
	public Nat creerNatAvecRepresentation(String repDecimale) {
		return this.creerNatAvecValeur(Integer.parseInt(repDecimale));
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
