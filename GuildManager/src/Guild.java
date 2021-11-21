import java.util.TreeMap;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
public class Guild{
    String name;
    TreeMap<String,List<String>> memberDetails = new TreeMap<>();

    public Guild(){
        ;
    }
    public Guild(String GuildName){
        this.name = GuildName;
    }

    /**
     * Method to add a member and their details to the guild
     * @param id
     * @param name
     * @param job
     * @param upkeep
     * @return true if member is added successfully else false
     */
    public boolean addMember(String id, String name, String job, String upkeep){
        if (memberDetails.containsKey(id)){
            return false;
        }
        List<String> details = new ArrayList<String>();
        details.add(name);
        details.add(job);
        details.add(upkeep);
        memberDetails.put(id,details);   
        
        return true;
    }

    /**
     * Prints out all the ids for each member along with their details
     */
    public void getMembers(){
        for (String key : memberDetails.keySet()){
            System.out.println("id: " + key);
            System.out.println("Details: " + memberDetails.get(key));
        }
    }
    
    /**
     * Fetches the name of a member given some id
     * @param id
     * @return name of member if the id exists else "id not found"
     */
    public String getName(String id){
        List<String> details = new ArrayList<String>();

        for (String key : memberDetails.keySet()){
            if (key ==id){
                details = memberDetails.get(key);
                return details.get(0);
            }
        }
        return "id not found";
    }
    /**
     * Fetches name of job for a member given some id
     * @param id
     * @return job of member if the id exists else "id not found"
     */
    public String getJob(String id){
        List<String> details = new ArrayList<String>();

        for (String key : memberDetails.keySet()){
            if (key == id){
                details = memberDetails.get(key);
                return details.get(1);
            }
        }
        return "id not found";
    }

    /**
     * Fetches upkeep for a member given some id
     * @param id
     * @return upkeep of member if the id exists else "id not found"
     */
    public String getUpkeep(String id){
        List<String> details = new ArrayList<String>();

        for (String key : memberDetails.keySet()){
            if (key == id){
                details = memberDetails.get(key);
                return details.get(2);
            }
        }
        return "id not found";
    }
    /**
     * changes the upkeep of a member given their id, to a new upkeep that you specify
     * @param id
     * @param upkeep
     * @return true if upkeep is changed successfully else false
     */
    public boolean changeUpkeep(String id, String upkeep){
        List<String> details = new ArrayList<String>();

        for (String key : memberDetails.keySet()){
            if (key == id){
                details = memberDetails.get(key);
            }
        }

        if (details.isEmpty()){
            return false;
        }
        else{
            details.set(2,upkeep);
            RemoveMember(id);
            addMember(id, details.get(0), details.get(1), details.get(2));
            return true;
        }
    }
       
    /**
     * Removes a member from the memberDetails treemap 
     * @param id
     * @return true if the member was removed successfully else false
     */
    public boolean RemoveMember(String id){
        for (String key : memberDetails.keySet()){
            if (key == id){
                memberDetails.remove(key);
                return true;
            }
        }
        return false;
    }

    /**
     * @param name
     * @return a List of all the ids of members with the same name
     */
    public List<String> SameName(String name){
        List<String> ids = new ArrayList<String>();
        List<String> details = new ArrayList<String>();

        for (String key : memberDetails.keySet()){
            details = memberDetails.get(key);
            if (details.get(0) == name){
                ids.add(key);
            }
        }
        
        return ids;
    }

    /**
     * @param id1
     * @param id2
     * @return the absolute difference in upkeep between two members
     */
    public Integer UpkeepDiff(String id1, String id2){
        Integer Upkeep1 = 0;
        Integer Upkeep2 = 0;

        List<String> details = new ArrayList<String>();

        for (String key : memberDetails.keySet()){
            if (key == id1){
                details = memberDetails.get(key);
                Upkeep1 = Integer.parseInt(details.get(2)); //convert String to Integer
            }
            else if (key == id2){
                details = memberDetails.get(key);
                Upkeep2 = Integer.parseInt(details.get(2));
            }
        }
        return Math.abs((Upkeep1 - Upkeep2)); 
    }
}