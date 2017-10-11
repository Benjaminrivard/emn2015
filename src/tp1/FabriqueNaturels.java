package tp1;

public interface FabriqueNaturels<T> {
	T creer(int val); // Crée un entier valant val (supposé positif)

	T creer(); // Crée un entier valant zéro

	T creer(T predecesseur); // Crée un entier naturel égal au successeur de
								// pred

}
