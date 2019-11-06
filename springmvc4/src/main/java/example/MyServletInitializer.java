package example;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.FilterRegistration.Dynamic;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.MultipartConfigElement;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MyServletInitializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// TODO Auto-generated method stub
		//Add listener
		WebApplicationContext context = getContext();
        servletContext.addListener(new ContextLoaderListener(context));
        servletContext.addListener(new MySessionListeren());
        servletContext.addListener(new MyRequestListener());
        
        //Add Filter
        Dynamic filter2 = servletContext.addFilter("MyFilter2",MyFilter2.class);
        Dynamic filter1 = servletContext.addFilter("MyFilter1",MyFilter1.class);
        EnumSet<DispatcherType> dispatcherTypeSet = EnumSet.noneOf(DispatcherType.class);
        dispatcherTypeSet.add(DispatcherType.REQUEST);
        
        filter2.addMappingForUrlPatterns(dispatcherTypeSet, false, "/");
        filter1.addMappingForUrlPatterns(dispatcherTypeSet, false, "/");
        
        //set DispatcherServlet
        //Dynamic myServlet = servletContext.addServlet("myServlet", MyServlet.class);
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
		
        //dispatcher.addMapping("/custom");
        dispatcher.addMapping("/");
        
        //other settting
        //set muti-file upload
        dispatcher.setMultipartConfig(
        		new MultipartConfigElement("/"));
	}

    private AnnotationConfigWebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("example");
        return context;
    }
}
