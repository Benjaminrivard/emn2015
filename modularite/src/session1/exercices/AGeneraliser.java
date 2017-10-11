package session1.exercices;

import hierarchie.SemiAnneauUnitaireEuclidien;

public class AGeneraliser {

	public static int f(int x, int y){
		int r = 0;
		if(y == 0)
			return r;
		while(true){
			if(y == 1){
				return r + x;
			}
			if((y%2) == 1){
				r = r + x;
			}
			y = y / 2;
			x = x + x;
		}
	}
	
	public static <T extends SemiAnneauUnitaireEuclidien<T>> T g(T x, T y){
		T zero = x.zero();
		T un = x.un();
		T deux = un.somme(un);
		T r = zero;
		if(y.equals(zero))
			return r;
		while(true){
			if(y.equals(un)){
				return r.somme(x);
			}
			if((y.modulo(deux)).equals(un)){
				r = r.somme(x);
			}
			y = y.div(deux);
			x = x.somme(x);
		}
	}
	
	public static void main(String[] args) {

	}

}
