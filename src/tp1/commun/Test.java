package tp1.commun;

import tp1.FabriqueNaturels;
import tp1.FabriqueRationnels;
import tp1.FabriqueReels;
import tp1.FabriqueRelatifs;
import tp1.Nat;
import tp1.Q;
import tp1.Reel;
import tp1.Z;
import tp1.agregationDelegation.FabriquerQ;
import tp1.agregationSimple.FabriquerReel;
import tp1.commun.Mutable;
import tp1.heritageAscendant.FabriquerNat;
import tp1.heritageDescendant.FabriquerZ;

import static tp1.agregationSimple.FabriquerReel.cloner;
import static tp1.heritageDescendant.FabriquerZ.cloner;

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

		FabriqueRelatifs<Z> fabZ = FabriquerZ.MUTABLE;
		System.out.println("**** Test de " + fabZ.getClass() + "  ****");
		test(fabN, fabZ);
		fabZ = FabriquerZ.IMMUTABLE;
		System.out.println("**** Test de " + fabZ.getClass() + "  ****");
		test(fabN, fabZ);

		FabriqueReels<Reel> fabR = FabriquerReel.MUTABLE;
		System.out.println("**** Test de " + fabR.getClass() + "  ****");
		test(fabR);
		fabR = FabriquerReel.IMMUTABLE;
		System.out.println("**** Test de " + fabR.getClass() + "  ****");
		test(fabR);
		
		FabriqueRationnels<Q> fabQ = FabriquerQ.EFFICACE_MUTABLE;
		System.out.println("**** Test de " + fabQ.getClass() + "  ****");
		test(FabriquerZ.MUTABLE, FabriquerReel.MUTABLE, fabQ);
		fabQ = FabriquerQ.SYMBOLIQUE_MUTABLE;
		System.out.println("**** Test de " + fabQ.getClass() + "  ****");
		test(FabriquerZ.MUTABLE, FabriquerReel.MUTABLE, fabQ);
		fabQ = FabriquerQ.EFFICACE_IMMUTABLE;
		System.out.println("**** Test de " + fabQ.getClass() + "  ****");
		test(FabriquerZ.IMMUTABLE, FabriquerReel.IMMUTABLE, fabQ);
		fabQ = FabriquerQ.SYMBOLIQUE_IMMUTABLE;
		System.out.println("**** Test de " + fabQ.getClass() + "  ****");
		test(FabriquerZ.IMMUTABLE, FabriquerReel.IMMUTABLE, fabQ);
		
		fabQ = tp1.heritageMultiple.FabriquerQ.EFFICACE_MUTABLE;
		System.out.println("**** Test de " + fabQ.getClass() + "  ****");
		test(FabriquerZ.MUTABLE, FabriquerReel.MUTABLE, fabQ);
		fabQ = tp1.heritageMultiple.FabriquerQ.SYMBOLIQUE_MUTABLE;
		System.out.println("**** Test de " + fabQ.getClass() + "  ****");
		test(FabriquerZ.MUTABLE, FabriquerReel.MUTABLE, fabQ);
		fabQ = tp1.heritageMultiple.FabriquerQ.EFFICACE_IMMUTABLE;
		System.out.println("**** Test de " + fabQ.getClass() + "  ****");
		test(FabriquerZ.IMMUTABLE, FabriquerReel.IMMUTABLE, fabQ);
		fabQ = tp1.heritageMultiple.FabriquerQ.SYMBOLIQUE_IMMUTABLE;
		System.out.println("**** Test de " + fabQ.getClass() + "  ****");
		test(FabriquerZ.IMMUTABLE, FabriquerReel.IMMUTABLE, fabQ);
	}

	private static void test(FabriqueNaturels<Nat> fabrique) {
		long time = threadBean.getCurrentThreadCpuTime();
		Nat x = null;
		Nat zero = fabrique.creer();
		System.out.println("0 ? " + zero);
		x = fabrique.creer();
		x = x.zero();
		System.out.println("true ? " + zero.equals(x));
		Nat un = fabrique.creer(zero);
		System.out.println("1 ? " + un);
		x = x.un();
		System.out.println("true ? " + un.equals(x));
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
			// Optimisation : x = FabriquerNat.mutable(x); (passage en mutable
			// pour les calculs intensifs)
		}
		for (int i = 0; i < 100000000; i++) {
			x = x.somme(cinq);
		}
		System.out.println((5 * 100000000) + " ? " + x);
		time = threadBean.getCurrentThreadCpuTime() - time;
		System.out.println("Test " + fabrique.getClass() + " : " + (time / MS));
	}

	private static void test(FabriqueNaturels<Nat> fabN,
			FabriqueRelatifs<Z> fabrique) {
		long time = threadBean.getCurrentThreadCpuTime();
		Z x = null;
		Z zero = fabrique.creer(0);
		System.out.println("0 | 0 - 0 ? " + zero);
		x = fabrique.creer(2);
		x = x.zero();
		System.out.println("0 = 0 ? " + zero.equals(x));
		zero = fabrique.creer(true, fabN.creer());
		System.out.println("0 | 0 - 0 ? " + zero);
		System.out.println("0 = 0 ? " + zero.equals(x));
		zero = fabrique.creer(fabN.creer(), fabN.creer());
		System.out.println("0 | 0 - 0 ? " + zero);
		System.out.println("0 = 0 ? " + zero.equals(x));

		Z un = fabrique.creer(1);
		System.out.println("1 | 1 - 0 ? " + un);
		x = x.un();
		System.out.println("1 = 1 ? " + un.equals(x));
		un = fabrique.creer(true, fabN.creer(1));
		System.out.println("1 | 1 - 0 ? " + un);
		System.out.println("1 = 1 ? " + un.equals(x));
		un = fabrique.creer(fabN.creer(1), fabN.creer());
		System.out.println("1 | 1 - 0 ? " + un);
		System.out.println("1 = 1 ? " + un.equals(x));

		Z moinsUn = fabrique.creer(-1);
		System.out.println("-1 | 0 - 1 ? " + moinsUn);
		x = x.oppose();
		System.out.println("-1 = -1 ? " + moinsUn.equals(x));
		moinsUn = fabrique.creer(false, fabN.creer(1));
		System.out.println("-1 | 0 - 1 ? " + moinsUn);
		System.out.println("-1 = -1 ? " + moinsUn.equals(x));
		System.out.println("-1 | 0 - 1 ? " + moinsUn);
		System.out.println("-1 = -1 ? " + moinsUn.equals(x));

		Z moinsCinq = fabrique.creer(-5);
		System.out.println("-5 | 0 - 5 ? " + moinsCinq);
		Z six = fabrique.creer(6);
		System.out.println("6 | 6 - 0 ? " + six);
		x = x.zero();
		x = x.somme(moinsCinq).somme(six);
		System.out.println("1 | 6 - 5 ? " + x);
		x = x.produit(x);
		System.out.println("1 | 61 - 60 ? " + x);
		x = x.produit(moinsCinq).produit(six);
		System.out.println("-30 | 0 - 30 ? " + x);
		System.out.println("-30 <= 0 ? " + x.estNegatif());
		System.out.println("-30 >= 0 ? " + x.estPositif());
		System.out.println("0 - 30 ? " + x.diminuende() + " - "
				+ x.diminuteur());
		x = x.zero();
		if (x instanceof Mutable) {
			System.out.println("Mutable : " + x + " ; Immutable : "
					+ FabriquerZ.immutable(x));
		} else {
			System.out.println("Immutable : " + x + " ; Mutable : "
					+ FabriquerZ.mutable(x));
			// Optimisation : x = FabriquerZ.mutable(x); (passage en mutable
			// pour les calculs intensifs)
		}
		for (int i = 0; i < 100000000; i++) {
			x = x.somme(moinsUn);
		}
		System.out.println((-1 * 100000000) + " ? " + x);
		time = threadBean.getCurrentThreadCpuTime() - time;
		System.out.println("Test " + fabrique.getClass() + " : " + (time / MS));

	}

	private static void test(FabriqueReels<Reel> fabrique) {
		long time = threadBean.getCurrentThreadCpuTime();
		Reel x = null;
		Reel demi = fabrique.creer(0.5);
		Reel moinsDemi = cloner(demi).oppose();
		Reel zero = cloner(demi).somme(moinsDemi);
		System.out.println("0.0 ? " + zero);
		x = fabrique.creer(0.0);
		x = x.zero();
		System.out.println("true ? " + zero.equals(x));
		Reel un = fabrique.creer(1.0);
		System.out.println("1.0 ? " + un);
		x = x.un();
		System.out.println("true ? " + un.equals(x));
		Reel cinqDemi = fabrique.creer(5.0 / 2.0);
		System.out.println("2.5 ? " + cinqDemi);
		x = fabrique.creer(cinqDemi.val());
		x = x.inverse();
		System.out.println("0.4 ? " + x);
		x = x.inverse();
		x = x.somme(cinqDemi);
		System.out.println("5.0 ? " + x);
		x = x.produit(cinqDemi);
		System.out.println("12.5 ? " + x);
		x = x.zero();
		if (x instanceof Mutable) {
			System.out.println("Mutable : " + x + " ; Immutable : "
					+ FabriquerReel.immutable(x));
		} else {
			System.out.println("Immutable : " + x + " ; Mutable : "
					+ FabriquerReel.mutable(x));
			// Optimisation :
			// x = FabriquerReel.mutable(x);
			// (passage en mutable pour les calculs intensifs)
		}
		for (int i = 0; i < 100000000; i++) {
			x = x.somme(cinqDemi);
		}
		System.out.println(((5.0 / 2.0) * 100000000) + " ? " + x);
		time = threadBean.getCurrentThreadCpuTime() - time;
		System.out.println("Test " + fabrique.getClass() + " : " + (time / MS));
	}

	private static void test(FabriqueRelatifs<Z> fabZ,
			FabriqueReels<Reel> fabR, FabriqueRationnels<Q> fabQ) {
		long time = threadBean.getCurrentThreadCpuTime();
		Q x = null;
		Z moinsUn = fabZ.creer(-1);
		Z moinsDeux = fabZ.creer(-2);
		Q unDemi = fabQ.creer(cloner(moinsUn), cloner(moinsDeux));
		System.out.println("-1/-2 ou 0.5 ? " + unDemi);
		Q deuxQ = fabQ.creer(cloner(moinsDeux), cloner(moinsUn));
		System.out.println("-2/-1 ou 2 ? " + deuxQ);
		x = fabQ.creer(cloner(deuxQ.quotient()));
		x = x.inverse();
		System.out.println("1/2 ou 0.5 ? " + x);
		System.out.println("1/2 = 1/2 ? " + unDemi.equals(x));
		x = x.somme(unDemi);
		System.out.println("1 ? " + x);
		x = x.somme(fabQ.creer(fabR.creer(-0.5)));
		x = x.produit(unDemi);
		System.out.println("1/4 ou 0.25 ? " + x);
		x = fabQ.creer(fabR.creer(0));
		if (x instanceof Mutable) {
			System.out.println("Mutable : " + x + " ; Immutable : "
					+ FabriquerQ.immutable(x));
		} else {
			System.out.println("Immutable : " + x + " ; Mutable : "
					+ FabriquerQ.mutable(x));
			// Optimisation : 
			//x = FabriquerQ.mutable(x); //(passage en mutable
			// pour les calculs intensifs)
		}
		long t = threadBean.getCurrentThreadCpuTime();
		for (int i = 0; i < 10000000; i++) {
			x = x.somme(deuxQ);
		}
		t = threadBean.getCurrentThreadCpuTime() - t;
		System.out.println("!!! Boucle sans partage : " + (t / MS));
		x = fabQ.creer(fabR.creer(0));
		t = threadBean.getCurrentThreadCpuTime();
		for (int i = 0; i < 10000000; i++) {
			x = x.somme(deuxQ);
		}
		t = threadBean.getCurrentThreadCpuTime() - t;
		System.out.println("!!! Boucle avec partage : " + (t / MS));
		System.out.println((2 * 10000000) + " ? " + x);
		time = threadBean.getCurrentThreadCpuTime() - time;
		System.out.println("Test " + fabQ.getClass() + " : " + (time / MS));

	}


	
}
