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
    //TODO Randomlize the start
    super(m);
    brain=new Evo_Ann(3,4,1);
    mo1=new Motor(11.1);
    rad.z=5;
    vel.z=Math.random()*5;
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

@Override
public double Appra() 
{
  //TODO Fix the test Code
  //以及阙值逐步收缩的算法
  double dt=1e-4;
  double t=5;
  int flap=0;
  Vec a;
  int miniStep=new Double(0.1/dt).intValue();
  for(int i=0;i<t/dt;i++)
  {
    this.simubydt(dt);
    a=this.rad;
    if(Math.abs(a.z)<1)
      flap++;
    else
      flap=0;
    if(flap>miniStep)
    {
      Appra= 1/((i-miniStep+1)*dt) ;
      return Appra;
    }
  }
  //TODO 设计合理的不落入区间的评分体系
  //保持薪火相传
  return 0;
}

@Override
public String toString() 
{
  return String.format("mass :Appra:%f",Appra);
}
public void Graph()
{
  //TODO 直接画图,完成图形化调试接口
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
  res.mo1=this.mo1.clone();
  res.brain=this.brain.clone();
  return res;
}
}
