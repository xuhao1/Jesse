/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jesse.GA_ANN;
import jesse.ann.*;
import Jama.Matrix;
/**
 *
 * @author xuhao
 */
class Evo_Ann_Exception extends Exception
{
    public Evo_Ann_Exception()
    {

    }
    public Evo_Ann_Exception(String ErrMsg)
    {
        super(ErrMsg);
    }
}
public class Evo_Ann extends ann
{
    final static double Var_Amp=0.1;
    static void VaritionMatrix(Matrix a,double m)
    {
        double[][] array=a.getArray();
        for(int i=0;i<array.length;i++)
            for(int j=0;j<array[i].length;j++)
                {
                    if(Math.random()<m)
                        array[i][j]+=array[i][j]*Var_Amp*(Math.random()-0.5);
                }
    }
    void Varition() 
    {
    	VaritionMatrix(hide2output,0.02);
        VaritionMatrix(input2hide,0.02);
    }

    static void CrossMatrix(Matrix a,Matrix b,double m)throws Exception
    {
        double[][] arrA=a.getArray(),arrB=b.getArray();
        if(arrA.length!=arrB.length||arrA[1].length!=arrB[1].length)
            throw new Evo_Ann_Exception("Cross Matrix Not Pair");
        for(int i=0;i<arrA.length;i++)
            for(int j=0;j<arrA[i].length;j++)
            {
                if(Math.random()<m)
                {
                    double k=arrA[i][j];
                    arrA[i][j]=arrB[i][j];
                    arrB[i][j]=k;
                }
            }
    }

    void Cross(Evo_Ann a) throws Exception
    {
        CrossMatrix(this.input2hide  , a.input2hide  ,Var_Amp);
        CrossMatrix(this.hide2output , a.hide2output ,Var_Amp);
    }
    public Evo_Ann clone() throws CloneNotSupportedException
    {
        //super.clone();
    	return new Evo_Ann(input2hide,hide2output);
    }
    public Evo_Ann(Matrix input2hide,Matrix hide2output)
    {
        super(input2hide,hide2output);
        Randomlize(10);
    }
	Evo_Ann(int input_num,int hide_num,int output_num)
	{
		super(input_num,hide_num,output_num);
	}
}
 
