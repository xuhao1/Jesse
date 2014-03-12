/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jesse.GA;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 *
 * @author xuhao
 */
class PopulationException extends Exception
{
	public PopulationException()
	{

	}
	public PopulationException(String ErrMsg)
	{
		super(ErrMsg);
	}
}
public class Population 
{
	Vector<Evo_Individual> data;
	Vector<Evo_Individual> temp;
	int maxn;
	Evo_Individual RWS()throws Exception
	{
		//TODO 细分RWS错误
		double m=0;
		double r=Math.random();
		int i=0;
		for(Evo_Individual a:data)
		{
			m=m+a.getP();
			if(r<=m)
				return a.clone();
		}
		throw new PopulationException(String.format("RWS Wrong ->m=%f",m));
	}
	static double AppraToP(double Appra)
	{
		return Appra;
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
	void sort(int l,int r)
	{
		int i=l,j=r;
		Evo_Individual k;
		try
		{
			k=data.get(l);
			while(i<j)
			{
				while(i<j&&data.get(j).getP()<=k.getP())
					j--;
				data.set(i,data.get(j) );
				while(i<j&&data.get(i).getP()>k.getP())
					i++;
				data.set(j,data.get(i));
			}
			data.set(i,k);

			if(i-l>1)
				sort(l,i-1);
			if(r-i>1)
				sort(i+1,r);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	void ConfP()
	{
		double[] Appra=getAppra();
		double sum=0;
		double[] P=new double[Number()];
		for(int i=0;i<Appra.length;i++)
			P[i]=AppraToP(Appra[i]);
		for(int i=0;i<Appra.length;i++)
			sum+=P[i];
		for(int i=0;i<Appra.length;i++)
		{
			P[i]=P[i]/sum;
			data.get(i).setP(P[i]);
		}
		sort(0,data.size()-1);
	}

	Evo_Individual FindBest()
	{
		try{
		if(data.size()==0)
			throw new Exception("the data is empty");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return data.get(0);
	}

	protected void kill(int num)
	{
		int maxrange=data.size()-1;
		for(int i=maxrange;i>maxrange-num;i--)
		{
			data.removeElementAt(i);
		}
	}
	int Number()
	{
		return data.size();
	}

	void addtoNext(Evo_Individual a)
	{
		temp.add(a);
	}

	void add(Evo_Individual a)
	{
		if(this.data.size()<maxn)
			data.add(a);
	}
	public void Merge()
	{
		kill(temp.size());
		for(int i=0;i<temp.size();i++)
			data.add(temp.get(i));
		temp.removeAllElements();
	}
	public boolean full(int ma)
	{
		if(temp.size()+data.size()>=ma)
			return true;
		return false;
	}

	public Population(int maxn)	
	{
		//给出容量限制
		this.maxn=maxn;
		temp=new Vector<Evo_Individual>();
		data=new Vector<Evo_Individual>();
	}

	public Population()
	{
		temp=new Vector<Evo_Individual>();
		data=new Vector<Evo_Individual>();
	}
}
