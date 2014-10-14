package tutoGuice.constructeur;

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

public class DependanceImplem {

	public static void main(String[] args) {
		/*
		 *  Changement de classe d'implémentation au niveau de la classe : NON
		 */
		// Solution 1
		A a = new A();
		a.setX(new C()); // A ajouter systématiquement 
		System.out.println(a);
		// Solution 2
		a = new A(new C()); // A ajouter systématiquement
		System.out.println(a);
		/*
		 * Changement au niveau de l'instance : OUI
		 */
		a.setX(new B());
		System.out.println(a);
	}

}
