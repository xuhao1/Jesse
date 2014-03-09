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
class Motor
{
	double Vol=0;
		//14inch*7 m=0.026g J=1/12ml*l=2.74*10e-4
	double J=10;
	double beta=0;
	double MaxVol=22.2;
	double Force()
	{
		return(-0.00664235+1.20157*Vol+0.0732154*Vol*Vol);
	}
	void setValue(double s)
	{
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
	Motor clone()
	{
		Motor res=new Motor();
	}
	Motor()
	{

	}
	Motor(double max)
	{
		
	}
}