
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
    static int[] period;
    static int[] flags;
    static int[] queuePriority;
    static int[] tempPeriod;
    static int[] array;
    static int[] startingTime;
    static int[] readyQueue;
    static int[] runningQueue;
    static int[] count;
    static int[] temp;
    static int time = 0;
    static int process = 0;
    static Scanner scan = new Scanner(System.in);
    static String answer, algorithm = "";
    static double sum = 0;
    static int output = 0;
    static int lcm = 1;
    static int startTime = 0;
    static int check = 0;

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
        System.out.println("[C] Priority (Prio)");
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
                prio.getPrio();
                break;
            case "D":
                getDeadline();
                getPeriod();
                EDF edf = new EDF(processId, startingTime, burstTime, deadline, period, completionTime, turnAroundTime,waitingTime, temp,tempPeriod, count,
                    array, readyQueue,time,process,sum, lcm);
                edf.getEDF();
                System.out.println(process);
                break;
            case "E":
                System.out.println("MLQ");
                System.out.println("Input Number of Queues:");
                System.out.println("[1] First Come First Serve (FCFS)");
                System.out.println("[2] Shortest Job First (SJF)");
                getProcessQueue();
                MLQ mlq = new MLQ(processId, arrivalTime, burstTime, completionTime, turnAroundTime, waitingTime, flags, queuePriority, startTime ,check, process);
                mlq.execute();
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
        count= new int [process];
        period = new int [process];
        array = new int [process];
        flags = new int [process];
        tempPeriod = new int [process];
        queuePriority = new int [process];
        startingTime = new int [process];
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
            processId[i] = i;
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
                    startingTime[i] = 0;
                }
                else {
                    System.out.print("ENTER VALID INPUT FOR Deadline " + (i+1) + ": ");
                    scan.next();
                    continue;
                }
            done = false;
            }
        }
        temp = deadline.clone();
        System.out.println();
    }

    static void getPeriod(){
        System.out.println();
        System.out.println("Input period for each process:");
        boolean done = true;
        for(int i = 0; i < process; i++){
            done = true;
            System.out.print("Period " + (i+1) + ": ");
            while (done) {
                if (scan.hasNextInt()){
                    period[i] = scan.nextInt();
                }
                else {
                    System.out.print("ENTER VALID INPUT FOR Period " + (i+1) + ": ");
                    scan.next();
                    continue;
                }
            done = false;
            }
        }
        array = period.clone();
        tempPeriod = period.clone();
        System.out.println();
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


    static void getProcessQueue(){
        //user input to assign the queue number for each processes
        for(int i = 0; i < process; i++){
            boolean done = true;
            System.out.print("Enter Process " + (i + 1) + " Queue: ");
            while (done) {
                if (scan.hasNextInt()){
                    queuePriority[i] = scan.nextInt();
                    if(queuePriority[i]<1 || queuePriority[i]>2){
                        System.out.println("1 or 2 only!");
                        getProcessQueue();
                    }
                }
                else {
                    System.out.print("ENTER VALID INPUT FOR Process Queue" + (i+1) + ": ");
                    scan.next();
                    continue;
                }
                done = false;
            }
        } 
        System.out.println();
    }

}