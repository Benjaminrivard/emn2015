package tp1.agregationDelegation;

import tp1.Q;
import tp1.Reel;
import tp1.Z;
import tp1.agregationSimple.FabriquerReel;
import tp1.commun.Efficace;
import tp1.commun.Mutable;
import tp1.heritageDescendant.FabriquerZ;

public class QEfficaceMutable extends QEfficace implements Q, Efficace, Mutable{
	
	public QEfficaceMutable(){}
	
	public QEfficaceMutable(Z numerateur, Z denominateur) {
		this.rat = new RationnelParQuotient(numerateur, denominateur, FabriquerReel.MUTABLE, FabriquerZ.MUTABLE);
	}

	public QEfficaceMutable(Reel rationnel) {
		this.rat = new RationnelParQuotient(FabriquerReel.mutable(rationnel), FabriquerZ.MUTABLE);
	}

	/*
	 * (non-Javadoc)
	 * @see tp1.agregationDelegation.QEfficace#creer(tp1.Z, tp1.Z)
	 */
	@Override
	public Q creer(Z numerateur, Z denominateur) {
		return new QEfficaceMutable(numerateur, denominateur);
	}

	@Override
	public Q creer(Reel rationnel) {
		return new QEfficaceMutable(rationnel);
	}

	protected Q mettreAJour(Reel r){
		return this;
	}

}
