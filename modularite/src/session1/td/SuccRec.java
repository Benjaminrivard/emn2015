package session1.td;

public class SuccRec extends Succ implements Nat {

	public static FabriqueNaturels<Nat> FAB = new SuccRec(ZeroRec.FAB.creerZero());
	
	public SuccRec(Nat predecesseur) {
		super(predecesseur);
	}

	@Override
	public Nat creerZero() {
		return new ZeroRec();
	}

	@Override
	public Nat creerSuccesseur(Nat predecesseur) {
		return new SuccRec(predecesseur);
	}
	
	// Remplacement du code copié-collé par du code récursif
	
	@Override
	public Nat somme(Nat x) {
		return this.creerSuccesseur(this.predecesseur().somme(x));
	}

	@Override
	public Nat produit(Nat x) {
		return x.somme(this.predecesseur().produit(x));
	}
	
	@Override
	public Nat modulo(Nat x) {
		Nat r = this.predecesseur().modulo(x);
		return this.creerSuccesseur(r).equals(x) ? this.creerZero() : this.creerSuccesseur(r);
	}

	@Override
	public Nat div(Nat x) {
		Nat r = this.predecesseur().modulo(x);
		Nat q = this.predecesseur().div(x);
		return this.creerSuccesseur(r).equals(x) ? this.creerSuccesseur(q) : q;
	}
	
	@Override
	public String toString() {
		return "S^" + this.val() + "(0)";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Nat))
			return false;
		Nat x = (Nat)obj;
		if(x.estNul())
			return false;
		return this.predecesseur().equals(x.predecesseur());
	}

	
	
}
