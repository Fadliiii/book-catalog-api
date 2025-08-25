package com.ucorp.catalog.config;

import java.lang.reflect.Method;

import javax.sql.DataSource;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.util.Reflection;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Component;

import net.ttddyy.dsproxy.listener.logging.SLF4JLogLevel;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;

@Component
public class DataSourceProxyBeanProcessor implements BeanPostProcessor{
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
	
		return bean;
	}
	
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(bean instanceof DataSource) {
			ProxyFactory factory = new ProxyFactory(bean);
			factory.setProxyTargetClass(true);
			factory.addAdvice(new ProxyDataSourceInterceptor((DataSource)bean));
			return factory.getProxy();
		}
		
		return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}

	private static class ProxyDataSourceInterceptor implements MethodInterceptor{
		private final DataSource dataSource;
		
		
		public ProxyDataSourceInterceptor(final DataSource dataSource) {
			super();
			this.dataSource = ProxyDataSourceBuilder.create(dataSource).countQuery().logQueryBySlf4j(SLF4JLogLevel.INFO).build();
		}


		@Override
		public Object invoke(MethodInvocation invocation) throws Throwable {
			Method proxMethod = ReflectionUtils.findMethod(dataSource.getClass(),invocation.getMethod().getName());
			if(proxMethod !=null ) {
				return proxMethod.invoke(dataSource,invocation.getArguments());
			}
			// TODO Auto-generated method stub
			return invocation.proceed();
		}
		
	}
}
