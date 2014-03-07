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

public class Gene_Algorithm 
{

	double[] ConfP(double[] Appra)
	{
		double P[]=new double[maxn+1];
		double sum=0;
		int i;
		for(i=0;i<maxn;i++)
			P[i]=Math.exp(5/Appra[i]);
		for(i=0;i<maxn;i++)
			sum+=P[i];
		for(i=0;i<maxn;i++)
			P[i]=P[i]*(1/sum);
		return P;
	}

	int RWS(double[] P)
	{
		double m=0;
		double r=Math.random();
		int i=0;
		for(i=0;i<maxn;i++)
		{
			m=m+P[i];
			if(r<=m)
				return i;
		}
		return -1;
	}

	void Develop() throws Exception
	{
		int i,j,n,t0,t1;
		double m;
		plane Pop[]=new plane[maxn];
		plane Poptem[]=new plane[maxn];
		for(i=0;i<maxn;i++)
		{
			double[] k=new double[100];
			for(j=0;j<=3;j++)
				k[j]=Math.random()*3;
			Pop[i]=new plane(1,1,1,1,k,3);
			//Pop[i].vel.z=Math.random()*5-2.5;
		}
		double Appra[]=new double[maxn+1];
		double P[];
		double mini=10000;
		int mi=0;
		for(j=0;j<100;j++)
		{
			for(i=0;i<maxn;i++)
				Appra[i]=0;
			PopMaps PopM=new PopMaps(Pop,Appra,0,maxn-1);
			PopM.start();
			P=ConfP(Appra);
			for(i=0;i<maxn;i++)
			{
				//System.out.println(P[i]);
			}
			mini=100000;
			for(i=0;i<maxn;i++)
				if(mini>Appra[i])
				{
					mini=Appra[i];
					mi=i;
				}

			System.out.format("%d:%f %f %f %f\n",j,mini,Pop[mi].k[1],Pop[mi].k[2],Pop[mi].k[3]);
			//reset sucessfully
			//make new pop
			n=-1;
			Poptem=new plane[maxn];
			while(n<maxn)
			{
				t0=RWS(P);
				t1=RWS(P);
				//System.out.println(P[t0]);
				//System.out.println(Appra[t0]);
				plane p0=new plane(Pop[t0]);
				plane p1=new plane(Pop[t1]);
				m=Math.random();
				if(m<0.2)
					Cross(p0,p1);
				m=Math.random();
				if(m<0.3)
					Varition(p0);
				m=Math.random();
				if(m<0.3)
					Varition(p1);
				n++;
				if(n<maxn)
					Poptem[n]=p0;
				n++;
				if(n<maxn)
					Poptem[n]=p1;
			}
			Pop=Poptem;
		}
	}
}
