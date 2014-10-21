package cours2.exemples.ensembles;

/*
 * Ensemble ::= Vide | Cons(Element, Ensemble) | Union(Ensemble, Ensemble)
 */
interface Ensemble2 {
	// Sélecteurs
	int taille();
	boolean estVide();
	boolean estCons();
	boolean estUnion();
	// Destructeurs
	int element();
	Ensemble2 reste();
	Ensemble2 gauche();
	Ensemble2 droit();
	// Constructeurs
	default Ensemble2 vide(){
		return Vide2.SINGLETON; // Exemple d'une méthode par défaut
	}
	Ensemble2 cons(int n, Ensemble2 ens);
	Ensemble2 union(Ensemble2 ens);
}

enum Vide2 implements Ensemble2 {
	SINGLETON;

	@Override
	public int taille() {
		return 0;
	}

	@Override
	public boolean estVide() {
		return true;
	}

	@Override
	public boolean estCons() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean estUnion() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int element() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Ensemble2 reste() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Ensemble2 gauche() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Ensemble2 droit() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Ensemble2 cons(int n, Ensemble2 ens) {
		return new Cons2(n, ens);
	}
	@Override
	public Ensemble2 union(Ensemble2 ens) {
		return new Union2(this, ens);
	}

}

class Cons2 implements Ensemble2 {

	private int element;
	private Ensemble2 reste;
	private int taille;
	
	public Cons2(int i, Ensemble2 ens) {
		this.element = i;
		this.reste = ens;
		this.taille = 1 + ens.taille();
	}

	@Override
	public int taille() {
		return this.taille;
	}

	
	@Override
	public boolean estVide() {
		return false;
	}

	@Override
	public boolean estCons() {
		return true;
	}

	@Override
	public boolean estUnion() {
		return false;
	}

	@Override
	public int element() {
		return this.element;
	}

	@Override
	public Ensemble2 reste() {
		return this.reste;
	}

	@Override
	public Ensemble2 gauche() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Ensemble2 droit() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Ensemble2 cons(int n, Ensemble2 ens) {
		return new Cons2(n, ens);
	}

	
	@Override
	public Ensemble2 union(Ensemble2 ens) {
		return new Union2(this, ens);
	}
}

class Union2 implements Ensemble2 {

	private Ensemble2 gauche;
	private Ensemble2 droit;
	private int taille;
	
	private int element;
	private Ensemble2 reste;

	public Union2(Ensemble2 g, Ensemble2 d) {
		this.gauche = g;
		this.droit = d;
		this.taille = g.taille() + d.taille();
	}

	@Override
	public int taille() {
		return this.taille;
	}

	@Override
	public boolean estVide() {
		return (this.taille() == 0);
	}

	@Override
	public boolean estCons() {
		return false;
	}

	@Override
	public boolean estUnion() {
		return true;
	}
	
	private void decomposer(){
		// Precondition : !this.estVide()
		Ensemble2 courant = this; // Invariant : !courant.estVide()
		while(true){
			if(courant.estCons()){ // Cons
				this.reste = courant.reste();
				this.element = courant.element();
				return;
			}else{ // Union
				if(courant.gauche().estVide()){
					courant = courant.droit();
				}else if(courant.gauche().estCons()){
					this.reste = courant.gauche().reste().union(courant.droit());
					this.element = courant.gauche().element();
					return;
				}else{
					courant = courant.gauche().gauche().union(courant.gauche().droit().union(courant.droit()));
				}
			}
		}
	}
	
	@Override
	public int element() {
		if(this.estVide()){
			throw new UnsupportedOperationException();
		}
		if(this.reste != null){
			return this.element;
		}
		decomposer();
		return this.element;
	}
	
	@Override
	public Ensemble2 reste() {
		if(this.estVide()){
			throw new UnsupportedOperationException();
		}
		if(this.reste != null){
			return this.reste;
		}
		decomposer();
		return this.reste;
	}

	@Override
	public Ensemble2 gauche() {
		return this.gauche;
	}

	@Override
	public Ensemble2 droit() {
		return this.droit;
	}

	@Override
	public Ensemble2 cons(int n, Ensemble2 ens) {
		return new Cons2(n, ens);
	}
	
	@Override
	public Ensemble2 union(Ensemble2 ens) {
		return new Union2(this, ens);
	}

}


public class EnsemblesIterables {

	public static void main(String[] args) {
		Ensemble2 a = new Cons2(1, Vide2.SINGLETON);
		Ensemble2 b = Vide2.SINGLETON;
		for(int i = 0; i < 10000000; i++){
			b = b.union(a);
		}
		int s = 0;
		while(!b.estVide()){
			s = s + b.element();
			b = b.reste(); 
		}
		System.out.println(s);
	}

}
