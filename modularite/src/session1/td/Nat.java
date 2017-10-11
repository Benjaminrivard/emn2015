package session1.td;

import hierarchie.SemiAnneauUnitaireEuclidien;

public interface Nat extends FabriqueNaturels<Nat>, SemiAnneauUnitaireEuclidien<Nat> {
	/*
	 * Sélecteur pour la définition par récurrence.
	 */
	boolean estNul();
	/*
	 * Décomposition d'un successeur.
	 */
	Nat predecesseur();
	/*
	 * Unités en 0, dizaines en un, etc.
	 */
 	int chiffre(int i);
	/*
	 * Nombre de chiffres (aucun 0 superflu en tête). 
	 */
	int taille();

	/*
	 * Représentation par un int.
	 */
	int val();
}
