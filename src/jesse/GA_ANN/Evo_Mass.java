/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jesse.GA_ANN;

/**
 *
 * @author xuhao
 */
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import java.lang.*;

import jesse.GA.*;
import jesse.motion.*;
import jesse.control.*;

public class Evo_Mass extends Mass implements ControlBody,Evo_Individual
{
	Motor mo1;
	Evo_Ann brain;
	double P;
	double Appra;

	public Evo_Mass(double m)
	{
		super(m);
		brain=new Evo_Ann(3,4,1);
		mo1=new Motor(11.1);
		rad.z=5;
		int k=1;
		vel.z=0;
	}

	protected Vec SumForce()
	{
		//TODO 完成三轴控制
		Vec res=new Vec();
		res.z=mo1.Force()-9.8*m;
		return res;
	}

	public void Control()
	{
		//TODO 完成三轴控制
		double[] inputtoAnn={rad.z,vel.z,Acc.z};
		double[] output=brain.CalOut(inputtoAnn);
		mo1.setValue(output[0]); 
	}


	@Override
	public void Varition() 
	{
		brain.Varition();
	}

	@Override
	public void Cross(Evo_Individual a) 
	{
		try {
			this.brain.Cross( ((Evo_Mass)a).brain);
		} catch (Exception ex) {
			Logger.getLogger(Evo_Mass.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	@Override
	public void simubydt(double dt)
	{
		Control();
		super.simubydt(dt);
	}
	public void run(double t)
	{
		his_z=new Vector<Double>();
		his_t=new Vector<Double>();
		double dt=1e-3;
		this.rad.z=Math.random()*10-5;
		for(int i=0;i<t/dt;i++)
		{
			this.simubydt(dt);
			Vec a=this.rad;
			if (i%1000==0)
			{
				his_t.add(i*dt);
				his_z.add(a.z);
			}
		}

	}
  protected void reset()
  {
    rad=new Vec(0,0,0);
    vel=new Vec(0,0,0);
    Acc=new Vec(0,0,0);
  }
	public Vector<Double> his_t,his_z;
  private static double Variance(double [] z)
  {
    double res=0,mean=Mean(z);
    for(int i=0;i<z.length;i++)
    {
      res+=(z[i]-mean)*(z[i]-mean);
    }
    res/=z.length;
    return res;
  }
  private static double Mean(double[] z)
  {
    double res=0;
    for(int i=0;i<z.length;i++)
    {
      res+=z[i];
    }
    res/=z.length;
    return res;
  }
	@Override
	public double Appra() 
	{
		//TODO Fix the test Code
		//以及阙值逐步收缩的算法
		double dt=1e-2;
		double t=5;
		int flap=0,flapsum=0;
		Vec a;
		//int miniStep=new Double(1/dt).intValue();
		int maxStep=0;
    double square=0;
    double [] z_his=new double[new Double(t/dt).intValue()];
    reset();
		for(int i=0;i<t/dt;i++)
		{
			this.simubydt(dt);
			a=this.rad;
			Vec RandomForce=new Vec(0,0,0);
      z_his[i]=a.z;
		}
    double mean=Mean(z_his);
    double sigma2=Variance(z_his);

    Appra=1/(mean*mean);
    
		return Appra;
	}

	@Override
	public String toString() 
	{
		return String.format("mass :P:%f ,Appra:%f",getP(),Appra);
	}

	@Override
	public void setP(double P) 
	{
		this.P=P;
	}

	@Override
	public double getP() 
	{
		return this.P;
	}

	@Override
	public Evo_Individual clone() throws CloneNotSupportedException
	{
		//super.clone();
		Evo_Mass res=new Evo_Mass(this.m);
		res.P=this.P;
		res.Appra=this.Appra;
		res.mo1=this.mo1.clone();
		res.brain=this.brain.clone();
		return res;
	}
}
