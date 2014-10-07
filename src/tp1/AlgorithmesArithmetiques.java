package tp1;

public class AlgorithmesArithmetiques {
	/**
	 * Renvoie le plus grand diviseur commun (PGCD) des entiers passés en
	 * argument.
	 * 
	 * @param a
	 *            un premier entier
	 * @param b
	 *            un second entier
	 * @return le PGCD des arguments
	 */
	static public int pgcd(int a, int b) {
		if (b == 0)
			return a;
		int r = a % b;
		return pgcd(b, r);
	}

}
