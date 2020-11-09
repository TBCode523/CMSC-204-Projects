package sample;

import java.util.Objects;

public class CourseDBElement implements Comparable{
    private String courseID;
    private String roomNumber;
    private String instructor;
    private int crn;
    private int credits;
public CourseDBElement(String courseID, int crn, int credits, String roomNumber, String instructor){
    this.courseID = courseID;
    this.credits = credits;
    this.crn =crn;
    this.roomNumber = roomNumber;
    this.instructor = instructor;

}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseDBElement that = (CourseDBElement) o;
        return crn == that.crn &&
                credits == that.credits &&
                Objects.equals(courseID, that.courseID) &&
                Objects.equals(roomNumber, that.roomNumber) &&
                Objects.equals(instructor, that.instructor);
    }

    @Override
    public int hashCode() {
        String crnString = crn + "";
        return Objects.hash(crnString);
    }

    public int getCredits() {
        return credits;
    }

    public int getCRN() {
        return crn;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getInstructor() {
        return instructor;
    }

    @Override
    public String toString() {
        return
                "\nCourse:" + courseID +  " CRN:" + crn + " Credits:" + credits + " Instructor:"+ instructor+
                " Room:" + roomNumber ;
    }

    @Override
    public int compareTo(CourseDBElement element) {
        if(getCRN() < element.getCRN() ) return -1;
        else if(getCRN() > element.getCRN()) return 1;
        else return 0;
    }
}
