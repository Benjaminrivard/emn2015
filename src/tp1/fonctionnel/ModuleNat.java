package tp1.fonctionnel;

import tp1.NombreNaturel;

public class ModuleNat {
	public static NombreNaturel creer(int val) {
		return new IntPositif(val);
	}

	public static NombreNaturel creer() {
		return new IntPositif();
	}

	public static NombreNaturel creer(NombreNaturel n) {
		return new IntPositif(n);
	}
	
	public static NombreNaturel somme(NombreNaturel t, NombreNaturel n) {
		return creer(t.val() + n.val());
	}

	public static NombreNaturel zero() {
		return creer();
	}

	public static NombreNaturel produit(NombreNaturel t, NombreNaturel n) {
		return creer(t.val() * n.val());
	}

	public static NombreNaturel un() {
		return creer(1);
	}

	public static boolean egal(NombreNaturel t, NombreNaturel n) {
		return (t.val() == n.val());
	}

	public static String representer(NombreNaturel t) {
		return Integer.toString(t.val());
	}
}
