package one;

import java.io.File;
import java.net.URLDecoder;

/**
 * Created with spidertest.
 * User: hzyuqi1
 * Date: 2016/10/25
 * Time: 20:02
 * To change this template use File | Settings | File Templates.
 */

public class Beauty {
    private String name;
    private String pictureName;
    private String url;

    public String getPictureName() {
        return pictureName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Beauty{" +
                "name='" + name + '\'' +
                ", pictureName='" + pictureName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public static void main(String [] args){

        String path = "D:\\img";
        path = path + File.separator + "img.png";
        System.out.println(path);
        String keyWord = null;
        try{
            keyWord = URLDecoder.decode("%E6%96%87%E6%A1%A3", "UTF-8");
        }catch (Exception e){

        }

        System.out.println(keyWord);

    }
}
