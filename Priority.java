
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

   //display content of table
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
    
    //execute Priority Algorithm
    void getRunningProcess(){
        int completed=0;
        //will execute until all processes are completed
        for(int i = 0; i < process;i=completed){
            //get all processes that arrives at time = n;
            getReadyQueue();
            //sort ready queue by priority
            sortPriority();
            //if ready queue is empty
            if(readyQueue[0]==999){
                //increase time
                time++;
            }
            //if ready queue is not empty
            else{
                //get the first index of the ready queue, compute the CT,TAT and WT
                computeCompletionTime();
                //once executed, completed integer will increase
                //once the integer completed is equal to number of process, the algorithm will stop
                completed++;
            }
        }
        displayTable();
    }

    //get ready queue
    void getReadyQueue(){
        readyQueue= new int [process];
        for(int i = 0; i<process;i++){
            //if process' AT is less than or equal to time, and it's BT is greater than 0, add it to Ready Queue
            if(arrivalTime[i]<=time && burstTime[i]>0){
                readyQueue[i] = i;
            }
            //else ready queue will be empty
            else{  
                readyQueue[i] = 999;
            }
        }
      
        //sort ready queue from lowest to highest using bubblesort algortihm
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
    //sort ready queue by priority using bubble sort  
     void sortPriority(){
        for(int i = 0; i<process-1; i++){
            for(int j=0; j<process-i-1;j++){
                    //if j+1 index of ready queue is 999, ignore
                    //else, do this
                    if(!(readyQueue[j+1]==999)){
                        //if priority of jth index of ready queue is greater than j+1 index, do swap
                        if(prio[readyQueue[j]]>prio[readyQueue[j+1]]){
                            int tmp = readyQueue[j];
                            readyQueue[j] = readyQueue[j+1];
                            readyQueue[j+1] = tmp;
                        }
                    }
                }
        }
    }
    
    //once BT of a process = 0, it will be completed
    //time when BT = 0 will be the CT
     void computeCompletionTime(){
        time+=burstTime[readyQueue[0]];
        completionTime[readyQueue[0]] = time;
        turnAroundTime[readyQueue[0]] = completionTime[readyQueue[0]]-arrivalTime[readyQueue[0]];
        waitingTime[readyQueue[0]] = turnAroundTime[readyQueue[0]] - burstTime[readyQueue[0]];
        burstTime[readyQueue[0]] = 0;
    }

    //compute avereage of TAT or WT
     double computeAverage(int array[]){
        sum = 0;
        for(int num: array){
            sum += num;
        }
        return sum/process;
    }
}


