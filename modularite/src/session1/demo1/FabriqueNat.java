package session1.demo1;

/*
 * Implémentations possibles
 * - par des fabriques séparées,
 * - des méthodes dans les classes NatDecimal et NatParInt
 */

public interface FabriqueNat {

	public Nat creerNatAvecChiffres(String chiffres);
	public Nat creerNatAvecInt(int n);
}
