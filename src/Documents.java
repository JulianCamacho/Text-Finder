import java.io.File;

public class Documents {

    private File file;
    private String path;
    //private String text;
    private String name;
    private String size;
    private String date;

    protected Documents next = null;
    protected Documents prev = null;

    public Documents(File file, String path, String name, String size, String date) {
        this.file = file;
        this.path = path;
        //this.text = text;
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

    //public String getText() { return text; }

   // public void setText(String text) { this.text = text; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
