/**
 * Created by BORIS on 17.02.17.
 */
public class ThreadsDeadLock
  {
    public static class ThreadOne extends Thread
	  {
		Thread ThreadTwo;

	    public ThreadOne()
		  {
			System.out.println("ThreadOne created");
		  }

		@Override
		public void run()
		  {
			System.out.println("ThreadOne started");
			try
			  {
			    sleep(1000);
			  }
			catch (InterruptedException e)
			  {
			    e.printStackTrace();
			  }
			try
			  {
				System.out.println("ThreadOne waits when ThreadTwo finish...");
				ThreadTwo.join();
			  }
			catch (InterruptedException e)
			  {
			    e.printStackTrace();
			  }
			System.out.println("ThreadOne finish");

		  }

		public void setThreadTwo (Thread t)
		{
  			this.ThreadTwo = t;
		}
	  }

	public static class ThreadTwo extends Thread
	  {
		Thread ThreadOne;

	    public ThreadTwo()
		  {
			System.out.println("ThreadTwo created");
		  }

		@Override
		public void run()
		  {
			System.out.println("ThreadTwo started");
			try
			  {
			    sleep(1000);
			  }
			catch (InterruptedException e)
			  {
			    e.printStackTrace();
			  }
			try
			  {
				System.out.println("ThreadTwo waits when ThreadOne finish...");
				ThreadOne.join();
			  }
			catch (InterruptedException e)
			  {
			    e.printStackTrace();
			  }
			System.out.println("ThreadTwo finish");
		  }

		public void setThreadOne (Thread t)
		{
		  this.ThreadOne = t;
		}
	  }

	public static void main(String[] args)
	  {
	    ThreadOne one = new ThreadOne();
	    ThreadTwo two = new ThreadTwo();
		one.setThreadTwo(two);
		two.setThreadOne(one);
	    one.start();
	    two.start();
	  }
  }