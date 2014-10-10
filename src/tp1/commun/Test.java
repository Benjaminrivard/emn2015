package tp1.commun;

import tp1.FabriqueNaturels;
import tp1.Nat;
import tp1.commun.Mutable;
import tp1.heritageAscendant.FabriquerNat;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class Test {
	private static final ThreadMXBean threadBean = ManagementFactory
			.getThreadMXBean();
	private static final long MS = 1000000;

	public static void main(String[] args) {

		threadBean.setThreadCpuTimeEnabled(true);
		FabriqueNaturels<Nat> fabN = FabriquerNat.MUTABLE;
		System.out.println("**** Test de " + fabN.getClass() + "  ****");
		test(fabN);
		fabN = FabriquerNat.IMMUTABLE;
		System.out.println("**** Test de " + fabN.getClass() + "  ****");
		test(fabN);
	}

	private static void test(FabriqueNaturels<Nat> fabrique) {
		long time = threadBean.getCurrentThreadCpuTime();
		Nat x = null;
		Nat zero = fabrique.creer();
		System.out.println("0 ? " + zero);
		x = fabrique.creer();
		System.out.println("true ? " + zero.equals(x.zero()));
		Nat un = fabrique.creer(zero);
		System.out.println("1 ? " + un);
		x = fabrique.creer();
		System.out.println("true ? " + un.equals(x.un()));
		Nat cinq = fabrique.creer(5);
		System.out.println("5 ? " + cinq);
		x = fabrique.creer(cinq);
		System.out.println("6 ? " + x);
		x = x.somme(cinq);
		System.out.println("11 ? " + x);
		x = x.produit(cinq);
		System.out.println("55 ? " + x);
		x = x.zero();
		if (x instanceof Mutable) {
			System.out.println("Mutable : " + x + " ; Immutable : "
					+ FabriquerNat.immutable(x));
		} else {
			System.out.println("Immutable : " + x + " ; Mutable : "
					+ FabriquerNat.mutable(x));
			x = FabriquerNat.mutable(x);
		}
		for (int i = 0; i < 100000000; i++) {
			x = x.somme(cinq);
		}
		System.out.println((5 * 100000000) + " ? " + x);
		time = threadBean.getCurrentThreadCpuTime() - time;
		System.out.println("Test " + fabrique.getClass() + " : " + (time / MS));
	}

}
