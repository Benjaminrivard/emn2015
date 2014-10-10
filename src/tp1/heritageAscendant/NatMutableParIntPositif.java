package tp1.heritageAscendant;

import tp1.commun.Mutable;
import tp1.Nat;

public class NatMutableParIntPositif extends IntPositif implements Nat, Mutable {
	public NatMutableParIntPositif(int val) {
		super(val);
	}

	public NatMutableParIntPositif() {
		super();
	}

	public NatMutableParIntPositif(Nat pred) {
		super(pred);
	}

	public Nat predecesseur() {
		return creer(super.predecesseur().val());
	}

	public Nat creer(int val) {
		return new NatMutableParIntPositif(val);
	}

	public Nat creer() {
		return new NatMutableParIntPositif();
	}

	public Nat creer(Nat predecesseur) {
		return new NatMutableParIntPositif(predecesseur);
	}

	public Nat somme(Nat n) {
		this.val = (this.val() + n.val());
		return this;
	}

	public Nat zero() {
		this.val = 0;
		return this;
	}

	public Nat produit(Nat n) {
		this.val = (this.val() * n.val());
		return this;
	}

	public Nat un() {
		this.val = 1;
		return this;
	}

	public boolean equals(Object o) {
		if (!(o instanceof Nat))
			return false;
		Nat x = (Nat) o;
		return (this.val() == x.val());
	}

	public String toString() {
		return Integer.toString(this.val());
	}

}
