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
	default A creer(){
		return new A();
	}
	default A creer(I x){
		 A r = new A(x); 
		 return r;	
	}
}

class FabriquerA {
	public static FabriqueA uneConfig;
}



public class DependanceFabriqueStatique {

	public static void main(String[] args) {
		/*
		 * Changement de classe d'implémentation au niveau de la classe : OUI
		 */
		// Préambule
		@SuppressWarnings("unused")
		FabriqueA fabA_B = new FabriqueA() {};
		FabriqueA fabA_C = new FabriqueA() {
			public A creer(){ return this.creer(new C()); }
		}; 
		FabriquerA.uneConfig = fabA_C; 
		
		// Solution 1
		A a = FabriquerA.uneConfig.creer();
		System.out.println(a);
		
		// Solution 2 : inutile

		/*
		 * Changement au niveau de l'instance : OUI
		 */
		a.setX(new B());
		System.out.println(a);
	}

}
