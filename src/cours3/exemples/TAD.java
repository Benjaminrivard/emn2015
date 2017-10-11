package cours3.exemples;

import java.util.Collections;
import java.util.List;

/*
 * A . Approche Objet
 */

/*
 * A1. Décomposition classique d'une interface : état + fabrique + service
 */

interface Etat {
	int etat();
}

// Définitions prédicatives pour éviter la dépendance en I
interface Fabrique<T> {
	T creer(int e);
	default T creer(){
		return this.creer(0);
	}
}

// Interface définissant des lois externes
interface Service<T> {
	T somme(T x);

	T zero();
}

interface I extends Etat, Fabrique<I>, Service<I> {
}

/*
 * A2. Implémentation triviale
 */

class C implements I {
	private int e;

	public C(int e) {
		this.e = e;
	}

	public int etat() {
		return e;
	}

	public I creer(int e) {
		return new C(e);
	}

	public I somme(I x) {
		return this.creer(this.etat() + x.etat());
	}

	public I zero() {
		return this.creer(0);
	}
}

/*
 * A3. Bibliothèque Objet
 */

class BiblioOO {
	// Obligation d'avoir au moins un T
	public static <T extends Service<T>> T sommeNAire(List<T> l, Fabrique<T> fab) {
		T r = fab.creer().zero();
		for (T x : l) {
			r = r.somme(x);
		}
		return r;
	}
}

/*
 * B. Approche type abstrait de données
 */

/*
 * B1. Définition du module (sa signature précisément)
 */

// Nouvelle interface (prédicat) définissant des lois internes
interface TAService<Rep> {
	Rep somme(Rep x, Rep y);

	Rep zero();
}

// Quantification universelle pour spécifier que toute représentation des
// données implentera l'interface Etat
interface Module<Rep extends Etat> extends Fabrique<Rep>, TAService<Rep> {
}

/*
 * B2. Implémentation de l'état
 */

class ImplemEtat implements Etat {
	private int e;

	public ImplemEtat(int e) {
		this.e = e;
	}

	public int etat() {
		return e;
	}
}

/*
 * B3. Implémentation du module avec l'implémentation précédente de l'état
 */
// Classe singleton
class ImplemModule implements Module<ImplemEtat> {

	private ImplemModule() {
	}

	public ImplemEtat creer(int e) {
		return new ImplemEtat(e);
	}

	public ImplemEtat somme(ImplemEtat x, ImplemEtat y) {
		return this.creer(x.etat() + y.etat());
	}

	public ImplemEtat zero() {
		return this.creer(0);
	}

	private static Module<? extends Etat> m = new ImplemModule();

	// Fonction permettant l'accès à l'unique instance après abstraction du type
	// -> Usage d'un type existentiel
	public static Module<? extends Etat> abstraction() {
		return m;
	}

}

/*
 * B4. Implémentation alternative sans fonction d'abstraction
 */
class ImplemEtat2 implements Etat {
	private int e;

	public ImplemEtat2(int e) {
		this.e = e;
	}

	public int etat() {
		return e;
	}

	public void setEtat(int e) {
		this.e = e;
	}
}

class ImplemModule2 implements Module<ImplemEtat2> {

	public ImplemModule2() {
	}

	public ImplemEtat2 creer(int e) {
		return new ImplemEtat2(e);
	}

	public ImplemEtat2 somme(ImplemEtat2 x, ImplemEtat2 y) {
		return this.creer(x.etat() + y.etat());
	}

	public ImplemEtat2 zero() {
		return this.creer(0);
	}
}

/*
 * B5. Bibliothèque utilisant un module
 */
class BiblioMod {
	public static <Rep extends Etat> Rep sommeNAire(List<Rep> l, Module<Rep> m) {
		Rep r = m.zero();
		for (Rep x : l) {
			r = m.somme(r, x);
		}
		return r;
	}

}

/*
 * Fonction principale
 */

public class TAD {

	public static void main(String[] args) {
		testOO();

		// Module abstrait (type inconnu pour la représentation)
		Module<?> m = ImplemModule.abstraction();
		testMod(m); // -> Capture du joker

		// Module concret (type connu pour la représentation)
		Module<ImplemEtat2> mc = new ImplemModule2();
		testMod(mc);
		// Possibilité d'interagir avec le type ImplemEtat2 directement
		ImplemEtat2 x = mc.creer(0);
		ImplemEtat2 un = mc.creer(1);
		x = mc.somme(x, un);
		x.setEtat(-2); // -> Possibilité de violer un invariant du module
						// -> A éviter en utilisant l'abstraction de type
	}

	private static <Rep extends Etat> void testMod(Module<Rep> m) {
		Rep un = m.creer(1);
		List<Rep> l = Collections.nCopies(10, un);
		System.out.println(BiblioMod.sommeNAire(l, m).etat());

	}

	private static void testOO() {
		I un = new C(1);
		List<I> l = Collections.nCopies(10, un);
		System.out.println(BiblioOO.sommeNAire(l, un).etat());
	}

}
