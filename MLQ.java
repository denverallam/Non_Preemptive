import java.util.Scanner;


public class MLQ {
	static int[] arrival_time1 = new int[30],arrival_time2 = new int[30], arrival_time3 = new int[30];
	static int[] priority2 = new int[30],process2 = new int[30];
	static int[] burst_time1 = new int[30];
	static int[] burst_time2 = new int[30];
	static int[] burst_time3 = new int[30];

	static int Total=0,t1=0,t2=0,t3=0;
	static int[] at = new int[30];
	static int[] bt = new int[30],pr = new int[30];
	static int n,i,j=0,k=0,l=0;

	static int total,x,counter=0;
	static int[] temp = new int[30];
	static float avg_waiting_time1,avg_turnaround_time1;

	static int p;
	static int[] waiting_time3 = new int[30],turnaround_time3 = new int[30];
	static float avg_waiting_time3,avg_turnaround_time3;

	static int position,q,temp1,sum=0;
	static int[] waiting_time2 = new int[30],turnaround_time2 = new int[30];
	static float avg_waiting_time2,avg_turnaround_time2;
	
	static Scanner sc = new Scanner(System.in);

	static void round_robin()
	{
		System.out.print("Time Quantum for Queue1 is 4\n");
		for(i=0;i<j;i++)
		{
			temp[i]=burst_time1[i];
		} 
		System.out.print("\nProcess ID\tBurst Time\t Turnaround Time\t Waiting Time\n");
		x=j;
	    for(i=0,total=0;x!=0;) 
	    { 
	    	if(temp[i]<=4&&temp[i]>0) 
	        {
	    		System.out.printf("\nProcess[%d] of Queue1 is running for %d units",i+1,temp[i]); 
	            total=total+temp[i]; 
	            temp[i]=0; 
	            counter=1; 
	        } 
	        else if(temp[i]>0) 
	        {
	        	System.out.printf("\nProcess[%d] of Queue1 is running for 4 units",i+1); 
	            temp[i]=temp[i]-4; 
	            total=total+4; 
	        } 
	        if(temp[i]==0&&counter==1) 
	        { 
	            x--; 
	            System.out.printf("\nProcess[%d]\t%d\t%d\t%d",i+1,burst_time1[i],total-arrival_time1[i],total-arrival_time1[i]-burst_time1[i]);
	            avg_waiting_time1=avg_waiting_time1+total-arrival_time1[i]-burst_time1[i]; 
	            avg_turnaround_time1=avg_turnaround_time1+total-arrival_time1[i]; 
	            counter = 0; 
	        } 
	        if(i==j-1) 
	        {
	            i=0; 
	        }
	        else if(arrival_time1[i+1]<=total) 
	        {
	            i++;
	        }
	        else 
	        {
	            i=0;
	        }
	    } 
	    avg_waiting_time1=avg_waiting_time1/j;
	    avg_turnaround_time1=avg_turnaround_time1/j;
	    System.out.printf("\nAverage Waiting Time:%f",avg_waiting_time1); 
	    System.out.printf("\nAverage Turnaround Time:%f\n",avg_turnaround_time1); 
	}

	static void priority()
	{
		for(i=0;i<k;i++)
	    {
	        position=i;
	        for(q=i+1;q<k;q++)
	        {
	            if(priority2[q]<priority2[position])
	            {
	                position=q;
	            }
	        }
	        temp1=priority2[i];
	        priority2[i]=priority2[position];
	        priority2[position]=temp1; 
	        
	        temp1=burst_time2[i];
	        burst_time2[i]=burst_time2[position];
	        burst_time2[position]=temp1;
	        
	        temp1=process2[i];
	        process2[i]=process2[position];
	        process2[position]=temp1;
	    }
	    waiting_time2[0]=0;
	    for(i=1;i<k;i++)
	    {
	        waiting_time2[i]=0;
	        for(q=0;q<i;q++)
	        {
	            waiting_time2[i]=waiting_time2[i]+burst_time2[j];
	        }
	        sum=sum+waiting_time2[i];
	    }
	    avg_waiting_time2=sum/k;
	    sum=0;
	    System.out.print("\nProcess ID\t\tBurst Time\t Waiting Time\t Turnaround Time\n");
	    for(i=0;i<k;i++)
	    {
	    	turnaround_time2[i]=burst_time2[i]+waiting_time2[i];
	        sum=sum+turnaround_time2[i];
	        System.out.printf("\nProcess[%d]\t\t%d\t\t %d\t\t %d\n",process2[i],burst_time2[i],waiting_time2[i],turnaround_time2[i]);
	    }
	    avg_turnaround_time2=sum/k;
	    System.out.printf("\nAverage Waiting Time:\t%f",avg_waiting_time2);
	    System.out.printf("\nAverage Turnaround Time:\t%f\n",avg_turnaround_time2);
	    
	    for(i=0;i<k;i++)
	    {
	    	while(burst_time2[i]!=0)
	    	{
	    		if(burst_time2[i]>10)
	    		{
	    			System.out.printf("\nProcess[%d] of Queue2 is running for 10 units",i+1);
					burst_time2[i]=burst_time2[i]-10;
				}
				else if(burst_time2[i]<=10)
				{
					System.out.printf("\nProcess[%d] of Queue2 is running for %d units",i+1,burst_time2[i]);
					burst_time2[i]=0;
				}
			}
		}

	}

	static void fcfs()
	{
		waiting_time3[0] = 0;   
	    for(i=1;i<l;i++)
	    {
	        waiting_time3[i] = 0;
	        for(p=0;p<l;p++)
	        {
	            waiting_time3[i]=waiting_time3[i]+burst_time3[p];
	        }
	    }
	    System.out.print("\nProcess\t\tBurst Time\tWaiting Time\tTurnaround Time\n");
	    for(i=0;i<l;i++)
	    {
	        turnaround_time3[i]=burst_time3[i]+waiting_time3[i];
	        avg_waiting_time3=avg_waiting_time3+waiting_time3[i];
	        avg_turnaround_time3=avg_turnaround_time3+turnaround_time3[i];
	        System.out.printf("\nProcess[%d]\t\t%d\t\t%d\t\t%d\n",i+1,burst_time3[i],waiting_time3[i],turnaround_time3[i]);
	    }
	    avg_waiting_time3=avg_waiting_time3/l;
	    avg_turnaround_time3=avg_turnaround_time3/l;
	    System.out.printf("\nAverage Waiting Time=%f",avg_waiting_time3);
	    System.out.printf("\nAverage Turnaround Time=%f",avg_turnaround_time3);
	    for(i=0;i<l;i++)
	    {
	    	while(burst_time3[i]!=0)
	    	{
	    		if(burst_time3[i]>10)
	    		{
	    			System.out.printf("\nProcess[%d] of Queue3 is running for 10 units",i+1);
					burst_time3[i]=burst_time3[i]-10;
				}
				else if(burst_time3[i]<=10)
				{
					System.out.printf("\nProcess[%d] of Queue2 is running for %d units",i+1,burst_time3[i]);
					burst_time3[i]=0;
				}
			}
		}
	}

	static void round_robin1()
	{
		System.out.printf("Time Quantum between the 3 queues is 10\n");
		for(i=1;i<Total;i=i+10)
		{
			if(t1>10)
			{
				System.out.printf("Queue1 is running for 10 units\n");
				t1=t1-10;
			}
			else if(t1<=10&&t1!=0)
			{
				System.out.printf("Queue1 is running for %d units\n",t1);
				t1=0;
			}
			if(t2>10)
			{
				System.out.printf("Queue2 is running for 10 units\n");
				t2=t2-10;
			}
			else if(t2<=10&&t2!=0)
			{
				System.out.printf("Queue2 is running for %d units\n",t2);
				t2=0;
			}
			if(t3>10)
			{
				System.out.printf("Queue3 is running for 10 units\n");
				t3=t3-10;
			}
			else if(t3<=10&&t3!=0)
			{
				System.out.printf("Queue3 is running for %d units\n",t3);
				t3=0;
			}
		}
	}

	public static void main(String[] args)
	{
		System.out.print("Enter the no. of process you want to enter: ");
		n = sc.nextInt();
		for(i=0;i<n;i++)
		{
			System.out.printf("Enter details of process[%d]\n",i+1);
			System.out.printf("Arrival Time:");
			at[i] = sc.nextInt();
			System.out.printf("Burst Time:");
			bt[i] = sc.nextInt();
			System.out.printf("Priority(1 to 15):");
			pr[i] = sc.nextInt();
			Total=Total+bt[i];
			System.out.println();
		}
		for(i=0;i<n;i++)
		{
			if(pr[i]>=1&&pr[i]<=5)
			{
				System.out.printf("\n\nProcess[%d] belongs to Queue 1\n",i+1);
				arrival_time1[j]=at[i];
				burst_time1[j]=bt[i];
				j++;
				t1=t1+bt[i];
			}
			
			else if(pr[i]>=6&&pr[i]<=10)
			{
				System.out.printf("Process[%d] belongs to Queue 2\n",i+1);
				arrival_time2[k]=at[i];
				burst_time2[k]=bt[i];
				priority2[k]=pr[i];
				process2[k]=k+1;
				k++;
				t2=t2+bt[i];
			}
			else if(pr[i]>=11&&pr[i]<=15)
			{
				System.out.printf("Process[%d] belongs to Queue 3\n",i+1);
				arrival_time3[l]=at[i];
				burst_time3[l]=bt[i];
				l++;
				t3=t3+bt[i];
			}
		}
		round_robin1();
		round_robin();
		fcfs();
		priority();
	}
}
