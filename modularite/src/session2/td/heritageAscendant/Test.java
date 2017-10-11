package session2.td.heritageAscendant;

import session1.td.FabriqueNaturels;
import session1.td.Nat;

public class Test {

	public static void main(String[] args) {
		FabriqueNaturels<Nat> fab = new ZeroCalculantSurInt();
		test(fab);
		fab = new SuccCalculantSurInt(new ZeroCalculantSurInt());
		test(fab);
		fab = new ZeroRec();
		test(fab);
		fab = new SuccRec(new ZeroRec());
		test(fab);
	}

	private static void test(FabriqueNaturels<Nat> fab) {
		/*
		 * - Créer l'entier zéro à partir de la fabrique et l'affecter à une
		 * variable *zero*. 
		 * - Afficher sa valeur. 
		 * - Tester l'égalité entre *zero* et *zero.zero()*. 
		 * - Créer l'entier un à partir de la fabrique et l'affecter à une variable *un*. 
		 * - Afficher sa valeur. 
		 * - Tester l'égalité entre *un* et *un.un()*. 
		 * - Créer l'entier cinq à partir de la fabrique et l'affecter à une variable *cinq*. 
		 * - Afficher sa valeur. 
		 * - Créer l'entier six à partir de la fabrique et l'affecter à une variable *six*. 
		 * - Calculer la somme de cinq et six et afficher le
		 * résultat. 
		 * - Calculer le produit de cinq et six et afficher le
		 * résultat.
		 */
		
		Nat zero = fab.creerZero();
		System.out.println("0 ? " + zero);
		System.out.println("true ? " + zero.equals(zero.zero()));
		Nat un = fab.creerSuccesseur(zero);
		System.out.println("1 ? " + un);
		System.out.println("true ? " + un.equals(un.un()));
		Nat cinq = fab.creerNatAvecValeur(5);
		System.out.println("5 ? " + cinq);
		Nat six = fab.creerNatAvecValeur(6);
		System.out.println("11 ? " + cinq.somme(six));
		System.out.println("30 ? " + cinq.produit(six));
	}

}
