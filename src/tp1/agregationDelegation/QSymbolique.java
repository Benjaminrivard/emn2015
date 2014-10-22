package tp1.agregationDelegation;

import tp1.Q;
import tp1.Reel;
import tp1.Z;
import tp1.agregationSimple.FabriquerReel;
import tp1.heritageDescendant.FabriquerZ;

public class QSymbolique extends QDeleguant implements Q {

	public QSymbolique() {
	}

	public QSymbolique(Z numerateur, Z denominateur) {
		this.rat = new RationnelParFraction(FabriquerZ.immutable(numerateur),
				FabriquerZ.immutable(denominateur), FabriquerReel.IMMUTABLE);
	}

	public QSymbolique(Reel rationnel) {
		this.rat = new RationnelParFraction(rationnel, FabriquerReel.IMMUTABLE,
				FabriquerZ.IMMUTABLE);
	}

	@Override
	public Q creer(Z numerateur, Z denominateur) {
		return new QSymbolique(numerateur, denominateur);
	}

	@Override
	public Q creer(Reel rationnel) {
		return new QSymbolique(rationnel);
	}

	protected Q mettreAJour(Z numerateur, Z denominateur) {
		return this.creer(numerateur, denominateur);
	}

	@Override
	public Q somme(Q x) {
		// Code ne fonctionnant pas si les relatifs sont mutables
		Z num = this.numerateur().produit(x.denominateur())
				.somme(this.denominateur().produit(x.numerateur()));
		Z den = this.denominateur().produit(x.denominateur());
		return this.mettreAJour(num, den);
	}

	@Override
	public Q zero() {
		return this.mettreAJour(this.numerateur().zero(), this.denominateur());
	}

	@Override
	public Q oppose() {
		return this
				.mettreAJour(this.numerateur().oppose(), this.denominateur());
	}

	@Override
	public Q produit(Q x) {
		Z num = this.numerateur().produit(x.numerateur());
		Z den = this.denominateur().produit(x.denominateur());
		return this.mettreAJour(num, den);
	}

	@Override
	public Q un() {
		return this.mettreAJour(this.numerateur().un(), this.denominateur()
				.un());
	}

	@Override
	public Q inverse() {
		// Code ne fonctionnant pas si les relatifs sont mutables
		return this.mettreAJour(this.denominateur(), this.numerateur());
	}

}
