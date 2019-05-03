package se.cambiosys.client.trainingclient.util;

import se.cambiosys.client.framework.subjectofcare.SOCChangeListener;
import se.cambiosys.client.framework.subjectofcare.SOCEvent;
import se.cambiosys.client.trainingclient.datahandler.DataHandler;

/**
 * SocChangeHandler class overrides socChanged method in SOCChangeListener
 */
public class SocChangeHandler implements SOCChangeListener
{
  @Override
  public void socChanged(SOCEvent e)
  {
    String socId = e.getSOCID();
    if(socId == null)
    {
      DataHandler.getInstance().clearPatient();
      DataHandler.getInstance().getPatientTableModel().fireTableDataChanged();
    }
  }
}
