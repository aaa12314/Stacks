package cn.net.stacks.Tool.Crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * zh-CN: 爬虫内核
 * <br>en-US: Crawler Core
 *
 * @author Kaiser.zsk
 * @version 1.0
 */
public class Core {

    /**
     * zh-CN: 解析字节流获取页面Dom对象
     * <br>en-US: Parse byte stream to get page Dom object
     *
     * @param Html Html
     * @return Dom对象
     */
    public static Document AnalysisPageDom(String Html) {
        try {
            //将String对象转换为Dom对象
            return Jsoup.parseBodyFragment(Html);
        } catch (Exception e) {
            System.out.println("解析页面失败 - {}" + e.getMessage());
            return null;
        }
    }

    /**
     * zh-CN: 定制 采集页面链接集合
     * <br>en-US: Customized - Acquisition page links set
     *
     * @param Dom         目标页面Dom
     * @param FilterRegex 过滤正则表达式
     * @param CheckRegex  校验正则表达式
     * @param Prefix      链接前缀
     * @return 页内链接集
     */
    private static Map<String, Boolean> PageLinks(Document Dom, String FilterRegex, String CheckRegex, String Prefix) {
        //创建链接临时存储集合
        Map<String, Boolean> Links = new HashMap<String, Boolean>();
        //解析Dom并检索页内A标签
        Elements dom = Dom.select("a");
        //循环遍历获取页内全部A标签
        for (Element link : dom) {
            //检出A标签内附链接
            String linkToString = link.attr("href");
            //装载过滤正则表达式
            Pattern pattern = Pattern.compile(FilterRegex);
            //创建正则检测器
            Matcher matcher;
            //检查内附链接
            matcher = pattern.matcher(linkToString);
            //判断是否匹配
            if (matcher.find()) {
                //装载校验正则表达式
                Pattern patterns = Pattern.compile(CheckRegex);
                //创建正则检测器
                Matcher matchers;
                //校验链接
                matchers = patterns.matcher(linkToString);
                //判断是否匹配
                if (matchers.find()) {
                    //将链接加入临时存储集合
                    Links.put(linkToString, true);
                } else {
                    //校验链接
                    matchers = patterns.matcher(Prefix + linkToString);
                    if (matchers.find())
                        Links.put(Prefix + linkToString, true);
                }
            }
        }
        return Links;
    }

    /**
     * zh-CN: 内置 采集页面字符集
     * <br>en-US: Built-in - Acquisition page character set
     *
     * @param Dom 目标页面Dom
     * @return 页面字符集
     */
    public static String PageCharacterSetn(Document Dom) {
        //解析Dom并检索页面字符集设定
        Elements dom = Dom.select("meta[charset]");
        //返回页面字符集
        return dom.get(0).attr("charset").trim();
    }

    /**
     * zh-CN: 内置 采集页面标题
     * <br>en-US: Built-in - Collection page title
     *
     * @param Dom 目标页面Dom
     * @return 页面标题
     */
    public static String PageTitle(Document Dom) {
        //解析Dom并检索页面标题
        Element dom = Dom.select("title").first();
        //返回页面标题
        return dom.text().trim();
    }

    /**
     * zh-CN: 定制 采集页面标题
     * <br>en-US: Customized - Collection page title
     *
     * @param Dom 目标页面Dom
     * @return 页面标题
     */
    public static String PageTitle(Document Dom, String Range) {
        //解析Dom并检索页面标题
        Element dom = Dom.select(Range).first();
        //返回页面标题
        return dom.text().trim();
    }

    /**
     * zh-CN: 内置 采集页面描述
     * <br>en-US: Built-in - Acquisition page description
     *
     * @param Dom 目标页面Dom
     * @return 页面描述
     */
    public static String PageDescription(Document Dom) {
        //解析Dom并检索页面描述信息
        Elements dom = Dom.select("meta[name=description]");
        //返回页面描述
        return dom.get(0).attr("content").trim();
    }

    /**
     * zh-CN: 定制 采集页面描述
     * <br>en-US: Customized - Acquisition page description
     *
     * @param Dom 目标页面Dom
     * @return 页面描述
     */
    public static String PageDescription(Document Dom, String Range) {
        //解析Dom并检索页面描述信息
        Elements dom = Dom.select("meta[name=" + Range + "]");
        //返回页面描述
        return dom.get(0).attr("content").trim();
    }

    /**
     * zh-CN: 定制 采集页面图像集
     * <br>en-US: Customized - Collection page image set
     *
     * @param Dom   目标页面Dom
     * @param Range 采集范围
     * @param Src   图像链接位置
     * @return 页面图像集
     */
    public static List<String> PagePictureList(Document Dom, String Range, String Src) {
        //初始化图像临时存储集合
        List<String> Images = new ArrayList<String>();
        //解析Dom并检索页内图像
        Elements dom = Dom.select(Range + " img");
        //循环遍历获取范围内全部图像
        for (Element img : dom) {
            //将图像装载入临时存储集合
            Images.add(img.attr(Src));
        }
        //返回图像集合
        return Images;
    }

    /**
     * zh-CN: 定制 采集页面正文内容
     * <br>en-US: Customized - Collecting the main content of the page
     *
     * @param Dom   目标页面Dom
     * @param Range 采集范围
     * @return 页面正文内容
     */
    public static String PageContent(Document Dom, String Range, String Remove) {
        //解析Dom并检索页面正文内容
        Element dom = Dom.select(Range).first();
        //判断是否需要删除内容
        if (Remove != null) {
            //解析Dom获取需要删除的内容
            Elements RemoveContent = dom.select(Remove);
            //遍历内容集合
            for (Element remove : RemoveContent) {
                //删除内容
                remove.remove();
            }
        }
        //清除不受信任的HTML代码
        String Content = Jsoup.clean(dom.html(), Whitelist.basicWithImages());
        //返回页面正文内容
        return Content;
    }

    /**
     * zh-CN: 定制 采集页面正文内容
     * <br>en-US: Customized - Collecting the main content of the page
     *
     * @param Dom    目标页面Dom
     * @param Range  采集范围
     * @param Remove 排除内容
     * @param imgSrc 图像链接位置
     * @return 页面正文内容
     */
    public static String PageContent(Document Dom, String Range, String Remove, String imgSrc) {
        //解析Dom并检索页面正文内容
        Element dom = Dom.select(Range).first();
        //判断是否需要删除内容
        if (Remove != null) {
            //解析Dom获取需要删除的内容
            Elements RemoveContent = dom.select(Remove);
            //遍历内容集合
            for (Element remove : RemoveContent) {
                //删除内容
                remove.remove();
            }
        }
        //解析正文内容获取内置图像
        Elements images = dom.getElementsByTag("img");
        //遍历图像集合
        for (Element image : images) {
            //获取图像真实地址
            String Src = image.attr(imgSrc);
            //修改图像地址
            image.attr("src", Src);
        }
        //清除不受信任的HTML代码
        String Content = Jsoup.clean(dom.html(), Whitelist.basicWithImages());
        //返回页面正文内容
        return Content;
    }

    /**
     * zh-CN: 定制 采集页面下载链接
     * <br>en-US: Customized - Acquisition page down link
     *
     * @param Dom 目标页面Dom
     * @return 页面描述
     */
    public static String PageDown(Document Dom, String Range) {
        String why = Range.substring(0,Range.indexOf(";"));
        //解析Dom并检索页面下载链接
        Elements dom = Dom.select(Range.substring(0,Range.indexOf(";")));
        //返回页面下载链接
        return dom.get(0).attr(Range.substring(Range.indexOf(";")+1,Range.length())).trim();
    }

    /**
     * zh-CN: 定制 采集页面时间
     * <br>en-US: Customized - Acquisition page date
     *
     * @param Dom 目标页面Dom
     * @return 页面描述
     */
    public static Date PageDate(Document Dom, String Range) {
        //解析Dom并检索页面时间
        Elements dom = Dom.select(Range);
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy年MM月dd日");
        try {
            //返回页面时间
            return SDF.parse(dom.get(0).text().trim());
        } catch (ParseException e) {
            System.out.println("时间转换错误");
            return null;
        }
    }

    /**
     * zh-CN: 定制 采集页面磁力链接
     * <br>en-US: Built-in - Collection page magnetic links
     *
     * @param Dom 目标页面Dom
     * @return 磁力链接
     */
    public static List<String> MagnetURIScheme(Document Dom) {

        HashSet<String> Hash = new HashSet<String>();
        //解析Dom并检索页面外链
        Elements dom = Dom.select("a[href]");
        for (Element MagnetTag : dom) {
            String URIHref = MagnetTag.attr("href");
            if (URIHref.contains("magnet:?xt=urn:btih:")) {
                Hash.add(URIHref.substring(URIHref.indexOf("magnet:?xt=urn:btih:"), URIHref.indexOf("magnet:?xt=urn:btih:") + 40));
            }
        }
        List<String> Uri = new ArrayList<String>();
        Uri.addAll(Hash);
        return Uri;
    }

}