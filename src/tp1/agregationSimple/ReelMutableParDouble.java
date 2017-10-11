package tp1.agregationSimple;

import tp1.Reel;
import tp1.commun.Mutable;

public class ReelMutableParDouble extends ReelParDouble implements Reel, Mutable {

	public ReelMutableParDouble(double val) {
		super(val);
	}

	@Override
	public Reel creer(double r) {
		return new ReelMutableParDouble(r);
	}

	
	protected Reel mettreAJour(double val){
		this.val = val;
		return this;
	}
}
