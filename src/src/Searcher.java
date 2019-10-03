
import java.lang.annotation.ElementType;
import java.util.ArrayList;

public class Searcher {

    Tree tree;
    Controller controller;

    public Searcher(Tree tree, Controller controller){
        this.tree=tree;
        this.controller=controller;
    }

    public void search(String phrase){
        String[] splitPhrase = this.decomposePhrase(phrase);
        if(splitPhrase.length>=1){

            WordOcurrences wordOcurrences= this.searchWord(splitPhrase[0]);
            wordOcurrences=this.getRealOcurrences(wordOcurrences,splitPhrase);
            String[] context = this.getContext(wordOcurrences);
            String[] names = wordOcurrences.getDocuments().toArray(new String[wordOcurrences.getDocuments().size()]);
            String[] dates = {};
            String[] sizes = {};
            controller.updateSearchPane(context, names, dates,sizes);

        }else{
            //Interfaz.show(no input entered)
        }
    }

    private WordOcurrences searchWord(String word){
        WordOcurrences ocurrenceList= this.tree.getOcurrences(word);
        return ocurrenceList;
    }

    private WordOcurrences getRealOcurrences(WordOcurrences wordOcurrences, String[] phrase){
        int size = wordOcurrences.getDocuments().size();
        for(int i = 0; i < size; i++){
           //Updates stuff
        }
        return null;
    }

    private String[] getContext(WordOcurrences wordOcurrences){
        return null;
    }

    private String[] decomposePhrase(String phrase){
        String[] split=phrase.split(" ");
        ArrayList<String> noSpacesSplit=new ArrayList<>();
        for(String word: split){
            if(!word.equals("")){
                noSpacesSplit.add(word);
            }
        }
        split= noSpacesSplit.toArray(split);
        return split;
    }
}
