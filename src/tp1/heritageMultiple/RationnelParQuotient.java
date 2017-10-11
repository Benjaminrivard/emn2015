package tp1.heritageMultiple;

import tp1.ApproximationDiophantienne;
import tp1.FabriqueReels;
import tp1.FabriqueRelatifs;
import tp1.Rationnel;
import tp1.Reel;
import tp1.Z;
import tp1.commun.Efficace;

public class RationnelParQuotient implements Rationnel, Efficace {

	private Reel quotient;
	private FabriqueRelatifs<Z> fabZ;

	public RationnelParQuotient(Z numerateur, Z denominateur,
			FabriqueReels<Reel> fabR, FabriqueRelatifs<Z> fabZ) {
		this.fabZ = fabZ;
		this.quotient = fabR.creer((double) (numerateur.val())
				/ (double) (denominateur.val()));
	}

	public RationnelParQuotient(Reel rationnel, FabriqueRelatifs<Z> fabZ) {
		this.fabZ = fabZ;
		this.quotient = rationnel;
	}

	public Z numerateur() {
		ApproximationDiophantienne.Fraction f = ApproximationDiophantienne
				.approximation(quotient().val());
		return fabZ.creer(f.numerateur);
	}

	public Z denominateur() {
		ApproximationDiophantienne.Fraction f = ApproximationDiophantienne
				.approximation(quotient().val());
		return fabZ.creer(f.denominateur);
	}

	public Reel quotient() {
		return this.quotient;
	}

	public String toString() {
		return this.quotient().toString();
	}

	public boolean equals(Object o) {
		if (!(o instanceof Rationnel))
			return false;
		Rationnel r = (Rationnel) o;
		return this.quotient().equals(r.quotient());
	}

}
