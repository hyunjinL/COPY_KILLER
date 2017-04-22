#include<stdio.h>
#include<stdlib.h>
int main(void)
{	
	int  *ptr;
	FILE *fp = fopen("../../../tmp/scheme.GO.data", "r");	
	int i = 0;
	ptr = (int*)malloc(sizeof(int)*500);
	
	printf("Reading scheme.GO.data\n");

	while(!feof(fp))
	{
		
		fscanf(fp,"%d",&ptr[i]);
		i++;
	}

	printf("%d\n",i-1);
	
	free(ptr);
	fclose(fp);

	return 0;
}
