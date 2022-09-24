import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.util.Random;

public class Standings {
    private ArrayList<Team> teams;
    
    public Standings() {
        this.teams = new ArrayList<>();
    }
    
    public Standings(String filename) {
        this.teams = new ArrayList<>();
        this.readMatchData(filename);
    }
    
    public void readMatchData(String filename) {
        try(var file = new BufferedReader(new FileReader(filename))) {
            String line;
            while((line = file.readLine()) != null) {
              String[] line_parts = line.split("\\t");
              int[] team_scores = Arrays.stream(line_parts[1].split("-")).mapToInt(Integer::parseInt).toArray();    
              this.addMatchResult(line_parts[0], team_scores[0], team_scores[1], line_parts[2]);
            }
        } catch(IOException e) {
            System.out.println("An exception occurred: " + e);
        }
    }
    
    public void addMatchResult(String teamNameA, int goalsA, int goalsB, String teamNameB) {
        ArrayList<Team> teamsList = this.getTeams();
        Team team1 = new Team(teamNameA);
        Team team2 = new Team(teamNameB);
        boolean team1Found = false;
        boolean team2Found = false;
        for(Team t : teamsList) {
            if(t.name.equals(teamNameA)) {
                team1 = t;
                team1Found = true;
            } 
            if(t.name.equals(teamNameB)) {
                team2 = t;
                team2Found = true;
            }
        }        
        if(goalsA < goalsB) {
            team1.setLosses();
            team2.setWins();
            team2.setPoints(3);
        } else if(goalsA > goalsB) {
            team1.setWins();
            team2.setLosses();
            team1.setPoints(3);
        } else {
            team1.setTies();
            team2.setTies();
            team1.setPoints(1);
            team2.setPoints(1);
        }
        team1.setMatches();
        team2.setMatches();
        team1.setScored(goalsA);
        team1.setAllowed(goalsB);  
        team2.setScored(goalsB);
        team2.setAllowed(goalsA);
        if(!team1Found)
            this.teams.add(team1);
        if(!team2Found)
            this.teams.add(team2);
    }
    
    public void printStandings() {
        ArrayList<Team> teamsList = this.getTeams();
        if(!teamsList.isEmpty()) {
            teamsList.sort((t1, t2)-> t1.getName().compareTo(t2.getName())); 
            teamsList.sort((t1, t2)-> t2.getScored() - t1.getScored()); 
            teamsList.sort((t1, t2)-> (t2.getScored() - t2.getAllowed()) - (t1.getScored() - t1.getAllowed()));
            teamsList.sort((t1, t2)-> t2.getPoints() - t1.getPoints());        
        }
        int longest_name = 0;
        int longest_allowed = 0;
        for(Team t : teamsList) {        
            if(t.getName().length() > longest_name) {
                longest_name = t.getName().length();
            }
            if(String.valueOf(t.getAllowed()).length() > longest_allowed) {
                longest_allowed = String.valueOf(t.getAllowed()).length();
            }
            
        }  
        for(Team team : teamsList) {
            System.out.format("%s%s %3d %3d %3d %3d %s%3d-%1d %3d%n", team.name, " ".repeat(longest_name-team.name.length()), team.getMatches(), team.getWins(), team.getTies(), team.getLosses(), " ".repeat(longest_allowed-String.valueOf(team.getAllowed()).length()), team.getScored(), team.getAllowed(), team.getPoints());
        }
    }
    
    public ArrayList<Team> getTeams() {
        if(!this.teams.isEmpty()) {
            this.teams.sort((t1, t2)-> t1.getName().compareTo(t2.getName())); 
            this.teams.sort((t1, t2)-> t2.getScored() - t1.getScored()); 
            this.teams.sort((t1, t2)-> (t2.getScored() - t2.getAllowed()) - (t1.getScored() - t1.getAllowed()));
            this.teams.sort((t1, t2)-> t2.getPoints() - t1.getPoints()); 
        }
        return this.teams;
    }
    
    public static class Team {
        private String name;
        private int no_of_wins;
        private int no_of_ties; 
        private int no_of_losses;
        private int no_of_scored_goals;
        private int no_of_allowed_goals;
        private int no_of_total_points;
        private int no_of_total_matches;
        
        public Team(String name) {
            this.name = name;
        }
        
        public String getName() {
            return this.name;
        }
        
        private void setMatches() {
            this.no_of_total_matches = ++this.no_of_total_matches;
        }
        
        private void setWins() {
            this.no_of_wins = ++this.no_of_wins;
        }
        
        private void setTies() {
            this.no_of_ties = ++this.no_of_ties;
        }
        
        private void setLosses() {
            this.no_of_losses = ++this.no_of_losses;
        }
        
        private void setScored(int scored) {
            this.no_of_scored_goals += scored;
        }
        
        private void setAllowed(int allowed) {
            this.no_of_allowed_goals += allowed;
        }
        
        private void setPoints(int points) {
            this.no_of_total_points += points;
        }
        
        private int getMatches() {
            return this.no_of_total_matches;
        }
        
        public int getWins() {
            return this.no_of_wins;
        }
        
        public int getTies() {
            return this.no_of_ties;
        }
        
        public int getLosses() {
            return this.no_of_losses;
        }
        
        public int getScored() {
            return this.no_of_scored_goals;
        }
        
        public int getAllowed() {
            return this.no_of_allowed_goals;
        }
        
        public int getPoints() {
            return this.no_of_total_points;
        }
    }
}
