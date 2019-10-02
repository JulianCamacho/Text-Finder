import java.lang.annotation.ElementType;
import java.util.ArrayList;

public class Searcher {

    Tree tree;

    public Searcher(Tree tree){
        this.tree=tree;
    }

    public void search(String phrase){
        String[] splitPhrase = this.decomposePhrase(phrase);
        if(splitPhrase.length>=1){

            ArrayList ocurrenceList= this.searchWord(splitPhrase[0]);
            ocurrenceList=this.getRealOcurrenceList(ocurrenceList,splitPhrase);
            String[] context = this.getContext(ocurrenceList);
            String[] names = this.getDocsNames(ocurrenceList);
            String[] dates = this.getDates(ocurrenceList);
            String[] sizes = this.getSize(ocurrenceList);

        }else{
            //Interfaz.show(no input enterd)
        }
    }

    private ArrayList<> searchWord(String word){
        ArrayList<> ocurrenceList= this.tree.getOcurrenceList(word);
        return ocurrenceList;
    }

    private ArrayList<> getRealOcurrenceList(ArrayList<> temporaryOcurrenceList, String[] phrase){
        ArrayList<> realOcurrenceList= new ArrayList();
        for(Element element: ocurrenceList){
            //Context in the ocurrencelist is compared with the remaining words of the phrase
        }
        return realOcurrenceList;
    }

    private String[] getContext(ArrayList<> ocurrenceList){
        String[] context= new String[ocurrenceList.size()];
        for(Element element: ocurrenceList){
            //The context for each word is gotten here
        }
        return context;
    }

    private String[] getDocsNames(ArrayList<> ocurrenceList){
        String[] names= new String[ocurrenceList.size()];
        for(Element element: ocurrenceList){
            //The names of the documents for each phrase is gotten here
        }
        return names;
    }

    private String[] getDates(ArrayList<> ocurrenceList){
        String[] date= new String[ocurrenceList.size()];
        for(Element element: ocurrenceList){
            //The names of the documents for each phrase is gotten here
        }
        return date;
    }

    private String[] getSize(ArrayList<> ocurrenceList){
        String[] size= new String[ocurrenceList.size()];
        for(Element element: ocurrenceList){
            //The names of the documents for each phrase is gotten here
        }
        return size;
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
