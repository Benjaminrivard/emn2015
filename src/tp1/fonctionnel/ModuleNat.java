package tp1.fonctionnel;

import tp1.NombreNaturel;
import tp1.heritageAscendant.NatParIntPositif;

public class ModuleNat {
	public static NombreNaturel creer(int val) {
		return new IntPositif(val);
	}

	public static NombreNaturel creer() {
		return new NatParIntPositif();
	}

	
	public static NombreNaturel somme(NombreNaturel t, NombreNaturel n) {
		return creer(t.val() + n.val());
	}

	public NombreNaturel zero() {
		return creer();
	}

	public NombreNaturel produit(NombreNaturel t, NombreNaturel n) {
		return creer(t.val() * n.val());
	}

	public NombreNaturel un() {
		return creer(1);
	}

	public boolean egal(NombreNaturel t, NombreNaturel n) {
		return (t.val() == n.val());
	}

	public String representer(NombreNaturel t) {
		return Integer.toString(t.val());
	}
}
