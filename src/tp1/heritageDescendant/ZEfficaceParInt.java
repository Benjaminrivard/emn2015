package tp1.heritageDescendant;

import tp1.FabriqueNaturels;
import tp1.Nat;
import tp1.Z;
import tp1.heritageAscendant.FabriquerNat;

public class ZEfficaceParInt extends ZEfficace implements Z {

	protected int val;
	
	public ZEfficaceParInt(boolean signe, Nat abs) {
		this.val = signe ? abs.val() : - abs.val();
	}

	public ZEfficaceParInt(Nat diminuende, Nat diminuteur) {
		this.val = diminuende.val() - diminuteur.val();
	}

	public ZEfficaceParInt(int val) {
		this.val = val;
	}

	@Override
	public int val() {
		return this.val;
	}

	@Override
	public boolean estPositif() {
		return (this.val() >= 0);
	}

	@Override
	public boolean estNegatif() {
		return (this.val() <= 0);
	}

	@Override
	public Nat valAbsolue() {
		return fabriqueNat().creer(Math.abs(val));
	}

	@Override
	public Nat diminuende() {
		return estPositif() ? valAbsolue() : valAbsolue().zero() ;
	}

	@Override
	public Nat diminuteur() {
		return estNegatif() ? valAbsolue() : valAbsolue().zero() ;
	}

	@Override
	public Z creer(boolean signe, Nat abs) {
		return new ZEfficaceParInt(signe, abs);
	}

	@Override
	public Z creer(Nat diminuende, Nat diminuteur) {
		return new ZEfficaceParInt(diminuende, diminuteur);
	}

	@Override
	public Z creer(int val) {
		return new ZEfficaceParInt(val);
	}

	@Override
	public FabriqueNaturels<Nat> fabriqueNat() {
		return FabriquerNat.IMMUTABLE;
	}
	public boolean equals(Object o) {
		if (!(o instanceof Z))
			return false;
		Z x = (Z) o;
		return (this.val() == x.val());
	}

	public String toString() {
		return Integer.toString(this.val());
	}

	@Override
	protected Z mettreAJour(int val) {
		return this.creer(val);
	}


}
