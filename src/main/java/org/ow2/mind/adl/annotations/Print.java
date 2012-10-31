package org.ow2.mind.adl.annotations;

import org.ow2.mind.adl.annotation.ADLAnnotationTarget;
import org.ow2.mind.adl.annotation.ADLLoaderPhase;
import org.ow2.mind.adl.annotation.ADLLoaderProcessor;
import org.ow2.mind.annotation.Annotation;
import org.ow2.mind.annotation.AnnotationElement;
import org.ow2.mind.annotation.AnnotationTarget;

@ADLLoaderProcessor(processor = PrintAnnotationProcessor.class, phases = { ADLLoaderPhase.AFTER_CHECKING })
public class Print implements Annotation {

	/**
	 * Author Stephane Seyvoz
	 */
	private static final long serialVersionUID = -6855697539610710097L;
	private static final AnnotationTarget[] ANNOTATION_TARGETS = { ADLAnnotationTarget.DEFINITION };

	@AnnotationElement(hasDefaultValue=true)
	public String text = "";
	
	@AnnotationElement(hasDefaultValue=true)
	public String filePath = "Print_output.txt";
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.mind.annotation.Annotation#getAnnotationTargets()
	 */
	public AnnotationTarget[] getAnnotationTargets() {
		return ANNOTATION_TARGETS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.mind.annotation.Annotation#isInherited()
	 */
	public boolean isInherited() {
		return false;
	}

}
