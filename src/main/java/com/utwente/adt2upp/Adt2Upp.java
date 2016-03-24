package com.utwente.adt2upp;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;

import com.utwente.adt2upp.bo.Adt2atmmBO;
import com.utwente.adt2upp.bo.Atmm2UppaalBO;
import com.utwente.standalone.EtlStandaloneExample;



/**
 * Hello world!
 *
 */
public class Adt2Upp 
{
	public boolean skipEVL, noEGL;
	
	public Adt2Upp (String input, String output, boolean skipEVL, boolean noEGL) {
		this.skipEVL = skipEVL;
		this.noEGL = noEGL;
		if (!input.endsWith(".xml") && !input.endsWith(".model")) {			
			usage();
			return;
		}
		
		System.out.println( "|-----------------------------|" );
		System.out.println( "|      Starting ADT2Uppaal    |" );
		if (skipEVL) System.out.println( "| >> Skipping EVL" );
		System.out.println( "|-----------------------------|" );
		try {
			if (input.endsWith(".xml")) {
				if ( noEGL ) {
					Adt2atmmBO aa = new Adt2atmmBO(input, output);
					aa.execute();
				} else {
					Adt2atmmBO aa = new Adt2atmmBO(input, "instance.model");
					aa.execute();
					System.out.println( "|-----------------------------|" );
					Atmm2UppaalBO au = new Atmm2UppaalBO("instance.model", output);
					au.execute();
				}
				System.out.println( "|-----------------------------|" );
			} else if (input.endsWith(".model")) {
				System.out.println( "| .model file detected        |" );
				System.out.println( "| Only doing EGL-part         |" );
				System.out.println( "|-----------------------------|" );
				Atmm2UppaalBO au = new Atmm2UppaalBO("instance.model", output);
				au.execute();				
			} else {
				usage();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println( "|      Finished ADT2Uppaal    |" );
		System.out.println( "|-----------------------------|" );
		
	}
	
    public static void main( String[] args )
    {
		boolean skipEVL = false;
		boolean noEGL = false;
    	if (args.length == 2){
			new Adt2Upp(args[0], args[1], skipEVL, noEGL);
    	} else if (args.length == 3){
    		skipEVL = "-skipEVL".equals(args[0]);
    		noEGL = "-noEGL".equals(args[0]);
    		if (skipEVL ^ noEGL) {
    			new Adt2Upp(args[1], args[2], skipEVL, noEGL);
    		} else {
    			usage();
    		}
    	} else if (args.length == 4){
    		skipEVL = ( "-skipEVL".equals(args[0]) || "-skipEVL".equals(args[1]));
    		noEGL = ( "-noEGL".equals(args[0]) || "-noEGL".equals(args[1]));
    		if (skipEVL && noEGL) {
    			new Adt2Upp(args[2], args[3], skipEVL, noEGL);
    		} else {
    			usage();
    		}
		} else {
			usage();
		}
    }
    
    private static void usage(){
    	System.out.println("Oops,  I assume you have learned so much from your mistakes that you decided to make one more, please add atleast 2 arguments");
		System.out.println();
		System.out.println("Usage:> java -jar ADT2Uppaal.jar [options] <input_file> <output_file>");
		System.out.println("input_file:");
		System.out.println("    *.xml    : as ADTool input");
		System.out.println("    *.model  : as ATMM input");
		System.out.println("Options:");
		System.out.println("    -skipEVL : Skipping the Epsilon Validation Step");
		System.out.println("    -noEGL   : Skipping the Epsilon Generation Step, producing model instance");
	
    }
}
