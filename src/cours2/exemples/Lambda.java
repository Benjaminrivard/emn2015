package cours2.exemples;

import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

@FunctionalInterface
interface I {
	String f(int x);
}

class A {
	private int x;
	public void fermeture(int y){
		I c = z -> Integer.toString(y);
		// y = 4; // Impossible !
		c = z -> Integer.toString(this.x); // Attribut utilisable
		System.out.println(c.f(9));
		this.x = 4; // Effet : modification de la lambda-abstraction
		System.out.println(c.f(9));
	}
}

public class Lambda {
	
	public static void main(String[] args) {
		Function<Integer, Integer> id0 = x -> x;
		System.out.println(id0.apply(3));
		IntUnaryOperator id1 = x -> x;
		System.out.println(id1.applyAsInt(3));
		IntBinaryOperator plus = (x, y) -> x + y;
		plus = (int x, int y) -> x + y;
		System.out.println(plus.applyAsInt(3, 5));
		I c = Integer::toString;
		System.out.println(c.f(7));
		c = x -> Integer.toString(x);
		System.out.println(c.f(9));
		IntUnaryOperator id2 = x -> { 
			System.out.println("int : " + x);
			return x;
		};
		System.out.println(id2.applyAsInt(11));
		new A().fermeture(13);
	}

}
