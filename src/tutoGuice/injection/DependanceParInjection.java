package tutoGuice.injection;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

interface I {}

class B implements I { }

class C implements I {}

class A {
	private I x;
	public A(){
		this.x = new B();
	}
	@Inject
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

class ModuleB extends AbstractModule {
	@Override
	protected void configure() {
		bind(I.class).to(B.class);
	}
}

class ModuleC extends AbstractModule {
	@Override
	protected void configure() {
		bind(I.class).to(C.class);
	}
}



public class DependanceParInjection {

	public static void main(String[] args) {
		/*
		 * Changement de classe d'implémentation au niveau de la classe : OUI
		 */
		// Préambule
		Injector injector = Guice.createInjector(new ModuleC());

		// Solution 1
		A a = injector.getInstance(A.class);
		System.out.println(a);
		
		// Solution 2
		a = injector.getInstance(A.class);
		System.out.println(a);

		/*
		 * Changement au niveau de l'instance : OUI
		 */
		a.setX(new B());
		System.out.println(a);
	}

	
}
