package tp1;

public interface Z extends DifferenceNat, FabriqueRelatifs<Z>, Anneau<Z> {
    boolean equals(Object o); // Renvoie false
    //   si o n'est pas de type Z,
    // teste l'égalité des entiers relatifs sinon
    String toString(); // Représente l'entier relatif sous la forme d'un int

}
