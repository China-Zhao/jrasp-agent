package com.jrasp.agent;

import com.jrasp.agent.log.RaspLogger;

import java.lang.instrument.Instrumentation;

public class RaspAgent {

    public static void premain(String agentArgs, Instrumentation inst) {
        agentmain(agentArgs, inst);
    }

    public static void agentmain(String agentArgs, Instrumentation inst) {
        String probe = System.getProperty("rasp.probe");

        if (probe != null) {
            RaspLogger.logger.info("probe running");
            return;
        }

        System.setProperty("rasp.probe", "smith");

        Rasptransformer mytransformer = new Rasptransformer();
        mytransformer.init();
        inst.addTransformer(mytransformer, true);
    }

}
