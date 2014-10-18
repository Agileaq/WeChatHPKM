package com.hp.cdc.km.wechat.util;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * WechatUser: zhouqi
 * Date: 14-9-6
 * Time: PM7:00
 * To change this template use File | Settings | File Templates.
 */
public class TemplateUtilTest extends TestCase {

    @Test
    public void test()
    {
        HashMap<String, Object> context = new HashMap<String, Object>();
        context.put("param1", "value1");
        String rst = TemplateUtil.merge(context, "This is a test case for ${param1}");

        Assert.assertEquals("This is a test case for value1", rst);


    }
}
