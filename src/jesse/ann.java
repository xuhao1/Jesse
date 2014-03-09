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

import Jama.Matrix;

class ann
{
	Matrix hide,output,input2hide,hide2output;

	public ann(int input_num,int hide_num,int output_num)
	{
		hide = 	new Matrix(1,hide_num);
		output 	= new Matrix(1,output_num);
		input2hide =new Matrix(input_num,hide_num);
		hide2output=new Matrix(hide_num,output_num);
		RandomLize();
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
				_array[i][j]=Math.random()*0.1-0.05;
			}
	}
	public Matrix CalOut(Matrix input)
	{
		hide=Sigmoid(input.times(input2hide));
		output=Sigmoid(hide.times(hide2output));	
		return output;
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
		ann test=new ann(2,4,2);
		double[][] input={{3,2}};
		Matrix res=test.CalOut(new Matrix(input));
		System.out.print(PrintMat(res));

	}
}