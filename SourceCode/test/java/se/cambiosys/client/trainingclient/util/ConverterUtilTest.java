package se.cambiosys.client.trainingclient.util;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;
import se.cambiosys.spider.StructureService.DateTime;
import se.cambiosys.spider.StructureService.Date;
import se.cambiosys.spider.StructureService.Time;


/**
 * Test Cases for  {@Link ConverterUtil}
 */
public class ConverterUtilTest
{

  @Test
  public void testChangeToDateString_whenDateTimeIsNull_thenReturnEmptyString()
  {
    assertThat(ConverterUtil.changeToDateString(null)).isEqualTo("");
  }

  @Test
  public void testChangeToDateString_whenDateTimeIsNotNull_thenReturnDateTimeString()
  {
    Date date = new Date((short)2019 , (short)6 , (short)23);
    Time time = new Time((short)3 , (short)20 , (short)30);
    DateTime dateTime = new DateTime(date,time);

    String dateString = "DD:MM:YYYY HH:MM:SS   " + "23-6-2019  3:20:30";

    assertThat(ConverterUtil.changeToDateString(dateTime)).isEqualTo(dateString);
  }

  @Test
  public void testChangeToDateString_whenDateIsNullAndTimeIsNotNull_thenReturnTimeString()
  {
    Date date = null;
    Time time = new Time((short)3 , (short)20 , (short)30);
    DateTime dateTime = new DateTime(date,time);

    String dateString = "HH:MM:SS   " + "3:20:30";

    assertThat(ConverterUtil.changeToDateString(dateTime)).isEqualTo(dateString);
  }

  @Test
  public void testChangeToDateString_whenDateIsNotNullAndTimeIsNull_thenReturnDateString()
  {
    Date date = new Date((short)2019 , (short)6 , (short)23);
    Time time = null;
    DateTime dateTime = new DateTime(date,time);

    String dateString = "DD:MM:YYYY   " + "23-6-2019";

    assertThat(ConverterUtil.changeToDateString(dateTime)).isEqualTo(dateString);
  }

  @Test
  public void testChangeToDateString_whenDateIsNullAndTimeIsNull_thenReturnEmptyString()
  {
     DateTime dateTime = new DateTime(null , null);

     assertThat(ConverterUtil.changeToDateString(dateTime)).isEqualTo("");

  }
}
