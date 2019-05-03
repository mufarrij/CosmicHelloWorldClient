package se.cambiosys.client.trainingclient.models;

/**
 * WorkingUnit class define WorkingUnit
 */
public class WorkingUnit
{
  private String name;
  private String unitAddress;
  private String phoneNumber;
  private String emailAddress;
  private String UnitHSAIDs;

  public WorkingUnit(String name, String unitAddress, String phoneNumber, String emailAddress, String unitHSAIDs)
  {
    this.name = name;
    this.unitAddress = unitAddress;
    this.phoneNumber = phoneNumber;
    this.emailAddress = emailAddress;
    UnitHSAIDs = unitHSAIDs;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public void setUnitAddress(String unitAddress)
  {
    this.unitAddress = unitAddress;
  }

  public void setPhoneNumber(String phoneNumber)
  {
    this.phoneNumber = phoneNumber;
  }

  public void setUnitHSAIDs(String unitHSAIDs)
  {
    UnitHSAIDs = unitHSAIDs;
  }

  public void setEmailAddress(String email)
  {
    this.emailAddress = email;
  }

  public String getName()
  {
    return name;
  }

  public String getUnitAddress()
  {
    return unitAddress;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public String getEmailAddress()
  {
    return emailAddress;
  }

  public String getUnitHSAIDs()
  {
    return UnitHSAIDs;
  }
}
