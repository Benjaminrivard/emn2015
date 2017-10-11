package tutoGuice.fabriqueStatiqueAgrege;

interface I {}

class B implements I { }

class C implements I {}

interface FabriqueI {
	I creer();
}

class FabriquerI {
	public static FabriqueI uneImplem;
}

class A {
	private I x;
	public A(){
		this.x = FabriquerI.uneImplem.creer();
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
		FabriqueI fabB = new FabriqueI() {
			public I creer(){ return new B(); }
		};
		FabriqueI fabC = new FabriqueI() {
			public I creer(){ return new C(); }
		};
		FabriquerI.uneImplem = fabC;
		// Solution 1
		A a = new A();
		System.out.println(a);
		
		// Solution 2 : inutile

		/*
		 * Changement au niveau de l'instance : OUI
		 */
		a.setX(fabB.creer());
		System.out.println(a);
	}

}
