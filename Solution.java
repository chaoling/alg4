import java.util.ArrayList;
import java.util.HashMap;

public class Solution { 
    
    public static ArrayList<Integer> findSubstring(String S, String[] L) { 
        ArrayList<Integer> ai = new ArrayList<Integer>(); 
        //Put L into a hashmap and knock down one by one from the substring of S 
        HashMap<String,Integer> hm = new HashMap<String,Integer>(); 
        for (String l:L) { 
            System.out.println("building map: "+l);
            if (hm.containsKey(l)) { 
                hm.put(l,hm.get(l)+1); 
            } else { 
                hm.put(l,1); 
            } 
        } 
        HashMap<String,Integer> thm = new HashMap<String,Integer>(hm);
        int mNumOfString = L.length; 
        int mSizel = L[0].length(); //equal size for all strings in the list 
        int mSizeS = S.length(); 
        //handle special case:
        for (int i=0; i <= mSizeS-mSizel*mNumOfString; i++) { 
            System.out.println(" index is: "+i);
            String subStr = S.substring(i,i+mSizel); 
            System.out.println("Now searching: "+subStr);
            thm.putAll(hm); //restore hashmap;
            if (thm.get(subStr) == null) { 
                continue; 
            } else { 
                int j = i;
                while (true) { 
                    //j+=mSizel; 
                    subStr = S.substring(j,j+mSizel); 
                    System.out.println("Now serving: "+subStr);
                    if (thm.get(subStr) == null){ 
                        break; 
                    } else {
                        j+=mSizel;
                        thm.put(subStr,thm.get(subStr)-1);
                        System.out.println("continue finding..."+subStr+" at index "+ j + " numofKeyleft: "+thm.get(subStr));
                        if (thm.get(subStr) == 0) { 
                            thm.remove(subStr); 
                        }  
                        if (thm.isEmpty()) { 
                            ai.add(i); 
                            //i=j-1;
                            break;
                        }
                    }
                } 
            } 
             
        } 
         
     return ai;    
         
    } 
    
    public static void main(String args[]) {
     String S = "aaaaaa";
     String[] L = new String[]{"a","a"};
     ArrayList<Integer> a = new ArrayList<Integer>();
     a=findSubstring(S,L);
     System.out.println("The final answer is "+a.toString());
    }
}