package tp1.agregationSimple;

import tp1.commun.Mutable;
import tp1.FabriqueReels;
import tp1.Messages;
import tp1.Reel;

public class FabriquerReel {
	public static final FabriqueReels<Reel> MUTABLE = new ReelMutableParDouble(0.0);
	public static final FabriqueReels<Reel> IMMUTABLE = new ReelParDouble(0.0);
	
	public static Reel mutableStrict(Reel n) {
		if (!(n instanceof Mutable)) {
			return MUTABLE.creer(n.val());
		} else {
			throw new IllegalArgumentException(n + Messages.REEL_MUTABLE);
		}
	}
	
	public static Reel mutable(Reel n) {
		if (!(n instanceof Mutable)) {
			return MUTABLE.creer(n.val());
		} else {
			return n;
		}
	}


	public static Reel immutableStrict(Reel n) {
		if (n instanceof Mutable) {
			return IMMUTABLE.creer(n.val());
		} else {
			throw new IllegalArgumentException(n + Messages.REEL_NON_MUTABLE);
		}
	}

	public static Reel immutable(Reel n) {
		if (n instanceof Mutable) {
			return IMMUTABLE.creer(n.val());
		} else {
			return n;
		}
	}
	
	
	public static Reel cloner(Reel r) {
		if (r instanceof Mutable) {
			return MUTABLE.creer(r.val());
		} else {
			return r;
		}
	}
}
