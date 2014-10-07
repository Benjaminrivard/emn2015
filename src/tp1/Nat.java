package tp1;

public interface Nat extends NombreNaturel, SemiAnneau<Nat>, FabriqueNaturels<Nat>{
    Nat predecesseur(); // Donne le prédécesseur s'il existe (spécialisation)
    boolean equals(Object o); // Renvoie false
    //   si o n'est pas de type Nat,
    // teste l'égalité des entiers naturels sinon
    String toString(); // Affiche l'entier renvoyé par val()
}
