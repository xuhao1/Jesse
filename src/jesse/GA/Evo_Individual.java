/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jesse.GA;

/**
 *
 * @author xuhao
 */
public interface Evo_Individual
{
	void Varition();
	void Cross(Evo_Individual a);
	double Appra();
    double getAppra();
	String toString();
	void Report();
	void setP(double P);
	double getP();
	Evo_Individual clone() throws CloneNotSupportedException ;	
}
