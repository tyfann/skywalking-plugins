package com.xcewell.esb.skywalking.apm.plugin.gateway;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.matcher.ElementMatcher;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.ConstructorInterceptPoint;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.InstanceMethodsInterceptPoint;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.ClassInstanceMethodsEnhancePluginDefine;
import org.apache.skywalking.apm.agent.core.plugin.match.ClassMatch;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * @author tyfann
 * @date 2021/2/3 4:21 下午
 */
public class GatewayInstrumentation extends ClassInstanceMethodsEnhancePluginDefine {

    private static final String ENHANCE_CLASS =
            "com.xcewell.esb.filter.global.AnalysisResponseFilter";
    private static final String INTERCEPT_CLASS =
            "com.xcewell.esb.skywalking.apm.plugin.gateway.GatewayInterceptor";

    protected ClassMatch enhanceClass() {
        return null;
    }

    public ConstructorInterceptPoint[] getConstructorsInterceptPoints() {
        return null;
    }

    public InstanceMethodsInterceptPoint[] getInstanceMethodsInterceptPoints() {
        return new InstanceMethodsInterceptPoint[] {
                new InstanceMethodsInterceptPoint() {

                    @Override
                    public ElementMatcher<MethodDescription> getMethodsMatcher() {
                        return named("invoke");
                    }

                    @Override
                    public String getMethodsInterceptor() {
                        return INTERCEPT_CLASS;
                    }

                    @Override
                    public boolean isOverrideArgs() {
                        return false;
                    }
                }
        };
    }
}
