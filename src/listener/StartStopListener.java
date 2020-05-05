package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import dao.HibernateDAO;

public class StartStopListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent arg0) {
    HibernateDAO.getInstance().getSession();
  }

  @Override
  public void contextDestroyed(ServletContextEvent arg0) {
    HibernateDAO.getInstance().closeSession();
  }
}
