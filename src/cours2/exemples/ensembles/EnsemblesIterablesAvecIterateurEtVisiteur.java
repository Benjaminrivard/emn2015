package cours2.exemples.ensembles;

/*
 * Ensemble ::= Vide | Cons(Element, Ensemble) | Union(Ensemble, Ensemble)
 */
interface Ensemble4 {
	// Sélecteurs
	int taille();
	boolean estVide();
	boolean estCons();
	boolean estUnion();
	// Destructeurs
	int element();
	Ensemble4 reste();
	default Iterateur4 iterateur(){
		return new Iterateur4(this);
	}
	Ensemble4 gauche();
	Ensemble4 droit();
	// Constructeurs
	default Ensemble4 vide(){
		return Vide4.SINGLETON; // Exemple d'une méthode par défaut
	}
	Ensemble4 cons(int n, Ensemble4 ens);
	Ensemble4 union(Ensemble4 ens);
	// Visiteur
	default <T> Visiteur4<T> accueillir(Visiteur4<T> v){
		if(this.estVide()){
			return v.visiterVide(this);
		}
		return v.visiterCons(this);
	}
}
/*
 * Visiteur générique (le paramètre T désigne le type de ce qui est calculé)
 * Le visiteur visite le composite comme un itérateur.
 */
interface Visiteur4<T>{
	T resultat();
	Visiteur4<T> visiterVide(Ensemble4 ens);
	Visiteur4<T> visiterCons(Ensemble4 ens);
}

class CalculCardinal implements Visiteur4<Integer> {

	private int card;

	public CalculCardinal(int card) {
		this.card = card;
	}

	@Override
	public Integer resultat() {
		return this.card;
	}

	@Override
	public Visiteur4<Integer> visiterVide(Ensemble4 ens) {
		return this;
	}

	@Override
	public Visiteur4<Integer> visiterCons(Ensemble4 ens) {
		int r = ens.reste().accueillir(this).resultat();
		return new CalculCardinal(r + 1);
	}
	
}

class Representation implements Visiteur4<String>{

	private String rep;

	public Representation(String rep) {
		this.rep = rep;
	}

	@Override
	public String resultat() {
		return this.rep;
	}

	@Override
	public Visiteur4<String> visiterVide(Ensemble4 ens) {
		return this;
	}

	@Override
	public Visiteur4<String> visiterCons(Ensemble4 ens) {
		Ensemble4 reste = ens.reste();
		int tete = ens.element();
		if(reste.estVide()){
			return new Representation(Integer.toString(tete));
		}
		String r = reste.accueillir(this).resultat();
		return new Representation(tete + ", " + r);
	}
	
}

/*
 * Les itérateurs sont supposés mutables, conforméménet à l'interface Java.
 */
class Iterateur4 {
	private Ensemble4 reste;
	private int element;
	
	public Iterateur4(Ensemble4 ens){
		decomposer(ens);
	}
	private void decomposer(Ensemble4 ens){
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
	public Ensemble4 reste(){
		if (reste == null)
			throw new UnsupportedOperationException();
		return this.reste;
	}
}


enum Vide4 implements Ensemble4 {
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
	public Ensemble4 reste() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Ensemble4 gauche() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Ensemble4 droit() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Ensemble4 cons(int n, Ensemble4 ens) {
		return new Cons4(n, ens);
	}
	@Override
	public Ensemble4 union(Ensemble4 ens) {
		return new Union4(this, ens);
	}

}

class Cons4 implements Ensemble4 {

	private int element;
	private Ensemble4 reste;
	private int taille;
	
	public Cons4(int i, Ensemble4 ens) {
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
	public Ensemble4 reste() {
		return this.reste;
	}

	@Override
	public Ensemble4 gauche() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Ensemble4 droit() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Ensemble4 cons(int n, Ensemble4 ens) {
		return new Cons4(n, ens);
	}

	
	@Override
	public Ensemble4 union(Ensemble4 ens) {
		return new Union4(this, ens);
	}
}

class Union4 implements Ensemble4 {

	private Ensemble4 gauche;
	private Ensemble4 droit;
	private int taille;
	
	public Union4(Ensemble4 g, Ensemble4 d) {
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
		Iterateur4 i = this.iterateur();
		return i.suivant();
	}
	
	@Override
	public Ensemble4 reste() {
		Iterateur4 i = this.iterateur();
		return i.reste();
	}

	@Override
	public Ensemble4 gauche() {
		return this.gauche;
	}

	@Override
	public Ensemble4 droit() {
		return this.droit;
	}

	@Override
	public Ensemble4 cons(int n, Ensemble4 ens) {
		return new Cons4(n, ens);
	}
	
	@Override
	public Ensemble4 union(Ensemble4 ens) {
		return new Union4(this, ens);
	}

}


public class EnsemblesIterablesAvecIterateurEtVisiteur {

	public static void main(String[] args) {
		Ensemble4 a = new Cons4(1, Vide4.SINGLETON);
		Ensemble4 b = Vide4.SINGLETON;
		for(int i = 0; i < 10000000; i++){
			b = b.union(a);
		}
		int s = 0;
		while(!b.estVide()){
			s = s + b.element();
			b = b.reste(); 
		}
		System.out.println(s);
		b = Vide4.SINGLETON;
		for(int i = 0; i < 100; i++){
			b = b.union(new Cons4(i, Vide4.SINGLETON));
		}
		Representation r = new Representation("");
		System.out.println("Ens 0, ..., 99 : (" + b.accueillir(r).resultat() + ")");
		CalculCardinal c = new CalculCardinal(0);
		System.out.println("Taile ens (100) : " + b.accueillir(c).resultat());
	}

}
