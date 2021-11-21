import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestGuild{
    @Test 
    /**
     * Test1:
     * Test if members are added correctly
     */
    public void testAddMember(){ 
        Guild guild = new Guild("Test Guild");
        assertTrue(guild.addMember("M001", "John", "Warrior", "10000"));
        assertFalse(guild.addMember("M001", "Jimmy", "Archer", "20000"));
    }
    @Test
    /**
     * Test2:
     * Test if member names are fetched correctly
     */
    public void testGetName(){
        Guild guild = new Guild("Test Guild");
        String notFound = "id not found";
        guild.addMember("M001", "John", "Warrior", "10000");
        guild.addMember("M002", "Jimmy", "Archer", "20000");
        assertEquals("John",guild.getName("M001"));
        assertEquals("Jimmy",guild.getName("M002"));
        assertEquals(notFound,guild.getName("M003"));

    }

    @Test
    /**
     * Test3:
     * Test if member jobs are fetched correctly
     */
    public void testGetJob(){
        Guild guild = new Guild("Test Guild");
        String notFound = "id not found";
        guild.addMember("M001", "John", "Warrior", "10000");
        guild.addMember("M002", "Jimmy", "Archer", "20000");
        assertEquals("Warrior",guild.getJob("M001"));
        assertEquals("Archer",guild.getJob("M002"));
        assertEquals(notFound,guild.getJob("M003"));

    }

    @Test
    /**
     * Test4:
     * Test if member upkeeps are fetched correctly
     */
    public void testGetUpkeep(){
        Guild guild = new Guild("Test Guild");
        String notFound = "id not found";
        guild.addMember("M001", "John", "Warrior", "10000");
        guild.addMember("M002", "Jimmy", "Archer", "20000");
        assertEquals("10000",guild.getUpkeep("M001"));
        assertEquals("20000",guild.getUpkeep("M002"));
        assertEquals(notFound,guild.getUpkeep("M003"));
        
    }

    @Test
    /**
     * Test5:
     * Test if member upkeeps are changed correctly
     */
    public void testChangeUpkeep(){
        //Test to see if change happens or not
        Guild guild = new Guild("Test Guild");
        guild.addMember("M001", "John", "Warrior", "10000");
        guild.addMember("M002", "Jimmy", "Archer", "20000");
        assertTrue(guild.changeUpkeep("M001", "12500"));
        assertFalse(guild.changeUpkeep("M003", "30000"));
        
        //Test to see if result is correct
        guild.changeUpkeep("M001","9000");
        guild.changeUpkeep("M002","25000");
        
        List<String> details1 = new ArrayList<String>();
        List<String> details2 = new ArrayList<String>();

        details1 = guild.memberDetails.get("M001");
        details2 = guild.memberDetails.get("M002");

        assertEquals("9000",details1.get(2));
        assertEquals("25000",details2.get(2));
    }

    @Test
    /**
     * Test6:
     * Test if Members get removed correctly
     */
    public void testRemoveMember(){
        Guild guild = new Guild("Test Guild");
        guild.addMember("M001", "John", "Warrior", "10000");
        guild.addMember("M002", "Jimmy", "Archer", "20000");
        assertTrue(guild.RemoveMember("M001"));
        assertFalse(guild.RemoveMember("M003"));
    }

    @Test
    /**
     * Test7:
     * Test if a list of ids of all members with the same name are given
     */
    public void testSameName(){
        Guild guild = new Guild("Test Guild");
        guild.addMember("M001", "John","Archer","10000");
        guild.addMember("M002", "Tim","Warrior","12000");
        guild.addMember("M003", "Ben","Mage","15000");
        guild.addMember("M004", "Ben","Warrior","15000");
        guild.addMember("M005", "Ben","Archer","15000");
        guild.addMember("M006", "Ben","Mage","17000");
        List<String> ids = new ArrayList<String>();
        ids.add("M003");
        ids.add("M004");
        ids.add("M005");
        ids.add("M006");
        assertEquals(ids, guild.SameName("Ben"));
    }

    @Test
    /**
     * Test8:
     * Test if the correct result is given for upkeep difference
     */
    public void testUpkeepDiff(){
        Guild guild = new Guild("Test Guild");
        guild.addMember("M001", "John","Archer","10000");
        guild.addMember("M002", "Tim","Warrior","12000");
        Integer diff = 2000;
        assertEquals(diff,guild.UpkeepDiff("M001", "M002"));
        assertEquals(diff,guild.UpkeepDiff("M002", "M001"));
    }
}