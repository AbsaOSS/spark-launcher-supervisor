package za.co.absa.sparlus;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.asm.AsmVisitorWrapper;

import java.lang.instrument.Instrumentation;

public class AgentInit {
    public static void premain(String agentArgs, Instrumentation inst) {
        init(inst);
    }

    private static void init(Instrumentation inst) {
        final SparkContextConstructorAdvice advice = new SparkContextConstructorAdvice();

        final AsmVisitorWrapper visitor = Advice
                .to(advice.getClass())
                .on(advice.ELEMENT_MATCHER);

        new AgentBuilder.Default()
                .type(advice.TYPE_MATCHER)
                .transform((builder, td, cl, jm, pd) -> builder.visit(visitor))
                .installOn(inst);
    }
}
