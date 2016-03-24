package com.utwente.adt2upp.bo;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.epsilon.eol.IEolExecutableModule;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.etl.EtlModule;

import com.utwente.standalone.EpsilonStandalone;

public class Adt2atmmBO extends EpsilonStandalone {

	String input = "";
	String output = "";	
	
	@Override
	public IEolExecutableModule createModule() {
		return new EtlModule();
	}

	@Override
	public List<IModel> getModels() throws Exception {
		List<IModel> models = new ArrayList<IModel>();
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
	
	public Adt2atmmBO(String input, String output) {
		this.input = input;
		this.output = output;
	}
}
