package se.cambiosys.client.trainingclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.cambiosys.client.framework.AbstractModule;
import se.cambiosys.client.framework.Framework;
import se.cambiosys.client.framework.ModuleInformation;
import se.cambiosys.client.framework.components.CambioMenu;
import se.cambiosys.client.framework.components.CambioMenuItem;
import se.cambiosys.client.trainingclient.controllers.MainController;
import se.cambiosys.client.trainingclient.settings.SettingToolKit;
import se.cambiosys.client.trainingclient.util.I18NToolkit;
import se.cambiosys.client.trainingclient.util.SocChangeHandler;
import se.cambiosys.spider.AccessService.Dimension;
import se.cambiosys.client.framework.access.AccessToolkit;


import java.awt.event.ActionEvent;

/**
 * module class provide module information and specify menu item and action
 * @author M.Mufarrij
 * @version 1.0 - 3/19/2019
 */
public class Module extends AbstractModule
{
  private ModuleInformation moduleInformation;
  private MainController mainController;

  private static final Logger logger = LoggerFactory.getLogger(Module.class);

  public static final String MODULE_NAME = I18NToolkit.getResourceString("CosmicHello");


  public Module()
  {
    moduleInformation = new ModuleInformation("Hello Client", "moduleId", "This is the description", null);
  }

  @Override public ModuleInformation getModuleInformation()
  {
    return moduleInformation;
  }

  @Override public void initiateModule()
  {
    logger.error("Module  Initiated");
    registerSOCListener();
    CambioMenu cambioMenu = new CambioMenu(se.cambiosys.client.trainingclient.util.I18NToolkit.getResourceString("Cosmichello.client.menu"));
    CambioMenuItem menuItemOne = new CambioMenuItem(se.cambiosys.client.trainingclient.util.I18NToolkit.getResourceString("Cosmichello.client.menu"), "itemId")
    {
        @Override
        public void itemSelected(ActionEvent e)
        {
          boolean permission = authoriseCurrentUser();
          if (permission)
          {
            mainController = new MainController();
            mainController.setMainFrame();
            mainController.init();
          }
        }
    };

    cambioMenu.add(menuItemOne);
    Framework.getInstance().registerMenu(moduleInformation.getName(), cambioMenu);
    SettingToolKit.registerSettings(MODULE_NAME);

  }

  @Override
  public Object getServiceObject()
  {
    return null;
  }

  @Override
  public void login()
  {
    // Logic to be executed when user Login
  }

  @Override
  public void logout()
  {
    mainController = null;
  }

  public boolean authoriseCurrentUser()
  {
    return AccessToolkit.getInstance()
      .accessGranted(new String[] { "se", "cambiosys", "client", "trainingclient", "AccessExample" }, "AllowRead", new Dimension[] {}, Framework.getInstance().getCurrentUserId());
  }

  private void registerSOCListener()
  {
    SocChangeHandler socChangeHandler = new SocChangeHandler();
    Framework.getInstance().registerSubjectOfCareListener(socChangeHandler);
  }
}
