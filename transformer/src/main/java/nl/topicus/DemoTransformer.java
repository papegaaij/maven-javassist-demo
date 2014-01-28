package nl.topicus;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import nl.topicus.plugins.maven.javassist.ClassTransformer;
import nl.topicus.plugins.maven.javassist.TransformationException;

public class DemoTransformer extends ClassTransformer {

	@Override
	public void applyTransformations(ClassPool classPool,
			CtClass classToTransform) throws TransformationException {
		if (classToTransform.getName().equals("nl.topicus.HelloWorld")) {
			try {
				classToTransform.getDeclaredMethod("perform").setBody(
						"System.out.println(\"Hello world!\");");
			} catch (CannotCompileException | NotFoundException e) {
				throw new TransformationException(e);
			}
		}
	}
}
