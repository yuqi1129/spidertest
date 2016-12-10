package two;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created with spidertest.
 * User: hzyuqi1
 * Date: 2016/12/10
 * Time: 12:39
 * To change this template use File | Settings | File Templates.
 */

public class SaveBaoSpider {

    private static final Logger logger = LoggerFactory.getLogger(SaveBaoSpider.class);

    private String BasePath = "E:\\image";
    private String url ;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public SaveBaoSpider(String url) {
        this.url = url;
    }

    public SaveBaoSpider() {
    }

    public void saveResource(){
        System.out.println("come here ,url = " + url);
        HttpClient httpClient = HttpClients.createDefault();
        HttpResponse httpResponse = null ;
        try {
            HttpGet httpGet = new HttpGet(url);
            int tryTimes = 0 ;
            for (tryTimes = 0 ;tryTimes < 3 ;tryTimes ++) {
                httpResponse = httpClient.execute(httpGet);
                if (httpResponse.getStatusLine().getStatusCode() / 100 == 2) {
                    break;
                }
            }
            if (tryTimes == 3){
                System.out.println("get " + url + "  error");
                return ;
            }

            String [] path = URLDecoder.decode(url,"UTF-8").split("/");
            System.out.println(path);
            int index = 0 ;
            for(int i= 0 ; i< path.length ;i++){
                if (path[i].equals("10g")) {
                    index = i;
                }
            }
            System.out.println("index " + String.valueOf(index) + " length:" + String.valueOf(path.length));
            String filepath = BasePath ;
            for (int i= index ;i < path.length ; i ++){

                filepath += (File.separator + path[i]);
            }
            System.out.println(filepath);

            File file = new File(filepath);
            if (!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            if (!file.exists())
                file.createNewFile();

            InputStream inputStream = httpResponse.getEntity().getContent();
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            byte [] b = new byte[4096 ];
            int len = 0 ;
            while((len = inputStream.read(b)) != -1){
                fileOutputStream.write(b,0,len);
                b = new byte[4096 ];
            }
            System.out.println(String.format("save %s success",filepath));
            fileOutputStream.flush();
            fileOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("error:" + e);
        }
    }

}
