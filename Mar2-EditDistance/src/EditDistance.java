/**
 * Created by Carlos on 3/2/2017.
 */
public class EditDistance {

	/**
	* Recursive Edit Distance
	*/
    public int editDistance(String x, String y){
        if (x.length() == 0) return y.length();
        else if (y.length() == 0) return x.length();
        else{
            String xsub = x.substring(0, x.length()-1);
            String ysub = y.substring(0, y.length()-1);
            int insertDistance = editDistance(x, ysub) + 1;
            int deleteDistance = editDistance(xsub, y) + 1;
            int replaceDistance = editDistance(xsub, ysub) + ((x.charAt(x.length()-1) == y.charAt(y.length()-1)) ? 0:1);

            return Math.min(Math.min(insertDistance, deleteDistance), replaceDistance);

        }

    }

    /**
     * Computes edit distance using dynamic programming
     *
     * @param s1 original query term
     * @param s2 suggested corrected term
     * @return minimum edit distance distance between the terms
     */
    public int editDistanceDynamic(String s1, String s2){
    	//matrix to store previous computations
        int[][] m = new int[s1.length()+1][s2.length()+1];

        for (int i=1; i < m.length; i++){
            m[i][0] = i;
        }

        for (int j=1; j < m[0].length; j++){
            m[0][j] = j;
        }

        for (int i=1; i < m.length; i++){
            for (int j=1; j < m[i].length; j++){
                int insertD = m[i-1][j-1] + (s1.charAt(i-1) == s2.charAt(j-1) ? 0 : 1);

                m[i][j] = Math.min(insertD, Math.min(m[i-1][j]+1, m[i][j-1]+1));
            }
        }

        return m[m.length-1][m[0].length-1];
    }

    public static void main(String[] args) {
        EditDistance ed = new EditDistance();
        System.out.println(ed.editDistance("zeil", "trial"));
        System.out.println(ed.editDistance("cats", "fast"));
    }
}
