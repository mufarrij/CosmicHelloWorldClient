package se.cambiosys.client.trainingclient.views;

import se.cambiosys.client.framework.DefaultExceptionHandler;
import se.cambiosys.client.framework.Framework;
import se.cambiosys.client.framework.components.CambioInternalFrame;
import se.cambiosys.client.framework.components.CambioPanel;
import se.cambiosys.client.framework.components.CambioScrollPane;
import se.cambiosys.client.framework.components.CambioTable;
import se.cambiosys.client.framework.subjectofcare.SubjectOfCareToolkit;
import se.cambiosys.client.framework.subjectofcare.SubjectOfCareWrapper;
import se.cambiosys.client.trainingclient.datahandler.DataHandler;
import se.cambiosys.client.trainingclient.models.Patient;
import se.cambiosys.client.trainingclient.models.PatientTableModel;
import se.cambiosys.client.trainingclient.models.UserTableModel;
import se.cambiosys.client.trainingclient.models.WorkingUnitModel;
import se.cambiosys.spider.PatientService.PatientData;
import se.cambiosys.spider.StructureService.Gender;
import se.cambiosys.spider.DataService.MultiValuedData;
import se.cambiosys.client.trainingclient.settings.SettingFacade;
import se.cambiosys.client.trainingclient.models.WorkingUnit;
import java.util.List;
import se.cambiosys.client.framework.components.CambioComboBox;


import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;


/**
 * BasicView class consist of interface logic for Menu Window
 * 
 * @author M.Mufarrij
 * @version 1.0- 3/19/2019
 */
public class BasicView extends CambioInternalFrame
{
  private static BasicView basicView = null;
  private static UserTableModel userTableModel = null;
  private static PatientTableModel patientTableModel = DataHandler.getInstance().getPatientTableModel();
  private static WorkingUnitModel workingUnitModel = null;

  // patient table
  private static CambioTable patientTable = new CambioTable();



  private BasicView(UserTableModel usertableModel , WorkingUnitModel unitModel)
  {
    try
    {
      setSOCRelated(new String[] { SubjectOfCareToolkit.PATIENT });
      setUserTableModel(usertableModel);
      setWorkingUnitModel(unitModel);
      String currentUserId = Framework.getInstance().getCurrentUserId();
      this.setTitle(currentUserId);
      initGUI();

    }
    catch (Exception e)
    {
      DefaultExceptionHandler.getInstance().handleThrowable(e);
    }

  }

  private static void setUserTableModel(UserTableModel userModel)
  {
    userTableModel = userModel;
  }

  private static void setWorkingUnitModel(WorkingUnitModel unitModel)
  {
    workingUnitModel = unitModel;
  }
 
  public static BasicView getInstance(UserTableModel userTableModel ,WorkingUnitModel workingUnitModel)
  {
    if (basicView == null)
    {
      basicView = new BasicView(userTableModel,workingUnitModel);
    }
    return basicView;
  }

  @Override
  public void setActiveSOC(SubjectOfCareWrapper soc) throws Exception
  {
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

    String phoneNumbers="";

    if(patientData.phoneNumbers.length > 0)
    {
      phoneNumbers = patientData.phoneNumbers[0].number;
    }

    String emailAddresses="";

    if(patientData.emails.length > 0)
    {
      emailAddresses = patientData.emails[0].address;
    }

    String patientName = patientData.name.generationName + " " + patientData.name.middleName + " " + patientData.name.familyName ;

    Patient patient = new Patient( patientName, patientData.age , phoneNumbers , emailAddresses , genderStr , patientData.basic.active );
    
    DataHandler.getInstance().changePatient(patient);
    patientTableModel.fireTableDataChanged();

  }
    super.showWindowContent();
  }

  @Override
  public void updateActiveSOCData(SubjectOfCareWrapper soc)
  {
    super.showWindowContent();
  }

  public void initGUI()
  {
    CambioPanel mainPanel = new CambioPanel(new GridBagLayout());
    this.getContentPane().add(mainPanel);

    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 0;
    c.gridy = 0;
    c.fill = GridBagConstraints.BOTH;
    c.anchor = GridBagConstraints.NORTH;
    c.weightx = 1;
    c.weighty = 1;
    c.insets = new Insets(4, 4, 4, 4);

    //creating UserTablePanel
    CambioPanel userTablePanel = this.createUserTablePanel();

    mainPanel.add(userTablePanel, c);

    //creating PatientTablePanel
    CambioPanel patientTablePanel = this.createPatientTablePanel();

    c.gridx = 0;
    c.gridy = 1;
    mainPanel.add(patientTablePanel, c);
    
    //creating workingUnitPanel
    CambioPanel workingUnitPanel = this.createWorkingUnitPanel();
    
    c.gridx = 0;
    c.gridy = 2;
    mainPanel.add(workingUnitPanel, c);
    
    this.pack();

    this.setVisible(true);

  }

  public CambioPanel createUserTablePanel()
  {
    CambioTable userTable = new CambioTable();
    userTable.setModel(userTableModel);

    CambioScrollPane userTabelPane = new CambioScrollPane(userTable);

    // initializing usertablePanel  and placing the internal components using GridBagLayout

    CambioPanel userTablePanel = new CambioPanel();
    userTablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Login User", TitledBorder.CENTER, TitledBorder.TOP));
    userTablePanel.setPreferredSize(new Dimension(600, 180));
    userTablePanel.setLayout(new GridBagLayout());

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;
    gbc.anchor = GridBagConstraints.LINE_START;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.insets = new Insets(5, 5, 5, 5);

    int i = 0;

    gbc.gridx = 0;
    gbc.gridy = i;
    gbc.gridwidth = 2;
    userTablePanel.add(userTabelPane, gbc);

    return userTablePanel;
  }

  public CambioPanel createPatientTablePanel()
  {
    patientTable.setModel(patientTableModel);

    //reading column order setting for the patient table
    MultiValuedData columnOrder = SettingFacade.getInstance().getColumnOrderSetting();
    patientTable.customizeColumnModel(columnOrder);

    CambioScrollPane patientTabelPane = new CambioScrollPane(patientTable);

    // initializing patienttablePanel  and placing the internal components using GridBagLayout

    CambioPanel patientTablePanel = new CambioPanel();
    patientTablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Selected Patient", TitledBorder.CENTER, TitledBorder.TOP));
    patientTablePanel.setPreferredSize(new Dimension(600, 180));
    patientTablePanel.setLayout(new GridBagLayout());

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;
    gbc.anchor = GridBagConstraints.LINE_START;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.insets = new Insets(5, 5, 5, 5);

    int i = 0;

    gbc.gridx = 0;
    gbc.gridy = i;
    gbc.gridwidth = 2;
    patientTablePanel.add(patientTabelPane, gbc);

    return patientTablePanel;

  }
  
  CambioPanel createWorkingUnitPanel()
  {
    CambioTable workingUnitTable = new CambioTable()
      {
        @Override
        public String getToolTipText(java.awt.event.MouseEvent e)
        {
          String tip = null;
          java.awt.Point p = e.getPoint();
          int rowIndex = rowAtPoint(p);
          int colIndex = columnAtPoint(p);
          int realColumnIndex = convertColumnIndexToModel(colIndex);

          if (realColumnIndex == 4 || realColumnIndex == 2 || realColumnIndex == 3 )
          { //UnitHSAIds Column
            tip = getValueAt(rowIndex, colIndex).toString();
          }
          return tip;
        }
      };

    workingUnitTable.setModel(workingUnitModel);

    // creating HSAIDs comboBox
    List<WorkingUnit> workingUnitList = workingUnitModel.getWorkingUnitList();
    WorkingUnit workingUnit = workingUnitList.get(0);
    String unitIds = workingUnit.getUnitHSAIDs();
    String[] hsaIDs = unitIds.split(",");
    CambioComboBox idList = new CambioComboBox(hsaIDs);

    workingUnitTable.getColumnModel().getColumn(4).setCellEditor(new javax.swing.DefaultCellEditor(idList));
    workingUnitTable.getColumnModel().getColumn(4).setMaxWidth(300);

    // creating phone numbers comboBox
    String phoneNumbers = workingUnit.getPhoneNumber();
    String[] numbers = phoneNumbers.split(",");
    CambioComboBox numList = new CambioComboBox(numbers);

    workingUnitTable.getColumnModel().getColumn(2).setCellEditor(new javax.swing.DefaultCellEditor(numList));

    //creating email address comboBox
    String emailAddress = workingUnit.getEmailAddress();
    String[] emails = emailAddress.split(",");
    CambioComboBox emailList = new CambioComboBox(emails);

    workingUnitTable.getColumnModel().getColumn(3).setCellEditor(new javax.swing.DefaultCellEditor(emailList));

    CambioScrollPane workingUnitTabelPane = new CambioScrollPane(workingUnitTable);

    // initializing workingUnittablePanel  and placing the internal components using GridBagLayout

    CambioPanel workingUnitTablePanel = new CambioPanel();
    workingUnitTablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Working Unit", TitledBorder.CENTER, TitledBorder.TOP));
    workingUnitTablePanel.setPreferredSize(new Dimension(600, 180));
    workingUnitTablePanel.setLayout(new GridBagLayout());

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;
    gbc.anchor = GridBagConstraints.LINE_START;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.insets = new Insets(5, 5, 5, 5);

    int i = 0;

    gbc.gridx = 0;
    gbc.gridy = i;
    gbc.gridwidth = 2;
    workingUnitTablePanel.add(workingUnitTabelPane, gbc);

    return workingUnitTablePanel;
  }
}
