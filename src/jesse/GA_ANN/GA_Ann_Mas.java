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
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GA_Ann_Mas extends Gene_Algorithm
{
	DataVis dv;
	@Override
	public Evo_Individual mkIndividual()
	{
		return new Evo_Mass(1);
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

 class threads implements Runnable
 {
    int k;
    Evo_Mass best;
    public void run() 
    {
			//TODO 修改画图
      best.run(60); 
      System.out.println("Draw");
      for(int i=0;i<best.his_t.size();i++)
      {
        dv.addData(k,best.his_t.get(i),best.his_z.get(i));
      }
}
threads(int k,Evo_Mass best)
{
    try {
      this.best=(Evo_Mass)best.clone();
  }
  catch (CloneNotSupportedException ex) {
      Logger.getLogger(GA_Ann_Mas.class.getName()).log(Level.SEVERE, null, ex);
  }
  this.k=k;
}
}
@Override 
public void reportBest(Evo_Individual best,int j)
{
  System.out.format("Round :%d Best:%s\n",j,best);
  if(this.dv!=null)
  {
   ExecutorService exe=Executors.newFixedThreadPool(1);
   exe.execute(new threads(j,(Evo_Mass) best  ));
}
} 
public GA_Ann_Mas(int num)
{
  super(num);
}
	//TODO 输出大部分的运动趋势，查看神经网络的调整方向
public static void main(String[] args)throws Exception
{
  GA_Ann_Mas test0=new GA_Ann_Mas(100);
  test0.Develop(1);
}
public void DisplayDevelop(DataVis dv,int step)throws Exception
{
  System.out.println("欣欣最好了！");
  this.dv=dv;
  this.Develop(step); 
}
}
