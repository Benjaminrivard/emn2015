package session1.td;

public class ZeroRec extends Zero implements Nat {

	public static final FabriqueNaturels<Nat> FAB = new ZeroRec();

	@Override
	public Nat creerSuccesseur(Nat predecesseur) {
		return new SuccRec(predecesseur);
	}

	// Remplacement du code copié-collé par du code récursif

	@Override
	public Nat zero() {
		return this.creerZero();
	}

	@Override
	public Nat somme(Nat x) {
		return x;
	}

	@Override
	public Nat un() {
		return this.creerSuccesseur(this.creerZero());
	}

	@Override
	public Nat produit(Nat x) {
		return this.creerZero();
	}

	@Override
	public Nat modulo(Nat x) {
		return this.creerZero();
	}

	@Override
	public Nat div(Nat x) {
		return this.creerZero();
	}

	@Override
	public String toString() {
		return "0";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Nat))
			return false;
		Nat x = (Nat) obj;
		return x.estNul();
	}

}
