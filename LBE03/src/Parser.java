import java.io.*;
import java.util.*;

/**
 * ISTE-612 LBE03 Text processing
 * Carlos Rocha
 */
public class Parser {
    private String[] myDocs;
    private File[] listOfFiles;

    String[] stopWords = {"a", "is", "in", "so", "of", "at", "the", "to", "and", "it", "as",
        "be", "are", "on", "into", "if", "it's"};

    public Parser(String folderName){
        File folder = new File(folderName);
        listOfFiles = folder.listFiles();
        myDocs = new String[listOfFiles.length];

        for (int i=0; i<listOfFiles.length; i++) {
            myDocs[i] = listOfFiles[i].getName();
            System.out.println("File: "+listOfFiles[i].getName());
        }

        Arrays.sort(stopWords);
    }

    public int searchStopWords(String key) {
        int lo = 0;
        int hi = stopWords.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi-lo)/2;//(hi+lo) / 2;
            int result = key.compareTo(stopWords[mid]);
            if (result< 0) hi = mid-1;
            else if (result > 0 ) lo = mid + 1;
            else return mid;
        }

        return -1;
    }

    public ArrayList<String> parse(File fileName) throws IOException{
        String[] tokens = null;
        ArrayList<String> stemms = new ArrayList<>();

        Scanner scan = new Scanner(fileName);
        String allLines = new String();

        while(scan.hasNextLine()){
            allLines += scan.nextLine().toLowerCase();
        }

        tokens = allLines.split("[ ',.?!$%()\\-\\*]+");



        for (String token: tokens) {
            if(searchStopWords(token) < 0){

                Stemmer st = new Stemmer();
                st.add(token.toCharArray(), token.length());
                st.stem();
                String finalWord = st.toString();
                stemms.add(finalWord);
//                System.out.println(finalWord);
            }
        }

//

        return stemms;
    }

    public File getFile(int i){
        return listOfFiles[i];
    }
    public static void main(String[] args){
        Parser p = new Parser("./LBE3_Data");//"./"+args[0]);
//        p.parse()

        System.out.println("Stropword: " + p.searchStopWords("are"));

        try{
            File file = p.getFile(1);
            ArrayList<String> stemmed = p.parse(file);

            for (String st :
                    stemmed) {
                System.out.println(st);
            }
            
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
