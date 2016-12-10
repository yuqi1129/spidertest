import com.alibaba.fastjson.JSONObject;

import java.util.List;

import one.MysqlPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created with spidertest.
 * User: hzyuqi1
 * Date: 2016/10/25
 * Time: 20:28
 * To change this template use File | Settings | File Templates.
 */

public class BeautySpiderTwo implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3);
    private JSONObject jsonObject ;

    public void process(Page page){

        List<String> urls = page.getHtml().xpath("//*[@id=\"contents_post\"]/div[4]/div[3]/a/text()").all();

        int number  = 0 ;
        try {
            number = new Integer(urls.get(urls.size() - 2));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        for(int i = 1; i< number ; i++){
            String url = page.getUrl() + "?page=" + String.valueOf(i);
            System.out.println("Start to crawl: " + url + " in BeautySpiderTwo");

            JSONObject jsonObject = new JSONObject();
            String [] names = page.getUrl().toString().split("/");
            jsonObject.put("name",names[3]);
            Spider.create(new BeautySpiderThree(jsonObject)).addUrl(url).addPipeline(new MysqlPipeline()).thread(1).start();
        }
    }

    public Site getSite(){
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public BeautySpiderTwo(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public BeautySpiderTwo() {
    }
}