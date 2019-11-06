package example;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class Springmvc3WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	//identifies one or more paths that DispatcherServlet will be mapped to
	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] { "/" };
	}
	
	//load the other beans such as controllers, view resolvers, and handler mappings in your application
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[] { WebConfig.class };
	}
	
	//application context with beans defined in the WebConfig configuration.class (using Java configuration)
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[] { RootConfig.class };
	}
}
