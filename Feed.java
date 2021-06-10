
class Feed {
    String title;
    String link;
    String pubDate;

    Feed(String t, String l, String p) {
        title = t;
        link = l;
        pubDate = p;
    }

    public String toString() {
        return title + "\n" + link + "\n" + pubDate;
    }
}