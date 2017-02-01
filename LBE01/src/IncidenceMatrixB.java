import java.util.*;
/**
 * 612 LBE01 IncidenceMatrixB
 * Prof. Kang
 */
 
public class IncidenceMatrixB {
   //attributes
   private String[] myDocs;               //input docs
   private ArrayList<String> termList;    //dictionary
   private ArrayList<int[]> docLists;
   
   //Constructor
   public IncidenceMatrixB(String[] docs) {
      myDocs = docs;
      termList = new ArrayList<>();
      docLists = new ArrayList<int[]>();

      for(int i=0; i < myDocs.length; i++){
         String[] words = myDocs[i].split(" ");

         for(String word : words){
            if (!termList.contains(word)) {
               termList.add(word);
               int[] docList = new int[myDocs.length];
               docList[i] = 1;
               docLists.add(docList);
            } else {
               int index = termList.indexOf(word);
               int[] docList = docLists.remove(index);
               docList[i] = 1;
               docLists.add(index, docList);

            }
         }
      }
   }
   
    public int[] search(String query) {
       int[] docList = new int[0];

        for (int i=0; i < termList.size(); i++) {
            if (termList.get(i).equals(query)) {
                docList = docLists.get(i);
            }
        }
        return docList;
    }
   
   public String toString() {
      String outputString = new String();
      for(int i=0;i<termList.size();i++) {
         outputString += String.format("%-15s", termList.get(i));
         int[] docList = docLists.get(i);
         for(int j=0;j<docList.length;j++) {
            outputString += docList[j] + "\t";
         }
         outputString += "\n";
      }
      return outputString;
   }

   public ArrayList<Integer> seek(String query){
      return null;
   }
   
   public static void main(String[] args) {
      //a document collection: corpus
      String[] docs = {"text warehousing over big data",
                       "dimensional data warehousing over big data",
                       "nlp before text mining",
                       "nlp before text classification"};

      IncidenceMatrixB matrixB = new IncidenceMatrixB(docs);

      System.out.println(matrixB);

       int[] res = matrixB.search(args[0]);

       if (res.length>0) {

           for (int i=0; i < res.length; i++){
               if (res[i] == 1) {
                   System.out.println(docs[i]);
               }
           }
       } else {
           System.out.println("No results were found!");
       }
   }
}


/*
Run sample

java IncidenceMatrix "Brutus"
                     args[0]

ArrayList<int[]> result = matrix.search(args[0]);

==>output

Query: text
Result:
- text warehousing over big data
- nlp before text mining
- nlp before text classification

 */