package se.cambiosys.client.trainingclient.datahandler;


import se.cambiosys.client.trainingclient.util.ConverterUtil;
import se.cambiosys.client.framework.DefaultExceptionHandler;
import se.cambiosys.client.framework.Framework;
import se.cambiosys.client.framework.users.UserDataProvider;
import se.cambiosys.client.trainingclient.models.Patient;
import se.cambiosys.client.trainingclient.models.PatientTableModel;
import se.cambiosys.client.trainingclient.models.User;
import se.cambiosys.client.trainingclient.models.UserTableModel;
import se.cambiosys.client.trainingclient.models.WorkingUnit;
import se.cambiosys.client.trainingclient.models.WorkingUnitModel;
import se.cambiosys.spider.PatientService.PatientData;
import se.cambiosys.spider.StructureService.DateTime;
import se.cambiosys.spider.StructureService.Gender;
import se.cambiosys.spider.UnitService.UnitData;
import se.cambiosys.spider.UserService.UserData;
import se.cambiosys.spider.UserService.UserRoleData;

import java.util.ArrayList;
import java.util.List;

/**
 * this class is used to handle data related to patient , user , workingUnit
 */
public class DataHandler
{
  private static UserTableModel userTableModel;
  private static List<User> userList = new ArrayList<>();

  private static PatientTableModel patientTableModel;
  private static List<Patient> patientList = new ArrayList<>();


  private static WorkingUnitModel workingUnitModel;
  private static List<WorkingUnit> workingUnits = new ArrayList<>();

  private static DataHandler dataHandler;

  public DataHandler()
  {
     loadData();
     setData();
  }

  private static void setData()
  {
    userTableModel = new UserTableModel(userList);
    patientTableModel = new PatientTableModel(patientList);
    workingUnitModel = new WorkingUnitModel(workingUnits);
  }

  private void loadData()
  {
    try
    {

      // user data
      
      UserRoleData userRoleData = Framework.getInstance().getWorkingUserRoleData();

      String currentUserId = Framework.getInstance().getCurrentUserId();
      UserData currentUserData = UserDataProvider.getInstance().getUserDataByID(currentUserId);

      DateTime lastLogin = currentUserData.lastLogin;
      String lastLoginStr = ConverterUtil.changeToDateString(lastLogin);

      User currentUser = new User( lastLoginStr , currentUserData.loginSource , currentUserId , userRoleData.name ,"" ,"" );
      userList.add(currentUser);
      
      //patient data
     
      PatientData patientData = Framework.getInstance().getActiveSubjectOfCare().getPatientData();

      if (patientData != null)
      {
        Gender.Type gender = Gender.Type.getGenderBySex((short) Integer.parseInt(patientData.gender.getLabel()));

        String genderStr = "";

        if (gender == Gender.Type.MALE)
        {
          genderStr = "Male";
        }
        else if (gender == Gender.Type.FEMALE)
        {
          genderStr = "Female";
        }

        String phoneNumbers = "";

        if (patientData.phoneNumbers.length > 0)
        {
          phoneNumbers = patientData.phoneNumbers[0].number;
        }

        String emailAddresses = "";

        if (patientData.emails.length > 0)
        {
          emailAddresses = patientData.emails[0].address;
        }

        String patientName = patientData.name.generationName + " " + patientData.name.middleName + " " + patientData.name.familyName ;
        
        Patient patient = new Patient(patientName, patientData.age, phoneNumbers, emailAddresses, genderStr, patientData.basic.active);
        patientList.add(patient);
      }

      //Unit data
      
      UnitData currentUnit = Framework.getInstance().getWorkingUnitData();

      String addresses = "";

      if (currentUnit.addresses.length > 0)
      {
        addresses = currentUnit.addresses[0].streetAddress+ " , " + currentUnit.addresses[0].city  +  " , " + currentUnit.addresses[0].country ;
      }

      String unitPhoneNumbers = "";

      if (currentUnit.phoneNumbers.length > 0)
      {
        for (int i = 0; i < currentUnit.phoneNumbers.length - 1; i++)
        {
          unitPhoneNumbers = unitPhoneNumbers + currentUnit.phoneNumbers[i].number + " , ";
        }

        unitPhoneNumbers = unitPhoneNumbers + currentUnit.phoneNumbers[currentUnit.phoneNumbers.length - 1].number;
      }

      String unitEmailAddresses = "";

      if (currentUnit.emails.length > 0)
      {
        for (int i = 0; i < currentUnit.emails.length - 1; i++)
        {
          unitEmailAddresses = unitEmailAddresses + currentUnit.emails[i].address + " , ";
        }

        unitEmailAddresses = unitEmailAddresses + currentUnit.emails[patientData.emails.length - 1].address;
      }

      String unitHSAIDs = "";

      if (currentUnit.basic.identifiers.length > 0)
      {
        for (int i = 0; i < currentUnit.basic.identifiers.length - 1; i++)
        {
          unitHSAIDs = unitHSAIDs + currentUnit.basic.identifiers[i].identifier + " , ";
        }

        unitHSAIDs = unitHSAIDs + currentUnit.basic.identifiers[currentUnit.basic.identifiers.length - 1].identifier;
      }
      
      WorkingUnit workingUnit = new WorkingUnit(currentUnit.name , addresses,unitPhoneNumbers,unitEmailAddresses ,unitHSAIDs);
      workingUnits.add(workingUnit);

    }
    catch(Exception e)
    {
      DefaultExceptionHandler.getInstance().handleThrowable(e);
    }
  }

  public static DataHandler getInstance()
  {
    if (dataHandler == null)
    {
      dataHandler = new DataHandler();
    }
    return dataHandler;
  }

  public void changePatient(Patient p)
  {
    if (patientList.isEmpty())
    {
      patientList.add(p);
    }
    else if (patientList.size() == 1)
    {
      patientList.remove(0);
      patientList.add(p);
    }
  }

  public UserTableModel getUserModel()
  {
     return userTableModel;
  }

  public PatientTableModel getPatientTableModel() {return patientTableModel;}

  public WorkingUnitModel getWorkingUnitModel() {return workingUnitModel;}

  public void clearPatient()
  {
    if(!patientList.isEmpty())
    {
      patientList.remove(0);
    }
  }

}

