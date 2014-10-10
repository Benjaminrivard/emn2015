package cours1.exemples;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class StringVSStringBuilder {
	private static final ThreadMXBean threadBean = ManagementFactory
			.getThreadMXBean();
	private static final long MS = 1000000;
	
	public static void main(String[] args) {
		threadBean.setThreadCpuTimeEnabled(true);
		testString();
		testStringBuilder();
	}

	private static void testStringBuilder() {
		long time = threadBean.getCurrentThreadCpuTime();
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < 100000; i++){
			s = s.append('a');
		}
		System.out.println(s.charAt(99999));
		time = threadBean.getCurrentThreadCpuTime() - time;
		System.out.println("Test StringBuilder : " + (time / MS));
	}

	private static void testString() {
		long time = threadBean.getCurrentThreadCpuTime();
		String s = new String();
		for(int i = 0; i < 100000; i++){
			s = s + 'a';
		}
		System.out.println(s.charAt(99999));
		time = threadBean.getCurrentThreadCpuTime() - time;
		System.out.println("Test String : " + (time / MS));
	}

}
