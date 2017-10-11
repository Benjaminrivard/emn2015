package tp1;

public interface DifferenceNat {
	int val(); // Convertit en un int

	boolean estPositif(); // Teste si l'entier relatif est positif

	boolean estNegatif(); // Teste si l'entier relatif est négatif

	Nat valAbsolue(); // Renvoie la valeur absolue de l'entier relatif

	Nat diminuende(); // Renvoie le diminuende associé à l'entier relatif

	Nat diminuteur(); // Renvoie le diminuteur associé à l'entier relatif
}
