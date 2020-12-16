import java.util.Scanner;

public class MLQ{

	int queuePriority[];
	int completionTime[]; 
	int turnAroundTime[];
	int waitingTime[];
	int arrivalTime[];
	int processID[];
	int burstTime[];
	int flags[]; 
	int startingTime=0, checkProcessComplete=0;
	int numOfProcesses=0;

	public MLQ(int pId[], int at[], int bt[], int ct[], int tat[], int wt[], int f[], int qp[], int st, int check, int p){
		this.processID = pId;
		this.arrivalTime = at;
		this.burstTime = bt;
		this.completionTime = ct;
		this.flags = f;
		this.turnAroundTime = tat;
		this.waitingTime = wt;
		this.queuePriority = qp;
		this.startingTime =st;
		this.checkProcessComplete = check;
        this.numOfProcesses = p;
        
	}

    

    void getRunningProcess(){
        while (true) {
            int c = numOfProcesses, min = 99999;
            if (checkProcessComplete == numOfProcesses)
                break; 

            for (int i = 0; i < numOfProcesses; i++) {
            	processID[i] = i+1;
                if (queuePriority[i] == 1) { //FCFS algo is for queue 1
                    if ((arrivalTime[i] <= startingTime) && (flags[i] == 0) && (arrivalTime[i] < min)) {
                        min = arrivalTime[i];
                        c = i;
                    }
                }
                
                if (queuePriority[i] == 2) { //SJF algo is for queue 2
                    if ((arrivalTime[i] <= startingTime) && (flags[i] == 0) && (burstTime[i]<min)){ 
                        min = burstTime[i];
                        c = i;
                    }
                }
            }


            if (c == numOfProcesses)
                startingTime++; //this is for the idle time
            else {
                completionTime[c] = startingTime + burstTime[c]; 
                startingTime += burstTime[c]; 
                turnAroundTime[c] = completionTime[c] - arrivalTime[c]; 
                waitingTime[c] = turnAroundTime[c] - burstTime[c]; 
                flags[c] = 1; 
                checkProcessComplete++; 
			}
		}    
		displayTable();
	}
	public void displayTable(){
		float totalWaitingTime=0, totalTurnAroundTime=0;
		float averageWaitingTime=0,averageTurnAroundTime=0;

		System.out.println("PID\tAT\tBT\tCT\tTAT\tWT");
        System.out.println("--------------------------------------------------");
        for(int i = 0; i<numOfProcesses;i++){
        System.out.println("P" + (i+1) + "\t" + arrivalTime[i] + "\t" + burstTime[i] +"\t" + completionTime[i]
        + "\t" + turnAroundTime[i] + "\t" + waitingTime[i]);
        System.out.println("--------------------------------------------------");
        }	

        averageWaitingTime = totalWaitingTime / numOfProcesses; //formula for average waiting time
        averageTurnAroundTime = totalTurnAroundTime / numOfProcesses; //formula for average turnaround time

        System.out.println("Average Turn-around Time:\t" + String.format("%.2f", computeAverage(turnAroundTime)) + " units");
        System.out.println("Average Waiting Time:\t\t" +String.format("%.2f", computeAverage(waitingTime))+ " units");
        System.out.println();  
	}
	double computeAverage(int array[]){
        double sum = 0;
        for(int num: array){
            sum += num;
        }
        return sum/numOfProcesses;
    }
}
