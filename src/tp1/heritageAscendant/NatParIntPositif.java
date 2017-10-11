package tp1.heritageAscendant;

import tp1.Nat;

public class NatParIntPositif extends IntPositif implements Nat {
	public NatParIntPositif(int val) {
		super(val);
	}
	public NatParIntPositif() {
		super();
	}

	public NatParIntPositif(Nat pred) {
		super(pred);
	}

	public Nat predecesseur() {
		return creer(super.predecesseur().val());
	}

	public Nat creer(int val) {
		return new NatParIntPositif(val);
	}

	public Nat creer() {
		return new NatParIntPositif();
	}

	public Nat creer(Nat predecesseur) {
		return new NatParIntPositif(predecesseur);
	}

	public Nat somme(Nat n) {
		return this.creer(this.val() + n.val());
	}

	public Nat zero() {
		return this.creer();
	}

	public Nat produit(Nat n) {
		return this.creer(this.val() * n.val());
	}

	public Nat un() {
		return this.creer(zero());
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
