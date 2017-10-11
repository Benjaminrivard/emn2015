package session1.demo1;

public class Test {

	public static void main(String[] args) {
		tester(new NatParInt(0)); // Nat converti en FabriqueNat
		tester(new NatDecimal("0")); // Nat converti en FabriqueNat
		tester(new FabriqueNatParInt());
		tester(new FabriqueNatDecimal());
	}

	private static void tester(FabriqueNat fab) {
		Nat x = fab.creerNatAvecChiffres("75");
		System.out.println("150 ? " + x.somme(x));

		x = fab.creerNatAvecInt(2_000_000_000);
		try {
			System.out.println("4000000000 ? " + x.somme(x));
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}

	}
}
