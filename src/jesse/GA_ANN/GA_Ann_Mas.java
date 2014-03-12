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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;

public class GA_Ann_Mas extends Gene_Algorithm
{
	DataVis dv;
	int num=0;
	@Override
	public Evo_Individual mkIndividual()
	{
		num++;
		return new Evo_Mass(1,num,dv);
	}

	public double MeanAppra()
	{
		double res=0;
		for(int i=0;i<Pop0.data.size();i++)
		{
			res+=((Evo_Mass) Pop0.data.get(i)).Appra;
		}
		return res/Pop0.data.size();
	}

	public GA_Ann_Mas(int num,DataVis dv)
	{
		this.maxn=num;
		this.dv=dv;
		SetPopulation();
	}
	private GA_Ann_Mas()
	{

	}

	public void DisplayDevelop(int step)throws Exception
	{
		System.out.println("欣欣最好了！");
		this.Develop(step); 
	}
}
