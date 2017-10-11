package tp1.heritageMultiple;

import tp1.Q;
import tp1.Reel;
import tp1.Z;
import tp1.agregationSimple.FabriquerReel;
import tp1.commun.Mutable;
import tp1.heritageDescendant.FabriquerZ;

public class QEfficaceParQuotientMutable extends RationnelParQuotient implements QEfficace, Mutable {
	
	public QEfficaceParQuotientMutable(Z numerateur, Z denominateur) {
		super(numerateur, denominateur, FabriquerReel.MUTABLE, FabriquerZ.MUTABLE);
	}

	public QEfficaceParQuotientMutable(Reel rationnel) {
		super(FabriquerReel.mutable(rationnel), FabriquerZ.MUTABLE);
	}

	public QEfficaceParQuotientMutable() {
		this(FabriquerReel.MUTABLE.creer(0));
	}

	@Override
	public Q creer(Z numerateur, Z denominateur) {
		return new QEfficaceParQuotientMutable(numerateur, denominateur);
	}

	@Override
	public Q creer(Reel rationnel) {
		return new QEfficaceParQuotientMutable(rationnel);
	}

	public Q mettreAJour(Reel r){
		return this;
	}

	
}
