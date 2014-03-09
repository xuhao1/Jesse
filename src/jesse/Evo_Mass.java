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
class Evo_Ann extends ann
{

    public void Varition() 
    {
    	//TODO finish
    }

    public void Cross(Evo_Individual a) 
    {
    	//TODO finish
    }

}

public class Evo_Mass extends Mass 
			implements ControlBody,Evo_Individual
{
	Motor mo1;
	Evo_Ann brain;
	double P;
   	Vec SumForce()
   	{
   		Vec res;
   		res.z=mo.Force();
   	}
   	void Control()
   	{

   	}
   	Evo_Mass(double m)
   	{
   		super(m);
   	}

    @Override
    public void Varition() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Cross(Evo_Individual a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double Appra() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String report() {
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
    	Evo_Mass res=new Evo_Mass();
    }
}
