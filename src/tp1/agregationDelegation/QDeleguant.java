package tp1.agregationDelegation;

import tp1.Q;
import tp1.Rationnel;
import tp1.Reel;
import tp1.Z;

public abstract class QDeleguant implements Q {
	protected Rationnel rat;


	public Z numerateur() {
		return rat.numerateur();
	}

	public Z denominateur() {
		return rat.denominateur();
	}

	public Reel quotient() {
		return rat.quotient();
	}

	public boolean equals(Object o) {
		if (!(o instanceof Q))
			return false;
		return rat.equals(o);
	}

	public String toString() {
		return rat.toString();
	}

}
