package tp1.heritageAscendant;

import tp1.commun.Mutable;
import tp1.FabriqueNaturels;
import tp1.Messages;
import tp1.Nat;

public class FabriquerNat {
	public static final FabriqueNaturels<Nat> MUTABLE = new NatMutableParIntPositif();
	public static final FabriqueNaturels<Nat> IMMUTABLE = new NatParIntPositif();

	public static Nat mutable(Nat n) {
		if (!(n instanceof Mutable)) {
			return MUTABLE.creer(n.val());
		} else {
			throw new IllegalArgumentException(n + Messages.NAT_MUTABLE);
		}
	}

	public static Nat immutable(Nat n) {
		if (n instanceof Mutable) {
			return IMMUTABLE.creer(n.val());
		} else {
			throw new IllegalArgumentException(n + Messages.NAT_NON_MUTABLE);
		}
	}
	
	public static Nat cloner(Nat n) {
		if (n instanceof Mutable) {
			return MUTABLE.creer(n.val());
		} else {
			return n;
		}
	}
}
