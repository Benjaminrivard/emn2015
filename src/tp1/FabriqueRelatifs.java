package tp1;

public interface FabriqueRelatifs<T> {
	T creer(boolean signe, Nat abs); // Crée un entier relatif de signe et de
										// valeur absolue donnés

	T creer(Nat diminuende, Nat diminuteur); // Crée un entier relatif
												// correspondant
												// à la différence diminuende - diminuteur

	T creer(int val); // Crée un entier relatif valant val
	
	FabriqueNaturels<Nat> fabriqueNat(); // Renvoie une fabrique d'entiers naturels

}
