package com.sg.classroster.dao;

import com.sg.classroster.dto.Student;

import java.io.*;
import java.util.*;

public class ClassRosterDaoFIleImpl implements ClassRosterDao {

    public static final String ROSTER_FILE = "roster.txt";
    public static final String DELIMITER = "::";
    private Map<String, Student> students = new HashMap<>();

    @Override
    public Student addStudent(String studentId, Student student) throws ClassRosterDaoException{
        loadRoster();
        Student newStudent = students.put(studentId, student);
        writeRoster();
        return newStudent;
    } //we simply put the supplied Student into our map using the supplied student id as the key and we're done.

    /**
     * This code gets all of the Student objects out of the students map as a collection by calling the values() method.
     * We pass that returned collection into the constructor for a new ArrayList.
     * One of the constructors for ArrayList take a collection as a parameter,
     * which effectively allows us to convert the collection of Student objects into an ArrayList of Student objects that we can return from the method.
     * Note that our method specifies that we'll return a List<Student> but we create and return an ArrayList<Student>.
     * This is perfectly fine because ArrayList implements the List interface so it can be treated as a List.
     * @return
     */
    @Override
    public List<Student> getAllStudents() throws ClassRosterDaoException{
        loadRoster();
        return new ArrayList<Student>(students.values());
    }

    @Override
    public Student getStudent(String studentId) throws ClassRosterDaoException {
        loadRoster();
        return students.get(studentId);
    }

        @Override
    public Student removeStudent(String studentId) throws ClassRosterDaoException {
        loadRoster();
            Student removedStudent = students.remove(studentId);
            writeRoster();
            return removedStudent;
    }

    private Student unmarshallStudent(String studentAsText){

        String[] studentTokens = studentAsText.split(DELIMITER);

        // Given the pattern above, the student Id is in index 0 of the array.
        String studentId = studentTokens[0];

        // Which we can then use to create a new Student object to satisfy
        // the requirements of the Student constructor.
        Student studentFromFile = new Student(studentId);

        // However, there are 3 remaining tokens that need to be set into the
        // new student object. Do this manually by using the appropriate setters.

        // Index 1 - FirstName
        studentFromFile.setFirstName(studentTokens[1]);

        // Index 2 - LastName
        studentFromFile.setLastName(studentTokens[2]);

        // Index 3 - Cohort
        studentFromFile.setCohort(studentTokens[3]);

        // We have now created a student! Return it!
        return studentFromFile;
    }


    private void loadRoster() throws ClassRosterDaoException {
        Scanner scanner;

        try{
           scanner = new Scanner(
                   new BufferedReader(
                           new FileReader(ROSTER_FILE)));
        } catch (FileNotFoundException e){
            throw new ClassRosterDaoException(" -_- Could not load roster data in memory.",e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentStudent holds the most recent student unmarshalled
        Student currentStudent;
        // Go through ROSTER_FILE line by line, decoding each line into a
        // Student object by calling the unmarshallStudent method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a Student
            currentStudent = unmarshallStudent(currentLine);

            // We are going to use the student id as the map key for our student object.
            // Put currentStudent into the map using student id as the key
            students.put(currentStudent.getStudentId(), currentStudent);
        }
        // close scanner
        scanner.close();
    }

    private String marshallStudent(Student aStudent){
        // Start with the student id, since that's supposed to be first.
        String studentAsText = aStudent.getStudentId() + DELIMITER;

        // add the rest of the properties in the correct order:

        // FirstName
        studentAsText += aStudent.getFirstName() + DELIMITER;

        // LastName
        studentAsText += aStudent.getLastName() + DELIMITER;

        // Cohort skip the DELIMITER here.
        studentAsText += aStudent.getCohort();

        // We have now turned a student to text! Return it!
        return studentAsText;
    }

    /**
     * Writes all students in the roster out to a ROSTER_FILE.  See loadRoster
     * for file format.
     *
     * @throws ClassRosterDaoException if an error occurs writing to the file
     */
    private void writeRoster() throws ClassRosterDaoException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ROSTER_FILE));
        } catch (IOException e) {
            //At the top of this method, in the try-catch block, we catch the IOException and translate it into a ClassRosterDaoException.
            throw new ClassRosterDaoException(
                    "Could not save student data.", e);
        }

        String studentAsText;
        List<Student> studentList = this.getAllStudents();
        for (Student currentStudent : studentList) {
            // turn a Student into a String
            studentAsText = marshallStudent(currentStudent);
            // write the Student object to the file
            out.println(studentAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

}
    //TEMPLATE
    /* @Override
    public Student removeStudent(String studentId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }*/
