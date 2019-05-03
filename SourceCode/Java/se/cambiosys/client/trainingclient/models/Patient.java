package se.cambiosys.client.trainingclient.models;

/**
 * Patient class define Patient
 */
public class Patient
{
  private String fullName;
  private Short age;
  private String phoneNumber;
  private String emailAddress;
  private String gender;
  private boolean isDeceased;

  public Patient(String fullName, Short age, String phoneNumber, String emailAddress, String gender, boolean isDeceased)
  {
    this.fullName = fullName;
    this.age = age;
    this.phoneNumber = phoneNumber;
    this.emailAddress = emailAddress;
    this.gender = gender;
    this.isDeceased = isDeceased;
  }

  public void setFullName(String fullName)
  {
    this.fullName = fullName;
  }

  public void setAge(Short age)
  {
    this.age = age;
  }

  public void setPhoneNumber(String phoneNumber)
  {
    this.phoneNumber = phoneNumber;
  }

  public void setEmailAddress(String emailAddress)
  {
    this.emailAddress = emailAddress;
  }

  public void setGender(String gender)
  {
    this.gender = gender;
  }

  public void setDeceased(boolean deceased)
  {
    isDeceased = deceased;
  }

  public String getFullName()
  {
    return fullName;
  }

  public Short getAge()
  {
    return age;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public String getEmailAddress()
  {
    return emailAddress;
  }

  public String getGender()
  {
    return gender;
  }

  public boolean isDeceased()
  {
    return isDeceased;
  }
}
