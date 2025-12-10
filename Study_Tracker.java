import java.io.*;
import java.time.LocalDate;
import java.util.*;

// Represents a single study log entry
class StudyLog
{
    public LocalDate Date;
    public String Subject;
    public double Duration;
    public String Description;

    public StudyLog(LocalDate A, String B, double C, String D)
    {
        this.Date = A;
        this.Subject = B;
        this.Duration = C;
        this.Description = D;
    }

    @Override
    public String toString()
    {
        return Date + " | " + Subject + " | " + Duration + " | " + Description;
    }

    public LocalDate getDate() { return Date; }
    public String getSubject() { return Subject; }
    public double getDuration() { return Duration; }
    public String getDescription() { return Description; }
}

// Main tracker class
class StudyTracker
{
    private ArrayList<StudyLog> Database = new ArrayList<>();

    public void InsertLog()
    {
        Scanner Scannerobj = new Scanner(System.in);

        System.out.println("------------------------------------------------------------");
        System.out.println("--------------- Enter Study Details ------------------------");
        System.out.println("------------------------------------------------------------");

        LocalDate DateObj = LocalDate.now();

        System.out.println("Enter Subject name (Example: C/C++/Java/DS):");
        String sub = Scannerobj.nextLine();

        System.out.println("Enter duration (hours):");
        double dur = Scannerobj.nextDouble();
        Scannerobj.nextLine(); 

        System.out.println("Enter description:");
        String desc = Scannerobj.nextLine();

        StudyLog StudyObj = new StudyLog(DateObj, sub, dur, desc);
        Database.add(StudyObj);

        System.out.println("Study log added successfully.");
        System.out.println("------------------------------------------------------------");
    }

    public void DisplayLog()
    {
        System.out.println("------------------------------------------------------------");

        if (Database.isEmpty())
        {
            System.out.println("No study logs found.");
            System.out.println("------------------------------------------------------------");
            return;
        }

        System.out.println("-------------- All Study Logs -----------------------------");

        for (StudyLog sobj : Database)
        {
            System.out.println(sobj);
        }

        System.out.println("------------------------------------------------------------");
    }

    public void ExportCSV()
    {
        System.out.println("------------------------------------------------------------");

        if (Database.isEmpty())
        {
            System.out.println("No study logs to export.");
            System.out.println("------------------------------------------------------------");
            return;
        }

        String FileName = "StudyTracker.csv";

        try (FileWriter fwobj = new FileWriter(FileName))
        {
            fwobj.write("Date,Subject,Duration,Description\n");

            for (StudyLog sobj : Database)
            {
                fwobj.write(
                    sobj.getDate() + "," +
                    sobj.getSubject().replace(",", " ") + "," +
                    sobj.getDuration() + "," +
                    sobj.getDescription().replace(",", " ") + "\n"
                );
            }

            System.out.println("CSV exported successfully.");

        }
        catch (Exception eobj)
        {
            System.out.println("Error occurred while creating CSV.");
        }
    }

    public void SummaryByDate()
    {
        System.out.println("------------------------------------------------------------");

        if (Database.isEmpty())
        {
            System.out.println("No study logs found.");
            System.out.println("------------------------------------------------------------");
            return;
        }

        System.out.println("--------------- Summary By Date ----------------------------");

        TreeMap<LocalDate, Double> tobj = new TreeMap<>();

        for (StudyLog sobj : Database)
        {
            LocalDate date = sobj.getDate();
            double duration = sobj.getDuration();

            tobj.put(date, tobj.getOrDefault(date, 0.0) + duration);
        }

        for (LocalDate ldobj : tobj.keySet())
        {
            System.out.println("Date: " + ldobj + " | Total Study: " + tobj.get(ldobj));
        }

        System.out.println("------------------------------------------------------------");
    }

    public void SummaryBySubject()
    {
        System.out.println("------------------------------------------------------------");

        if (Database.isEmpty())
        {
            System.out.println("No study logs found.");
            System.out.println("------------------------------------------------------------");
            return;
        }

        System.out.println("--------------- Summary By Subject -------------------------");

        TreeMap<String, Double> tobj = new TreeMap<>();

        for (StudyLog sobj : Database)
        {
            String subject = sobj.getSubject();
            double duration = sobj.getDuration();

            tobj.put(subject, tobj.getOrDefault(subject, 0.0) + duration);
        }

        for (String str : tobj.keySet())
        {
            System.out.println("Subject: " + str + " | Total Study: " + tobj.get(str));
        }

        System.out.println("------------------------------------------------------------");
    }
}

class Study_Tracker
{
    public static void main(String A[])
    {
        StudyTracker stobj = new StudyTracker();
        Scanner sobj = new Scanner(System.in);

        int iChoice = 0;

        System.out.println("------------------------------------------------------------");
        System.out.println("--------------- Welcome to Study Tracker App ---------------");
        System.out.println("------------------------------------------------------------");

        do
        {
            System.out.println("\nChoose an option:");
            System.out.println("1 : Add new Study Log");
            System.out.println("2 : View all Study Logs");
            System.out.println("3 : Summary by Date");
            System.out.println("4 : Summary by Subject");
            System.out.println("5 : Export Study Logs to CSV");
            System.out.println("6 : Exit");

            iChoice = sobj.nextInt();

            switch(iChoice)
            {
                case 1:
                    stobj.InsertLog();
                    break;

                case 2:
                    stobj.DisplayLog();
                    break;

                case 3:
                    stobj.SummaryByDate();
                    break;

                case 4:
                    stobj.SummaryBySubject();
                    break;

                case 5:
                    stobj.ExportCSV();
                    break;

                case 6:
                    System.out.println("------------------------------------------------------------");
                    System.out.println("Thank you for using Study Tracker App.");
                    System.out.println("------------------------------------------------------------");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } 
        while(iChoice != 6);
    }
}
