import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;

public class BaseballElimination {
    private int mNumOfTeams;
    private Team[] mTeams;
    private String[] mTeamNames;
    private Map<String, Team> mSymTbl;
    // serve as a cache of the results
    // 0 means no, 1 means yes, -1 means not visited.
    private int[]  mEliminated;
    

public BaseballElimination(String filename) {
// create a baseball division from given filename in format specified below
    try {
    Scanner in = new Scanner(new File(filename), "UTF-8");
    mNumOfTeams = in.nextInt();
    if (mNumOfTeams <= 0) {
        throw new IllegalArgumentException("number of teams should be positive");
    }
    mTeams = new Team[mNumOfTeams];
    mSymTbl = new HashMap<String, Team>();
    mTeamNames = new String[mNumOfTeams];
    mEliminated = new int[mNumOfTeams];
    
    for (int i = 0; i < mNumOfTeams; i++) {
        String[] tokens = new String[mNumOfTeams + 4];
        for (int j = 0; j < mNumOfTeams + 4; j++) {
            tokens[j] = in.next();
        }
        mTeams[i] = new Team(i, tokens);
        mSymTbl.put(mTeams[i].getName(), mTeams[i]);
        mTeamNames[i] = mTeams[i].getName();
        mEliminated[i] = -1;
        }
    } catch (java.io.FileNotFoundException e) {
        e.printStackTrace();
    }
}

public int numberOfTeams() {
// number of teams
   return mNumOfTeams;
}

public Iterable<String> teams() {
// all teams
   return mSymTbl.keySet();
}

public int wins(String team) {
// number of wins for given team
   Team tm = mSymTbl.get(team);
   if (tm == null) {
       throw new IllegalArgumentException("team does not exists!");
   }
   return tm.getWin();
}

public int losses(String team) {
// number of losses for given team
   Team tm = mSymTbl.get(team);
   if (tm == null) {
       throw new IllegalArgumentException("team does not exists!");
   }
   return tm.getLoss();
}

public int remaining(String team) {
// number of remaining games for given team
   Team tm = mSymTbl.get(team);
   if (tm == null) {
       throw new IllegalArgumentException("team does not exists!");
   }
   return tm.getRemaining();
}

public int against(String team1, String team2) {
// number of remaining games between team1 and team2
   Team tm1 = mSymTbl.get(team1);
   Team tm2 = mSymTbl.get(team2);
   if (tm1 == null || tm2 == null) {
       throw new IllegalArgumentException("team does not exists!");
   }
   return tm1.getOpponents()[tm2.getId()];
}

public boolean isEliminated(String team) {
   // is given team eliminated?
   Team ctm = mSymTbl.get(team);
   if (ctm == null) {
       throw new IllegalArgumentException("team does not exists!");
   }
   if (numberOfTeams() <= 1) {
       return false;
   }
   int res = mEliminated[ctm.getId()];
   if (res != -1) {
       if (res == 0) {
          return false;
       } 
       return true;
   }
   
   if (testTrivalElimination(team)) {
       mEliminated[ctm.getId()] = 1;
       System.out.println(ctm.getName()+"  is trivally eliminated");
       return true;
   }
   
   FlowNetwork fn = constructFlowNetwork(team);
   //StdOut.println(fn);
   int s = numberOfTeams() - 1;
   int t = s + 1;
   new FordFulkerson(fn, s, t);
//   StdOut.println("Max flow from " + s + " to " + t);
//        for (int v = 0; v < fn.V(); v++) {
//            for (FlowEdge e : fn.adj(v)) {
//                if ((v == e.from()) && e.flow() > 0)
//                    StdOut.println("   " + e);
//            }
//        }
//
//        // print min-cut
//        StdOut.print("Min cut: ");
//        for (int v = 0; v < numberOfTeams(); v++) {
//            if (maxflow.inCut(v)) StdOut.print(v + " ");
//        }
//        StdOut.println();
//
//        StdOut.println("Max flow value = " +  maxflow.value());
   for (FlowEdge e : fn.adj(s)) {
       //System.out.println("flow edge: "+e);
       if (e.from() == s && e.flow() < e.capacity()) {
            mEliminated[ctm.getId()] = 1;
            return true;
       }
   }
   mEliminated[ctm.getId()] = 0;
   return false;
}

public Iterable<String> certificateOfElimination(String team) {
  // subset R of teams that eliminates given team; null if not eliminated
   Team ctm = mSymTbl.get(team);
   if (ctm == null) {
       throw new IllegalArgumentException("team does not exists!");
   }
   if (!isEliminated(team)) {
       return null;
   }
   List<String> teams = new ArrayList<String>();
   //test trival case first:
   for (Team tm : getTeams()) {
       //w[x] + r[x] < w[i]
       if (ctm.getWin() + ctm.getRemaining() < tm.getWin()) {
          teams.add(tm.getName()); 
       }
   }
   if (teams.size() > 0) {
       return teams;
   }
   
   //Now consider non-trival case:
   FlowNetwork fn = constructFlowNetwork(team);
   //StdOut.println(fn);
   int s = numberOfTeams() - 1;
   int t = s + 1;
   boolean isEliminated = false;
   FordFulkerson ff = new FordFulkerson(fn, s, t);
//      StdOut.println("Max flow from " + s + " to " + t);
//        for (int v = 0; v < fn.V(); v++) {
//            for (FlowEdge e : fn.adj(v)) {
//                if ((v == e.from()) && e.flow() > 0)
//                    StdOut.println("   " + e);
//            }
//        }
//        for (int v = 0; v < fn.V(); v++) {
//            if (ff.inCut(v)) StdOut.print("in cut:"+ v + " ");
//        }
   for (FlowEdge e : fn.adj(s)) {
       //System.out.println("flow edge: "+e);
       if (e.from() == s && e.flow() < e.capacity()) {
            mEliminated[ctm.getId()] = 1;
            isEliminated = true;
            break;
       }
   }
   
   if (!isEliminated) {
       return null;
   } else {
       teams = new ArrayList<String>();
       int i = 0;
       for (Team tm : getTeams()) {
           if (tm.getName().equals(ctm.getName())) continue;
           if (ff.inCut(i)) {
               teams.add(tm.getName());
           }
           i++;
       }
       //System.out.println("Team size: "+teams.size());
       assert (teams.size() > 0);
       return teams;
   }
}

private Team[] getTeams() {
    return mTeams;
}

public static void main(String[] args) {
    BaseballElimination division = new BaseballElimination(args[0]);
//    for (int i = 0; i < division.mNumOfTeams; i++) {
//        System.out.println(division.getTeams()[i]);
//    }
    for (String team : division.teams()) {
        if (division.isEliminated(team)) {
            StdOut.print(team + " is eliminated by the subset R = { ");
            for (String t : division.certificateOfElimination(team)) {
                StdOut.print(t + " ");
            }
            StdOut.println("}");
        }
        else {
            StdOut.println(team + " is not eliminated");
        }
    }
}

private FlowNetwork constructFlowNetwork(String teamName) {
    Team ctm = mSymTbl.get(teamName);
    if (ctm == null) {
       throw new IllegalArgumentException("team does not exists!");
    }
    int tIndex = ctm.getId();
    int numOfMatches = (numberOfTeams() -1) * (numberOfTeams() - 2) / 2;
    int V = numOfMatches + numberOfTeams() - 1 + 2;
    //System.out.println(" team is: "+teamName+" tIndex is: "+tIndex);
    //TeamNodes + s + t + MatchNodes
    int s = numberOfTeams() - 1;
    int t = s + 1;
    int mnode = t + 1; //match nodes
    int tnode = 0; //team nodes
    int cnode = 0; //another pair of team node
    int row = -1;
    FlowNetwork G = new FlowNetwork(V);
    for (Team tm : getTeams()) {
        row++;
        if (tm.getName().equals(ctm.getName())) continue;
        int[] op = tm.getOpponents();
        cnode = row;
        for (int col = row + 1; col < op.length; col++) {
            //only look upper triangle non zero item excluding ctm
            if (col != tIndex) {
            cnode++;
            //add match to source edges
            G.addEdge(new FlowEdge(s, mnode, op[col]));
            // add match to team edges
            G.addEdge(new FlowEdge(mnode, tnode, 
                               Integer.MAX_VALUE));
            
            G.addEdge(new FlowEdge(mnode, cnode, 
                               Integer.MAX_VALUE));
            mnode++;
            }
        }
        G.addEdge(new FlowEdge(tnode, t, Math.max(0, 
                              ctm.getWin() + ctm.getRemaining() - tm.getWin()))); 
        tnode++;
    }
    return G;
}

private boolean testTrivalElimination(String teamName) {
    Team ctm = mSymTbl.get(teamName);
    if (ctm == null) {
       throw new IllegalArgumentException("team does not exists!");
    }
    for (Team tm : getTeams()) {
       //w[x] + r[x] < w[i]
       if (ctm.getWin() + ctm.getRemaining() < tm.getWin()) {
           return true;
       }
    }
    return false;
}
}
