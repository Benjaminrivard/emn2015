package tp1.heritageDescendant;

import tp1.commun.Mutable;
import tp1.FabriqueRelatifs;
import tp1.Messages;
import tp1.Z;

public class FabriquerZ {
	public static final FabriqueRelatifs<Z> MUTABLE = new ZEfficaceMutableParInt(0);
	public static final FabriqueRelatifs<Z> IMMUTABLE = new ZEfficaceParInt(0);

	public static Z mutableStrict(Z n) {
		if (!(n instanceof Mutable)) {
			return MUTABLE.creer(n.val());
		} else {
			throw new IllegalArgumentException(n + Messages.Z_MUTABLE);
		}
	}

	public static Z mutable(Z n) {
		if (!(n instanceof Mutable)) {
			return MUTABLE.creer(n.val());
		} else {
			return n;
		}
	}

	
	public static Z immutableStrict(Z n) {
		if (n instanceof Mutable) {
			return IMMUTABLE.creer(n.val());
		} else {
			throw new IllegalArgumentException(n + Messages.Z_NON_MUTABLE);
		}
	}

	public static Z immutable(Z n) {
		if (n instanceof Mutable) {
			return IMMUTABLE.creer(n.val());
		} else {
			return n;
		}
	}

	
	public static Z cloner(Z n) {
		if (n instanceof Mutable) {
			return MUTABLE.creer(n.val());
		} else {
			return n;
		}
	}
}
