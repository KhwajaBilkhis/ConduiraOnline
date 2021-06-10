
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.LinkedList;

public class feedsapi {
    public static void main(String[] args) {
        LinkedList<Feed> feeds = readRSS("http://feeds.marketwatch.com/marketwatch/topstories/");

        for (int i = 0; i < feeds.size(); i++) {
            System.out.println("-------\n" + feeds.get(i).toString());

        }
    }

    public static LinkedList<Feed> readRSS(String urlAddress) {
        LinkedList<Feed> feeds = new LinkedList<Feed>();

        try {
            URL rssUrl = new URL(urlAddress);
            BufferedReader in = new BufferedReader(new InputStreamReader(rssUrl.openStream()));

            String line;
            boolean flag = false;
            String t = "";
            String l = "";
            String p = "";
            while ((line = in.readLine()) != null) {
                if (line.contains("<item>")) {
                    flag = true;

                }
                if (flag) {
                    while (line != null && line.contains("<title>") == false)
                        line = in.readLine();
                    if (line != null && line.contains("<title>")) {
                        int firstPos = line.indexOf("<title>");
                        String temp = line.substring(firstPos);
                        temp = temp.replace("<title>", "");
                        int lastPos = temp.indexOf("</title>");
                        temp = temp.substring(0, lastPos);
                        t = temp;
                    }

                    while (line != null && line.contains("<link>") == false)
                        line = in.readLine();
                    if (line != null && line.contains("<link>")) {
                        int firstPos = line.indexOf("<link>");
                        String temp = line.substring(firstPos);
                        temp = temp.replace("<link>", "");
                        int lastPos = temp.indexOf("</link>");
                        temp = temp.substring(0, lastPos);
                        l = temp;
                    }
                    while (line != null && line.contains("<pubDate>") == false)
                        line = in.readLine();
                    if (line != null && line.contains("<pubDate>")) {
                        int firstPos = line.indexOf("<pubDate>");
                        String temp = line.substring(firstPos);
                        temp = temp.replace("<pubDate>", "");
                        int lastPos = temp.indexOf("</pubDate>");
                        temp = temp.substring(0, lastPos);
                        p = temp;
                    }

                    feeds.add(new Feed(t, l, p));
                    flag = false;
                    /*
                     * if (line.contains("<description>")) { int firstPos =
                     * line.indexOf("<description>"); String temp = line.substring(firstPos); temp =
                     * temp.replace("<description>", ""); sourceCode += "\ndis: " + temp + "\n";
                     * while ((line = in.readLine()) != null && !line.contains("</description>")) {
                     * sourceCode += temp; } int lastPos = line.indexOf("</description>"); temp =
                     * temp.substring(0, lastPos); sourceCode += temp + "\n\n------------";
                     * 
                     * }
                     */

                }

            }
            in.close();
            return feeds;
        } catch (MalformedURLException ue) {
            System.out.println("Mal Url");
        } catch (IOException ioe) {
            System.out.println("io exp");
        }
        return null;
    }
}
