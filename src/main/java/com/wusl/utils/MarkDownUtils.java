package com.wusl.utils;


import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TableBlock;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.internal.util.Html5Entities;
import org.commonmark.node.Image;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.commonmark.renderer.html.HtmlRenderer;
import org.hibernate.CustomEntityDirtinessStrategy;

import java.util.*;

/*
 * markdown转换htmml
 * */
public class MarkDownUtils {


    /*
     * markdown 格式转换成Html格式
     *
     * */
    public static String markdownToHtml(String markdown) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer build = HtmlRenderer.builder().build();
        return build.render(document);
    }


    /*
     * 增加扩展【标题锚点，表格生成】
     * */

    public static String markdownToHtmlExtensions(String markdown) {
        //h标题生成id
        Set<Extension> heading = Collections.singleton(HeadingAnchorExtension.create());
        //转换table的Html
        List<Extension> tableExcension = Arrays.asList(TablesExtension.create());
        Parser parser = Parser.builder().extensions(tableExcension).build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().extensions(heading)
                .extensions(tableExcension)
                .attributeProviderFactory(new AttributeProviderFactory() {
                    public AttributeProvider create(AttributeProviderContext context) {
                        return new CustomAttributeProvider();
                    }
                }).build();

        return renderer.render(document);

    }


    /*
     * 处理标签的属性
     * */
    static class CustomAttributeProvider implements AttributeProvider {
        @Override
        public void setAttributes(Node node, String s, Map<String, String> map) {
            //改变a标签的target属性_blank
            if (node instanceof Link) {
                map.put("target", "_blank");
            }
            if (node instanceof TableBlock) {
                map.put("class", "ui celled table");
            }
            if (node instanceof Image){
                map.put("class","upldImg");
            }
        }
    }

}
