package tp1;

public interface FabriqueRationnels<T> {
    T creer(@Modifiable Z numerateur, @Modifiable Z denominateur); // Crée le rationnel "numerateur"/"denominateur"
    T creer(@Modifiable Reel rationnel); // // Crée le rationnel de valeur réelle "rationnel"
}
