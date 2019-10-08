import java.io.File;

public class Documents {

    private File file;
    private String path;
    //private String text;
    private String name;
    private String size;
    private String date;
    private int realSize;

    protected Documents next = null;
    protected Documents prev = null;

    public Documents(File file, String path, String name, String size, String date) {
        this.file = file;
        this.path = path;
        //this.text = text;
        this.name = name;
        this.size = size;
        this.date = date;
        this.realSize = toInt(this.size);
    }

    public static int toInt(String size){
        String subSize[] = size.split(" ");
        int result = 0;
        int exp = 0;
        for(int i = subSize.length-1; i == 0; i--, exp++){
            switch (subSize[i]){
                case "1": {result += Math.pow(1, exp);}
                case "2": {result += Math.pow(2, exp);}
                case "3": {result += Math.pow(3, exp);}
                case "4": {result += Math.pow(4, exp);}
                case "5": {result += Math.pow(5, exp);}
                case "6": {result += Math.pow(6, exp);}
                case "7": {result += Math.pow(7, exp);}
                case "8": {result += Math.pow(8, exp);}
                case "9": {result += Math.pow(9, exp);}
            }
        }
        return result;
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

    public int getRealSize() {
        return realSize;
    }

    public void setRealSize(int realSize) {
        this.realSize = realSize;
    }
}
