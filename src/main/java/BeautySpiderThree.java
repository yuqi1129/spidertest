import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.ObjectArraySerializer;

import java.util.ArrayList;
import java.util.List;

import one.Beauty;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created with spidertest.
 * User: hzyuqi1
 * Date: 2016/10/25
 * Time: 20:35
 * To change this template use File | Settings | File Templates.
 */
public class BeautySpiderThree implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3);
    private JSONObject jsonObject ;


    public void process(Page page){

        List<String> url1 = page.getHtml().xpath("//*[@id=\"contents_post\"]/div[4]/a/@href").all();
        List<String> url2 = page.getHtml().xpath("//*[@id=\"contents_post\"]/div[4]/p[2]/a/@href").all();

        List<String> all = new ArrayList<String>();
        all.addAll(url1);
        all.addAll(url2);

        for (String s : all){
            Beauty beauty = new Beauty();
            beauty.setName((String)jsonObject.get("name"));

            String [] pic = s.split("/");
            beauty.setPictureName(pic[pic.length-1]);
            beauty.setUrl(s);

            System.out.println(beauty);
            page.putField(s,beauty);
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

    public BeautySpiderThree(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public BeautySpiderThree() {
    }
}