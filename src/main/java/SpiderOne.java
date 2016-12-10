import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created with spidertest.
 * User: hzyuqi1
 * Date: 2016/10/25
 * Time: 16:29
 * To change this template use File | Settings | File Templates.
 */

public class SpiderOne implements PageProcessor{

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);


    public void process(Page page){
        /*
        List<String> urls = page.getHtml().xpath("//div/figure/ul/li/a/@href").all();
        List<String> titles = page.getHtml().xpath("//div/figure/ul/li/a/text()").all();

        for(int i = 0 ; i< urls.size() ; i++){
            System.out.println("start to crawl site:" + urls.get(i));
            Spider.create(new SpiderTwo()).addUrl(urls.get(i)).thread(1).run();
        }*/

        for (int i = 0 ; i< 45 ; i++) {

            String url = "http://www.beautylegmm.com/index-" + new Integer(i+1).toString() + ".html";
            System.out.println("Start to crawl: " + url + " in SpiderOne");
            Spider.create(new BeautySpiderOne()).addUrl(url).thread(1).start();
        }


    }

    public Site getSite(){
        return site;
    }

    public static void main(String [] args){

        /*
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        executorService.scheduleAtFixedRate(new Runnable() {
            public void run() {
                Spider.create(new SpiderOne()).addUrl("http://www.beautylegmm.com").thread(4).run();
            }
        },0,5, TimeUnit.HOURS);
        */
        Spider.create(new SpiderOne()).addUrl("http://www.beautylegmm.com").thread(4).run();

    }


}
