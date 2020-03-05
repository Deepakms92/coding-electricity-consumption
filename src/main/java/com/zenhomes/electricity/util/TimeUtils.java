package com.zenhomes.electricity.util;
/**
 * Util class to get the time in millis.
 *
 * @author Deepak Srinivas
 */
import org.springframework.stereotype.Component;

import com.zenhomes.electricity.exception.TimeFormatException;

@Component
public class TimeUtils
{

    /**
     * Method to convert the given time in string to time in milliseconds
     * @param duration string duration coming as part of request.
     * @return time in millis.
     */
    public long convertTimeIntoMillis(String duration)
    {
        long timeAgoInMillis = 0;
        if (duration.contains("d"))
        {
            String days = duration.replaceFirst("d", "");
            long daysAgo = Long.valueOf(days);
            timeAgoInMillis = System.currentTimeMillis() - daysAgo * 24 * 60 * 60 * 1000;
        }
        else if (duration.contains("h"))
        {
            String hours = duration.replaceFirst("h", "");
            long hourAgo = Long.valueOf(hours);
            timeAgoInMillis = System.currentTimeMillis() - hourAgo * 60 * 60 * 1000;

        }
        else if (duration.contains("m"))
        {
            String minute = duration.replaceFirst("m", "");
            long minutesAgo = Long.valueOf(minute);
            timeAgoInMillis = System.currentTimeMillis() - minutesAgo * 60 * 1000;

        }
        else
        {
            throw new TimeFormatException("Number format not matched try either h or d or m with the duration in the request URL");
        }
        return timeAgoInMillis;
    }
}
