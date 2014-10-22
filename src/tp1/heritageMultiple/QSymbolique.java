package tp1.heritageMultiple;

import tp1.Q;
import tp1.Z;

public interface QSymbolique extends Q {
	Q mettreAJour(Z numerateur, Z denominateur);
	
	@Override
	default Q somme(Q x) {
		// Code ne fonctionnant pas si les relatifs sont mutables
		Z num = this.numerateur().produit(x.denominateur()).somme(this.denominateur().produit(x.numerateur()));
		Z den = this.denominateur().produit(x.denominateur());
		return this.mettreAJour(num, den);
	}

	@Override
	default Q zero() {
		return this.mettreAJour(this.numerateur().zero(), this.denominateur());
	}

	@Override
	default Q oppose() {
		return this.mettreAJour(this.numerateur().oppose(), this.denominateur());
	}
	
	@Override
	default Q produit(Q x) {
		Z num = this.numerateur().produit(x.numerateur());
		Z den = this.denominateur().produit(x.denominateur());
		return this.mettreAJour(num, den);
	}

	@Override
	default Q un() {
		return this.mettreAJour(this.numerateur().un(), this.denominateur().un());
	}

	@Override
	default Q inverse() {
		// Code ne fonctionnant pas si les relatifs sont mutables
		return this.mettreAJour(this.denominateur(), this.numerateur());
	}

}
