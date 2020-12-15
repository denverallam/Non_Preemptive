import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static int[] processId;
    static int[] arrivalTime;
    static int[] burstTime;
    static int[] completionTime;
    static int[] turnAroundTime;
    static int[] waitingTime;
    static int[] priority;
    static int[] deadline;
    static int[] readyQueue;
    static int[] runningQueue;
    static int[] completed;
    static int[] temp;
    static int time = 0;
    static int process = 0;
    static Scanner scan = new Scanner(System.in);
    static String answer, algorithm = "";
    static double sum = 0;
    static int output = 0;
    static int totalCT = 0;

    public static void main(final String args[]) {
        answer = "Y";
        while (answer.equalsIgnoreCase("Y")) {
            getNumberOfProcess();
            getArrivalTime();
            getBurstTime();
            displayChoices();
            getAlgorithm();
            termination();
        }
        scan.close();
    }


    static void displayChoices(){
        System.out.println("CPU Scheduling Algorithm:");
        System.out.println("[A] First Come First Serve (FCFS)");
        System.out.println("[B] Shortest Job First (SJF)");
        System.out.println("[C] Priority (Prio");
        System.out.println("[D] Deadline");
        System.out.println("[E] Multilevel Queue (MLQ)");
        System.out.println("[F] Exit");
    }
    static void getAlgorithm() {
        System.out.print("Enter Choice: ");
        algorithm = scan.next().toUpperCase();
        if(algorithm.equalsIgnoreCase("A") || algorithm.equalsIgnoreCase("B") || algorithm.equalsIgnoreCase("C") ||
        algorithm.equalsIgnoreCase("D") || algorithm.equalsIgnoreCase("E") || algorithm.equalsIgnoreCase("F")){
            if(algorithm.equalsIgnoreCase("F")){
                System.out.println("Terminating Program.........");
                System.exit(0);
            }
        }
        else{
            getAlgorithm();
        }

        switch (algorithm) {
            case "A":
                FCFS fcfs = new FCFS(processId, arrivalTime, burstTime, temp, completionTime, turnAroundTime,
                        waitingTime, readyQueue, process, time, sum);
                fcfs.firstComeFirstServe();
                break;
            case "B":
                SJF sjf = new SJF(processId, arrivalTime, burstTime, temp, completionTime, turnAroundTime, waitingTime,
                        readyQueue, process, time, sum);
                sjf.getShortestJob();
                break;
            case "C":
                getPriority();
                Priority prio = new Priority(processId, arrivalTime, burstTime, temp, priority, completionTime,
                        turnAroundTime, waitingTime, readyQueue, process, time, sum);
                prio.getPriority();
                break;
            case "D":
                getDeadline();
                getOutputNumber();
                EDF edf = new EDF(processId, burstTime, deadline, completionTime, turnAroundTime, waitingTime,
                        readyQueue, process, output, sum, totalCT);
                edf.getEarliestDeadline();
                break;
            case "E":
                System.out.println("MLQ");
                break;
            case "F":
                System.out.println("Exit");
                answer = "N";
                break;
        }
        System.out.println();
    }

    static void termination(){
        System.out.print("Input again (y/n)?: ");
        answer = scan.next().toUpperCase();
        if(answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("N")){
            if(answer.equalsIgnoreCase("N")){
                System.out.println();
                System.out.println("Terminating Program.........");
                System.exit(0);
            }
        }
        else{
            termination();
        }
        System.out.println();
    }

    static void getNumberOfProcess(){
        System.out.print("Input number of processes [2-9]: ");
        boolean done = true;
        while (done) {
            if (scan.hasNextInt()){
                process = scan.nextInt();
                if(process<2 || process>9){
                    getNumberOfProcess();
                }
                done = false;
            }
            else {
                System.out.print("ENTER VALID NUMBER FROM 2 TO 9: ");
                scan.next();
                continue;
            }
        }
  
        processId= new int [process];
        arrivalTime= new int [process];
        burstTime= new int [process];
        deadline = new int [process];
        priority = new int [process];
        completionTime= new int [process];
        readyQueue = new int[process];
        turnAroundTime= new int [process];
        waitingTime= new int [process];
        temp = new int [process];

        System.out.println();
    }

    static void getArrivalTime(){

        boolean done = true;
        System.out.println("Input individual arrival time:");

        for(int i = 0; i < process; i++){
            done = true;
            System.out.print("AT" + (i+1) + ": ");
            while (done) {
                if (scan.hasNextInt()){
                    arrivalTime[i] = scan.nextInt();
                }
                else {
                    System.out.print("ENTER VALID INPUT FOR AT" + (i+1) + ": ");
                    scan.next();
                    continue;
                }
            done = false;
            }
            processId[i] = i;
        } 
        System.out.println();

    }

    static void getBurstTime(){
        System.out.println("Input individual burst time:");
        boolean done = true;
        for(int i = 0; i < process; i++){
            done = true;
            System.out.print("BT" + (i+1) + ": ");
            while (done) {
                if (scan.hasNextInt()){
                    burstTime[i] = scan.nextInt();
                }
                else {
                    System.out.print("ENTER VALID INPUT FOR BT" + (i+1) + ": ");
                    scan.next();
                    continue;
                }
            done = false;
            }
        } 
        temp = burstTime.clone();
        System.out.println();

    }
    static void getDeadline(){
        System.out.println();
        System.out.println("Input deadline for each process:");
        boolean done = true;
        for(int i = 0; i < process; i++){
            done = true;
            System.out.print("Deadline " + (i+1) + ": ");
            while (done) {
                if (scan.hasNextInt()){
                    deadline[i] = scan.nextInt();
                }
                else {
                    System.out.print("ENTER VALID INPUT FOR Deadline " + (i+1) + ": ");
                    scan.next();
                    continue;
                }
            done = false;
            }
        }
        readyQueue =processId.clone();
        System.out.println();

    }
    static void getOutputNumber(){
        boolean done = true;
        System.out.print("Enter number of output [MAX OF 3]:");
        while (done) {
            if (scan.hasNextInt()){
                output = scan.nextInt();
                if(output>3){
                    getOutputNumber();
                }
                done = false;
            }
            else {
                System.out.print("ENTER VALID NUMBER [MAX OF 3]: ");
                scan.next();
                continue;
            }
        }
    }
    static void getPriority(){
        System.out.println();
        System.out.println("Input individual priority number:");
        boolean done = true;
        for(int i = 0; i < process; i++){
            done = true;
            System.out.print("PRIO " + (i+1) + ": ");
            while (done) {
                if (scan.hasNextInt()){
                    priority[i] = scan.nextInt();
                }
                else {
                    System.out.print("ENTER VALID INPUT FOR PRIO " + (i+1) + ": ");
                    scan.next();
                    continue;
                }
            done = false;
            }
        } 
        System.out.println();
    }

}