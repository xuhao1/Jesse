/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jesse.control;

/**
 *
 * @author xuhao
 */
public class Motor
{
    //TODO:重做反力矩
	double Vol=0;
		//14inch*7 m=0.026g J=1/12ml*l=2.74*10e-4
	public double J=10;
	public double beta=0;
	double MaxVol=22.2;
	double dt0=0.1;
	public double Force()
	{
		return(-0.00664235+1.20157*Vol+0.0732154*Vol*Vol);
	}

	public void setValue(double s)
	{
		//Motor value in range(0,1)
		s*=MaxVol;
		double Vol0;
		Vol0=s;
		if(s>=MaxVol)
			Vol0=MaxVol;
		if(s<=0)
			Vol0=0;

		if(Math.abs(Vol0-Vol)>3.7*dt0)
		{
			if(Vol>Vol0)
				Vol0=Vol-3.7*dt0;
			else
				Vol0=Vol+3.7*dt0;
		}
		beta=(Vol0-Vol)*150*2*3.1415926535/dt0;
		Vol=Vol0;
	}

	public Motor clone() throws CloneNotSupportedException
	{
		//super.clone();
		Motor res=new Motor(MaxVol);
		return res;
	}
	//TODO: 重新测量电机参数
	public Motor()
	{
		MaxVol=22.2;
	}
	public Motor(double max)
	{
		MaxVol=max;	
	}
}