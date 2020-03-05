package com.zenhomes.electricity.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.zenhomes.electricity.exception.TimeFormatException;

@RunWith(MockitoJUnitRunner.class)
public class TimeUtilsTest
{

    @InjectMocks
    private TimeUtils timeUtils;

    @Test
    public void testConvertTimeIntoMilliswhenD() throws Exception
    {
        Assert.assertEquals(timeUtils.convertTimeIntoMillis("24d"), System.currentTimeMillis() - 24 * 24 * 60 * 60 * 1000);
    }


    @Test
    public void testConvertTimeIntoMilliswhenH() throws Exception
    {
        Assert.assertEquals(timeUtils.convertTimeIntoMillis("24h"), System.currentTimeMillis() - 24 * 60 * 60 * 1000);
    }


    @Test
    public void testConvertTimeIntoMilliswhenM() throws Exception
    {
        Assert.assertEquals(timeUtils.convertTimeIntoMillis("24m"), System.currentTimeMillis() - 24 * 60 * 1000);
    }


    @Test(expected = TimeFormatException.class)
    public void testConvertTimeIntoMilliswhenException() throws Exception
    {
        timeUtils.convertTimeIntoMillis("24f");

    }
}
