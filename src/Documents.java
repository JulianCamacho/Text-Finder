public class Documents {

    private String path;
    private String text;
    private String name;
    private int size;
    private String date;

    protected Documents next;

    public Documents(String path, String text, String name, int size, String date) {
        this.path = path;
        this.text = text;
        this.name = name;
        this.size = size;
        this.date = date;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
