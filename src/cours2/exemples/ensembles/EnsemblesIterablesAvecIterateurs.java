package cours2.exemples.ensembles;

/*
 * Ensemble ::= Vide | Cons(Element, Ensemble) | Union(Ensemble, Ensemble)
 */
interface Ensemble3 {
	// Sélecteurs
	int taille();
	boolean estVide();
	boolean estCons();
	boolean estUnion();
	// Destructeurs
	int element();
	Ensemble3 reste();
	default Iterateur iterateur(){
		return new Iterateur(this);
	}
	Ensemble3 gauche();
	Ensemble3 droit();
	// Constructeurs
	default Ensemble3 vide(){
		return Vide3.SINGLETON; // Exemple d'une méthode par défaut
	}
	Ensemble3 cons(int n, Ensemble3 ens);
	Ensemble3 union(Ensemble3 ens);
}

/*
 * Les itérateurs sont supposés mutables, conforméménet à l'interface Java.
 */
class Iterateur {
	private Ensemble3 reste;
	private int element;
	
	public Iterateur(Ensemble3 ens){
		decomposer(ens);
	}
	private void decomposer(Ensemble3 ens){
		while(true){
			if(ens.estVide()){
				this.reste = null;
				break;
			}
			if(ens.estCons()){
				this.reste = ens.reste();
				this.element = ens.element();
				break;
			}
			if(ens.estUnion()){
				if(ens.gauche().estVide()){
					ens = ens.droit();
					continue;
				}else if(ens.gauche().estCons()){
					this.reste = ens.gauche().reste().union(ens.droit());
					this.element = ens.gauche().element();
					break;
				}else{
					ens = ens.gauche().gauche().union(ens.gauche().droit().union(ens.droit()));
					continue;
				}
			}
		}
	}

	public boolean aSuivant(){
		return reste != null;
	}
	public int suivant(){
		if (reste == null)
			throw new UnsupportedOperationException();
		int r = element;
		decomposer(reste);
		return r;
	}
	public Ensemble3 reste(){
		if (reste == null)
			throw new UnsupportedOperationException();
		return this.reste;
	}
}


enum Vide3 implements Ensemble3 {
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
	public Ensemble3 reste() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Ensemble3 gauche() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Ensemble3 droit() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Ensemble3 cons(int n, Ensemble3 ens) {
		return new Cons3(n, ens);
	}
	@Override
	public Ensemble3 union(Ensemble3 ens) {
		return new Union3(this, ens);
	}

}

class Cons3 implements Ensemble3 {

	private int element;
	private Ensemble3 reste;
	private int taille;
	
	public Cons3(int i, Ensemble3 ens) {
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
	public Ensemble3 reste() {
		return this.reste;
	}

	@Override
	public Ensemble3 gauche() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Ensemble3 droit() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Ensemble3 cons(int n, Ensemble3 ens) {
		return new Cons3(n, ens);
	}

	
	@Override
	public Ensemble3 union(Ensemble3 ens) {
		return new Union3(this, ens);
	}
}

class Union3 implements Ensemble3 {

	private Ensemble3 gauche;
	private Ensemble3 droit;
	private int taille;
	
	public Union3(Ensemble3 g, Ensemble3 d) {
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
		
	@Override
	public int element() {
		Iterateur i = this.iterateur();
		return i.suivant();
	}
	
	@Override
	public Ensemble3 reste() {
		Iterateur i = this.iterateur();
		return i.reste();
	}

	@Override
	public Ensemble3 gauche() {
		return this.gauche;
	}

	@Override
	public Ensemble3 droit() {
		return this.droit;
	}

	@Override
	public Ensemble3 cons(int n, Ensemble3 ens) {
		return new Cons3(n, ens);
	}
	
	@Override
	public Ensemble3 union(Ensemble3 ens) {
		return new Union3(this, ens);
	}

}


public class EnsemblesIterablesAvecIterateurs {

	public static void main(String[] args) {
		Ensemble3 a = new Cons3(1, Vide3.SINGLETON);
		Ensemble3 b = Vide3.SINGLETON;
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
