package session1.td;

public class NatDecimal implements Nat {
	public static final FabriqueNaturels<Nat> FAB = new NatDecimal("");
	private static final Nat DIX = FAB.creerNatAvecRepresentation("10"); 
	
	private String chiffres; // au moins un chiffre - aucun 0 superflu en tête
	
	private NatDecimal(String rep) {
		this.chiffres = rep;
	}
	
	@Override
	public Nat creerNatAvecValeur(int x) {
		return new NatDecimal(Integer.toString(x));
	}

	private static void nettoyer(StringBuilder s){
		int debut = 0;
		int fin = 0;
		while((fin < s.length()) && Character.getNumericValue(s.charAt(fin)) == 0){
			fin++;
		}
		s.delete(debut, fin);
	}
	
	@Override
	public Nat creerNatAvecRepresentation(String repDEcimale) {
		StringBuilder repMutable = new StringBuilder(repDEcimale);
		nettoyer(repMutable);
		repDEcimale = new String(repMutable);
		if(repDEcimale.equals("")){
			repDEcimale = "0";
		}
		char min = Character.forDigit(0, 10);
		char max = Character.forDigit(9, 10);
		for(int i = 0; i < repDEcimale.length(); i++){
			char c = repDEcimale.charAt(i);
			if(c < min)	throw new IllegalArgumentException();
			if(c > max) throw new IllegalArgumentException();
		}
		return new NatDecimal(repDEcimale);
	}

	@Override
	public Nat creerZero() {
		return new NatDecimal("0");
	}

	@Override
	public Nat creerSuccesseur(Nat predecesseur) {
		// somme simplifiée : 1 + predecesseur
		int t = predecesseur.taille();
		StringBuilder rep = new StringBuilder();
		int retenue = 1;
		for(int i = 0; i < t; i++){
			int chiffre = predecesseur.chiffre(i) + retenue;
			if(chiffre > 9){
				chiffre = chiffre - 10;
				retenue = 1;
			}else{
				retenue = 0;
			}
			rep.append(Integer.toString(chiffre));
		}
		rep = retenue == 0 ? rep : rep.append(1);
		return new NatDecimal(rep.reverse().toString());
	}
	
	@Override
    public int chiffre(int i){
    	if(i < this.taille())
    		return Character.getNumericValue(chiffres.charAt(chiffres.length() -1 -i));
    	return 0;
    }
	@Override
    public int taille(){
    	return chiffres.length();
    }
	@Override
	public int val() {
		return Integer.parseInt(this.chiffres);
	}
	
	@Override
	public boolean estNul() {
		// Peut être simplifié du fait du nettoyage ("0" pour zéro)
		for(int i = 0; i < this.taille(); i++){
			if(this.chiffre(i) != 0){
				return false;
			}
		}
		return true;
	}

	@Override
	public Nat predecesseur() {
		if(this.estNul()){
			throw new UnsupportedOperationException();
		}
		int t = this.taille();
		StringBuilder rep = new StringBuilder();
		int retenue = -1;
		for(int i = 0; i < t; i++){
			int chiffre = this.chiffre(i) + retenue;
			if(chiffre == -1){
				chiffre = 9;
				retenue = -1;
			}else{
				retenue = 0;
			}
			rep.append(Integer.toString(chiffre));
		}
		return this.creerNatAvecRepresentation(rep.reverse().toString());
	}
	
	@Override
	public Nat somme(Nat x) {
		int t = this.taille() < x.taille() ? x.taille() : this.taille();
		StringBuilder rep = new StringBuilder();
		int retenue = 0;
		for(int i = 0; i < t; i++){
			int chiffre = this.chiffre(i) + x.chiffre(i) + retenue;
			if(chiffre > 9){
				chiffre = chiffre - 10;
				retenue = 1;
			}else{
				retenue = 0;
			}
			rep.append(Integer.toString(chiffre));
		}
		rep = retenue == 0 ? rep : rep.append(1);
		return new NatDecimal(rep.reverse().toString());
	}

	@Override
	public Nat zero() {
		return this.creerZero();
	}

	
	@Override
	public Nat produit(Nat x) {
		if(x.equals(DIX)){
			return this.creerNatAvecRepresentation(this.toString() + "0");
		}
		Nat res = zero();
		Nat index = zero();
		while(!index.equals(x)){
			res = res.somme(this);
			index = this.creerSuccesseur(index);
		}
		return res;
	}
	@Override
	public Nat un() {
		return this.creerNatAvecRepresentation("1");
	}
	@Override
	public Nat modulo(Nat x) {
		if(x.equals(DIX)){
			return this.creerNatAvecValeur(this.chiffre(0));
		}
		Nat courant = zero();
		Nat q = zero();
		Nat r = zero();
		while(!courant.equals(this)){
			r = this.creerSuccesseur(r);
			if(r.equals(x)){
				r = zero();
				q = this.creerSuccesseur(q);
			}
			courant = this.creerSuccesseur(courant);
		}
		return r;
	}
	@Override
	public Nat div(Nat x) {
		if(x.equals(DIX)){
			if(this.taille() == 1)
				return this.zero();
			return this.creerNatAvecRepresentation(this.toString().substring(0, this.taille() - 1));
		}
		Nat courant = zero();
		Nat q = zero();
		Nat r = zero();
		while(!courant.equals(this)){
			r = this.creerSuccesseur(r);
			if(r.equals(x)){
				r = zero();
				q = this.creerSuccesseur(q);
			}
			courant = this.creerSuccesseur(courant);
		}
		return q;
	}

	@Override
	public boolean equals(Object x){
		if(!(x instanceof Nat)) return false;
		Nat n = (Nat)x;
		return this.toString().equals(n.toString());
	}

    @Override
    public String toString() {
    	return this.chiffres;
    }

	
}
