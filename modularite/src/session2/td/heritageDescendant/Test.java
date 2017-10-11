package session2.td.heritageDescendant;

import session1.td.FabriqueNaturels;
import session1.td.Nat;

public class Test {

	private static void test(FabriqueNaturels<Nat> fabN) {
		System.out.println("Test de la classe " + fabN.getClass());
		Nat nA = fabN.creerZero();
		System.out.println("true ( 0 = 0) ? " + nA.equals(nA.zero()));
		nA = fabN.creerSuccesseur(nA);
		System.out.println("true ( 1 = 1) ? " + nA.equals(nA.un()));
		nA = fabN.creerNatAvecValeur(5);
		Nat nB = fabN.creerNatAvecValeur(3);
		System.out.println("8 (5 + 3) ? " + nA.somme(nB));
		System.out.println("15 (5 * 3) ? " + nA.produit(nB));
	}

	public static void main(String[] args) {
		test(new NatParInt(0));
		test(new Zero());
		test(new Succ(new Zero()));
	}
}
