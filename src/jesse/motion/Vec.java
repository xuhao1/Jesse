/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jesse.motion;

/**
 *
 * @author xuhao
 */
public class Vec
{
	public double x,y,z;
	public String toString()
	{
            //TODO FINISH STRING FORMAT
		System.out.print(x);
		System.out.print(" ");
		System.out.print(y);
		System.out.print(" ");
		System.out.println(z);
                return null;
	}
	public Vec()
	{
		x=0;
		y=0;
		z=0;
	}
	public Vec(Vec a)
	{
		x=a.x;
		y=a.y;
		z=a.z;
	}
	public Vec(double x0,double y0,double z0)
	{
		x=x0;
		y=y0;
		z=z0;
	}
	public void Plus(Vec a)
	{
		x+=a.x;
		y+=a.y;
		z+=a.z;
	}

	public void Plus(Vec a,double k)
	{
		x+=a.x*k;
		y+=a.y*k;
		z+=a.z*k;
	}
	public void Multi(Vec a,double k)
	{
		x=a.x*k;
		y=a.y*k;
		z=a.z*k;
	}
}
