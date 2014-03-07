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
public class Vec
{
	double x,y,z;
	void print()
	{
		System.out.print(x);
		System.out.print(" ");
		System.out.print(y);
		System.out.print(" ");
		System.out.println(z);
	}
	Vec()
	{
		x=0;
		y=0;
		z=0;
	}
	Vec(Vec a)
	{
		x=a.x;
		y=a.y;
		z=a.z;
	}
	Vec(double x0,double y0,double z0)
	{
		x=x0;
		y=y0;
		z=z0;
	}
	void Plus(Vec a)
	{
		x+=a.x;
		y+=a.y;
		z+=a.z;
	}

	void Plus(Vec a,double k)
	{
		x+=a.x*k;
		y+=a.y*k;
		z+=a.z*k;
	}
	void Multi(Vec a,double k)
	{
		x=a.x*k;
		y=a.y*k;
		z=a.z*k;
	}
}
