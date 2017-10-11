package tp1.agregationDelegation;

import tp1.agregationSimple.FabriquerReel;
import tp1.commun.Efficace;
import tp1.commun.Mutable;
import tp1.heritageDescendant.FabriquerZ;
import tp1.FabriqueRationnels;
import tp1.Messages;
import tp1.Q;

public class FabriquerQ {
	public static final FabriqueRationnels<Q> SYMBOLIQUE_MUTABLE = new QSymboliqueMutable();
	public static final FabriqueRationnels<Q> SYMBOLIQUE_IMMUTABLE = new QSymbolique();
	public static final FabriqueRationnels<Q> EFFICACE_MUTABLE = new QEfficaceMutable();
	public static final FabriqueRationnels<Q> EFFICACE_IMMUTABLE = new QEfficace();
	
	public static Q mutableStrict(Q r) {
		if (!(r instanceof Mutable)) {
			if(r instanceof Efficace){
				return EFFICACE_MUTABLE.creer(FabriquerReel.mutable(r.quotient()));
			}else{
				return SYMBOLIQUE_MUTABLE.creer(FabriquerZ.mutable(r.numerateur()), FabriquerZ.mutable(r.denominateur()));
			}
		} else {
			throw new IllegalArgumentException(r + Messages.RATIONNEL_MUTABLE);
		}
	}

	public static Q mutable(Q r) {
		if (!(r instanceof Mutable)) {
			if(r instanceof Efficace){
				return EFFICACE_MUTABLE.creer(FabriquerReel.mutable(r.quotient()));
			}else{
				return SYMBOLIQUE_MUTABLE.creer(FabriquerZ.mutable(r.numerateur()), FabriquerZ.mutable(r.denominateur()));
			}
		} else {
			return r;
		}
	}

	public static Q immutableStrict(Q r) {
		if (r instanceof Mutable) {
			if(r instanceof Efficace){
				return EFFICACE_IMMUTABLE.creer(FabriquerReel.immutable(r.quotient()));
			}else{
				return SYMBOLIQUE_IMMUTABLE.creer(FabriquerZ.immutable(r.numerateur()), FabriquerZ.immutable(r.denominateur()));
			}
		} else {
			throw new IllegalArgumentException(r + Messages.RATIONNEL_NON_MUTABLE);
		}
	}

	public static Q immutable(Q r) {
		if (r instanceof Mutable) {
			if(r instanceof Efficace){
				return EFFICACE_IMMUTABLE.creer(FabriquerReel.immutable(r.quotient()));
			}else{
				return SYMBOLIQUE_IMMUTABLE.creer(FabriquerZ.immutable(r.numerateur()), FabriquerZ.immutable(r.denominateur()));
			}
		} else {
			return r;
		}
	}

	
	public static Q cloner(Q r) {
		if (r instanceof Mutable) {
			if(r instanceof Efficace){
				return EFFICACE_MUTABLE.creer(FabriquerReel.cloner(r.quotient()));
			}else{
				return SYMBOLIQUE_MUTABLE.creer(FabriquerZ.cloner(r.numerateur()), FabriquerZ.cloner(r.denominateur()));
			}
		} else {
			return r;
		}
	}
	
}
