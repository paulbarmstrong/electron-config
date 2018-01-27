import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

/**
	Author: Paul Armstrong

	Description:
		This script will evaluate the electron configuration of a given element.

		The reason why I made this script was not just to find the electron
		configurations (google can do that), but to better understand how to
		calculate the configurations by hand.

**/

public class ElectronConfig
{
	// Array of all elements of the periodic table.
	// The index of each element is that element's atomic number.
	static final String[] ELEMENTS = {"",
		"H","He",
		"Li","Be","B","C","N","O","F","Ne",
		"Na","Mg","Al","Si","P","S","Cl","Ar",
		"K","Ca","Sc","Ti","V","Cr","Mn","Fe","Co",
		"Ni","Cu","Zn","Ga","Ge","As","Se","Br","Kr",
		"Rb","Sr","Y","Zr","Nb","Mo","Tc","Ru","Rh",
		"Pd","Ag","Cd","In","Sn","Sb","Te","I","Xe",
		"Cs","Ba","La",
		"Ce","Pr","Nd","Pm","Sm","Eu","Gd","Tb","Dy","Ho","Er","Tm","Yb","Lu",
		"Hf","Ta","W","Re","Os","Ir","Pt","Au","Hg","Tl","Pb","Bi","Po","At","Rn",
		"Fr","Ra","Ac",
		"Th","Pa","U","Np","Pu","Am","Cm","Bk","Cf","Es","Fm","Md","No","Lr",
		"Rf","Db","Sg","Bh","Hs","Mt","Uun","Uuu","Uub"};
		
	// Array representing the configuration of the filling of orbital slots
	static final String[] CONFIG_LIST = 
	{	"1s",
		"2s",
		"2p","3s",
		"3p","4s",
		"3d","4p","5s",
		"4d","5p","6s",
		"4f","5d","6p","7s",
		"5f","6d","7p",
		"6f","7d",
		"7f"
	};
	
	// Corresponding values associated with the electron orbital slots in CONFIG_LIST
	static final int[] CONFIG_VALUES = {2,2,6,2,6,2,10,6,2,10,6,2,14,10,6,2,14,10,6,14,10,14};
	
	public static void main(String[] args)
	{
		// Perform the initial prompt for the atomic number
		int atomicNum = getAtomicNumber(JOptionPane.showInputDialog(null,"Atomic number or element: "));
		
		// Continue as long as there is input
		while (atomicNum > 0)
		{
			// Prepare the resultString before evaluating
			String resultString = "Element: "+ELEMENTS[atomicNum]+
					"\nElectrons: "+atomicNum+
					"\nLong form electron configuration: \n";
			
			// Iterate through the configurations to evaluate the electron
			// configuration and record the results as a part of the resultString
			int i=0;
			int atomicCount = atomicNum - CONFIG_VALUES[0];
			while (atomicCount > 0)
			{
				resultString += CONFIG_LIST[i]+"^"+CONFIG_VALUES[i]+" ";
				i++;
				atomicCount -= CONFIG_VALUES[i];
			}
			resultString += CONFIG_LIST[i]+"^"+(CONFIG_VALUES[i]+atomicCount);
			
			// Show the results while re-prompting in order to evaluate the next element
			atomicNum = getAtomicNumber(
				JOptionPane.showInputDialog(null,resultString+"\nAtomic number or element: ")
			);
		}
	}
	static int getAtomicNumber(String input)
	{
		if (input.length()==0)
			return 0;
		
		// Check to see if a valid number was input
		boolean good = true;
		for (int j=0; j<input.length(); j++)
		{
			if (57 < input.charAt(j) || input.charAt(j) < 48)
				good = false;
		}
		
		// If so, that is the atomic number
		if (good && Integer.parseInt(input)<ELEMENTS.length)
			return Integer.parseInt(input);
		
		// Lastly, if the input corresponds to an element string,
		// return that element's atomic number
		for (int j=1; j<ELEMENTS.length; j++)
		{
			if (ELEMENTS[j].equals(input))
				return j;
		}
		
		return 0;
	}
}