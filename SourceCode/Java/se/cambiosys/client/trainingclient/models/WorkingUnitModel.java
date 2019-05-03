package se.cambiosys.client.trainingclient.models;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * WorkingUnitModel is used to present WorkingUnit data
 */
public class WorkingUnitModel extends AbstractTableModel
{

  private List<WorkingUnit> workingUnitList;

  private static final String[] columnNames =
    new String[] { "Name", "Unit Address", "Phone Number,", "Email Address", "UnitHSA IDs" };
  private static final Class[] columnClass =
    new Class[] { String.class, String.class, String.class, String.class, String.class };

  public WorkingUnitModel(List<WorkingUnit> unitList)
  {
    this.workingUnitList = unitList;
  }

  public List<WorkingUnit> getWorkingUnitList()
  {
    return this.workingUnitList;
  }

  public void setWorkingUnitList(List<WorkingUnit> unitList)
  {
    this.workingUnitList = unitList;
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
    return workingUnitList.size();
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex)
  {
    WorkingUnit row = workingUnitList.get(rowIndex);
    if (0 == columnIndex)
    {
      return row.getName();
    }
    else if (1 == columnIndex)
    {
      return row.getUnitAddress();
    }
    else if (2 == columnIndex)
    {
      return row.getPhoneNumber();
    }
    else if (3 == columnIndex)
    {
      return row.getEmailAddress();
    }
    else if (4 == columnIndex)
    {
      return row.getUnitHSAIDs();
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
    WorkingUnit row = workingUnitList.get(rowIndex);
    if (0 == columnIndex)
    {
      row.setName((String) aValue);
    }
    else if (1 == columnIndex)
    {
      row.setUnitAddress((String) aValue);
    }
    else if (2 == columnIndex)
    {
      row.setPhoneNumber((String) aValue);
    }
    else if (3 == columnIndex)
    {
      row.setEmailAddress((String) aValue);
    }
    else if (4 == columnIndex)
    {
      row.setUnitHSAIDs((String) aValue);
    }


  }

  public void removeRow(int row)
  {
    // remove a row from your internal data structure
    workingUnitList.remove(row);
  }

}
