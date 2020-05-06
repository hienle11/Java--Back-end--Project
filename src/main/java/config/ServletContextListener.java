package config;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener
{

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent)
    {
        System.out.println("here");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent)
    {

    }
}
