package org.ow2.mind.adl.annotations;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.objectweb.fractal.adl.ADLException;
import org.objectweb.fractal.adl.Definition;
import org.objectweb.fractal.adl.Node;
import org.objectweb.fractal.adl.util.FractalADLLogManager;
import org.ow2.mind.adl.annotation.ADLLoaderPhase;
import org.ow2.mind.adl.annotation.AbstractADLLoaderAnnotationProcessor;
import org.ow2.mind.annotation.Annotation;

public class PrintAnnotationProcessor extends
AbstractADLLoaderAnnotationProcessor {

	protected static Logger printAnnotationLogger = FractalADLLogManager.getLogger("print-annotation");

	// We do not set it as static as we presume the annotation processor is singleton
	private static Set<String> handledFiles = new HashSet<String>();

	public Definition processAnnotation(Annotation annotation, Node node,
			Definition definition, ADLLoaderPhase phase,
			Map<Object, Object> context) throws ADLException {
		assert annotation instanceof Print;

		doStuff(definition, ((Print)annotation).text, ((Print)annotation).filePath);

		return null;
	}

	private void doStuff(Definition definition, String text, String filePath) {

		Writer writer = null;
		try {
			File file = new File(filePath);

			// Check if a file from a previous execution exist
			if (!handledFiles.contains(filePath)) {
				if (file.exists())
					file.delete();

				file.createNewFile();
				handledFiles.add(filePath);
			}

			// true to append content
			writer = new BufferedWriter(new FileWriter(file, true));
			
			writer.write(text + System.getProperty("line.separator"));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
