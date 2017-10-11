package session1.td;

public interface FabriqueNaturels<T> {
	T creerNatAvecValeur(int val);
	T creerZero();
	T creerSuccesseur(T predecesseur);
	T creerNatAvecRepresentation(String repDecimale);
}
