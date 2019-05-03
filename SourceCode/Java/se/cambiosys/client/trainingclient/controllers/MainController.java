package se.cambiosys.client.trainingclient.controllers;


import se.cambiosys.client.trainingclient.datahandler.DataHandler;
import se.cambiosys.client.trainingclient.views.BasicView;

/**
 * Controller class for the client module
 */
public class MainController
{
  private static BasicView mainFrame = null;

  public void setMainFrame()
  {
    if (mainFrame == null)
    {
      mainFrame = BasicView.getInstance(DataHandler.getInstance().getUserModel(),DataHandler.getInstance().getWorkingUnitModel());
    }
  }

  public void init()
  {
    mainFrame.setVisible(true);
  }
}
