package session1.demo1;

public class FabriqueNatDecimal implements FabriqueNat {

	@Override
	public Nat creerNatAvecChiffres(String chiffres) {
		return new NatDecimal(chiffres);
	}

	@Override
	public Nat creerNatAvecInt(int n) {
		return new NatDecimal(Integer.toString(n));
	}

}
