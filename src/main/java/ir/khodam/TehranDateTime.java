package ir.khodam;

import com.github.mfathi91.time.PersianDateTime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Utility methods related to date and time in tehran zone.
 *
 * @author Alireza Pourtaghi
 */
public final class TehranDateTime {
    public static final ZoneId TEHRAN_ZONE = ZoneId.of("+03:30");
    public static final DateTimeFormatter YY_MM = DateTimeFormatter.ofPattern("yyMM");
    public static final DateTimeFormatter YY_MM_DD = DateTimeFormatter.ofPattern("yyMMdd");
    public static final DateTimeFormatter YY_MM_DD_HH_MM_SS_S6 = DateTimeFormatter.ofPattern("yyMMddHHmmssSSSSSS");
    public static final DateTimeFormatter YY_MM_DD_HH_MM_SS_S4 = DateTimeFormatter.ofPattern("yyMMddHHmmssSSSS");

    public static PersianDateTime now() {
        return PersianDateTime.fromGregorian(LocalDateTime.ofInstant(Instant.now(), TEHRAN_ZONE));
    }
}
