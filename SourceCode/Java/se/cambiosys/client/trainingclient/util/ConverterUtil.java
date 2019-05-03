package se.cambiosys.client.trainingclient.util;

import se.cambiosys.spider.StructureService.Date;
import se.cambiosys.spider.StructureService.DateTime;
import se.cambiosys.spider.StructureService.Time;

/**
 * this class contains methods for dateTime convertion
 */
public class ConverterUtil
{
  public static String changeToDateString(DateTime dateTime)
  {

    if (dateTime == null)
    {
      return "";
    }
    else
    {
      Date date = dateTime.date;
      Time time = dateTime.time;

      if (dateTime.date == null && dateTime.time != null)
      {
        return "HH:MM:SS   " + time.hour + ":" + time.minute + ":" + time.second;
      }
      else if (dateTime.date != null && dateTime.time == null)
      {
        return "DD:MM:YYYY   " + date.day + "-" + date.month + "-" + date.year;
      }
      else if (dateTime.date == null && dateTime.time == null)
      {
        return "";
      }

      return "DD:MM:YYYY HH:MM:SS   " + date.day + "-" + date.month + "-" + date.year + "  " + time.hour + ":" + time.minute + ":" + time.second;

    }
  }
}
