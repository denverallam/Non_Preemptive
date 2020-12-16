
class EDF{
    int [] processId;
    int [] burstTime;
    int [] turnAroundTime;
    int [] waitingTime;
    int [] completionTime;
    int [] deadline;
    int [] temp;
    int [] startingTime;
    int [] period;
    int [] tempPeriod;
    int [] count;
    int [] array;
    int [] readyQueue;
    int time = 0;
    int process = 0;
    double sum = 0;
    int lcm = 1;


    public EDF(int pId[], int st[], int bt[], int d[], int period[], int ct[], int tat[],int wt[], int temp[], int tp[], int c[],int a[], int rq[], int t, int p, double s, int l){
        this.processId = pId;
        this.burstTime = bt;
        this.turnAroundTime = tat;
        this.completionTime = ct;
        this.waitingTime = wt;
        this.deadline = d;
        this.temp = temp;
        this.startingTime = st;
        this.period = period;
        this.tempPeriod = tp;
        this.count = c;
        this.array = a;
        this.readyQueue = rq;
        this.time = 0;
        this.process = p;
        this.sum = s;
        this.lcm = l;
    }

    
    //execute earlist deadline algorithm
    void getRunningProcess(){
        //get how many times each process will execute
        getCounter();
        //execute until time is equal to LCM of Period
        for(int i = 0; i<findLcm(array); i=time){
            //get all processes with counter greater than 0 and haven't executed yet
            getReadyQueue();
            //sort ready queue by deadline using bubble sort
            sortDeadline();
            //if ready queue is not empty
            if(!(readyQueue[0]==999)){
                //compute the completion time of the first index of the ready queue
                computeCompletionTime();
                //decrease the counter of the first index of the running process
                count[readyQueue[0]]--;
                //if the counter of ready queue is greater than 0
                //update deadline, period, and starting time
                if(count[readyQueue[0]]>0){
                    deadline[readyQueue[0]]+=tempPeriod[readyQueue[0]];
                    period[readyQueue[0]]+=tempPeriod[readyQueue[0]];
                    startingTime[readyQueue[0]]=period[readyQueue[0]]-tempPeriod[readyQueue[0]];
                }
            }
            else{
                //if there are no processes in ready queue, increase time
                time++;
            }

        }
        //once completed, display table
        displayTable();
    }

    //get ready queue
    void getReadyQueue(){
        for(int i = 0; i<process;i++){
            //get all processes that with counter greater than 0 and has not executed yet
            if(count[i]>0 && startingTime[i]<=time){
                readyQueue [i] =i;
            }
            else{
            //else, ready queue will be empty
                readyQueue[i] = 999;
            }
        }

        //sort ready queue from highest to lowest using bubble sort algorithm
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

      //sort ready queue by deadline using bubble sort
     void sortDeadline(){
        for(int i = 0; i<process-1; i++){
            for(int j=0; j<process-i-1;j++){
                //if j+1 index of ready queue is 999, ignore
                //else, do this
                if(!(readyQueue[j+1]==999)){
                    //if deadline of jth index of ready queue is greater than j+1 index, do swap
                    if(deadline[readyQueue[j]]>deadline[readyQueue[j+1]]){
                        int tmp = readyQueue[j];
                        readyQueue[j] = readyQueue[j+1];
                        readyQueue[j+1] = tmp;
                    }
                }
                }
        }
    }

    //once BT of a process = 0, the process will be completed
    //time when BT becomes 0 will be the CT of the process
    void computeCompletionTime(){
        time+=burstTime[readyQueue[0]];
        completionTime[readyQueue[0]] = time;
        turnAroundTime[readyQueue[0]] = completionTime[readyQueue[0]];
        waitingTime[readyQueue[0]] = turnAroundTime[readyQueue[0]] - burstTime[readyQueue[0]];
}
    //compute avereage of TAT or WT
    double computeAverage(int array[]){
        sum = 0;
        for(int num: array){
            sum +=num;
        }
        return sum/process;
    }


    //get how many times each process will execute
    //least common multiple divided by the period of the process = counter
    void getCounter(){
        for(int i =0; i<process; i++){
            count[i] = findLcm(array)/tempPeriod[i];
        }
    }

    //find the LCM of the Period
    int findLcm(int[] array) 
    { 
        int divisor = 2; 
        
        while (true) { 
            int counter = 0; 
            boolean divisible = false; 
            
            for (int i = 0; i < array.length; i++) { 
                if (array[i] == 0) { 
                    return 0; 
                } 
                if (array[i] == 1) { 
                    counter++; 
                } 
                if (array[i] % divisor == 0) { 
                    divisible = true; 
                    array[i] = array[i] / divisor; 
                } 
            } 
            if (divisible) { 
                lcm = lcm * divisor; 
            } 
            else { 
                divisor++; 
            } 

            if (counter == array.length) { 
                return lcm; 
            } 
        } 
    } 

    //display content of table
    void displayTable(){
        System.out.println();
        System.out.println("DEADLINE");
        System.out.println("PID\tBT\tD\tP\tCT\tTAT\tWT");
        System.out.println("--------------------------------------------------");
        for(int i = 0; i<process;i++){
            System.out.println("P" + (processId[i]+1)+ "\t" + burstTime[i] + "\t" + temp[i] + "\t" + tempPeriod[i] + "\t" + completionTime[i] + "\t" + turnAroundTime[i] + "\t" + waitingTime[i]);
            System.out.println("--------------------------------------------------");
        }	
        System.out.println("Average Turn-around Time:\t" + String.format("%.2f", computeAverage(turnAroundTime)) + " units");
        System.out.println("Average Waiting Time:\t\t" +String.format("%.2f", computeAverage(waitingTime))+ " units");
        System.out.println();
        System.out.println();
    }
}
