import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static int [] processId;
    static int [] arrivalTime;
    static int [] burstTime;
    static int [] completionTime ;
    static int [] turnAroundTime;
    static int [] waitingTime;
    static int [] priority;
    static int [] deadline;
    static int [] readyQueue;
    static int [] runningQueue;
    static int [] completed;
    static int [] temp;
    static int time = 0;
    static int process = 0;
    static Scanner scan = new Scanner(System.in);
    static String answer,algorithm = "";
    static double sum = 0;
    public static void main(final String args[]){
        answer = "Y";
        while(answer.equalsIgnoreCase("Y")){
                getNumberOfProcess();
                getArrivalTime();
                getBurstTime();
                getAlgorithm();
                selectAlgorithm();
                termination();
        }
        scan.close();
    }


    static void selectAlgorithm(){
        switch(algorithm){
            case "A": 
            System.out.println("FCFS");
                System.out.println("SJF");
                FCFS fcfs = new FCFS(processId,arrivalTime,burstTime,temp,completionTime,turnAroundTime,waitingTime,readyQueue,process,time,sum);
                fcfs.firstComeFirstServe();
                fcfs.displayTable();
                break;
            case "B": 
                System.out.println("SJF");
                SJF sjf = new SJF(processId,arrivalTime,burstTime,temp,completionTime,turnAroundTime,waitingTime,readyQueue,process,time,sum);
                sjf.getShortestJob();
                sjf.displayTable();
                break;
            case "C": 
            System.out.println("Priority");
                getPriority();
                Priority prio = new Priority(processId,arrivalTime,burstTime,temp,priority,completionTime,turnAroundTime,waitingTime,readyQueue,process,time,sum);
                prio.getPriority();
                prio.displayTable();
                break;
            case "D": 
            System.out.println("Deadline");
            getDeadline();
            break;
            case "E": 
            System.out.println("MLQ");
            break;
            case "F": 
            System.out.println("Exit");
            answer = "N"; 
            break;
        }
    }
    static void getAlgorithm(){
        System.out.println("CPU Scheduling Algorithm:");
        System.out.println("[A] First Come First Serve (FCFS)");
        System.out.println("[B] Shortest Job First (SJF)");
        System.out.println("[C] Priority (Prio");
        System.out.println("[D] Deadline");
        System.out.println("[E] Multilevel Queue (MLQ)");
        System.out.println("[F] Exit");
        algorithm = scan.next().toUpperCase();

    }
    static void termination(){
        System.out.println("Input again (y/n)?");
        answer = scan.next().toUpperCase();
        if(answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("N")){
            if(answer.equalsIgnoreCase("N")){
                System.out.println("Terminating Program.........");
            }
            else{
                System.out.print("\033[H\033[2J");  
                System.out.flush(); 
            }
        }
        else{
            termination();
        }
    }

    static void getNumberOfProcess(){
        System.out.print("Input number of processes [2-9]: ");
        process = scan.nextInt();

        processId= new int [process];
        arrivalTime= new int [process];
        burstTime= new int [process];
        deadline = new int [process];
        priority = new int [process];
        completionTime= new int [process];
        turnAroundTime= new int [process];
        waitingTime= new int [process];
        temp= new int [process];
    }

    static void getArrivalTime(){
        System.out.println("Input individual arrival time:");
        for(int i = 0; i < process; i++){
            System.out.print("AT" + (i+1) + ": ");
            arrivalTime[i] = scan.nextInt();
            processId[i] = i+1;
        }
        System.out.println();
    }

    static void getBurstTime(){
        System.out.println("Input individual burst time:");
        for(int i = 0; i < process; i++){
            System.out.print("BT" + (i+1) + ": ");
                burstTime[i] = scan.nextInt();
        }
        temp = burstTime.clone();
        System.out.println();
    }
    static void getDeadline(){
        System.out.println("Enter Deadline.");
        for(int i = 0; i < process; i++){
            System.out.print("Deadline" + (i+1) + ": ");
                deadline[i] = scan.nextInt();
        }
    }
    static void getPriority(){
        System.out.println("Input individual priority number:");
        for(int i = 0; i < process; i++){
            System.out.print("Prio" + (i+1) + ": ");
            priority[i] = scan.nextInt();
        }
    }

}