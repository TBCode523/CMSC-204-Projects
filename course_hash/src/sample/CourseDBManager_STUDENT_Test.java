package sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CourseDBManager_STUDENT_Test {
    private CourseDBManagerInterface dataMgr = new CourseDBManager();

    /**
     * Create an instance of CourseDBManager
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        dataMgr = new CourseDBManager();
    }

    /**
     * Set dataMgr reference to null
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        dataMgr = null;
    }

    /**
     * Test for the add method
     */
    @Test
    public void testAddToDB() {
        try {
            dataMgr.add("CMSC233",30510,4,"SC455","Vizaya Khan");
        }
        catch(Exception e) {
            fail("This should not have caused an Exception");
        }
    }

    /**
     * Test for the showAll method
     */
    @Test
    public void testShowAll() {
        dataMgr.add("GEOL155",30503,3,"SC350","Alexander Broyes");
        dataMgr.add("CMSC206",30504,4,"SC450","Biance Deezo-So");
        dataMgr.add("CMSC222",30559,4,"SC450","Talal Brek");
        ArrayList<String> list = dataMgr.showAll();

        assertEquals(list.get(0),"\nCourse:GEOL155 CRN:30503 Credits:3 Instructor:Alexander Broyes Room:SC350");
        assertEquals(list.get(1),"\nCourse:CMSC206 CRN:30504 Credits:4 Instructor:Biance Deezo-So Room:SC450");
        assertEquals(list.get(2),"\nCourse:CMSC222 CRN:30559 Credits:4 Instructor:Talal Brek Room:SC450");
    }
    @Test
    public void getCRN() throws IOException {
        dataMgr.add("GEOL155",30503,3,"SC350","Alexander Broyes");
        dataMgr.add("CMSC206",30504,4,"SC450","Biance Deezo-So");
        dataMgr.add("CMSC222",30559,4,"SC450","Talal Brek");
        ArrayList<String> list = dataMgr.showAll();
        CourseDBElement c = dataMgr.get(30503);
        CourseDBElement c1 = dataMgr.get(30504);
        assertEquals(new CourseDBElement("GEOL155",30503,3,"SC350","Alexander Broyes"), c);
        assertEquals(new CourseDBElement("CMSC206",30504,4,"SC450","Biance Deezo-So"), c1);

    }
    @Test
    public void failGetCRN() throws IOException {
        dataMgr.add("GEOL155",30503,3,"SC350","Alexander Broyes");
        dataMgr.add("CMSC206",30504,4,"SC450","Biance Deezo-So");
        dataMgr.add("CMSC222",30559,4,"SC450","Talal Brek");
        try {
           dataMgr.get(1111);
           assertTrue("This should have caused an IO Exception", false);
        } catch (IOException e){
            assertTrue("This should have caused an IO Exception", true);
        }

    }

    /**
     * Test for the read method
     */
    @Test
    public void testRead() {
        try {
            File inputFile = new File("Test1.txt");
            PrintWriter inFile = new PrintWriter(inputFile);
            inFile.println("GEOL155 30503 3 SC350 Alexander Broyes");
            inFile.print("CMSC206 30504 4 SC450 Biance Deezo-So");

            inFile.close();
            dataMgr.readFile(inputFile);
            CourseDBElement e1 = dataMgr.get(30503);
            CourseDBElement e2 = dataMgr.get(30504);
            assertEquals(new CourseDBElement("GEOL155",30503, 3, "SC350", "Alexander Broyes" ),e1);
            assertEquals(new CourseDBElement("CMSC206",30504, 4, "SC450", "Biance Deezo-So" ),e2);

            //System.out.println(dataMgr.showAll());
        } catch (Exception e) {
            fail("Should not have thrown an exception");
        }
    }
}
