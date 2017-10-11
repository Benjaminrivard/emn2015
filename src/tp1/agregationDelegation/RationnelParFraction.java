package tp1.agregationDelegation;

import tp1.ApproximationDiophantienne;
import tp1.FabriqueReels;
import tp1.FabriqueRelatifs;
import tp1.Rationnel;
import tp1.Reel;
import tp1.Z;
import tp1.commun.Mutable;

public class RationnelParFraction implements Rationnel {

	private Z numerateur;
	private Z denominateur;
	private FabriqueReels<Reel> fabR;

	public RationnelParFraction(Z numerateur, Z denominateur,
			FabriqueReels<Reel> fabR) {
		this.fabR = fabR;
		this.numerateur = numerateur;
		this.denominateur = denominateur;
	}

	public RationnelParFraction(Reel rationnel, FabriqueReels<Reel> fabR,
			FabriqueRelatifs<Z> fabZ) {
		this.fabR = fabR;
		ApproximationDiophantienne.Fraction f = ApproximationDiophantienne
				.approximation(rationnel.val());
		this.numerateur = fabZ.creer(f.numerateur);
		this.denominateur = fabZ.creer(f.denominateur);
	}

	public Z numerateur() {
		return numerateur;
	}

	public Z denominateur() {
		return denominateur;
	}

	public Reel quotient() {
		return fabR.creer((double) (numerateur().val())
				/ (double) (denominateur().val()));
	}

	public String toString() {
		return this.numerateur() + "/" + this.denominateur();
	}

	public boolean equals(Object o) {
		Z num = numerateur() instanceof Mutable ? this.numerateur().creer(
				this.numerateur().val()) : numerateur();
		Z den = denominateur() instanceof Mutable ? this.denominateur().creer(
				this.denominateur().val()) : denominateur();
		if (!(o instanceof Rationnel))
			return false;
		Rationnel r = (Rationnel) o;
		return num.produit(r.denominateur())
				.equals(den.produit(r.numerateur()));
	}

}
