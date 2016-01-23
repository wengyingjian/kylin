package com.wengyingjian.kylin.rpc.server.core;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.remoting.support.RemoteExporter;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import com.store59.rpc.utils.server.annotation.RemoteService;
import com.store59.rpc.utils.server.annotation.ServiceType;
import com.store59.rpc.utils.server.config.RemotingServiceConf;
import com.store59.rpc.utils.server.interceptor.RemoteInvocationExceptionInterceptor;

/**
 * Created by shanren on 7/16/15.
 */
@Component
public class RemotingServiceAnnotationBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter implements BeanFactoryAware, PriorityOrdered {

    private int order;
    private DefaultListableBeanFactory beanFactory;
    private SimpleUrlHandlerMapping remotingServiceHandlerMapping;
    private final static String EXPORT_METHOD_NAME_SERVICE_INTERFACE = "serviceInterface";
    private final static String EXPORT_METHOD_NAME_SERVICE = "service";
    private final static String EXPORT_SERVICE_BEAN_NAME_SUFFIX = "Exporter";

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        RemoteService service = AnnotationUtils.findAnnotation(bean.getClass(), RemoteService.class);

        if (null != service) {

            if (null == service.exportPath()) {
                throw new FatalBeanException("Exception initializing remoting service for " + beanName + " @RemotingService must has propertie with exportPath.");
            }

            if (!service.exportPath().startsWith("/")) {
                throw new FatalBeanException("Exception initializing remoting service for " + beanName + " @RemotingService should bean start with \"/\".");
            }

            Class<? extends RemoteExporter> nclass = null;
            if (ServiceType.HESSIAN == service.serviceType()) {
                nclass = HessianServiceExporter.class;
            } else if (ServiceType.HTTP == service.serviceType()) {
                nclass = HttpInvokerServiceExporter.class;
            }

            if (nclass != null) {
                String exportBeanName = beanName + EXPORT_SERVICE_BEAN_NAME_SUFFIX;
                // 通过BeanDefinitionBuilder创建bean定义
                BeanDefinitionBuilder exportDeanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(nclass);
                exportDeanDefinitionBuilder.addPropertyValue(EXPORT_METHOD_NAME_SERVICE_INTERFACE, service.serviceInterface());
                exportDeanDefinitionBuilder.addPropertyReference(EXPORT_METHOD_NAME_SERVICE, beanName);
                exportDeanDefinitionBuilder.addPropertyValue("interceptors",
                        new Object[] { new RemoteInvocationExceptionInterceptor(ClassUtils.getShortName(nclass)),
                                beanFactory.getBean("remoteInvocationMonitorInterceptor") });
                beanFactory.registerBeanDefinition(exportBeanName, exportDeanDefinitionBuilder.getBeanDefinition());

                Map<String, Object> umap = new HashMap<String, Object>();
                umap.put(service.exportPath(), beanFactory.getBean(exportBeanName));
                remotingServiceHandlerMapping.setUrlMap(umap);
                remotingServiceHandlerMapping.initApplicationContext();
            }

        }

        return bean;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
        this.remotingServiceHandlerMapping = (SimpleUrlHandlerMapping) beanFactory.getBean(RemotingServiceConf.REMOTING_SERVICE_HANDLER_MAPPING_NAME);
    }
}
