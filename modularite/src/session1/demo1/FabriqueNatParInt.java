package session1.demo1;

public class FabriqueNatParInt implements FabriqueNat {

	@Override
	public Nat creerNatAvecChiffres(String chiffres) {
		return new NatParInt(Integer.parseInt(chiffres));
	}

	@Override
	public Nat creerNatAvecInt(int n) {
		return new NatParInt(n);
	}

}
