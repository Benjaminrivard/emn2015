package session1.exercices;

class A {}

class B extends A {}

class C extends B {}

class X {
	void f(A a){
		System.out.println("X.f(A)");
	}
	void f(B b){
		System.out.println("X.f(B)");
	}
}

class Y extends X {
	void f(C c){
		System.out.println("Y.f(C)");
	}
}

public class ResolutionLiaison {
	public static void main(String[] args) {
		Y y = new Y();
		C c = new C();
		y.f(c);
		X x = y;
		x.f(c);;
	}
	
	
}
