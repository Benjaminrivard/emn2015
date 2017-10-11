package session1.demo1;

public class NatParInt implements Nat {

	private int n; // Invariant : n >= 0

	public NatParInt(int n) {
		super();
		if(n < 0){
			throw new IllegalArgumentException();
		}
		this.n = n;
	}
	
	/*
	 * alternative à des fabriques séparées : des fabriques comme méthodes.
	 */
	@Override
	public Nat creerNatAvecChiffres(String chiffres) {
		return new NatParInt(Integer.parseInt(chiffres));
	}

	@Override
	public Nat creerNatAvecInt(int n) {
		return new NatParInt(n);
	}
	
	public int val(){
		return this.n;
	}
	
	public Nat somme(Nat p){
		return this.creerNatAvecInt(this.val() + p.val());
	}
	
	public String toString(){
		return Integer.toString(this.val());
	}

	@Override
	public int taille() {
		return this.toString().length();
	}

	@Override
	public int getChiffre(int i) {
		if(i >= this.taille()){
			return 0;
		}
		return Character.getNumericValue(this.toString().charAt(this.taille() - 1 - i));
	}

}
