//package com.gstdev.template.common.utils;
//
//import com.frame.template.common.utils.StringUtils;
//import com.opencsv.bean.CsvToBean;
//import com.opencsv.bean.CsvToBeanBuilder;
//import com.opencsv.bean.HeaderColumnNameMappingStrategy;
//import org.springframework.web.multipart.MultipartFile;
//
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.*;
//import java.time.format.DateTimeFormatter;
//import java.time.format.DateTimeFormatterBuilder;
//import java.time.format.DateTimeParseException;
//import java.time.temporal.TemporalAccessor;
//import java.util.*;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class CsvUtil {
//
//
//  /**
//   * Convert csv file to JavaBean
//   *
//   * @param file
//   * @param clazz
//   * @param <T>
//   * @return
//   */
//  public static <T> List<T> csvToJavaBean(MultipartFile file, Class<T> clazz) {
//    InputStreamReader in = null;
//    CsvToBean<T> csvToBean = null;
//    try {
//      in = new InputStreamReader(file.getInputStream(), "utf-8");
//      HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
//      strategy.setType(clazz);
//      csvToBean = new CsvToBeanBuilder<T>(in).withMappingStrategy(strategy).build();
//    } catch (Exception e) {
//      e.printStackTrace();
//      return null;
//    }
//    return csvToBean.parse();
//  }
//
//  /**
//   * is not a number
//   *
//   * @param str
//   * @return
//   */
//  public static boolean isNumber(String str) {
//    String reg = "^[0-9]+(.[0-9]+)?$";
//    return str.matches(reg);
//  }
//
//  /**
//   * Verify US phone number
//   *
//   * @param phone
//   * @return
//   */
//  public static boolean checkPhone(String phone) {
//    String reg = "[\\(]?\\d{3}[\\)]?([-.]?)\\s*\\d{3}\\1\\s*\\d{4}";
//    return phone.matches(reg);
//  }
//
//  /**
//   * format amount
//   *
//   * @param value
//   * @return
//   */
//  public static String formatDue(String value) {
//    // Create a BigDecimal object
//    BigDecimal number = new BigDecimal(value);
//
//    // removes trailing zeros from the fractional part
//    number = number.stripTrailingZeros();
//
//    // convert the result to a string
//    String output = number.toPlainString();
//
//    // split string by decimal point
//    String[] parts = output.split("\\.");
//    // If there are more than two digits after the decimal point, keep the original string
//    if (parts.length > 1 && parts[1].length() > 2) {
//      return output;
//    } else {
//      return String.format("%.2f", Double.parseDouble(output));
//    }
//  }
//
//  /**
//   * export csv
//   *
//   * @param fileName
//   * @param response
//   * @throws UnsupportedEncodingException
//   */
//  public static void readCsvFileStream(String fileName, HttpServletResponse response)
//    throws UnsupportedEncodingException {
//    String myFileName = new String(fileName.getBytes("utf-8"), "gbk");
//    File file = new File(myFileName);
//    if (file.exists()) {
//      response.setContentType("application/force-download");// Set force download not to open
//      response.addHeader("Content-Disposition", "attachment;fileName=" + myFileName);// set filename
//      response.setCharacterEncoding("UTF-8");
//      byte[] uft8bom = {(byte) 0xef, (byte) 0xbb, (byte) 0xbf};
//      byte[] buffer = new byte[1024];
//      FileInputStream fis = null;
//      BufferedInputStream bis = null;
//      try {
//        fis = new FileInputStream(file);
//        bis = new BufferedInputStream(fis);
//        OutputStream os = response.getOutputStream();
//        os.write(uft8bom);
//        int i = bis.read(buffer);
//        while (i != -1) {
//          os.write(buffer, 0, i);
//          i = bis.read(buffer);
//        }
//      } catch (Exception e) {
//        e.printStackTrace();
//      } finally {
//        if (bis != null) {
//          try {
//            bis.close();
//          } catch (IOException e) {
//            e.printStackTrace();
//          }
//        }
//        if (fis != null) {
//          try {
//            fis.close();
//          } catch (IOException e) {
//            e.printStackTrace();
//          }
//        }
//      }
//    }
//    if (file.delete()) {
//      System.out.println("file has been deleted！");
//    } else {
//      System.out.println("File deletion failed！");
//    }
//  }
//
//  /**
//   * Check whether it is correct according to time and time format
//   *
//   * @param length checksum length
//   * @param sDate check date
//   * @param format check format
//   * @return
//   */
//  public static boolean isLegalDate(int length, String sDate, String format, String timeType) {
//
//    int legalLen = length;
//    if ((sDate == null) || (sDate.length() != legalLen)) {
//      return false;
//    }
//    DateFormat formatter = new SimpleDateFormat(format);
//    try {
//
//      String[] array = sDate.split(":");
//      if (array == null || array.length == 0) {
//        return false;
//      }
//      //0-AM 1-PM 2-HRS
//      if ("0".equals(timeType) || "1".equals(timeType)) {
//        //morning
//        String h = array[0];
//        if (Integer.valueOf(h) > 12 || Integer.valueOf(h) < 1) {
//          return false;
//        }
//      }
//
//      Date date = formatter.parse(sDate);
//      return sDate.equals(formatter.format(date));
//    } catch (Exception e) {
//      return false;
//    }
//  }
//
//  /**
//   * Convert 12 hours to 24 hours
//   *
//   * @param str
//   * @return
//   */
//  public static String timeFormat(String str, String type, Date dateNow) {
//    if ("0".equals(type)) {
//      str = str + "am";
//    } else if ("1".equals(type)) {
//      str = str + "pm";
//    }
//    String startHour = "";
//    if (!"2".equals(type)) {
//      String[] strs = str.split("--");
//      String total = strs[strs.length - 1];
//      startHour = total.substring(0, total.indexOf(":"));
//      if ((total.charAt(total.indexOf("m") - 1) + "").equals("a")
//        && (startHour.equals("12"))) {
//        startHour = Integer.valueOf(startHour) - 12 + "";
//      }
//      if ((total.charAt(total.indexOf("m") - 1) + "").equals("p")
//        && (!startHour.equals("12"))) {
//        startHour = Integer.valueOf(startHour) + 12 + "";
//      }
//      if ((total.charAt(total.indexOf("m") - 1) + "").equals("p")
//        && (startHour.equals("12"))) {
//        startHour = Integer.valueOf(startHour) + "";
//      }
//      startHour += total
//        .substring(total.indexOf(":"), total.indexOf("m") - 1);
//    } else {
//      startHour = str;
//    }
//
//    ZonedDateTime centralTime1 = ZonedDateTime.ofInstant((dateNow == null ? new Date(): dateNow).toInstant(), ZoneId.of("America/Chicago"));
//    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//    String output = centralTime1.format(formatter1);
//    startHour = output + " " + startHour + ":00";
//
//    System.out.println("startHour = " + output);
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//    ZonedDateTime zonedDateTime = ZonedDateTime.parse(startHour, formatter.withZone(ZoneId.of("America/Chicago")));
//    ZonedDateTime currentDateTime = ZonedDateTime.now(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("America/Chicago"));
//    if (currentDateTime.compareTo(zonedDateTime) > 0) {
//      zonedDateTime = zonedDateTime.plusDays(1);
//    }
//
//    String result = zonedDateTime.format(formatter);
//
//    LocalDateTime localDateTime = LocalDateTime.parse(result, formatter);
//    ZonedDateTime centralTime = ZonedDateTime.of(localDateTime, ZoneId.of("America/Chicago"));
//    Date gmtDate = Date.from(centralTime.toInstant());
//    SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    return format2.format(gmtDate);
//  }
//
//  /**
//   * U.S. time to GMT string
//   *
//   * @param usTime
//   * @return
//   */
//  public static String getAmericaDateToGmtString(String usTime) {
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//    LocalDateTime localDateTime = LocalDateTime.parse(usTime, formatter);
//    ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.of("America/Chicago"));
//    DateTimeFormatter gmtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("GMT"));
//    String gmtTime = gmtFormatter.format(zonedDateTime);
//    return gmtTime;
//  }
//
//  /**
//   * Get US time
//   *
//   * @return
//   */
//  public static Date getAmericaDate() {
////    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
////    ZonedDateTime currentDateTime = ZonedDateTime.now(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("America/Chicago"));
////    System.out.println("formatter.format(currentDateTime) = " + formatter.format(currentDateTime));
//    // Set the time zone to US Central Time Zone
//    TimeZone timeZone = TimeZone.getTimeZone("America/Chicago");
//
//    // Get the Calendar object of the current time and set its time zone to the US central time zone
//    Calendar calendar = Calendar.getInstance(timeZone);
//
//    // Get the milliseconds of the current time
//    long currentTimeMillis = calendar.getTimeInMillis();
//
//    // Creates a new Date object representing the current time in the US central time zone
//    Date date = new Date(currentTimeMillis);
//
//    return date;
//  }
//
//  /**
//   * Get the time after a few days
//   *
//   * @param str
//   * @param addDay
//   * @return
//   */
//  public static Date timeAdd(String str, Integer addDay) {
//    Date date = null;
//    try {
//      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//      date = format.parse(str);
//      Calendar calendar = new GregorianCalendar();
//      calendar.setTime(date);
//      // Add one day to the date, push the integer back, and move the negative number forward
//      calendar.add(Calendar.DATE, addDay);
//      //calendar.add(Calendar.MINUTE, addDay);
//      // This time is the result of pushing the date back by one day.
//      date = calendar.getTime();
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//    return date;
//  }
//
//
//  /**
//   * Check whether the string is a legal date format
//   *
//   * @param input
//   * @param format
//   * @return
//   */
//  public static boolean isValidDate(String input, String format) {
//    try {
//      // Create a DateTimeFormatter object
//      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
//      // Parse the input date string into a LocalDate object
//      LocalDate.parse(input, formatter);
//      return true; // If no DateTimeParseException is thrown, it means that the input date string conforms to the specified date format
//    } catch (DateTimeParseException e) {
//      return false; // If a DateTimeParseException is thrown, it means that the input date string does not conform to the specified date format
//    }
//  }
//
//  /**
//   * Check whether the string is a legal date format
//   *
//   * @param inputDateString
//   * @return
//   */
//  public static String isValidDate(String inputDateString) {
//    String formattedDateString = null;
//    try {
//      DateTimeFormatter formatter = new DateTimeFormatterBuilder()
//        .appendOptional(DateTimeFormatter.ofPattern("M/d/yyyy"))
//        .appendOptional(DateTimeFormatter.ofPattern("MM/d/yyyy"))
//        .appendOptional(DateTimeFormatter.ofPattern("M/dd/yyyy"))
//        .appendOptional(DateTimeFormatter.ofPattern("MM/dd/yyyy"))
//        .toFormatter();
//
//      TemporalAccessor parsed = formatter.parseBest(inputDateString, ZonedDateTime::from, LocalDate::from); // 尝试解析字符串
//
//      if (parsed instanceof ZonedDateTime) { // If the parsing result is of ZonedDateTime type, it means that the input string is a complete date and time, which can be directly formatted as an output format
//        ZonedDateTime zdt = (ZonedDateTime) parsed;
//        formattedDateString = zdt.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
//      } else if (parsed instanceof LocalDate) { // If the parsing result is of LocalDate type, it means that the input string has only date, and the output needs to be formatted after manually adding time and time zone information
//        LocalDate ld = (LocalDate) parsed;
//        formattedDateString = ld.atStartOfDay(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
//      } else {
//        System.out.println("Invalid input date string."); // If parsing fails, it means the input string is not a valid date format
//      }
//    } catch (Exception e) {
//
//    }
//
//    return formattedDateString;
//  }
//
//  /**
//   * date to string
//   *
//   * @param date
//   * @param formatStr "yyyy-MM-dd HH:mm:ss"
//   * @return
//   */
//  public static String dateToStr(Date date, String formatStr) {
//    SimpleDateFormat format = new SimpleDateFormat(formatStr);
//    String dateString = format.format(date);
//    return dateString;
//  }
//
//  /**
//   * Generate a 6-digit verification code
//   * @param length
//   * @return
//   */
//  public static String generateCode(int length) {
//    String codeChars = "0123456789";
//    StringBuilder code = new StringBuilder();
//    Random random = new Random();
//    for (int i = 0; i < length; i++) {
//      int index = random.nextInt(codeChars.length());
//      code.append(codeChars.charAt(index));
//    }
//    return code.toString();
//  }
//
//  /**
//   * Whether the timestamp is more than the current time
//   * @param timestamp
//   * @return
//   */
//  public static Boolean isDue(long timestamp){
//    // get current time
//    LocalDateTime currentDateTime = LocalDateTime.now();
//    // convert timestamp to local time
//    Instant instant = Instant.ofEpochSecond(timestamp);
//    LocalDateTime targetDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
//    // Compare the size of the target time and the current time
//    int comparison = targetDateTime.compareTo(currentDateTime);
//    if (comparison > 0) {
//      return false;
//    }
//    return true;
//  }
//
//  public static void main(String[] args) throws ParseException {
//    String date = "02:00";
//   // System.out.println("date " + isLegalDate(date.length(), date, "HH:mm", "2"));
//    System.out.println("timeFormat() = " + timeFormat("02:11","2",getAmericaDate()));
//    SimpleDateFormat format = new SimpleDateFormat("d/M/yy HH:mm:ss");
//    System.out.println("format.format(getAmericaDate()) = " + format.format(getAmericaDate()));
//    //System.out.println("isValidDate(\"12/12/2022\",\"MM/dd/yyyy\") = " + timeAdd("2023-03-22 00:00:00", -1));
////    SimpleDateFormat format = new SimpleDateFormat("d/M/yy");
////    Date date = format.parse("12/3/23");
////    System.out.println("date = " + date);
//    // System.out.println("timeAdd(\"2023-02-06 10:00:00\",1) = " + timeAdd("2023-02-06 10:00:00", 0));
//    //System.out.println("isValidDate(\"3/02/2023\") = " + StringUtils.isEmpty(isValidDate("3/2/2023")));
//  }
//
//
//}
