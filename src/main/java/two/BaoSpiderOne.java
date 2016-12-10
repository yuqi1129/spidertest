package two;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created with spidertest.
 * User: hzyuqi1
 * Date: 2016/12/10
 * Time: 12:17
 * To change this template use File | Settings | File Templates.
 */

public class BaoSpiderOne implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    public void process(Page page){
        List<String> urls = page.getHtml().xpath("/html/body/pre/a/@href").all() ;
        for (String url :urls){
            if (urls.indexOf(url) == 0)
                continue;
            Spider.create(new BaoSpiderTwo()).addUrl(url).thread(1).start();
        }
    }

    public Site getSite(){
        return site;
    }

    public static void main(String [] args){
        Spider.create(new BaoSpiderOne()).addUrl("https://www.jiedaibao10g.com/10g/").thread(4).run();
    }
}
