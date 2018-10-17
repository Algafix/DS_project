package core.ds.ds_project;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class Client {


    /**
     * Convierte un duration en un string para mostrar formato HH:mm:ss
     */
    public static String formatDuration(Duration duration) {
        long seconds = duration.getSeconds();
        long absSeconds = Math.abs(seconds);
        String positive = String.format(
                "%02d:%02d:%02d",
                absSeconds / 3600,
                (absSeconds % 3600) / 60,
                absSeconds % 60);
        return seconds < 0 ? "-" + positive : positive;
    }

    public static String formatDateTime(LocalDateTime time) {

        if(time != null ){
            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
            return time.format(formatter);
        }
        else{
            return "                       ";
        }
    }

    /**
     * Serializes a project to a file with the name given by the parameters
     *
     * @param allFather the project expected to be received (dummy project) and the one we will serialize
     * @param filename the name of the file where the project is going to be saved
     */
    public static void serializeProject(Project allFather, String filename){
        // Serialization
        try
        {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(allFather);

            out.close();
            file.close();

            System.out.println("Object has been serialized");

        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }

    }

    /**
     * Deserializes a project from a file with the name given by the parameters
     *
     * @param filename the name of the file where the project is going to be loaded from
     */
    public static Project deserializeProject(String filename){
        // Deserialization
        try
        {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            Project deserializedProject = (Project)in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized ");
            return deserializedProject;
        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
            return null;
        }

        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
            return null;
        }
    }


    public static void main(String [] args){


        AppClock.getInstance(2000);

        Test test = new Test();

        //test.testApenndA1();

        //test.testApenndA2();

        test.testProgramatedtaskAndLimitTimeTaskDecarator();

        //test.testLimitTimeDecorator();

        //test.testProgramatedtask();

        //test.testNestedInterval();
    }
}
