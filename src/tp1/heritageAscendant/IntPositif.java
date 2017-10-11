package tp1.heritageAscendant;

import tp1.Messages;
import tp1.NombreNaturel;

public class IntPositif implements NombreNaturel {
	protected int val; // val >=0

	public IntPositif(int val) {
		if (val < 0)
			throw new IllegalArgumentException(val + Messages.INT_NON_NATUREL);
		this.val = val;
	}

	public IntPositif() {
		this.val = 0;
	}

	public IntPositif(NombreNaturel pred) {
		this.val = pred.val() + 1;
	}

	public int val() {
		return val;
	}

	public boolean estNul() {
		return val == 0;
	}

	public NombreNaturel predecesseur() {
		if (estNul())
			throw new UnsupportedOperationException(Messages.SANS_PREDECESSEUR);
		return new IntPositif(val - 1);
	}
}
