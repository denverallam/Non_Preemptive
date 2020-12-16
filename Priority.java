
class Priority{
    int [] processId;
    int [] arrivalTime;
    int [] burstTime;
    int [] completionTime ;
    int [] turnAroundTime;
    int [] waitingTime;
    int [] prio;
    int [] readyQueue;
    int [] temp;
    int time;
    int process;
    double sum;

   public Priority(int pid[], int at[],int bt[],int temp[],int[] pr, int ct[], int tat[],int wt[],int rq[],int p, int t,double s){
           this.processId = pid;
           this.arrivalTime = at;
           this.burstTime = bt;
           this.temp = temp;
           this.prio = pr;
           this.completionTime = ct;
           this.turnAroundTime = tat;
           this.waitingTime = wt;
           this.readyQueue = rq;
           this.process = p;
           this.time = t;
           this.sum = s;
   }   


    void displayTable(){
        System.out.println();
        System.out.println("PRIORITY");

        System.out.println("PID\tAT\tBT\tP\tCT\tTAT\tWT");
        System.out.println("--------------------------------------------------");
        for(int i = 0; i<process;i++){
        System.out.println("P" + processId[i] + "\t" + arrivalTime[i] + "\t" + temp[i] + "\t" + prio[i] +"\t" + completionTime[i]
        + "\t" + turnAroundTime[i] + "\t" + waitingTime[i]);
        System.out.println("--------------------------------------------------");
        }	

        System.out.println("Average Turn-around Time:\t" + String.format("%.2f", computeAverage(turnAroundTime)) + " units");
        System.out.println("Average Waiting Time:\t\t" +String.format("%.2f", computeAverage(waitingTime))+ " units");
        System.out.println();
    }

    
     void getPrio(){
        int completed=0;;
        for(int i = 0; i < process;i=completed){
            getReadyQueue();
            sortPriority();
            if(readyQueue[0]==999){
                time++;
            }
            else{
                computeCompletionTime();
                completed++;
            }
        }
        displayTable();
    }


    void getReadyQueue(){
        readyQueue= new int [process];
        for(int i = 0; i<process;i++){
            if(arrivalTime[i]<=time && burstTime[i]>0){
                readyQueue[i] = i;
            }
            else{  
                readyQueue[i] = 999;
            }
        }
      
        for(int i = 0; i<process; i++){
            for(int j=0; j<process-i-1;j++){
                if(readyQueue[j]>readyQueue[j+1]){
                    int tmp = readyQueue[j];
                    readyQueue[j] = readyQueue[j+1];
                    readyQueue[j+1] = tmp;
                }
            }
        }
    }
        
     void sortPriority(){
        for(int i = 0; i<process-1; i++){
            for(int j=0; j<process-i-1;j++){
                    if(!(readyQueue[j+1]==999)){
                        if(prio[readyQueue[j]]>prio[readyQueue[j+1]]){
                            int tmp = readyQueue[j];
                            readyQueue[j] = readyQueue[j+1];
                            readyQueue[j+1] = tmp;
                        }
                    }
                }
        }
    }
    

     void computeCompletionTime(){
        time+=burstTime[readyQueue[0]];
        completionTime[readyQueue[0]] = time;
        turnAroundTime[readyQueue[0]] = completionTime[readyQueue[0]]-arrivalTime[readyQueue[0]];
        waitingTime[readyQueue[0]] = turnAroundTime[readyQueue[0]] - burstTime[readyQueue[0]];
        burstTime[readyQueue[0]] = 0;
    }


     double computeAverage(int array[]){
        sum = 0;
        for(int num: array){
            sum += num;
        }
        return sum/process;
    }
}


