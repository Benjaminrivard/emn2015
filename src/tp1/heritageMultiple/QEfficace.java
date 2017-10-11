package tp1.heritageMultiple;

import tp1.Q;
import tp1.Reel;
import tp1.commun.Efficace;

public interface QEfficace extends Q, Efficace {
	Q mettreAJour(Reel r);
	
	@Override
	default Q somme(Q x) {
		return this.mettreAJour(this.quotient().somme(x.quotient()));
	}

	@Override
	default Q zero() {
		return this.mettreAJour(this.quotient().zero());
	}

	@Override
	default Q oppose() {
		return this.mettreAJour(this.quotient().oppose());
	}
	
	@Override
	default Q produit(Q x) {
		return this.mettreAJour(this.quotient().produit(x.quotient()));
	}

	@Override
	default Q un() {
		return this.mettreAJour(this.quotient().un());
	}

	@Override
	default Q inverse() {
		return this.mettreAJour(this.quotient().inverse());
	}

}
