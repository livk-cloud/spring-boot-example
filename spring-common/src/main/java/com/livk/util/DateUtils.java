package com.livk.util;

import lombok.experimental.UtilityClass;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * <p>
 * DateUtils
 * </p>
 *
 * @author livk
 * @date 2022/6/7
 */
@UtilityClass
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	public static final DateTimeFormatter YMD_HMS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public static final DateTimeFormatter YMD = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	/**
	 * LocalDateTime 转时间戳
	 */
	public static Long getTimeStamp(LocalDateTime localDateTime) {
		return localDateTime.atZone(ZoneId.systemDefault()).toEpochSecond();
	}

	/**
	 * 时间戳转LocalDateTime
	 */
	public static LocalDateTime fromTimeStamp(Long timeStamp) {
		return LocalDateTime.ofEpochSecond(timeStamp, 0, OffsetDateTime.now().getOffset());
	}

	/**
	 * LocalDateTime 转 Date
	 */
	public static Date toDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * LocalDate 转 Date
	 */
	public static Date toDate(LocalDate localDate) {
		return toDate(localDate.atTime(LocalTime.now(ZoneId.systemDefault())));
	}

	/**
	 * Date转 LocalDateTime
	 */
	public static LocalDateTime toLocalDateTime(Date date) {
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}

	/**
	 * 日期 格式化
	 */
	public static String localDateTimeFormat(LocalDateTime localDateTime, String patten) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patten);
		return localDateTimeFormat(localDateTime, formatter);
	}

	/**
	 * 日期 格式化
	 */
	public static String localDateTimeFormat(LocalDateTime localDateTime, DateTimeFormatter formatter) {
		return formatter.format(localDateTime);
	}

	/**
	 * 字符串转 LocalDateTime
	 */
	public static LocalDateTime parseLocalDateTimeFormat(String localDateTime, String pattern) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
		return parseLocalDateTimeFormat(localDateTime, dateTimeFormatter);
	}

	/**
	 * 字符串转 LocalDateTime
	 */
	public static LocalDateTime parseLocalDateTimeFormat(String localDateTime, DateTimeFormatter dateTimeFormatter) {
		return LocalDateTime.from(dateTimeFormatter.parse(localDateTime));
	}

}
