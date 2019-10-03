public class Tree {
    private Node root= null;
    private static Tree instance;

    public boolean isEmpty() {
        return root==null;
    }

    public boolean isContained(String word){
        return this.isContained(word, root);
    }

    private boolean isContained(String word, Node root){
        if(root==null){
            return false;
        }else if(root.getWord().equals(word)){
            return true;
        }else if(root.getWord().compareTo(word)>0){
            return this.isContained(word, root.getLeft());
        }else{
            return this.isContained(word, root.getRigth());
        }
    }

    public void add(String word,String document, int lineNumber, int linePos){
        root=this.add(word, document,  lineNumber,  linePos, this.root);
    }

    private Node add(String word,String document, int lineNumber, int linePos,Node current){
        if(current==null){
            return new Node(word);
        }else if(current.getWord().equals(word)){
            this.addOcurrence(current.getWordOcurrences(),document,lineNumber,linePos);
        }else if(current.getWord().compareTo(word)>0){
            current.setLeft(this.add(word, document,  lineNumber,  linePos, current.getLeft()));
        }else{
            current.setRigth(this.add(word, document,  lineNumber,  linePos, current.getRigth()));
        }
        return current;

    }

    private void addOcurrence(WordOcurrences wordOcurrences,String document, int lineNumber, int linePos){
        wordOcurrences.getDocuments().add(document);
        wordOcurrences.getLineNumber().add(lineNumber);
        wordOcurrences.getLinePos().add(linePos);
    }

    public WordOcurrences getOcurrences(String word){
        return this.getOcurrences(word, root);
    }

    private WordOcurrences getOcurrences(String word, Node root){
        if(root==null){
            return null;
        }else if(root.getWord().equals(word)){
            return root.getWordOcurrences();
        }else if(root.getWord().compareTo(word)>0){
            return this.getOcurrences(word, root.getLeft());
        }else{
            return this.getOcurrences(word, root.getRigth());
        }
    }
}