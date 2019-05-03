package se.cambiosys.client.trainingclient.models;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * UserTableModel is used to present User Data
 */

public class UserTableModel extends AbstractTableModel
{
  private List<User> userList;

  private static final String[] columnNames =
    new String[] { "User Name", "Role", "Last Login", "Last Login Source", "Phone Number", "Email Address" };

  private static final Class[] columnClass =
    new Class[] { String.class, String.class, String.class, String.class, String.class, String.class };

  public UserTableModel(List<User> userList)
  {
    super();
    this.userList = userList;
  }

  public void setUserList(List<User> userList)
  {
    this.userList = userList;
  }

  public List<User> getUserList()
  {
    return this.userList;
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
    return userList.size();
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex)
  {
    User row = userList.get(rowIndex);
    if (0 == columnIndex)
    {
      return row.getUserName();
    }
    else if (1 == columnIndex)
    {
      return row.getRole();
    }
    else if (2 == columnIndex)
    {
      return row.getLastLogin();
    }
    else if (3 == columnIndex)
    {
      return row.getLoginSource();
    }
    else if (4 == columnIndex)
    {
      return row.getPhoneNumber();
    }
    else if (5 == columnIndex)
    {
      return row.getEmailAddress();
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
    User row = userList.get(rowIndex);
    if (0 == columnIndex)
    {
      row.setUserName((String) aValue);
    }
    else if (1 == columnIndex)
    {
      row.setRole((String) aValue);
    }
    else if (2 == columnIndex)
    {
      row.setLastLogin((String) aValue);
    }
    else if (3 == columnIndex)
    {
      row.setLoginSource((String) aValue);
    }
    else if (4 == columnIndex)
    {
      row.setPhoneNumber((String) aValue);
    }
    else if (5 == columnIndex)
    {
      row.setEmailAddress((String) aValue);
    }

  }

  public void removeRow(int row)
  {
    userList.remove(row);
  }

}
