/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jesse.GA;

/**
 *
 * @author xuhao
 */

public abstract class  Gene_Algorithm 
{

	protected int maxn;
	Population Pop0;
	protected Evo_Individual best;
	public void reportBest(Evo_Individual best,int j)
	{
		System.out.format("Round :%d Best:%s\n",j,best);
	}
	public void Develop(int step) throws Exception
	{
		System.out.println("Started Develop");
		int i,j,n;
		double m;
		Evo_Individual t0,t1;
		double mini=10000;
		int mi=0;
		for(j=0;j<step;j++)
		{
			System.out.format("Round:%d\n",j);
			Pop0.ConfP();
			System.out.println("I'm Here10");
			best=Pop0.FindBest();
			reportBest(best,j);
			//reset sucessfully
			//make new pop
			n=-1;

			Pop0.kill(maxn/3);
			while(Pop0.Number()<maxn)
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
				Pop0.add(t0);
				Pop0.add(t1);
			}
		}
	}

	public abstract Evo_Individual mkIndividual();

	void SetPopulation()
	{
		Pop0=new Population(maxn);
		Evo_Individual em0;
		for(int i=0;i<maxn;i++)
		{
			em0=mkIndividual();
			Pop0.add(em0);
		}
	}

	public Gene_Algorithm(int maxn)
	{
		this.maxn=maxn;
		SetPopulation();
	}
}