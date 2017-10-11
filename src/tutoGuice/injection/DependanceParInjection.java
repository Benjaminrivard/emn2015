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

public class DependanceParInjection {

	public static void main(String[] args) {
		/*
		 * Changement de classe d'implémentation au niveau de la classe : OUI
		 */
		// Préambule
		AbstractModule moduleB = new AbstractModule(){
			@Override
			protected void configure() {
				bind(I.class).to(B.class);
			}
		};
		AbstractModule moduleC = new AbstractModule(){
			@Override
			protected void configure() {
				bind(I.class).to(C.class);
			}
		};
		
		Injector injectorC = Guice.createInjector(moduleC);
		Injector injectorB = Guice.createInjector(moduleB);

		// Solution 1
		A a = injectorC.getInstance(A.class);
		System.out.println(a);
		
		// Solution 2 : inutile

		/*
		 * Changement au niveau de l'instance : OUI
		 */
		a.setX(injectorB.getInstance(I.class));
		System.out.println(a);
	}

	
}
