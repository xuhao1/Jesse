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
 import jesse.ann.*;
 import java.util.concurrent.*;
//进行双自由度的控制
 public class Evo_Mass extends Mass implements ControlBody,Evo_Individual
 {
     Motor mo1,mo2;
     Evo_Ann brain;
     double P;
     double Appra;
     public Vector<Double> his_t,his_z;
     int id;
     DataVis dv;
     public Evo_Mass(double m,int id,DataVis dv)
     {
         super(m);
         brain=new Evo_Ann(4,8,2);
         mo1=new Motor(11.1);
         mo2=new Motor(11.1);
         this.id=id;
         reset();
         this.dv=dv;
     }

     protected Evo_Mass(double m)
     {
         super(m);
         reset();
     }

     protected Vec SumForce()
     {
         //TODO 完成三轴控制
         Vec res=new Vec();
         res.z=mo1.Force()-9.8*m;
         res.y=mo2.Force();
         return res;
     }

     public void Control()
     {
         //TODO 完成三轴控制
         double[] inputtoAnn={rad.z,vel.z,rad.y,vel.y};//,Acc.z};
         double[] output=brain.CalOut(inputtoAnn);
         mo1.setValue(output[0]);
         mo2.setValue(output[1]);
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
         reset();
         double dt=1e-3;
         //this.rad.z=Math.random()*10-5;
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
     void Wind()
     {
     	Vec RandomForce=new Vec(0,0,0);
     	RandomForce.z=Math.random()*0.1-0.05;
     	RandomForce.y=Math.random()*0.1-0.05;

     	vel.z+=RandomForce.z/2;
     	vel.y+=RandomForce.y/2;
     }
     double AppraofMove(double mean,double sigma2)
     {
     	double App=0;
     	if(mean*mean<0.0001)
     		App+=10;
     	else
     	{
     		App+=0.001/(mean*mean);
     	}
     	if(sigma2<0.00001)
     		App+=10;
     	else
     	{
     		App+=0.0001/sigma2;
     	}
     	return App;
     }
     double [] z_his,y_his;
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
     	z_his=new double[new Double(t/dt).intValue()];
     	y_his=new double[new Double(t/dt).intValue()];
     	reset();
     	double Crime=0;
     	for(int i=0;i<t/dt;i++)
     	{
     		this.simubydt(dt);
     		a=this.rad;
     		Wind();
     		z_his[i]=a.z;
     		y_his[i]=a.y;
     		if(Math.abs(a.z)+Math.abs(a.y)>0.2)
     		{
     			Crime++;
     		}
     	}

     	double meanz=Mean(z_his);
     	double meany=Mean(y_his);
     	double sigma2z=Variance(z_his);
     	double sigma2y=Variance(y_his);

     	Appra=0;

     	Appra=AppraofMove(meanz,sigma2z)*AppraofMove(meany,sigma2y);
     	return Appra;
     }
     double mean,sigma2;
     @Override
     public double getAppra()
     {
     	return Appra;
     }
     @Override
     public String toString()
     {
     	return String.format("id :%dmass :P:%f ,Appra:%f mean:%f sigma2:%f",id,getP(),Appra,mean,sigma2);
     }

     class threads implements Runnable
     {
     	int k;
     	Evo_Mass father;
     	public void run()
     	{
     		int k=dv.getNext();

     		for(int i=0;i<father.z_his.length;i++)
     		{
     			double y=father.z_his[i]*father.z_his[i];
     			y+=father.y_his[i]*father.y_his[i];
     			dv.addData(k,i,Math.sqrt(y));
     		}
     	}
     	threads(Evo_Mass fa)
     	{
     		try{
     			father=(Evo_Mass)fa.clone();
     		}
     		catch(Exception ex)
     		{
     			System.out.println(ex);
     		}
     	}
     }
     @Override
     public void Report()
     {
         //System.out.format("Best:%s\n",this);
     	if(this.dv!=null)
     	{
     		ExecutorService exe=Executors.newFixedThreadPool(1);
     		exe.execute(new threads(this));
         }
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
         res.mo2=this.mo2.clone();
         res.z_his=this.z_his.clone();
         res.y_his=this.y_his.clone();
         res.brain=this.brain.clone();
         return res;
     }
 }
