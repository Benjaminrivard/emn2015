package tp1.agregationDelegation;

import tp1.Q;
import tp1.Reel;
import tp1.Z;
import tp1.agregationSimple.FabriquerReel;
import tp1.commun.Mutable;
import tp1.heritageDescendant.FabriquerZ;

public class QSymboliqueMutable extends QSymbolique implements Q, Mutable {

	public QSymboliqueMutable(){}
	
	public QSymboliqueMutable(Z numerateur, Z denominateur) {
		this.rat = new RationnelParFraction(FabriquerZ.mutable(numerateur), FabriquerZ.mutable(denominateur), FabriquerReel.MUTABLE);
	}

	public QSymboliqueMutable(Reel rationnel) {
		this.rat = new RationnelParFraction(rationnel, FabriquerReel.MUTABLE, FabriquerZ.MUTABLE);
	}

	@Override
	public Q creer(Z numerateur, Z denominateur) {
		return new QSymboliqueMutable(numerateur, denominateur);
	}

	@Override
	public Q creer(Reel rationnel) {
		return new QSymboliqueMutable(rationnel);
	}

	protected Q mettreAJour(Z numerateur, Z denominateur){
		return this;
	}

	@Override
	public Q somme(Q x) {
		Z denInitial = this.denominateur().creer(this.denominateur().val());
		Z num = this.numerateur().produit(x.denominateur()).somme(denInitial.produit(x.numerateur()));
		Z den = this.denominateur().produit(x.denominateur());
		return this.mettreAJour(num, den);
	}

	@Override
	public Q inverse() {
		Z numInitial = this.numerateur().creer(this.numerateur().val());
		Z num = this.numerateur().zero().somme(this.denominateur());
		Z den = this.denominateur().zero().somme(numInitial);
		return this.mettreAJour(num, den);
	}

}
