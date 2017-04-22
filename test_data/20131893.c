#include <stdio.h>
#include <stdlib.h>
#include <string.h>
struct counter{
	int uniq_num;
	int num_count;
};	

int main()
{	
	char *ptr_char;
	int *ptr_int;
	FILE *fp;
	char *moonza;
	int *sutza;
	char *ptr;
	char s[100];
	int i=0;
	sutza = (int*)malloc(sizeof(int));

	fp = fopen("scheme.GO.data","r");

	
	while(!feof(fp))
	{ i=0;
		fgets(s,sizeof(s),fp);

		ptr = strtok(s,"/t");	 
	
		sutza =(int *)realloc(sutza,(100+100)*sizeof(int));

		while(ptr != NULL){
					
			ptr = strtok(NULL,"\t\n");
			sutza =(int *)realloc(sutza,(100+100)*sizeof(int));
			/*if(    ){
				while(){
						]
			
				
			}*/

		}	
	}
	printf("%s",ptr);
	fclose(fp);	
	return 1;
}









	

	/*ptr_int = (int *) malloc(100 *size of(int));*/
