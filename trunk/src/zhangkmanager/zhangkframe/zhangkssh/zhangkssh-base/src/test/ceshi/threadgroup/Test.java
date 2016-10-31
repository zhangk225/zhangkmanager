package threadgroup;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String name = Thread.currentThread().getName();
		System.out.println("main" + name);
		ThreadGroup dd=new ThreadGroup("zhangk");
		Thread sessthread = new Thread(dd,new Runnable() {
			@Override
			public void run() {
				String ssname = Thread.currentThread().getThreadGroup()
						.getName();
				String yyname = Thread.currentThread().getName();
				System.out.println("sessthread:" + "groupname====" + ssname
						+ ";threadname===" + yyname);
				Thread subthread1 = new Thread(new Runnable() {
					@Override
					public void run() {
						String ssname = Thread.currentThread().getThreadGroup()
								.getName();
						String yyname = Thread.currentThread().getName();
						System.out.println("subthread1:" + "groupname====" + ssname
								+ ";threadname===" + yyname);
					}
				});
				Thread subthread2 = new Thread(new Runnable() {
					@Override
					public void run() {
						String ssname = Thread.currentThread().getThreadGroup()
								.getName();
						String yyname = Thread.currentThread().getName();
						System.out.println("subthread2:" + "groupname====" + ssname
								+ ";threadname===" + yyname);
					}

				});
				
				subthread1.start();
				subthread2.start();
				
			}

		});
		sessthread.start();

	}

}
