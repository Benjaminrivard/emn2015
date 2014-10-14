package tutoGuice.fabriqueStatiqueAgrege;

interface I {}

class B implements I { }

class C implements I {}

interface FabriqueI {
	I creer();
}

class FabriquerI {
	public static I uneImplem = new FabriqueI() {
		public I creer(){ return new B(); }
	}.creer();
}

class A {
	private I x;
	public A(){
		this.x = FabriquerI.uneImplem;
	}
	public A(I x){
		this.x = x;
	}
	public I getX(){
		return this.x;
	}
	public void setX(I x){
		this.x = x;
	}
	public String toString(){
		return "A utilisant un " + x.getClass();
	}
}

public class DependanceFabriqueStatique {

	public static void main(String[] args) {
		/*
		 * Changement de classe d'implémentation au niveau de la classe : OUI
		 */
		// Préambule
		FabriquerI.uneImplem = new FabriqueI() {
			public I creer(){ return new C(); }
		}.creer();
		// Solution 1
		A a = new A();
		System.out.println(a);
		
		// Solution 2
		a = new A(FabriquerI.uneImplem); // A ajouter systématiquement
		System.out.println(a);
		/*
		 * Changement au niveau de l'instance : OUI
		 */
		a.setX(new B());
		System.out.println(a);
	}

}
