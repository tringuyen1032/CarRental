/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trinm.mapping;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import trinm.map.MapObject;

/**
 *
 * @author tring
 */
public class ServerMapping implements ServletContextListener {

    private final Logger LOGGER = Logger.getLogger(this.getClass());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        MapObject map = new MapObject();
        String line;
        String path = sce.getServletContext().getRealPath("/") + "WEB-INF/" + "filter.txt";
        try {
            Scanner scanner = new Scanner(new FileInputStream(path));
            while (scanner.hasNext()) {
                line = scanner.next();
                if (!line.isEmpty()) {
                    String[] columns = line.split("=");
                    map.getItems().put(columns[0], columns[1]);
                }
            }
        } catch (FileNotFoundException e) {
            BasicConfigurator.configure();
            LOGGER.error("Mapping_FileNotFoundException: " + e.getMessage());
        }
        sce.getServletContext().setAttribute("MAP", map);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
