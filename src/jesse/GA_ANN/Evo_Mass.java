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
import jesse.GA.*;
import jesse.*;
import jesse.motion.*;
import jesse.control.*;

public class Evo_Mass extends Mass 
			implements ControlBody,Evo_Individual
{
	Motor mo1;
	Evo_Ann brain;
	double P;
 
   	protected Vec SumForce()
   	{
      //TODO 完成三轴控制
   		Vec res=new Vec();
   		res.z=mo1.Force();
   		return res;
   	}
   	public void Control()
   	{
      //TODO 完成三轴控制
      double[] inputtoAnn={rad.z,vel.z,Acc.z};
      mo1.setValue(brain.CalOut(inputtoAnn)[0]); 
   	}

   	public Evo_Mass(double m)
   	{
   		super(m);
      brain=new Evo_Ann(3,4,1);
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
      double dt=1e-4;
      double t=5;
      int flap=0;
      int i;
      Vec a;
      for(i=0;i<t/dt;i++)
      {
        this.simubydt(dt);
        a=this.vel;
        if(Math.abs(a.z)<=0.1)
          flap++;
        else
          flap=0;
        if(flap>10000)
          return (1/(i-10000)*dt);
      }
      return 0;
    }

    @Override
    public String toString() 
    {
      return String.format("mass :position:%f vel :%f : Acc %f",rad.z,vel.z,Acc.z);
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
      super.clone();
      Evo_Mass res=new Evo_Mass(this.m);
      res.mo1=this.mo1.clone();
      res.brain=this.brain.clone();
      return res;
    }
  }
