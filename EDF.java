class EDF {
    int [] processId;
    int [] burstTime;
    int [] turnAroundTime;
    int [] waitingTime;
    int [] completionTime;
    int [] deadline;
    int [] readyQueue;
    int time = 0;
    int process = 0;
    double sum = 0;
    int output = 0;
    int totalCT = 0;

   public EDF(int pid[],int bt[],int[] d, int ct[],int tat[],int wt[],int rq[],int p,int o, double s, int tct){
           this.processId = pid;
           this.burstTime = bt;
           this.deadline = d;
           this.completionTime = ct;
           this.turnAroundTime = tat;
           this.waitingTime = wt;
           this.readyQueue = rq;
           this.process = p;
           this.output = o;
           this.sum = s;
           this.totalCT = tct;


   }   

        
     void sortDeadline(){
        int temp[] = deadline.clone();
        for(int i = 0; i<process-1; i++){
            for(int j=0; j<process-i-1;j++){
                        if(temp[j]>temp[j+1]){
                            int tmp = temp[j];
                            temp[j] = temp[j+1];
                            temp[j+1] = tmp;

                            tmp = readyQueue[j];
                            readyQueue[j] = readyQueue[j+1];
                            readyQueue[j+1] = tmp;
                        }
                    }
                }
            }

    void computeTable(){
            completionTime[readyQueue[0]] = burstTime[readyQueue[0]] + totalCT;
            turnAroundTime[readyQueue[0]] = completionTime[readyQueue[0]];
            waitingTime[readyQueue[0]] = turnAroundTime[readyQueue[0]] - burstTime[readyQueue[0]];
                    for (int i = 1; i < process; i++){
                        completionTime[readyQueue[i]] = burstTime[readyQueue[i]] + completionTime[readyQueue[i-1]];
                        turnAroundTime[readyQueue[i]] = completionTime[readyQueue[i]];
                        waitingTime[readyQueue[i]] =   turnAroundTime[readyQueue[i]] - burstTime[readyQueue[i]];
                    }
            }

    void getEarliestDeadline(){
        sortDeadline();
        for(int i = 0; i<output;i++){
            computeTable();
            totalCT = completionTime[process-1];
            System.out.println("\t\t  OUTPUT "+(i+1));
            System.out.println();
            displayTable();
        }
    }

     double computeAverage(int array[]){
        sum = 0;
        for(int num: array){
            sum +=num;
        }
        return sum/process;
    }

    void displayTable(){
        System.out.println();
        System.out.println("DEADLINE");
        System.out.println("PID\tBT\tD\tCT\tTAT\tWT");
        System.out.println("--------------------------------------------------");
        for(int i = 0; i<process;i++){
            System.out.println("P" + processId[i] + "\t" + burstTime[i] + "\t" + deadline[i] + "\t" + completionTime[i] + "\t" + turnAroundTime[i] + "\t" + waitingTime[i]);
            System.out.println("--------------------------------------------------");
        }	
        System.out.println("Average Turn-around Time:\t" + String.format("%.2f", computeAverage(turnAroundTime)) + " units");
        System.out.println("Average Waiting Time:\t\t" +String.format("%.2f", computeAverage(waitingTime))+ " units");
        System.out.println();
        System.out.println();
    }
}