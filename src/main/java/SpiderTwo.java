import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created with spidertest.
 * User: hzyuqi1
 * Date: 2016/10/25
 * Time: 19:24
 * To change this template use File | Settings | File Templates.
 */

public class SpiderTwo implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);


    public void process(Page page){
        //Page
        String message = page.getHtml().toString();
        //System.out.println(message);
        //
    }

    public Site getSite(){
        return this.site;
    }

}
