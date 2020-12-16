
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

    //execute lahat ng code
    void getEDF(){
        getCounter();
        for(int i = 0; i<findLcm(array); i=time){
            getReadyQueue();
            sortDeadline();

            if(!(readyQueue[0]==999)){
                computeCompletionTime();

                count[readyQueue[0]]--;

                if(count[readyQueue[0]]>0){
                    deadline[readyQueue[0]]+=tempPeriod[readyQueue[0]];
                    period[readyQueue[0]]+=tempPeriod[readyQueue[0]];
                    startingTime[readyQueue[0]]=period[readyQueue[0]]-tempPeriod[readyQueue[0]];
                }

            }
            else{
                time++;
            }

        }
        displayTable();
    }

    void getReadyQueue(){
        for(int i = 0; i<process;i++){
            if(count[i]>0 && startingTime[i]<=time){
                readyQueue [i] =i;
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


     void sortDeadline(){
        for(int i = 0; i<process-1; i++){
            for(int j=0; j<process-i-1;j++){
                if(!(readyQueue[j+1]==999)){
                    if(deadline[readyQueue[j]]>deadline[readyQueue[j+1]]){
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
        turnAroundTime[readyQueue[0]] = completionTime[readyQueue[0]];
        waitingTime[readyQueue[0]] = turnAroundTime[readyQueue[0]] - burstTime[readyQueue[0]];
}

    double computeAverage(int array[]){
        sum = 0;
        for(int num: array){
            sum +=num;
        }
        return sum/process;
    }


    void getCounter(){
        for(int i =0; i<process; i++){
            count[i] = findLcm(array)/tempPeriod[i];
        }
    }

  
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
