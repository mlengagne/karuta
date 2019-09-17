package eportfolium.com.karuta.util;

import java.util.Date;
import java.util.TimeZone;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class JavaTimeUtil {
	public static final ZoneId paris = ZoneId.of("Europe/Paris");
	public static ZoneId losAngeles = ZoneId.of("America/Los_Angeles");
	public static ZoneId brisbane = ZoneId.of("Australia/Brisbane");
	public static ZoneId perth = ZoneId.of("Australia/Perth");
	public static ZoneId date_default_timezone = null;
	public static final DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern("YYYYMMDD");

	// ///////////////////////////////////////////
	// From JodaTime types to standard Java types
	// ///////////////////////////////////////////

	public static java.sql.Timestamp toSQLTimestamp(ZonedDateTime dt) {
		// TODO - confirm this conversion always works, esp. across timezones
		java.sql.Timestamp ts = (dt == null ? null : new java.sql.Timestamp(dt.toInstant().toEpochMilli()));
		return ts;
	}

	public static java.util.Date toJavaDate(ZonedDateTime dt) {
		// TODO - confirm this conversion always works, esp. across timezones
		java.util.Date ts = (dt == null ? null : new java.util.Date(dt.toInstant().toEpochMilli()));
		return ts;
	}

	public static String toTimeZoneID(ZonedDateTime dt) {
		String s = (dt == null ? null : dt.getZone().getId());
		return s;
	}

	public static java.sql.Date toSQLDate(LocalDate ld) {
		// TODO - confirm this conversion always works, esp. across timezones
		java.sql.Date d = (ld == null ? null
				: new java.sql.Date(ld.atStartOfDay(paris).toInstant().toEpochMilli()));
		return d;
	}

	public static java.util.Date toJavaDate(LocalDate ld) {
		// TODO - confirm this conversion always works, esp. across timezones
		java.util.Date d = (ld == null ? null : Date.from(ld.atStartOfDay(paris).toInstant()));
		return d;
	}

	public static String toString(LocalDate ld) {
		// TODO - confirm this conversion always works, esp. across timezones
		String s = (ld == null ? null : localDateFormatter.withZone(ZoneId.of("UTC")).format(ld));
		return s;
	}

	public static java.sql.Timestamp toSQLTimestamp(LocalDateTime ldt) {
		// TODO - confirm this conversion always works, esp. across timezones
		java.sql.Timestamp ts = (ldt == null ? null : Timestamp.valueOf(ldt));
		return ts;
	}

	public static java.util.Date toJavaDate(LocalDateTime ldt) {
		// TODO - confirm this conversion always works, esp. across timezones
		java.util.Date d = (ldt == null ? null : Date.from(ldt.atZone(paris).toInstant()));
		return d;
	}

	public static java.sql.Time toSQLTime(LocalTime lt) {
		// TODO - confirm this conversion always works, esp. across timezones
		java.sql.Time t = (lt == null ? null : Time.valueOf(lt));
		return t;
	}

	public static Long toNanoSecond(LocalTime lt) {
		Long i = (lt == null ? null : new Long(lt.toNanoOfDay()));
		return i;
	}

	public static String toString(LocalTime lt) {
		String s = (lt == null ? null : lt.toString());
		return s;
	}

	// ///////////////////////////////////////////
	// From standard Java types to JodaTime types
	// ///////////////////////////////////////////
	
	public static ZonedDateTime toDateMidnight(java.sql.Date d) {
		ZonedDateTime dm = (d == null ? null : d.toLocalDate().atStartOfDay(paris));
		return dm;
	}

	public static ZonedDateTime toDateMidnight(java.util.Date d) {
		ZonedDateTime dm = (d == null ? null : toLocalDate(d).atStartOfDay(paris));
		return dm;
	}

	public static ZonedDateTime toDateMidnight(java.sql.Date d, String timeZoneID) {
		ZonedDateTime dm = (d == null ? null : d.toLocalDate().atStartOfDay(ZoneId.of(timeZoneID)));
		return dm;
	}

	public static ZonedDateTime toDateMidnight(java.util.Date d, String timeZoneID) {
		ZonedDateTime dm = (d == null ? null : toLocalDate(d).atStartOfDay(ZoneId.of(timeZoneID)));
		return dm;
	}

	public static ZonedDateTime toDateTime(java.sql.Timestamp ts) {
		// TODO - confirm this conversion always works, esp. across timezones
		ZonedDateTime dt = (ts == null ? null : ZonedDateTime.ofInstant(ts.toInstant(), paris));
		return dt;
	}

	public static ZonedDateTime toDateTime(java.util.Date d) {
		// TODO - confirm this conversion always works, esp. across timezones
		ZonedDateTime dt = (d == null ? null : ZonedDateTime.ofInstant(d.toInstant(), paris));
		return dt;
	}

	public static ZonedDateTime toDateTime(java.sql.Timestamp ts, String timeZoneID) {
		// TODO - confirm this conversion always works, esp. across timezones
		ZonedDateTime dt = (ts == null ? null : ZonedDateTime.ofInstant(ts.toInstant(), ZoneId.of(timeZoneID)));
		return dt;
	}

	public static ZonedDateTime toDateTime(java.util.Date d, String timeZoneID) {
		// TODO - confirm this conversion always works, esp. across timezones
		ZonedDateTime dt = (d == null ? null : ZonedDateTime.ofInstant(d.toInstant(), ZoneId.of(timeZoneID)));
		return dt;
	}

	public static LocalDate toLocalDate(java.sql.Date d) {
		// TODO - confirm this conversion always works, esp. across timezones
		LocalDate ld = (d == null ? null : d.toLocalDate());
		return ld;
	}

	public static LocalDate toLocalDate(java.util.Date d) {
		// TODO - confirm this conversion always works, esp. across timezones
		LocalDate ld = (d == null ? null : d.toInstant().atZone(paris).toLocalDate());
		return ld;
	}

	public static LocalDate toLocalDate(String s) {
		// TODO - confirm this conversion always works, esp. across timezones
		LocalDate ld = (s == null ? null : LocalDate.parse(s, localDateFormatter.withZone(ZoneId.of("UTC"))));
		return ld;
	}

	public static LocalDateTime toLocalDateTime(java.sql.Timestamp ts) {
		// TODO - confirm this conversion always works, esp. across timezones
		LocalDateTime ldt = (ts == null ? null : ts.toLocalDateTime());
		return ldt;
	}

	public static LocalDateTime toLocalDateTime(java.util.Date d) {
		// TODO - confirm this conversion always works, esp. across timezones
		LocalDateTime ldt = (d == null ? null : d.toInstant().atZone(paris).toLocalDateTime());
		return ldt;
	}

	public static LocalTime toLocalTime(java.sql.Time t) {
		// TODO - confirm this conversion always works, esp. across timezones
		LocalTime lt = (t == null ? null : t.toInstant().atZone(ZoneId.of("UTC")).toLocalTime());
		return lt;
	}

	public static LocalTime toLocalTime(Long i) {
		LocalTime lt = (i == null ? null : LocalTime.ofNanoOfDay(i));
		return lt;
	}

	public static LocalTime toLocalTime(String s) {
		LocalTime lt = (s == null ? null : LocalTime.parse(s));
		return lt;
	}

	// ///////////////////////////////////////////
	// Tests
	// ///////////////////////////////////////////

	public static void main(String[] args) {
		System.out.println(" ");
		test_dateTime();
		System.out.println(" ");
		test_dateTime_tz();
		System.out.println(" ");
		test_localDate();
		System.out.println(" ");
		test_localDate_tz();
		System.out.println(" ");
		test_localDateTime();
		System.out.println(" ");
		test_localDate_shift_java_tz();
		System.out.println(" ");
		test_localTime_as_integer();
		System.out.println(" ");
		test_localTime_as_string();
		System.out.println(" ");
	}

	public static void test_dateTime() {
		System.out.println("Test ZonedDateTime");
		ZonedDateTime dt1 = ZonedDateTime.now();
		java.sql.Timestamp ts = toSQLTimestamp(dt1);
		ZonedDateTime dt2 = toDateTime(ts);
		System.out.println("ZonedDateTime 1 = " + dt1);
		System.out.println("Timestamp  = " + ts);
		System.out.println("ZonedDateTime 2 = " + dt2);
		if (!dt2.equals(dt1)) {
			throw new IllegalStateException();
		}
	}

	public static void test_dateTime_tz() {
		System.out.println("Test ZonedDateTime with timezone");
		ZonedDateTime dt1 = ZonedDateTime.now(losAngeles);
		java.sql.Timestamp ts = toSQLTimestamp(dt1);
		String tzID = toTimeZoneID(dt1);
		ZonedDateTime dt2 = toDateTime(ts, tzID);
		System.out.println("ZonedDateTime 1 = " + dt1);
		System.out.println("Timestamp  = " + ts);
		System.out.println("TimeZoneID = " + tzID);
		System.out.println("ZonedDateTime 2 = " + dt2);
		if (!dt2.equals(dt1)) {
			throw new IllegalStateException();
		}
	}

	public static void test_localDate() {
		System.out.println("Test LocalDate");
		LocalDate ld1 = LocalDate.now();
		java.sql.Date d = toSQLDate(ld1);
		LocalDate ld2 = toLocalDate(d);
		System.out.println("LocalDate 1 = " + ld1);
		System.out.println("Date        = " + d);
		System.out.println("LocalDate 2 = " + ld2);
		if (!ld2.equals(ld1)) {
			throw new IllegalStateException();
		}
	}

	public static void test_localDate_tz() {
		System.out.println("Test LocalDate with timezone");
		LocalDate ld1 = LocalDate.now(losAngeles);
		java.sql.Date d = toSQLDate(ld1);
		LocalDate ld2 = toLocalDate(d);
		System.out.println("LocalDate 1 = " + ld1);
		System.out.println("Date        = " + d);
		System.out.println("LocalDate 2 = " + ld2);
		if (!ld2.equals(ld1)) {
			throw new IllegalStateException();
		}
	}

	public static void test_localDate_shift_java_tz() {
		System.out.println("Test LocalDate with shifted Java timezone");

		TimeZone originalTZ = TimeZone.getDefault();
		TimeZone losAngelesTZ = TimeZone.getTimeZone("America/Los_Angeles");

		TimeZone.setDefault(losAngelesTZ);
		LocalDate ld1 = LocalDate.now();
		System.out.println("ld1 LocalDate()   = " + ld1 + " when default TZ = " + TimeZone.getDefault());

		java.sql.Date d = toSQLDate(ld1);
		System.out.println("d toSQLDate(ld1)  = " + d + " when default TZ = " + TimeZone.getDefault());
		TimeZone.setDefault(originalTZ);
		System.out.println("d toSQLDate(ld1)  = " + d + " when default TZ = " + TimeZone.getDefault());

		LocalDate ld2 = toLocalDate(d);
		System.out.println("ld2 toLocalDate(d) = " + ld2 + " when default TZ = " + TimeZone.getDefault());

		TimeZone.setDefault(originalTZ);
		if (!ld2.equals(ld1)) {
			throw new IllegalStateException();
		}
	}

	public static void test_localDateTime() {
		System.out.println("Test LocalDateTime");
		LocalDateTime ldt1 = LocalDateTime.now();
		java.sql.Timestamp ts = toSQLTimestamp(ldt1);
		LocalDateTime ldt2 = toLocalDateTime(ts);
		System.out.println("LocalDateTime 1 = " + ldt1);
		System.out.println("Timestamp       = " + ts);
		System.out.println("LocalDateTime 2 = " + ldt2);
		if (!ldt2.equals(ldt1)) {
			throw new IllegalStateException();
		}
	}

	public static void test_localTime_as_integer() {
		System.out.println("Test LocalTime as Integer");
		LocalTime lt1 = LocalTime.now();
		Long i = toNanoSecond(lt1);
		LocalTime lt2 = toLocalTime(i);
		System.out.println("LocalTime 1 = " + lt1);
		System.out.println("Integer     = " + i);
		System.out.println("LocalTime 2 = " + lt2);
		if (!lt2.equals(lt1)) {
			throw new IllegalStateException();
		}
	}

	public static void test_localTime_as_string() {
		System.out.println("Test LocalTime as String");
		LocalTime lt1 = LocalTime.now();
		String t = toString(lt1);
		LocalTime lt2 = toLocalTime(t);
		System.out.println("LocalTime 1 = " + lt1);
		System.out.println("String      = " + t);
		System.out.println("LocalTime 2 = " + lt2);
		if (!lt2.equals(lt1)) {
			throw new IllegalStateException();
		}
	}

}