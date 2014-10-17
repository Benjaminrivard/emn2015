package tutoGuice.fabriquesStatiques;

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

interface FabriqueA {
	default A creer(){
		A r = new A(); 
		return r; 
	}
	default A creer(I x){
		A r = new A(x); 
		return r;
	}
}

class FabriquerA {
	public static FabriqueA config;
}

public class DependanceFabriquesStatiques {

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
		@SuppressWarnings("unused")
		FabriqueA fabA_Defaut = new FabriqueA() {};
		FabriqueA fabA_C = new FabriqueA() {
			public A creer(){ return this.creer(fabC.creer()); }
		}; 
		FabriquerA.config = fabA_C; // ou: = fabA_Defaut; 

		// Solution 1
		A a = FabriquerA.config.creer();
		System.out.println(a);
		
		// Solution 2 : inutile

		/*
		 * Changement au niveau de l'instance : OUI
		 */
		a.setX(fabB.creer());
		System.out.println(a);
	}

}
