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

public class ann
{
	protected Matrix hide,output,input2hide,hide2output;

	public ann(int input_num,int hide_num,int output_num)
	{
		hide = 	new Matrix(hide_num,1);
		output 	= new Matrix(output_num,1);
		input2hide =new Matrix(input_num,hide_num);
		hide2output=new Matrix(hide_num,output_num);
		RandomLize();
	}
	public ann(Matrix input2hide,Matrix hide2output)
	{
		//TODO test 转型
		this.input2hide=(Matrix) input2hide.clone();
		this.hide2output=(Matrix) hide2output.clone();
	}
	public void RandomLize()
	{
		RandomLize(input2hide);
		RandomLize(hide2output);
	}
	void RandomLize(Matrix a)
	{

		double[][] _array=a.getArray();
		for(int i=0;i<_array.length;i++)
			for(int j=0;j<_array[i].length;j++)
			{
				_array[i][j]=Math.random()*0.5-0.25;
			}
	}
	public Matrix CalOut(Matrix input)
	{
		
		System.out.println(PrintMat(input));
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
	static String PrintMat(Matrix a)
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
		System.out.println(testa.CalOut(test)[0]);
	}
}