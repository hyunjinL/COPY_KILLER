#include<stdio.h>
#include<string.h>
#include<stdlib.h>
int main()
{
	FILE *f;
	char *unique;
	int i,j;
	char *token;
	char *arr[210000];
	int count=0,num;
	f=fopen("/tmp/scheme.GO.data","r");
	unique=(char *)malloc(100*sizeof(char));

	printf("Reading scheme.GO.data");
	
	for(i=0;fgets(unique,sizeof(unique),f)!=NULL;i++)
	{
		token=strtok(unique,"	");
		arr[i]=token;	
		while(1)
		{
			if(token==NULL)
			break;
				while(arr[i]==NULL)
				{
					token=strtok(NULL,"	");
					arr[i+1]=token;	
				}	
			 
		}
		
	}
	printf("Total number of GOs: %d",count);
	scanf("%d",&num);
	printf("Enter GO to find:%d",num);
	for(i=0;i<sizeof(arr);i++)
	{	
		if(arr[i]==arr[j])
			count++;
		count++;
	}
	printf("%d GO:%d were found",count,num);
}
