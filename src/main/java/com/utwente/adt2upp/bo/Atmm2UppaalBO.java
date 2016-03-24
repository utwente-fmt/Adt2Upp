package com.utwente.adt2upp.bo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.epsilon.egl.EglTemplateFactory;
import org.eclipse.epsilon.egl.EglTemplateFactoryModuleAdapter;
import org.eclipse.epsilon.eol.IEolExecutableModule;
import org.eclipse.epsilon.eol.models.IModel;

import com.utwente.standalone.EpsilonStandalone;

public class Atmm2UppaalBO extends EpsilonStandalone {

	String input = "";
	String output = "";	
	

	@Override
	public List<IModel> getModels() throws Exception {
		List<IModel> models = new ArrayList<IModel>();
		models.add(createEmfModel("AT", input, "UATMM.ecore", true, true));
		return models;
	}

	public Atmm2UppaalBO(String input, String output) {
		this.input = input;
		this.output = output;
	}
	
	@Override
	public IEolExecutableModule createModule() {
		return new EglTemplateFactoryModuleAdapter(new EglTemplateFactory());
	}
	
	@Override
	public String getSource() throws Exception {
		return "predefined/ATMM2Uppaal.egl";
	}

	@Override
	public void postProcess() {
		PrintWriter writer;
		try {
			writer = new PrintWriter(output, "UTF-8");
			writer.println(result);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
