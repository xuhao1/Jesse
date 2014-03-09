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
   		Vec res=new Vec();
   		res.z=mo1.Force();
   		return res;
   	}
   	public void Control()
   	{

   	}
   	Evo_Mass(double m)
   	{
   		super(m);
   	}

    @Override
    public void Varition() 
    {
    	brain.Varition();
    }

    @Override
    public void Cross(Evo_Individual a) 
    {
    	this.brain.Cross( ((Evo_Mass)a).brain);
    }

    @Override
    public double Appra() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String report() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public Evo_Individual clone() 
    {
    	Evo_Mass res=new Evo_Mass(this.m);
    	res.mo1=this.mo1.clone();
    	res.brain=this.brain.clone();
    	return res;
    }
}
