//PROGRAMMER: TOLENTINO, ARAH NICOLLE

class FCFS{

	int [] processId;
	int [] arrivalTime;
	int [] burstTime;
	int [] completionTime ;
	int [] turnAroundTime;
	int [] waitingTime;
	int [] readyQueue;
	int [] temp;
	int time;
	int process;
	double sum;

   public FCFS(int pid[], int at[],int bt[],int temp[],int ct[], int tat[],int wt[],int rq[],int p, int t,double s){
		   this.processId = pid;
		   this.arrivalTime = at;
		   this.burstTime = bt;
		   this.temp = temp;
		   this.completionTime = ct;
		   this.turnAroundTime = tat;
		   this.waitingTime = wt;
		   this.readyQueue = rq;
		   this.process = p;
		   this.time = t;
		   this.sum = s;
   }    

   	//sort process by arrival time using bubblesort algorithm
	 void sortArrivalTime()
	    {
	        for (int i = 0; i < process; i++) 
	        {
	            for (int j = 0; j < process - i - 1; j++) 
	            {
	                if (arrivalTime[j] > arrivalTime[j + 1]) 
	                {
	                    int temp = arrivalTime[j];
	                    arrivalTime[j] = arrivalTime[j + 1];
	                    arrivalTime[j + 1] = temp;
	                    
	                    temp = burstTime[j];
	                    burstTime[j] = burstTime[j + 1];
						burstTime[j + 1] = temp;
						
						temp = processId[j];
						processId[j] = processId[j + 1];
	                    processId[j + 1] = temp;
						
						
	                }
	            }
	        }
		}

		//sort process by priority id using bubblesort algorithm
		void sortProcessId()
	    {
	        for (int i = 0; i < process; i++) 
	        {
	            for (int j = 0; j < process - i - 1; j++) 
	            {
					//if j index is greater than (j+1) index, do swap
	                if (processId[j] > processId[j + 1]) 
	                {
	                    int temp = arrivalTime[j];
	                    arrivalTime[j] = arrivalTime[j + 1];
	                    arrivalTime[j + 1] = temp;
	                    
	                    temp = burstTime[j];
	                    burstTime[j] = burstTime[j + 1];
						burstTime[j + 1] = temp;
						
						temp = processId[j];
						processId[j] = processId[j + 1];
						processId[j + 1] = temp;

						temp = completionTime[j];
						completionTime[j] = completionTime[j + 1];
						completionTime[j + 1] = temp;
						
						temp = turnAroundTime[j];
						turnAroundTime[j] = turnAroundTime[j + 1];
						turnAroundTime[j + 1] = temp;
						
						temp = waitingTime[j];
						waitingTime[j] = waitingTime[j + 1];
	                    waitingTime[j + 1] = temp;
	                    
	                }
	            }
	        }
	    }

		void getRunningProcess()
	    {
			//sort process by AT
			sortArrivalTime();
			
			//compute for CT, TAT, & WT 
	        completionTime[0] = arrivalTime[0] + burstTime[0];
	        turnAroundTime[0] = completionTime[0] - arrivalTime[0];
			waitingTime[0] = turnAroundTime[0] - burstTime[0];
			int idle;
	        for (int i = 0; i < process-1; i++) 
	        {
				if(completionTime[i]>=arrivalTime[i+1]){
					completionTime[i+1] = burstTime[i+1] + completionTime[i];
				}
				else{
					//if arrival time of next process is less than CT of previous one, add idle time
					idle = arrivalTime[i+1] - completionTime[i];
					completionTime[i+1] += idle + completionTime[i] + burstTime[i+1];
				}
					turnAroundTime[i+1] = completionTime[i+1] - arrivalTime[i+1];
					waitingTime[i+1] = turnAroundTime[i+1] - burstTime[i+1];
			}
			//sort the processes by priority id
			sortProcessId();

			//display computed CT,TAT and WT
			displayTable();
		}

		void displayTable(){
			System.out.println();
			System.out.println("FCFS");
			System.out.println("PID\tAT\tBT\tCT\tTAT\tWT");
			System.out.println("--------------------------------------------------");
			for(int i = 0; i<process;i++){
			System.out.println("P" + processId[i] + "\t" + arrivalTime[i] + "\t" + temp[i] +"\t" + completionTime[i]
			+ "\t" + turnAroundTime[i] + "\t" + waitingTime[i]);
			System.out.println("--------------------------------------------------");
			}	
			System.out.println("Average Turn-around Time:\t" + String.format("%.2f", computeAverage(turnAroundTime)) + " units");
			System.out.println("Average Waiting Time:\t\t" +String.format("%.2f", computeAverage(waitingTime))+ " units");
			System.out.println();
		}

		double computeAverage(int array[]){
			sum = 0;
			for(int num: array){
				sum += num;
			}
			return sum/process;
		}
	}