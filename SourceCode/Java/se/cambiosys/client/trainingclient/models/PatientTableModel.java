package se.cambiosys.client.trainingclient.models;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * PatientTable model is used to present patient data
 */
public class PatientTableModel extends AbstractTableModel
{
  private List<Patient> patientList;

  private static final String[] columnNames = new String[] {
    "FullName", "Age", "Phone Number,", "Email Address","Gender","Deceased"
  };
  private static final Class[] columnClass = new Class[] {
    String.class,Short.class, String.class, String.class,String.class,Boolean.class
  };

  public PatientTableModel(List<Patient> patientList)
  {
    this.patientList = patientList;
  }

  public List<Patient> getPatientList()
  {
    return this.patientList;
  }

  public void setPatientList(List<Patient> patientList)
  {
    this.patientList = patientList;
  }

  @Override
  public String getColumnName(int column)
  {
    return columnNames[column];
  }

  @Override
  public Class<?> getColumnClass(int columnIndex)
  {
    return columnClass[columnIndex];
  }

  @Override
  public int getColumnCount()
  {
    return columnNames.length;
  }

  @Override
  public int getRowCount()
  {
    return patientList.size();
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex)
  {
    Patient row = patientList.get(rowIndex);
    if(0 == columnIndex) {
      return row.getFullName();
    }
    else if(1 == columnIndex) {
      return row.getAge();
    }
    else if(2 == columnIndex) {
      return row.getPhoneNumber();
    }
    else if(3 == columnIndex) {
      return row.getEmailAddress();
    }
    else if(4 == columnIndex) {
      return row.getGender();
    }
    else if(5 == columnIndex) {
      return row.isDeceased();
    }
    return null;
  }

  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex)
  {
    return true;
  }

  @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex)
  {
    Patient row = patientList.get(rowIndex);
    if(0 == columnIndex) {
      row.setFullName((String) aValue);
    }
    else if(1 == columnIndex) {
      row.setAge((Short) aValue);
    }
    else if(2 == columnIndex) {
      row.setPhoneNumber((String) aValue);
    }
    else if(3 == columnIndex) {
      row.setEmailAddress((String) aValue);
    }
    else if(4 == columnIndex) {
      row.setGender((String) aValue);
    }
    else if(5 == columnIndex) {
      row.setDeceased((Boolean) aValue);
    }

  }

  public void removeRow(int row) {
    // remove a row from your internal data structure
    patientList.remove(row);
  }
}
