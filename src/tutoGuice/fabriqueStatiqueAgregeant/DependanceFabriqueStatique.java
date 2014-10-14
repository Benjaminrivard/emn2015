package tutoGuice.fabriqueStatiqueAgregeant;

interface I {}

class B implements I { }

class C implements I {}

class A {
	private I x;
	public A(){
		this.x = new B();
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

interface FabriqueA {
	A creer();
	A creer(I x);
}

class FabriquerA {
	public static FabriqueA uneConfig = new FabriqueA() {
		public A creer(){ A r = new A(); return r; }
		public A creer(I x){ A r = new A(x); return r; }		
	};
}



public class DependanceFabriqueStatique {

	public static void main(String[] args) {
		/*
		 * Changement de classe d'implémentation au niveau de la classe : OUI
		 */
		// Préambule
		FabriquerA.uneConfig = new FabriqueA() {
			public A creer(){ A r = new A(); r.setX(new C()); return r; }
			public A creer(I x){ A r = new A(x); return r; }
		}; 
		// Solution 1
		A a = FabriquerA.uneConfig.creer();
		System.out.println(a);
		
		// Solution 2
		a = FabriquerA.uneConfig.creer(new C()); // A ajouter systématiquement
		System.out.println(a);

		/*
		 * Changement au niveau de l'instance : OUI
		 */
		a.setX(new B());
		System.out.println(a);
	}

}
