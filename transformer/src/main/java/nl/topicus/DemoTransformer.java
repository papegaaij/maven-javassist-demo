package nl.topicus;

import javassist.ClassPool;
import javassist.CtClass;
import nl.topicus.plugins.maven.javassist.ClassTransformer;

public class DemoTransformer extends ClassTransformer {

	@Override
	protected void applyTransformations(ClassPool classPool,
			CtClass classToTransform) throws Exception {
		if (classToTransform.getName().equals("nl.topicus.HelloWorld")) {
			classToTransform.getDeclaredMethod("perform").setBody(
					"System.out.println(\"Hello world!\");");
		}
	}

	@Override
	protected void writeFile(CtClass candidateClass, String targetDirectory)
			throws Exception {
		if (candidateClass.getName().equals("nl.topicus.HelloWorld")) {
			candidateClass.getClassFile().compact();
			candidateClass.rebuildClassFile();
			super.writeFile(candidateClass, getDefaultOutputDirectory());
		}
	}
}
