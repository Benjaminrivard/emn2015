package cours3.exemples;

enum Axe {
	X, Y;
}

interface FigRectangle {
	double longueurX();
	double longueurY();
}

interface FabriqueRectangle<T> {
	T creer(double lx, double ly);
}

interface Rectangle extends FigRectangle, FabriqueRectangle<Rectangle> {
	Rectangle homothetie(double rapport);
	Rectangle dilatationContraction(Axe a, double rapport);
}

interface FigCarre {
	double longueur();
}

interface FabriqueCarre<T> {
	T creer(double l);
}

interface Carre extends FigCarre, FabriqueRectangle<Rectangle>, FabriqueCarre<Carre> {
	Carre homothetie(double rapport); // spécialisation
	Rectangle dilatationContraction(Axe a, double rapport);
}

// Possibilité d'hériter
interface CarreH extends Rectangle, FigCarre, FabriqueCarre<CarreH> {
	CarreH homothetie(double rapport); // spécialisation
}

// Test (statique, puisqu'on n'implémente pas les interfaces)
public class CarreRectangle {
	public static void main(String[] args) {
		FabriqueRectangle<Rectangle> fabR = null; // instance d'une classe implémentant Rectangle 
		FabriqueCarre<Carre> fabC = null; // instance d'une classe implémentant Carre
		Rectangle r = fabR.creer(2.0, 1.0);
		Rectangle hr = r.homothetie(3.0);
		Carre c = fabC.creer(4.0);
		hr = c.dilatationContraction(Axe.X, 6.0);
		c = c.homothetie(5.0);
		System.out.println(c.longueur());
		
		FabriqueCarre<CarreH> fabCH = null; // instance d'une classe implémentant Carre
		CarreH cH = fabCH.creer(4.0);
		cH = cH.homothetie(5.0);
		System.out.println("l : " + cH.longueur() + ", lx : " + cH.longueurX() + ", ly : " + cH.longueurY());
		
	}
}

