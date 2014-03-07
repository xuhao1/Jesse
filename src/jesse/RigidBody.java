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
public abstract class RigidBody  extends Mass 
{
	double Jx,Jy,Jz;
	protected Vec Torque=new Vec(0,0,0);
	Vec n=new Vec(0,0,1);	
	Vec ome_rig=new Vec();//ome_rig is base on xyz on body
	Vec omega=new Vec();
	double theta=0,psi=0, fai=0;
	//psi quejiao
	//var yuande
	RigidBody(double m0,double jx,double jy,double jz)
	{
		super(m0);
		Jx=jx;
		Jy=jy;
		Jz=jz;
	}
	Vec Rotation(Vec r0,double theta,double fai,double psi)
	{
		Vec r1=new Vec();
		r1.x=r0.x*Math.cos(theta)*Math.cos(fai)+r0.y*Math.cos(theta)*Math.sin(fai)-r0.z*Math.sin(theta);
		r1.y=r0.x*(-Math.cos(psi)*Math.sin(fai)+Math.cos(fai)*Math.sin(theta)*Math.sin(psi))+
			r0.y*(Math.cos(fai)*Math.cos(psi)+Math.sin(theta)*Math.sin(fai)*Math.sin(psi))+
				r0.z*(Math.cos(theta)*Math.sin(psi));
		r1.z=r0.x*(Math.cos(fai)*Math.cos(psi)*Math.sin(theta)+Math.sin(psi)*Math.sin(fai))+
			r0.y*(Math.cos(psi)*Math.sin(theta)*Math.sin(fai)-Math.cos(fai)*Math.sin(psi))+
				r0.z*Math.cos(psi)*Math.cos(theta);
		return r1;
	}
	Vec InRotation(Vec r0,double theta,double fai,double psi)
	{
		double t;
		Vec r1=new Vec(r0);
		t=r1.x;r1.x=r1.z;r1.z=t;
		r1=Rotation(r1,theta,psi,fai);
		t=r1.x;r1.x=r1.z;r1.z=t;
		return r1;
	}
	double adjustangle(double a)
	{
		while(a>2*Math.PI)
			a-=2*Math.PI;
		while(a<0)
			a+=2*Math.PI;
		return(a);
	}
	abstract Vec SumTorque();
	InttQueue fthe=new InttQueue(),ffai=new InttQueue(),fpsi=new InttQueue();
	InttQueue fwx=new InttQueue(),fwy=new InttQueue(),fwz=new InttQueue();
	public void simubydt(double dt)
	{	
		super.simubydt(dt);
		
		Torque=SumTorque();

		double dwx,dwy,dwz;
		//Ouler Force Motion Equs
		dwx=(Torque.x-(Jz-Jy)*ome_rig.y*ome_rig.z)/Jx*dt;
		dwy=(Torque.y-(Jx-Jz)*ome_rig.z*ome_rig.x)/Jy*dt;
		dwz=(Torque.z-(Jy-Jx)*ome_rig.x*ome_rig.y)/Jz*dt;

		fwx.add(dwx);
		dwx=fwx.Intt();
		ome_rig.x+=dwx;

		fwy.add(dwy);
		dwy=fwy.Intt();
		ome_rig.y+=dwy;
		
		fwz.add(dwz);
		dwz=fwz.Intt();
		ome_rig.z+=dwz;	
		//Ouler Motion Equs;


		double a11,a12,a21,a22;
		double b11,b12,b21,b22;
		double deta;	
		double dtheta,dpsi,dfai;
		a11=Math.cos(psi);   a12=Math.cos(theta)*Math.sin(psi);
		a21=-Math.sin(psi);  a22=Math.cos(psi)*Math.cos(theta);
		deta=a11*a22-a12*a21;
		//b=a^-1
		b11=a22/deta;
		b12=-a12/deta;
		b21=-a21/deta;
		b22=a11/deta;
		
		//(dfai,dtheta)^T=b*(p,q)^T
		dtheta=(b11*ome_rig.y+b12*ome_rig.z)*dt;
		dfai=(b21*ome_rig.y+b22*ome_rig.z)*dt;
		dpsi=ome_rig.x*dt+dfai*Math.sin(theta);
		
		fthe.add(dtheta);
		dtheta=fthe.Intt();

		ffai.add(dfai);
		dfai=ffai.Intt();

		fpsi.add(dpsi);
		dpsi=fpsi.Intt();

		fai+=dfai;
		fai=adjustangle(fai);
		psi+=dpsi;
		psi=adjustangle(psi);
		theta+=dtheta;
		theta=adjustangle(theta);
		
		omega=Rotation(ome_rig,theta,fai,psi);
		n=Rotation(new Vec(0,0,1),theta,fai,psi);
	}
	public void print()
	{
		System.out.print("theta: ");
		System.out.println(theta);
		System.out.print("fai: ");
		System.out.println(fai);
		System.out.print("psi: ");
		System.out.println(psi);
		System.out.print("w: ");
		ome_rig.print();
	}
}

