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
public abstract class Mass
{
	double m=1;
	
	protected Vec rad=new Vec(0,0,0),vel=new Vec(0,0,0);
	Vec Acc=new Vec(0,0,0);
	protected Vec Force=new Vec(0,0,0);
	private InttQueue inttdvx=new InttQueue();
	private InttQueue inttdvy=new InttQueue();
	private InttQueue inttdvz=new InttQueue();
	private InttQueue inttdsx=new InttQueue();
	private InttQueue inttdsy=new InttQueue();
	private InttQueue inttdsz=new InttQueue();
	abstract Vec SumForce();
	void simubydt(double dt)
	{
		Force=SumForce();
		Vec dv=new Vec(0,0,0);
		Vec ds=new Vec(0,0,0);
		Acc.Multi(Force,1/m);
		dv.x=Acc.x*dt;
		inttdvx.add(dv.x);
		dv.x=inttdvx.Intt();
		vel.x+=dv.x;
		ds.x=vel.x*dt;
		inttdsx.add(ds.x);
		ds.x=inttdsx.Intt();
		rad.x+=ds.x;
		
		dv.y=Acc.y*dt;
		inttdvy.add(dv.y);
		dv.y=inttdvy.Intt();
		vel.y+=dv.y;
		ds.y=vel.y*dt;
		inttdsy.add(ds.y);
		ds.y=inttdsy.Intt();
		rad.y+=ds.y;
																			
		dv.z=Acc.z*dt;
		inttdvz.add(dv.z);
		dv.z=inttdvz.Intt();
		vel.z+=dv.z;
		ds.z=vel.z*dt;
		inttdsz.add(ds.z);
		ds.z=inttdsz.Intt();
		rad.z+=ds.z;
	
	}
	Mass(double m0)
	{
		m=m0;
	}
}
