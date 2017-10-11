package tp1.heritageDescendant;

import tp1.FabriqueNaturels;
import tp1.Nat;
import tp1.Z;
import tp1.heritageAscendant.FabriquerNat;

public class ZEfficaceMutableParInt extends ZEfficaceParInt implements Z {

	public ZEfficaceMutableParInt(boolean signe, Nat abs) {
		super(signe, abs);
	}

	public ZEfficaceMutableParInt(Nat diminuende, Nat diminuteur) {
		super(diminuende, diminuteur);
	}

	public ZEfficaceMutableParInt(int val) {
		super(val);
	}

	@Override
	public Z creer(boolean signe, Nat abs) {
		return new ZEfficaceMutableParInt(signe, abs);
	}

	@Override
	public Z creer(Nat diminuende, Nat diminuteur) {
		return new ZEfficaceMutableParInt(diminuende, diminuteur);
	}

	@Override
	public Z creer(int val) {
		return new ZEfficaceMutableParInt(val);
	}

	@Override
	public FabriqueNaturels<Nat> fabriqueNat() {
		return FabriquerNat.MUTABLE;
	}

	@Override
	protected Z mettreAJour(int val) {
		this.val = val;
		return this;
	}

}
