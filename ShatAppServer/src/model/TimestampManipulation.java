package model;

public class TimestampManipulation
{
  public static String convertTimestampToDateTimeFull(long timestamp){
    return new java.util.Date((long) timestamp * 1000).toString();
  }

  public static String convertTimestampToDateTimeShort(long timestamp){
    return new java.text.SimpleDateFormat("yyyy-MM-dd | HH:mm:ss").format(new java.util.Date((long) timestamp * 1000));
  }

  public static String convertTimestampToTime(long timestamp){
    return new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date((long) timestamp * 1000));
  }

  public static String convertTimestampToDate(long timestamp){
    return new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date((long) timestamp * 1000));
  }

  public static String convertTimestampCustom(long timestamp, String format){
    return new java.text.SimpleDateFormat(format).format(new java.util.Date((long) timestamp * 1000));
  }

  public static long getCurrentTimestamp(){
    return System.currentTimeMillis() / 1000L;
  }
}
