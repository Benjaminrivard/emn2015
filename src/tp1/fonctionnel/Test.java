package tp1.fonctionnel;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;


import static tp1.fonctionnel.ModuleNat.creer;
import static tp1.fonctionnel.ModuleNat.zero;
import static tp1.fonctionnel.ModuleNat.un;
import static tp1.fonctionnel.ModuleNat.somme;
import static tp1.fonctionnel.ModuleNat.produit;
import static tp1.fonctionnel.ModuleNat.egal;
import static tp1.fonctionnel.ModuleNat.representer;

import tp1.NombreNaturel;

public class Test {
	private static final ThreadMXBean threadBean = ManagementFactory
			.getThreadMXBean();
	private static final long MS = 1000000;

	public static void main(String[] args) {

		threadBean.setThreadCpuTimeEnabled(true);
		System.out.println("**** Test de " + IntPositif.class + "  ****");
		test();
	}

	private static void test() {
		long time = threadBean.getCurrentThreadCpuTime();
		NombreNaturel x = null;
		NombreNaturel zero = ModuleNat.creer();
		System.out.println("0 ? " + representer(zero));
		x = ModuleNat.creer();
		System.out.println("true ? " + egal(zero, zero()));
		NombreNaturel un = creer(zero);
		System.out.println("1 ? " + representer(un));
		x = creer();
		System.out.println("true ? " + egal(un, un()));
		NombreNaturel cinq = creer(5);
		System.out.println("5 ? " + representer(cinq));
		x = creer(cinq);
		System.out.println("6 ? " + representer(x));
		x = somme(x, cinq);
		System.out.println("11 ? " + representer(x));
		x = produit(x, cinq);
		System.out.println("55 ? " + representer(x));
		x = zero();
		for (int i = 0; i < 100000000; i++) {
			x = somme(x, cinq);
		}
		System.out.println((5 * 100000000) + " ? " + representer(x));
		time = threadBean.getCurrentThreadCpuTime() - time;
		System.out.println("Test " + x.getClass() + " : " + (time / MS));
	}

}
