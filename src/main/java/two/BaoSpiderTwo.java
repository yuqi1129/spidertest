package two;

import java.util.List;
import java.util.Random;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created with spidertest.
 * User: hzyuqi1
 * Date: 2016/12/10
 * Time: 12:24
 * To change this template use File | Settings | File Templates.
 */

public class BaoSpiderTwo implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
    public void process(Page page){
        List<String> urls1 = page.getHtml().xpath("/html/body/pre/a/@href").all();
        for(String url : urls1){
            if (urls1.indexOf(url) == 0)
                continue;
            try{
                if (url.endsWith(".jps") || url.endsWith(".png") || url.endsWith(".mp4") || !url.endsWith("/")){
                Thread.sleep(new Random().nextInt(1000));
                new SaveBaoSpider(url).saveResource();
                continue;
                }
            }catch (Exception e){
                System.out.println(e);
            }
            Spider.create(new BaoSpiderTwo()).addUrl(url).thread(1).start();
        }
    }

    public Site getSite(){
        return site;
    }
}
