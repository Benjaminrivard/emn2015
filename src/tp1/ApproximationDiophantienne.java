package tp1;

/**
 * Implémentation de l'algorithme d'approximation diophantienne, qui repose sur
 * le développement en fraction continue.
 * 
 * Etant donné un quotient rationnel donné sous la forme d'un
 * <code>double</code>, l'algorithme calcule une fraction représentée par un
 * couple de <code>int</code> telle que son évaluation est égale au quotient
 * donné, à une approximation près. Les approximations sont de deux ordres.
 * <ul>
 * <li>Une première approximation concerne la représentation de
 * rationnels par des <code>double</code>.
 * <P>
 * Exemple : "0.3333" est approché par "3333/10000" alors que "0.3333333333" (10
 * décimales) est approché par "1/3".
 * <li>Une seconde approximation concerne la convergence du développement en
 * fraction continue.
 * <P>
 * Exemple : "Math.PI" est approché par "80143857/25510582", à 10 puissance -15
 * près.
 * </ul>
 * La partie publique de la classe forme une classe module : tous ses membres
 * publics (variables, fonctions, classe interne) sont statiques.
 * 
 * @author hgrall
 * 
 */

public class ApproximationDiophantienne {
    /**
     * Seuil en deça duquel un double n'est pas inversé pour éviter tout
     * dépassement.
     * 
     * Dans l'algorithme d'approximation diophantienne, on inverse un réel qu'on
     * approche par un entier. Compte tenu du caractère fini du type
     * <code>int</code>, ce réel ne peut être inférieur à un milliardième (le
     * plus grand entier de type <code>int</code> étant de l'ordre du milliard).
     */
    public static final double UN_SUR_MAX_INT = 1e-9;
    /**
     * Précision relative de l'approximation.
     * 
     * On a :
     * "Valeur Absolue(Approximation - Rationnel) < PRECISION . Rationnel".
     */
    public static final double PRECISION = 1e-15;

    private int partieEntiere;
    private int num;
    private int den;
    private double reste;

    private ApproximationDiophantienne(double d) {
	this.partieEntiere = (int) Math.floor(d);
	this.reste = d - this.partieEntiere;
	if (this.reste >= 1.0)
	    throw new ArithmeticException(d
					  + Messages.DOUBLE_SANS_PARTIE_ENTIERE);
    }

    /**
     * Classe interne statique représentant la fraction obtenue pour
     * l'approximation.
     * 
     * @author hgrall
     * 
     */
    public static class Fraction {
	/**
	 * numérateur de la fraction calculée
	 */
	public int numerateur;
	/**
	 * dénominateur de la fraction calculée
	 */
	public int denominateur;
    }

    private static ApproximationDiophantienne iteration0(double d) {
	ApproximationDiophantienne resultat = new ApproximationDiophantienne(d);
	resultat.num = resultat.partieEntiere;
	resultat.den = 1;
	return resultat;
    }

    private static ApproximationDiophantienne iteration1(
							 ApproximationDiophantienne iter0) {
	ApproximationDiophantienne resultat = new ApproximationDiophantienne(
									     1 / iter0.reste);
	resultat.num = iter0.partieEntiere * resultat.partieEntiere + 1;
	resultat.den = resultat.partieEntiere;
	return resultat;
    }

    private static ApproximationDiophantienne iteration2(
							 ApproximationDiophantienne iter0, ApproximationDiophantienne iter1) {
	ApproximationDiophantienne resultat = new ApproximationDiophantienne(
									     1 / iter1.reste);
	resultat.num = iter1.num * resultat.partieEntiere + iter0.num;
	resultat.den = iter1.den * resultat.partieEntiere + iter0.den;
	return resultat;
    }

    private static boolean testArret(ApproximationDiophantienne iter, double val) {
	return (iter.reste < UN_SUR_MAX_INT)
	    || (Math.abs((double) iter.num / (double) iter.den - val) < PRECISION
		* val);
    }

    private static Fraction developpementFractionContinue(double val) {
	boolean signe = (val >= 0);
	double d = signe ? val : -val;
	ApproximationDiophantienne i0 = iteration0(d);
	if (testArret(i0, val))
	    return fraction(signe, i0);
	ApproximationDiophantienne i1 = iteration1(i0);
	if (testArret(i1, val))
	    return fraction(signe, i1);
	ApproximationDiophantienne i = iteration2(i0, i1);
	while (!testArret(i, val)) {
	    i0 = i1;
	    i1 = i;
	    i = iteration2(i0, i1);
	}
	return fraction(signe, i);
    }

    private static Fraction fraction(boolean signe, ApproximationDiophantienne i) {
	Fraction resultat = new Fraction();
	int div = AlgorithmesArithmetiques.pgcd(i.num, i.den);
	resultat.numerateur = i.num / div;
	resultat.numerateur = signe ? resultat.numerateur
	    : -resultat.numerateur;
	resultat.denominateur = i.den / div;
	return resultat;
    }

    /**
     * Lance l'approximation du rationnel passé en argument.
     * 
     * @param d
     *            rationnel de type <code>double</code> à approcher
     * @return une fraction approchant le rationnel passé en argument
     */
    public static Fraction approximation(double d) {
	return developpementFractionContinue(d);
    }

    public static void main(String[] args) {
	double d = 1.0 / 3;
	System.out.println("d : " + d);
	ApproximationDiophantienne.Fraction f = approximation(d);

	System.out.println("num : " + f.numerateur);
	System.out.println("den : " + f.denominateur);
	System.out.println("d : " + d);
	System.out.println("w : " + (double) f.numerateur
			   / (double) f.denominateur);
	System.out.println("pgcd : "
			   + AlgorithmesArithmetiques.pgcd(f.denominateur, f.numerateur));
    }

}
