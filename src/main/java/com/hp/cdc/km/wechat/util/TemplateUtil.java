package com.hp.cdc.km.wechat.util;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * WechatUser: zhouqi
 * Date: 14-9-6
 * Time: PM6:23
 * To change this template use File | Settings | File Templates.
 */
public class TemplateUtil {
    private static Configuration cfg;

    static {
        cfg = new Configuration();
        cfg.setObjectWrapper(new DefaultObjectWrapper());
    }


    public static String merge( Map<String, Object> model,String source)
    {
        try {
            Template t = new Template("templateName", new StringReader(source), cfg);
            Writer out = new StringWriter();
            t.process(model, out);

            String transformedTemplate = out.toString();

            return transformedTemplate;
        } catch (Exception e) {
            return source;
        }

    }
}
