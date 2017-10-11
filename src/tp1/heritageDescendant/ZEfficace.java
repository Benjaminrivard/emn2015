package tp1.heritageDescendant;

import tp1.Z;
import tp1.commun.Efficace;

public abstract class ZEfficace implements Z, Efficace{

	abstract protected Z mettreAJour(int val);
	
	@Override
	public Z somme(Z x) {
		return this.mettreAJour(this.val() + x.val());
	}

	@Override
	public Z zero() {
		return this.mettreAJour(0);
	}

	@Override
	public Z oppose() {
		return this.mettreAJour(-this.val());
	}

	
	@Override
	public Z produit(Z x) {
		return this.mettreAJour(this.val() * x.val());
	}

	@Override
	public Z un() {
		return this.mettreAJour(1);
	}

}
