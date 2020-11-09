package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class CourseDBManager implements CourseDBManagerInterface {
private CourseDBStructure courseDBStructure ;
public CourseDBManager(){
     courseDBStructure = new CourseDBStructure(20);
}
    @Override
    public void add(String id, int crn, int credits, String roomNum, String instructor) {
        courseDBStructure.add(new CourseDBElement(id, crn, credits, roomNum, instructor));
    }

    @Override
    public CourseDBElement get(int crn) throws IOException {
        return courseDBStructure.get(crn);
    }

    @Override
    public void readFile(File input) throws FileNotFoundException {
         String courseID;
         String roomNumber;
         String instructor;
         int crn;
         int credits;
        if(!input.exists()) throw new FileNotFoundException();
        else {
            Scanner fileScanner = new Scanner(input);
            while(fileScanner.hasNext()){

              String data =  fileScanner.nextLine();
                String [] strings = data.split(" ", 5);
                courseID = strings[0];
                crn = Integer.parseInt(strings[1]);
                credits = Integer.parseInt(strings[2]);
                roomNumber = strings[3];
                instructor = "";
                for(int i = 4; i < strings.length; i++  ){
                    instructor += strings[i];
                }
                CourseDBElement courseDBElement = new CourseDBElement(courseID,crn,credits,roomNumber,instructor);
                courseDBStructure.add(courseDBElement);
            }

        }
    }

    @Override
    public ArrayList<String> showAll() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < courseDBStructure.hashTable.length; i++){
            if(courseDBStructure.hashTable[i] != null){
                for (int j = 0; j < courseDBStructure.hashTable[i].size(); j++) {

                    arrayList.add(courseDBStructure.hashTable[i].get(j).toString());
                }
            }
        }
        return arrayList;
    }
}
