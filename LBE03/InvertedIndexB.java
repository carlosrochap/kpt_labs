import java.util.*;
/**
 * 612 LBE02 InvertedIndexB
 * 612 LBE02 InvertedIndexA
 * 612 LBE01 IncidenceMatrixC
 * Prof. Kang
 */
 
//public class IncidenceMatrixD {         //LBE02
public class InvertedIndexB {
   //attributes
   private String[] myDocs;               //input docs
   private ArrayList<String> termList;    //dictionary
   //private ArrayList<int[]> docLists;     //LbE02
   private ArrayList<ArrayList<Integer>> docLists;
   
   //Constructor
   public InvertedIndexB(String[] docs) { //LBE02
      myDocs = docs;
      termList = new ArrayList<String>();
      //docLists = new ArrayList<int[]>();
      docLists = new ArrayList<ArrayList<Integer>>();
      ArrayList<Integer> docList; 
      
      for(int i = 0; i < myDocs.length; i++){
         String[] words = myDocs[i].split(" ");
         for(String word:words){
            if(!termList.contains(word)){
               termList.add(word);
               //int[] docList = new int[myDocs.length];
               docList = new ArrayList<Integer>();
               //docList[i] = 1;
               docList.add(new Integer(i));
               docLists.add(docList);
            }  
            else{
               int index = termList.indexOf(word);
               // int[] docList = docLists.remove(index);
               docList = docLists.get(index);
               //docList[i] = 1;
               if(!docList.contains(new Integer(i))) {
                  docList.add(new Integer(i));
                  docLists.set(index, docList);
               }
            } 
         }
      }
   }
   //LBE01D
   public ArrayList<Integer> search(String query) {
   /*
      ArrayList<Integer> result = new ArrayList<Integer>();
      int index = termList.indexOf(query);
      if(index >= 0) {
         int[] docList = docLists.get(index);
         for(int i=0;i<docList.length;i++) {
            if(docList[i] != 0) {
               if(!result.contains(new Integer(i)))
                  result.add(new Integer(i));
            }
         }
      }
      return result;
    */
    //LBE02C
    int index = termList.indexOf(query);
    if(index >=0) {
      return docLists.get(index);
    }
    return null;
   }
   
   public String toString() {
      String outputString = new String();
      for(int i=0;i<termList.size();i++) {
         outputString += String.format("%-15s", termList.get(i));
         
         //int[] docList = docLists.get(i);
         ArrayList<Integer> docList = docLists.get(i);
         //for(int j=0;j<docList.length;j++) {
         for(int j=0;j<docList.size();j++) {
            //outputString += docList[j] + "\t";
            outputString += docList.get(j) + "\t";
         }
         outputString += "\n";
         
      }
      return outputString;
   }
   
   public static void main(String[] args) {
      //a document collection: corpus
      String[] docs = {"text warehousing over big data",
                       "dimensional data warehousing over big data",
                       "nlp before text mining",
                       "nlp before text classification"};  
                       
      InvertedIndexB matrix =  new InvertedIndexB(docs);
      
      System.out.println(matrix);
      
      //LBE02B
      
      if(args.length == 1) {
         System.out.println("Query: " + args[0]);
         ArrayList<Integer> result = matrix.search(args[0]);
         for(Integer i:result) {
            System.out.println(docs[i.intValue()]);
         }
      }
                   
   }
}
