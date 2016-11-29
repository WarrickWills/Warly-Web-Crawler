package adaassignment2;

import java.util.Scanner;

/**
 *
 * @author Warrick Wills - 13831575
 * Billy Galera - 13835546
 */
public class CUI {
    
    private String imgCount;
    private int imgCountAsInt;
    private String depth;
    private int depthAsInt;
    String url = "";
    
    public CUI()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to War-ly webcrawler");
        System.out.println("=========================");
        System.out.println("Please enter URL of desired site: ");
        url = sc.nextLine();
        url = convertToURL(url);
        System.out.println("Please enter the keyword: ");
        String keyword = sc.nextLine();
        System.out.println("Please enter minimum image count sought for in links: ");
        imgCount = sc.nextLine();
        while(!isInteger(imgCount))
        {
            System.out.println("Invalid image count");
            System.out.println("Please enter minimum image count sought for in links: ");
            imgCount = sc.nextLine();
        }
        imgCountAsInt = Integer.parseInt(imgCount);
        System.out.println("Please enter depth of crawl: ");
        depth = sc.nextLine();
        while(!isInteger(depth))
        {
            System.out.println("Invalid depth");
            System.out.println("Please enter depth of crawl: ");
            depth = sc.nextLine();
        }
        depthAsInt = Integer.parseInt(depth);
        System.out.println("========RESULTS:=======");
        String[] seeds = {url};
        Spider sp = new Spider(seeds, keyword, imgCountAsInt, depthAsInt);
        sp.crawl();
        System.out.println("========FINISHED=======");
    }

    public static void main(String[] args) {
        CUI cui = new CUI();
        String status = "";
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            System.out.println("Enter R to make a new search");
            System.out.println("Enter X to exit");
            status = sc.nextLine();
            if(status.equals("R") || status.equals("r"))
                cui = new CUI();
            if(status.equals("X") || status.equals("x"))
                break;            
        }
    }
    
    
    public boolean isInteger(String toConvert) 
    {
        try { 
            Integer.parseInt(toConvert); 
        } catch(NumberFormatException e) { 
            return false; 
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }
    
    public boolean verifyURL(String toVerify)
    {
        return true;
    }
    
    public String convertToURL(String toConvert)
    {
        String converted = "";
        if(!toConvert.equals("http://" + toConvert))
        {
            converted = "http://";
            converted += toConvert;
        }
        return converted;
    }
}
