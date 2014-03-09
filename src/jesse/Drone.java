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

import jesse.motion.*;
import jesse.control.*;

public class Drone extends RigidBody
{
	double dt0=0;
	int kn=0;
	double k[]=new double[30];
	double thr=0,ele=0,rud=0,ail=0;
	
	Vec M=new Vec(0,0,0);
	Vec Mc=new Vec(0,0,0);
	Vec Gravity;
	double raduis=0.375;
	Motor[] Motor=new Motor[4];
	public Vec SumForce()
	{
		int i;
		double fe=0;
		Vec res=new Vec(Gravity);
		for(i=1;i<=4;i++)
			fe+=Motor[i].Force();
		res.Plus(n,fe);	
		return res;
	}

	public Vec SumTorque()
	{
		Vec m1;
		m1=InRotation(M,theta,fai,psi);
		//Mc.x=(Motor[1].Force()-Motor[3].Force())*raduis;
		//Mc.y=(Motor[0].Force()-Motor[2].Force())*raduis;
		Mc.z=(Motor[0].beta+Motor[2].beta-Motor[3].beta-Motor[1].beta)*Motor[1].J;

		m1.x+=Mc.x;
		m1.y+=Mc.y;
		m1.z+=Mc.z;
		return m1;
	}
	void LoadControl()
	{
		try
		{
			//System.out.format("thr:%f\n",thr);
			Motor[0].setValue(thr - ail - rud);
			Motor[1].setValue(thr - ele + rud);
			Motor[2].setValue(thr + ail - rud);
			Motor[3].setValue(thr + ele + rud);
		}
		catch(NullPointerException e)
		{
			System.out.println(e);
		}
	}
	void Control()
	{
		thr=k[1]-k[2]*vel.z-k[3]*Acc.z;
		if(thr>11.1)
			thr=11.1;
		if(thr<0)
			thr=0;

	}

	Drone(double m0,double jx0,double jy0,double jz0,double[] k0,int kn0)
	{
		super(m0,jx0,jy0,jz0);
		Gravity=new Vec(0,0,-m0*9.8);
		for(int i=0;i<5;i++)
			Motor[i]=new Motor();
		k=k0;
		kn=kn0;
	}

	Drone(Drone a)
	{
		super(a.m,a.Jx,a.Jy,a.Jz);
		Gravity=new Vec(0,0,-a.m*9.8);
		int i;
		for(i=0;i<5;i++)
			Motor[i]=new Motor();
		kn=a.kn;
		for(i=0;i<=kn;i++)
			k[i]=a.k[i];
	}
	public void simubydt(double dt)
	{
		dt0=dt;
		LoadControl();
		super.simubydt(dt);
	}
}
