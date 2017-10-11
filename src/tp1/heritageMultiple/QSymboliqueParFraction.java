package tp1.heritageMultiple;

import tp1.Q;
import tp1.Reel;
import tp1.Z;
import tp1.agregationSimple.FabriquerReel;
import tp1.heritageDescendant.FabriquerZ;

public class QSymboliqueParFraction extends RationnelParFraction implements QSymbolique{
	public QSymboliqueParFraction(Z numerateur, Z denominateur) {
		super(FabriquerZ.immutable(numerateur), FabriquerZ.immutable(denominateur), FabriquerReel.IMMUTABLE);
	}

	public QSymboliqueParFraction(Reel rationnel) {
		super(rationnel, FabriquerReel.IMMUTABLE, FabriquerZ.IMMUTABLE);
	}

	public QSymboliqueParFraction() {
		this(FabriquerZ.IMMUTABLE.creer(0), FabriquerZ.IMMUTABLE.creer(1));
	}

	@Override
	public Q creer(Z numerateur, Z denominateur) {
		return new QSymboliqueParFraction(numerateur, denominateur);
	}

	@Override
	public Q creer(Reel rationnel) {
		return new QSymboliqueParFraction(rationnel);
	}

	public Q mettreAJour(Z numerateur, Z denominateur){
		return this.creer(numerateur, denominateur);
	}

}
