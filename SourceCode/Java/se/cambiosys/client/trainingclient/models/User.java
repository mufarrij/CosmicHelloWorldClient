package se.cambiosys.client.trainingclient.models;

/**
 * User class defines User
 */
public class User
{
  private String lastLogin;
  private String loginSource;
  private String userName;
  private String role;
  private String phoneNumber;
  private String emailAddress;

  public User(String lastLogin, String loginSource, String userName, String role, String phoneNumber, String emailAddress)
  {
    this.lastLogin = lastLogin;
    this.loginSource = loginSource;
    this.userName = userName;
    this.role = role;
    this.phoneNumber = phoneNumber;
    this.emailAddress = emailAddress;
  }

  public void setLastLogin(String lastLogin)
  {
    this.lastLogin = lastLogin;
  }

  public void setLoginSource(String loginSource)
  {
    this.loginSource = loginSource;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  public void setRole(String role)
  {
    this.role = role;
  }

  public void setPhoneNumber(String phoneNumber)
  {
    this.phoneNumber = phoneNumber;
  }

  public void setEmailAddress(String emailAddress)
  {
    this.emailAddress = emailAddress;
  }

  public String getLastLogin()
  {
    return lastLogin;
  }

  public String getLoginSource()
  {
    return loginSource;
  }

  public String getUserName()
  {
    return userName;
  }

  public String getRole()
  {
    return role;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public String getEmailAddress()
  {
    return emailAddress;
  }
}
