package adaassignment2;

import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Warrick Wills - 13831575
 * Billy Galera - 13835546
 */
public class SpiderLeg {

    private String url = "";
    private Connection connection;
    private Document document;
    private final String agent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36";

    private void initialiseConnection(String url) {
        if (connection == null || this.url.equalsIgnoreCase(url)) {
            try {
                this.connection = Jsoup.connect(url);
                this.connection.timeout(0);
                this.connection.userAgent(agent);
                this.url = url;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getTitle(String url) {
        initialiseConnection(url);
        try {
            Document doc = connection.get();
            return doc.title();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "no title";
    }

    public String[] getHyperlink(String url) {
        initialiseConnection(url);
        try {
            Document doc = connection.get();
            Elements link = doc.getElementsByTag("a");
            String[] ret = new String[link.size()];
            int index = 0;
            for (Element e : link) {
                ret[index] = e.attr("abs:href");
                ++index;
            }
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] getImages(String url) {
        initialiseConnection(url);

        try {
            Document doc = connection.get();
            Elements elements = doc.select("img");
            String[] toRet = new String[elements.size()];
            int index = 0;
            for (Element e : elements) {
                String ret = "";
                ret += (e.attr("src") + " ");
                ret += (e.attr("width") + " ");
                ret += (e.attr("height") + " ");
                ret += (e.attr("alt") + " ");
                toRet[index] = ret;
                ++index;
            }
            return toRet;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getMeta(String url) {
        initialiseConnection(url);
        try {
            Document doc = connection.get();
            for (Element e : doc.select("meta")) {
                System.out.println(e.attr("name"));
                System.out.println(e.attr("content"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isPageValid(String url) {
        try {
            initialiseConnection(url);
            Document doc = connection.get();
            return doc != null;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean searchMetaKeyword(String url, String keyword) {
        try {
            initialiseConnection(url);
            Elements el = connection.get().getElementsByTag("meta");
            for (Element e : el) {
                if (e.attr("name").equalsIgnoreCase("keywords")) {
                    String[] keywords = e.attr("content").trim().split(",");
                    for (String s : keywords) {
                        if (s.toLowerCase().trim().contains(keyword.toLowerCase())) {
                            return true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

//    public static void main(String[] args) {
//        SpiderLeg sp = new SpiderLeg();
//        System.out.println(sp.getTitle("http://www.w3schools.com"));
//        String [] links = sp.getHyperlink("http://reddit.com");
//        for (String s : links) {
//            System.out.println("url : "+ s);
//        }
//        sp.getImages("http://reddit.com");
//        System.out.println(sp.searchMetaKeyword("http://www.w3schools.com", "html"));
//    }

}
