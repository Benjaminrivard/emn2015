package cours2.exemples.ensembles;

/*
 * Ensemble ::= Vide | Cons(Element, Ensemble) | Union(Ensemble, Ensemble)
 */
interface Ensemble1 {
	boolean estVide();
	int element();
	Ensemble1 reste();
	Ensemble1 union(Ensemble1 ens);
}

enum Vide1 implements Ensemble1 {
	SINGLETON;

	@Override
	public boolean estVide() {
		return true;
	}

	@Override
	public int element() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Ensemble1 reste() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Ensemble1 union(Ensemble1 ens) {
		return new Union1(this, ens);
	}
}

class Cons1 implements Ensemble1 {

	private int element;
	private Ensemble1 reste;

	public Cons1(int i, Ensemble1 ens) {
		this.element = i;
		this.reste = ens;
	}

	@Override
	public boolean estVide() {
		return false;
	}

	@Override
	public int element() {
		return this.element;
	}

	@Override
	public Ensemble1 reste() {
		return this.reste;
	}

	@Override
	public Ensemble1 union(Ensemble1 ens) {
		return new Union1(this, ens);
	}
	
}

class Union1 implements Ensemble1 {

	private Ensemble1 gauche;
	private Ensemble1 droit;

	public Union1(Ensemble1 g, Ensemble1 d) {
		this.gauche = g;
		this.droit = d;
	}

	@Override
	public boolean estVide() {
		return false;
	}

	@Override
	public int element() {
		if(!this.gauche.estVide())
			return this.gauche.element();
		if(!this.droit.estVide())
			return this.droit.element();
		throw new UnsupportedOperationException();
	}

	@Override
	public Ensemble1 reste() {
		if(!this.gauche.estVide())
			return new Union1(this.gauche.reste(), this.droit);
		if(!this.droit.estVide())
			return this.droit.reste();
		throw new UnsupportedOperationException();
	}

	@Override
	public Ensemble1 union(Ensemble1 ens) {
		return new Union1(this, ens);
	}
	
}
public class EnsemblesNonIterables {

	public static void main(String[] args) {
		Ensemble1 a = new Cons1(1, Vide1.SINGLETON);
		Ensemble1 b = Vide1.SINGLETON;
		for(int i = 0; i < 10000; i++){
			b = b.union(a);
		}
		while(!b.estVide()){
			b = b.reste(); // La pile explose !
		}
	}

}
