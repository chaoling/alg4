public class Team {
    private int id;
    private String name;
    private int win;
    private int loss;
    private int remaining;
    private int[] againsts;
    
    public Team(int id, String[] tokens) {
        /*
           Atlanta       83 71  8  0 1 6 1
           Philadelphia  80 79  3  1 0 0 2
           New_York      78 78  6  6 0 0 0
           Montreal      77 82  3  1 2 0 0
         */
        //System.out.println(tokens);
        this.id = id;
        //String[] tokens = line.split("\\s+");
        assert (tokens.length > 4);
        this.name = tokens[0];
        this.win = Integer.parseInt(tokens[1]);
        this.loss = Integer.parseInt(tokens[2]);
        this.remaining = Integer.parseInt(tokens[3]);
        this.againsts = new int[tokens.length - 4];
        for (int i = 0; i < tokens.length - 4; i++) {
            this.againsts[i] = Integer.parseInt(tokens[i+4]);
        }
    }
    
    public Team() {
        
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getWin() {
        return this.win;
    }
    
    public int getLoss() {
        return this.loss;
    }
    
    public int getRemaining() {
        return this.remaining;
    }
    
    public int[] getOpponents() {
        int[] res = new int[againsts.length];
        System.arraycopy(againsts, 0, res, 0, againsts.length);
        return res;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: "+id);
        sb.append(name+"  ");
        sb.append(win+"  ");
        sb.append(loss+"  ");
        sb.append(remaining+"  ");
        for (int i = 0; i < againsts.length; i++) {
            sb.append(againsts[i]+" ");
        }
        return sb.toString();
    }
}