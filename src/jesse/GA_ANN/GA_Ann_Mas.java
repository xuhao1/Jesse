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

public class GA_Ann_Mas extends Gene_Algorithm
{
   @Override
   public Evo_Individual mkIndividual()
   {
      return new Evo_Mass(1);
   }

   public GA_Ann_Mas(int num)
   {
   	super(num);
   }
   //TODO 输出大部分的运动趋势，查看神经网络的调整方向
   public static void main(String[] args)throws Exception
   {
      System.out.println("欣欣最好了！");
      GA_Ann_Mas test0=new GA_Ann_Mas(1000);
      test0.Develop(10);
   }
}
