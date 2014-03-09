/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jesse;

import java.util.*;

/*
 *
 * @author xuhao
 */
public class Population 
{
	Vector<Evo_Individual> data;
	int maxn;
	Evo_Individual RWS()
	{
		double m=0;
		double r=Math.random();
		int i=0;
		for(Evo_Individual a:data)
		{
			m=m+a.getP();
			if(r<=m)
				return a.clone();
		}
		//TODO:Throws Exception
		return null;
	}

	double [] getAppra()
	{
		//TODO:Parallel It
		double [] Appra=new double[data.size()];

		for (int i=0;i<data.size();i++)
		{
			Appra[i]=data.get(i).Appra();
		}

		return Appra;
	}

	void ConfP()
	{
		double[] Appra=getAppra();
		double sum=0;
		int i;
		double[] P=new double[Number()];
		for(i=0;i<Appra.length;i++)
			P[i]=Math.exp(5/Appra[i]);
		for(i=0;i<Appra.length;i++)
			sum+=P[i];
		for(i=0;i<Appra.length;i++)
		{
			P[i]=P[i]*(1/sum);
			data.get(i).setP(P[i]);
		}
	}

	Evo_Individual FindBest()
	{
	//TODO:Parallel FindBest
		Evo_Individual best=data.get(0);
		for (Evo_Individual a:data)
		{
			if (best.getP()<a.getP())
				best=a;
		}
		return best;
	}

	int Number()
	{
		return data.size();
	}

	void add(Evo_Individual a)
	{
		//TODO 将Add函数加入数量限制等
		if(data.size()<maxn)
			data.add(a);
	}

	Population(int maxn)	
	{
		//给出容量限制
		this.maxn=maxn;
	}

	Population()
	{
		data=new Vector<Evo_Individual>();
	}
}
