package tp1.agregationSimple;

import tp1.Reel;

public class ReelParDouble implements Reel {
    //private static double PRECISION = 1e-12; // Précision relative lors des tests d'égalité.
	// equals et hashCode à modifier simultanément
	
	protected double val;
	
	public ReelParDouble(double val) {
		this.val = val;
	}

	protected Reel mettreAJour(double val){
		return this.creer(val);
	}
	
	@Override
	public double val() {
		return this.val;
	}

	@Override
	public Reel creer(double r) {
		return new ReelParDouble(r);
	}

	@Override
	public Reel somme(Reel x) {
		return this.mettreAJour(this.val() + x.val());
	}

	@Override
	public Reel zero() {
		return this.mettreAJour(0.0);
	}

	@Override
	public Reel oppose() {
		return this.mettreAJour(-this.val());
	}

	@Override
	public Reel produit(Reel x) {
		return this.mettreAJour(this.val() * x.val());
	}

	@Override
	public Reel un() {
		return this.mettreAJour(1.0);
	}

	@Override
	public Reel inverse() {
		return this.mettreAJour(1/this.val());
	}
	public boolean equals(Object o) {
		if (!(o instanceof Reel))
			return false;
		Reel x = (Reel) o;
		return (this.val() == x.val());
		//return Math.abs(this.val() - x.val()) <= Math.max(Math.abs(this.val()), Math.abs(x.val())) * PRECISION ?  true : false;
	}

	public String toString() {
		return Double.toString(this.val());
	}


}
