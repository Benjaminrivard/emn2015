package session1.demo1;

public class NatDecimal implements Nat {

	private String chiffres;

	public NatDecimal(String chiffres) {
		super();
		char min = Character.forDigit(0, 10);
		char max = Character.forDigit(9, 10);
		for(int i = 0; i < chiffres.length(); i++){
			char c = chiffres.charAt(i);
			if(c < min)	throw new IllegalArgumentException();
			if(c > max) throw new IllegalArgumentException();
		}
		this.chiffres = chiffres;
	}
	
	/*
	 * alternative à des fabriques séparées : des fabriques comme méthodes.
	 */
	
	@Override
	public Nat creerNatAvecChiffres(String chiffres) {
		return new NatDecimal(chiffres);
	}

	@Override
	public Nat creerNatAvecInt(int n) {
		return new NatDecimal(Integer.toString(n));
	}
	
	public int getChiffre(int i) {
		if(i >= this.taille()){
			return 0;
		}
		return Character.getNumericValue(chiffres.charAt(chiffres.length() - 1 - i));
	}

	public int taille() {
		return this.chiffres.length();
	}

	@Override
	public int val() {
		return Integer.parseInt(this.chiffres);
	}
	
	public Nat somme(Nat n){
		int t = this.taille() < n.taille() ? n.taille() : this.taille();
		int retenue = 0;
		String res = "";
		for(int i = 0; i < t; i++){
			int c = this.getChiffre(i) + n.getChiffre(i) + retenue;
			if(c > 9){
				c = c - 10;
				retenue = 1;
			}else{
				retenue = 0;
			}
			res = c + res;
		}
		if(retenue == 1){
			res = "1" + res;
		}
		return this.creerNatAvecChiffres(res);
	}

	public String toString(){
		return this.chiffres;
	}

}
