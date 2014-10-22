package tp1.heritageMultiple;

import tp1.Q;
import tp1.Reel;
import tp1.Z;
import tp1.agregationSimple.FabriquerReel;
import tp1.heritageDescendant.FabriquerZ;

public class QEfficaceParQuotient extends RationnelParQuotient implements QEfficace {
	
	public QEfficaceParQuotient(Z numerateur, Z denominateur) {
		super(numerateur, denominateur, FabriquerReel.IMMUTABLE, FabriquerZ.IMMUTABLE);
	}

	public QEfficaceParQuotient(Reel rationnel) {
		super(FabriquerReel.immutable(rationnel), FabriquerZ.IMMUTABLE);
	}

	public QEfficaceParQuotient() {
		this(FabriquerReel.IMMUTABLE.creer(0));
	}

	@Override
	public Q creer(Z numerateur, Z denominateur) {
		return new QEfficaceParQuotient(numerateur, denominateur);
	}

	@Override
	public Q creer(Reel rationnel) {
		return new QEfficaceParQuotient(rationnel);
	}

	public Q mettreAJour(Reel r){
		return this.creer(r);
	}

	
}
