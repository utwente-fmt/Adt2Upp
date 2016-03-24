package com.utwente.adt2upp.bo;

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

public class UpaalEcore2UppaalBO2 extends EpsilonStandalone {

	String input = "";
	String output = "";	
	

	@Override
	public List<IModel> getModels() throws Exception {
		List<IModel> models = new ArrayList<IModel>();
		models.add(createEmfModel("Uppaal", input, "uppaal_0.5.0", true, true));
		return models;
	}

	public UpaalEcore2UppaalBO2(String input, String output) {
		this.input = input;
		this.output = output;
	}
	
	@Override
	public IEolExecutableModule createModule() {
		return new EglTemplateFactoryModuleAdapter(new EglTemplateFactory());
	}
	
	@Override
	public String getSource() throws Exception {
		return "uppaalecore/Ecore2Uppaal.egl";
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
