/*******************************************************************************
 * Copyright (c) 2008 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Dimitrios Kolovos - initial API and implementation
 ******************************************************************************/
package com.utwente.standalone;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.epsilon.eol.IEolExecutableModule;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.etl.EtlModule;
import com.utwente.standalone.EpsilonStandalone;

/**
 * This example demonstrates using the 
 * Epsilon Transformation Language, the M2M language
 * of Epsilon, in a stand-alone manner 
 * @author Dimitrios Kolovos
 */
public class EtlStandaloneExample extends EpsilonStandalone {

	String input = "";
	String output = "";	
	
	public static void main(String[] args) throws Exception {
		if (args.length > 1){
			new EtlStandaloneExample(args[0], args[1]).execute();
		} else {
			System.out.println("I assume you have learned so much from your mistakes that you decided to make one more, please add 2 arguments");
		}
	}
	
	@Override
	public IEolExecutableModule createModule() {
		return new EtlModule();
	}

	@Override
	public List<IModel> getModels() throws Exception {
		List<IModel> models = new ArrayList<IModel>();
		//models.add(createEmfModel("Source", "models/Tree.xmi", "models/Tree.ecore", true, false));
		//models.add(createEmfModel("Target", "models/Copy.xmi", "models/Tree.ecore", false, true));
		//models.add(createXmlModel("ADTool", "src/main/resources/model/AND2Domain2.xml"));
		models.add(createXmlModel("ADTool", input));
		models.add(createEmfModel("UATMM", output, "UATMM.ecore", false, true));
		return models;
	}

	@Override
	public String getSource() throws Exception {
		return "ADTool2UATMM.etl";
	}

	@Override
	public void postProcess() {
		
	}
	
	public EtlStandaloneExample (String input, String output) {
		this.input = input;
		this.output = output;
	}

}
