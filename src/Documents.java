import java.io.File;

public class Documents {

    private File file;
    private String absPath;
    private String text;
    private String name;
    private String size;
    private String date;
    private int realSize;

    protected Documents next = null;
    protected Documents prev = null;

    public Documents(String absPath, String text, String name, String size, String date) {
        this.absPath = absPath;
        toFile(absPath);
        this.text = text;
        this.name = name;
        this.size = size;
        this.date = date;
        this.realSize = toInt(this.size);
    }

    public void toFile(String path){
        try{
            this.file = new File(path);
        } catch (NullPointerException e){
            AlertBoxes.displayAlertBox("Exception", "Invalid File");
        }
    }

    public static int toInt(String size){
        String[] subString = size.split(" ");
        String reallyRealSize = subString[0];
        return Integer.parseInt(reallyRealSize);
    }

    public String getText() { return text; }

    public void setText(String text) { this.text = text; }

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

    public int getRealSize() { return realSize; }

    public void setRealSize(int realSize) { this.realSize = realSize; }

    public File getFile() { return file; }

    public String getAbsPath() { return absPath; }

    public void setAbsPath(String absPath) { this.absPath = absPath; }
}
