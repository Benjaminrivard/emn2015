package tp1;

public interface FabriqueRationnels<T> {
    T creer(Z numerateur, Z denominateur); // Crée le rationnel "numerateur"/"denominateur"
    T creer(Reel rationnel); // // Crée le rationnel de valeur réelle "rationnel"
}
