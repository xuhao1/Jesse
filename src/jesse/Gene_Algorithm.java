/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jesse;

/**
 *
 * @author xuhao
 */

public abstract class  Gene_Algorithm 
{

	int maxn;
	Population Pop0;

	public void Develop() throws Exception
	{
		int i,j,n;
		double m;
		Population Poptem;
		Evo_Individual t0,t1;
		double mini=10000;
		int mi=0;
		for(j=0;j<100;j++)
		{
			Pop0.ConfP();

			Evo_Individual best=Pop0.FindBest();
			System.out.println(best.report());
			Poptem =new Population(maxn);
			//reset sucessfully
			//make new pop
			n=-1;
			while(n<maxn)
			{
				t0=Pop0.RWS();
				t1=Pop0.RWS();

				m=Math.random();
				if(m<0.2)
					t0.Cross(t1);
				m=Math.random();
				if(m<0.3)
					t0.Varition();
				m=Math.random();
				if(m<0.3)
					t1.Varition();
				Poptem.add(t0);
				Poptem.add(t1);
			}
			Pop0=Poptem;
		}
	}
	public abstract Population mkPopluation();
	public Gene_Algorithm(Population Pop0)
	{
		this.maxn=Pop0.maxn;
	}
}
