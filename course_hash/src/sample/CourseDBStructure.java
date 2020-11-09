package sample;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

public class CourseDBStructure implements CourseDBStructureInterface {

    private int tableSize;
    public LinkedList<CourseDBElement>[] hashTable;
    private String name;
    /**
     * Use the hashcode of the CourseDatabaseElement to see if it is
     * in the hashtable.
     * <p>
     * If the CourseDatabaseElement does not exist in the hashtable,
     * add it to the hashtable.
     *
     * @param element the CDE to be added
     */
    public CourseDBStructure( String name, int tableSize){
        this.tableSize = tableSize;
        this.name = name;
        hashTable =  new LinkedList[tableSize];
    }
    public CourseDBStructure(int tableSize){
        this.tableSize = tableSize;
        this.name ="";
        hashTable =  new LinkedList[tableSize];
    }
    @Override
    public void add(CourseDBElement element) {
        int index = element.hashCode() % getTableSize();
        if (hashTable[index]== null) {
            hashTable[index] = new LinkedList<>();
            hashTable[index].add(element);
        }else {

            for(int i = 0; i < hashTable[index].size(); i++){
                if(element.compareTo(hashTable[index].get(i)) < 0){
                    if(i == 0) hashTable[index].addFirst(element);
                    else if(i == hashTable[index].size()-1) hashTable[index].addLast(element);
                    else {
                        hashTable[index].add(i,element);
                    }
                    break;
                }
                else if(element.compareTo(hashTable[index].get(i)) > 0){
                    if(i == hashTable[index].size()-1) {
                        hashTable[index].addLast(element);
                        break;
                    }
                }
            }
        }



    }

    /**
     * Use the hashcode of the CourseDatabaseElement to see if it is
     * in the hashtable.
     * <p>
     * If the CourseDatabaseElement is in the hashtable, return it
     * If not, throw an IOException
     *
     * @param crn@throws IOException
     */
    @Override
    public CourseDBElement get(int crn) throws IOException {

        String crnString= ""+crn;
        int hash = Objects.hash(crnString);
        int index = hash% hashTable.length;
        if(hashTable[index] !=null)
        for (int i = 0; i < hashTable[index].size(); i++){
            CourseDBElement courseDBElement = hashTable[index].get(i);
            if(courseDBElement.getCRN() == crn) return courseDBElement;
        }
        throw new IOException();
    }

    /**
     * Returns the size of the ConcordanceDataStructure (number of indexes in the array)
     */
    @Override
    public int getTableSize() {
        return tableSize;
    }
}
