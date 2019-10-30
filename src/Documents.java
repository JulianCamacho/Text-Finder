public class Documents {

    private String text;
    private String name;
    private String size;
    private String date;
    private int realSize;

    protected Documents next = null;
    protected Documents prev = null;

    public Documents(String text, String name, String size, String date) {
        this.text = text;
        this.name = name;
        this.size = size;
        this.date = date;
        this.realSize = toInt(this.size);
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
}
