/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jesse.ann;

/**
 *
 * @author xuhao
 */

import Jama.Matrix;
//TODO 制定储存格式
public class ann
{
	//TODO make Exceptions 
	public Matrix hide,output,input2hide,hide2output;

	public ann(int input_num,int hide_num,int output_num)
	{
		hide = 	new Matrix(hide_num,1);
		output 	= new Matrix(output_num,1);
		input2hide =new Matrix(input_num,hide_num);
		hide2output=new Matrix(hide_num,output_num);
	}
	public ann(Matrix input2hide,Matrix hide2output)
	{
		//TODO test 转型
		this.input2hide=(Matrix) input2hide.clone();
		this.hide2output=(Matrix) hide2output.clone();
	}
	public void Randomlize(double randRatio)
	{
		Randomlize(input2hide,randRatio);
		Randomlize(hide2output,randRatio);
	}
	protected void Randomlize(Matrix a,double randRatio)
	{

		double[][] _array=a.getArray();
		for(int i=0;i<_array.length;i++)
			for(int j=0;j<_array[i].length;j++)
			{
				_array[i][j]=Math.random()*randRatio-randRatio/2;
			}
	}
	public Matrix CalOut(Matrix input)
	{
		
		hide=Sigmoid(input.times(input2hide));
		output=Sigmoid(hide.times(hide2output));	
		
		return output;
	}

	public double[] CalOut(double[] input)
	{
		//TODO test the Switch
		return CalOut(new Matrix(input,1)).getArray()[0];
	}

	static Matrix Sigmoid(Matrix a)
	{

		for (int i=0;i<a.getRowDimension() ;i++)
			for(int j=0;j<a.getColumnDimension();j++)
			a.getArray()[i][j]=Sigmoid(a.getArray()[i][j]);
		return a;
	}

	static double Sigmoid(double x)
	{
		return 1/(1+Math.exp(-x));	
	}
	public static String PrintMat(Matrix a)
	{
		String res="";
		for(int i=0;i<a.getRowDimension();i++)
		{
			for(int j=0;j<a.getColumnDimension();j++)
			{
				res+=String.format("%3f ",a.getArray()[i][j]);
			}
			res+="\n";
		}
		return res;
	}

	public static void main(String args[])
	{
		double[] test={3,4};
		ann testa=new ann(2,5,1);
		testa.Randomlize(10);
		System.out.println(PrintMat(testa.input2hide));
	}
}