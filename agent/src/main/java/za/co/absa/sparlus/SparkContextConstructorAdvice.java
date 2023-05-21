package za.co.absa.sparlus;

import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

import static za.co.absa.sparlus.Logger.warn;

public class SparkContextConstructorAdvice {
    public final ElementMatcher.Junction<TypeDescription> TYPE_MATCHER = ElementMatchers.named("org.apache.spark.SparkContext");
    public final ElementMatcher.Junction<MethodDescription> ELEMENT_MATCHER = ElementMatchers.isConstructor();

    public static final String SPARK_CONF_CLASS_NAME = "org.apache.spark.SparkConf";

    @SuppressWarnings("unused")
    @Advice.OnMethodEnter
    public static void enter(@Advice.AllArguments Object[] constrArgs) {
        Object sparkConf = null;
        for (final Object arg : constrArgs) {
            if (SPARK_CONF_CLASS_NAME.equals(arg.getClass().getName())) {
                sparkConf = arg;
                break;
            }
        }

        if (sparkConf != null) {
            SparkConfValidator.validate(sparkConf);
        } else {
            warn("WARNING !!! Cannot extract SparkConf from the SparkContext constructor");
        }
    }
}
