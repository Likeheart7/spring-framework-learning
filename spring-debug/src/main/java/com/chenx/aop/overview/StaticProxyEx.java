package com.chenx.aop.overview;

/**
 * 静态代理简单实现
 */
public class StaticProxyEx {
	public static void main(String[] args) {
		ProxyClass proxyClass = new ProxyClass(new Proxied());
		proxyClass.echo();
	}

	private static class ProxyClass implements Echo {

		private final Echo echo;

		public ProxyClass(Echo echo) {
			this.echo = echo;
		}

		@Override
		public void echo() {
			System.out.println("静态代理前置通知...");
			echo.echo();
			System.out.println("静态代理后置通知...");
		}
	}

	static class Proxied implements Echo{
		@Override
		public void echo() {
			System.out.println("Proxied echo message.");
		}
	}

	interface Echo {
		void echo();
	}
}
