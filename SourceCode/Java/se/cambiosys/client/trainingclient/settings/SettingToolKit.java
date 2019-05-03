package se.cambiosys.client.trainingclient.settings;

import se.cambiosys.client.trainingclient.util.I18NToolkit;
import se.cambiosys.client.framework.Framework;
import se.cambiosys.client.framework.settings.SettingDefinition;
import se.cambiosys.spider.DataService.MultiValueType;
import se.cambiosys.spider.DataService.MultiValuedData;

/**
 * this class provide method which is used to register settings and setting editor
 *
 * @author M.Mufarrij
 * @version 1.0 - 2019/03/13
 */
public final class SettingToolKit
{
  // setting constants for prioritized arrival time settings

  private static final String CAMBIO = "cambio";

  private static final String MODULE = "CosmicHelloWorld";

  private static final String SETTING = "Setting";

  private static final String[] SETTING_PARENT_PATH = new String[] { CAMBIO, MODULE, SETTING };

  private static final String CUSTOM_COLUMN_ORDER_SETTING = "CustomColumnOrder";

  private static final String CUSTOM_COLUMN_ORDER_SETTING_SETTING_VISIBLE_PATH =
    I18NToolkit.getResourceString("CosmicHelloWorldClient.settings.columnOrderSettings");

  protected static final String[] CUSTOM_COLUMN_ORDER_SETTING_SETTING_PATH =
    { CAMBIO, MODULE, SETTING, CUSTOM_COLUMN_ORDER_SETTING };

  private SettingToolKit()
  {

  }

  /**
   * Registers settings.
   *
   * @param moduleName
   */
  public static void registerSettings(String moduleName)
  {
    Framework.registerSetting(SETTING_PARENT_PATH, getSettingDefinitions(moduleName));
  }

  /**
   * Gets settingDefinitions.
   *
   * @param moduleName
   * @return setting definitions array
   */
  private static SettingDefinition[] getSettingDefinitions(String moduleName)
  {

    // setting definition for customized column order editor

    MultiValuedData customColumnOrderSetting = new MultiValuedData(MultiValueType.INTEGER, 0, "", null, null);

    SettingDefinition customColumnOrderSettingDefinition =
      new SettingDefinition(CUSTOM_COLUMN_ORDER_SETTING_SETTING_PATH[
                              CUSTOM_COLUMN_ORDER_SETTING_SETTING_PATH.length - 1],
                            new String[] { moduleName, CUSTOM_COLUMN_ORDER_SETTING_SETTING_VISIBLE_PATH }, customColumnOrderSetting, true,
                            se.cambiosys.client.framework.FrameworkConstants.SETTING_CUSTOM_TABLECOLUMNORDER_EDITOR, "",new String[]{"FullName", "Age", "Phone Number,", "Email Address","Gender","Deceased"});

    return new SettingDefinition[] { customColumnOrderSettingDefinition };
  }

}
