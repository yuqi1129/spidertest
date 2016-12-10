import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created with spidertest.
 * User: hzyuqi1
 * Date: 2016/10/25
 * Time: 20:18
 * To change this template use File | Settings | File Templates.
 */

public class BeautySpiderOne implements PageProcessor{

    private Site site = Site.me().setRetryTimes(3);

    public void process(Page page){

        List<String> urls = page.getHtml().xpath("//*[@id=\"content\"]/div/a/@href").all();
        for(int i = 2; i< urls.size() ; i++){
            System.out.println("Start to crawl: " + urls.get(i) + " in BeautySpiderOne");
            Spider.create(new BeautySpiderTwo()).addUrl(urls.get(i)).thread(1).start();
        }
    }

    public Site getSite(){
        return site;
    }

}
