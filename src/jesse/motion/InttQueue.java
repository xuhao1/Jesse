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

class node
{
	node next;
	node pre;
	double value=225;
}
class InttQueue
{
	int maxn=2;
	int n=0;
	node top,last;
	double[][] Intts=
	{
		{0,0,0,0,0,0},
		{1,     0,0,0,0,0},
		{(double) 1/2,(double) 1/2,  0,0,0,0},
		{(double)1/6,(double) 4/6, (double)1/6,   0,0,0},
		{(double)1/8,(double) 3/8,(double) 3/8,(double)1/8,     0,0}
	};
	InttQueue()
		{
			top=new node();
			top.pre=null;
		}
	void add(double f)
	{
		
		node tem;
		node t=new node();
		t.value=f;
		if(n==0)	
		{
			top.next=t;
			last=t;
			last.pre=top;
		}
		else
		{
			t.next=top.next;
			top.next=t;
			t.next.pre=t;
		}
		n++;
		if(n>maxn)
		{
			
			tem=last;
			last=last.pre;
			tem.next=tem;
			n=maxn;
		}
		
	}
	double Intt()
	{
		
		if(n==0)
			return 0;
		if(n==1)
			{
			return top.next.value;
			}
		if(n==2)
		{
			return ((top.next.value)+top.next.next.value)/2;
		}
		int i=0;
		node tem;tem=top;
		double sum=0;
		for(i=0;i<n;i++)
		{
			tem=tem.next;
			sum+=tem.value*Intts[n][i];
		}
	return sum;
	}
	void display()
	{
		node tem;
		int i;
		tem=top;
		System.out.format("%f %f\n",top.next.value,top.next.next.value);	
	}
}
