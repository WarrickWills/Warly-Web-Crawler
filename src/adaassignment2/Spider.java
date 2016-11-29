package adaassignment2;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author Warrick Wills - 13831575
 * Billy Galera - 13835546
 */
public class Spider {

    private String[] seeds;
    //private String title;
    private String keyword;
    private int imgCount;
    private int maxPage = 5;
    private int maxDepth;
    private int i;
    private SpiderLeg sl;

    private Queue<Node> urls;
    private Set<Node> visitedUrls;

    public Spider(String[] seeds, String keyword, int imgCount, int depth) {
        this.seeds = seeds;
        //this.title = title;
        this.keyword = keyword;
        this.imgCount = imgCount;
        urls = new LinkedList<>();
        visitedUrls = new LinkedHashSet<>();
        this.sl = new SpiderLeg();
        this.maxDepth = depth;
        for (String seed : seeds) {
            Node n = new Node(seed, 0);
            urls.add(n);
        }
        i = 0;
    }

    public boolean rules(String url, int imgCount, String kWord) {
        return maxPage > i && sl.isPageValid(url) && sl.getImages(url).length > imgCount && sl.searchMetaKeyword(url, kWord);
    }

    public void crawl() {
        while (!urls.isEmpty()) {
            Node n = urls.poll();
            visitedUrls.add(n);
            if (maxDepth <= (n.getDistance())) {
                break;
            }
            if (sl.isPageValid(n.getUrl())) {
                String[] links = sl.getHyperlink(n.getUrl());
                for (String s : links) {
                    if (rules(s, imgCount, keyword)) {
                        Node node = new Node(s, n.getDistance() + 1);
                        if (!urls.contains(node)) {
                            System.out.println(s + " depth : " + node.getDistance());
                            urls.add(node);
                            visitedUrls.add(n);
                            ++i;
                        }
                    }
                }
                i = 0;
            }

        }
    }

//    public static void main(String[] args) {
//        String[] seds = {"http://reddit.com"};
//        Spider s = new Spider(seds, "reddit", 5, 2);
//        s.crawl();
//    }
}
