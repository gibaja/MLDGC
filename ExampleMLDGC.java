/*    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program; if not, write to the Free Software
 *    Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

package mulan.examples;

import java.util.logging.Level;
import java.util.logging.Logger;

import mulan.classifier.lazy.MLDGC;
import mulan.data.InvalidDataFormatException;
import mulan.data.MultiLabelInstances;
import mulan.evaluation.Evaluator;
import mulan.evaluation.MultipleEvaluation;

import weka.core.Utils;

public class ExampleMLDGC {

	public static void main(String[] args) {
	    try {
            // e.g. -arff data/emotions.arff
            String arffFilename = Utils.getOption("arff", args); 
            // e.g. -xml data/emotions.xml
            String xmlFilename = Utils.getOption("xml", args);

            System.out.println("Loading the dataset...");
            MultiLabelInstances dataset = new MultiLabelInstances(arffFilename, xmlFilename);
           
            MLDGC mldgc = new MLDGC();
            

            Evaluator eval = new Evaluator();
            MultipleEvaluation results;

            int numFolds = 10;
            results = eval.crossValidate(mldgc, dataset, numFolds);
            System.out.println(results);

            
        } catch (InvalidDataFormatException ex) {
        	
        	
            Logger.getLogger(CrossValidationExperiment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CrossValidationExperiment.class.getName()).log(Level.SEVERE, null, ex);
        }

	}

}
