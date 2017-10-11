package tp1.agregationDelegation;

import tp1.Q;
import tp1.Reel;
import tp1.Z;
import tp1.agregationSimple.FabriquerReel;
import tp1.commun.Efficace;
import tp1.heritageDescendant.FabriquerZ;

public class QEfficace extends QDeleguant implements Q, Efficace {

	public QEfficace(){}
	
	public QEfficace(Z numerateur, Z denominateur) {
		this.rat = new RationnelParQuotient(numerateur, denominateur, FabriquerReel.IMMUTABLE, FabriquerZ.IMMUTABLE);
	}

	public QEfficace(Reel rationnel) {
		this.rat = new RationnelParQuotient(FabriquerReel.immutable(rationnel), FabriquerZ.IMMUTABLE);
	}

	@Override
	public Q creer(Z numerateur, Z denominateur) {
		return new QEfficace(numerateur, denominateur);
	}

	@Override
	public Q creer(Reel rationnel) {
		return new QEfficace(rationnel);
	}

	protected Q mettreAJour(Reel r){
		return this.creer(r);
	}
	
	@Override
	public Q somme(Q x) {
		return this.mettreAJour(this.quotient().somme(x.quotient()));
	}

	@Override
	public Q zero() {
		return this.mettreAJour(this.quotient().zero());
	}

	@Override
	public Q oppose() {
		return this.mettreAJour(this.quotient().oppose());
	}
	
	@Override
	public Q produit(Q x) {
		return this.mettreAJour(this.quotient().produit(x.quotient()));
	}

	@Override
	public Q un() {
		return this.mettreAJour(this.quotient().un());
	}

	@Override
	public Q inverse() {
		return this.mettreAJour(this.quotient().inverse());
	}

}
