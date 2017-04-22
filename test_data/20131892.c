#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>
typedef struct data {
	char num[10];
	int cnt;
} DATA;
int main(int argc, char *argv[])
{
	FILE *fpr;
	DATA *data;
	char *tmp = (char*)malloc(30);
	char input[300];		
	char *np= (char*)malloc(200);
	int overlap = 0, c= -1;		
	int j;
	int i ,k, suc;
	char fn[10]; 			
	strcpy(tmp,"/tmp/");
	strcat(tmp,argv[1]);
	fpr = fopen(tmp,"r");
	if ( fpr == NULL ) { printf("Not open file!\n"); exit(1);}
	printf("=>Reading %s\n",argv[1]);
	data = (DATA*)malloc(sizeof(DATA)); 
	while ( fgets(input,sizeof(input),fpr) != NULL ) {
		np = strtok(input,"	");
		suc = 0;
		while ( np != NULL) {
			for ( k = 0; np[k] ; k++ )
				if ( isalpha(np[k])) { suc =1; break; }
			if( suc ) break;
			
			overlap = 0;
			for ( k = 0, j = c ; k <= j ; k++, j-- )  {
				if ( atoi((data+k)->num) == atoi(np) )  {
					(data + k)->cnt++; overlap=1; break; }
				if ( atoi((data+j)->num) == atoi(np) )  {
					(data + j)->cnt++; overlap=1; break; }
			}
			if ( overlap == 0 ) {
				c = c+1; 
				data = (DATA*)realloc(data,sizeof(DATA)*(c+2));
				strcpy((data+c)->num,np);
				(data + c)->cnt = 1; 
			}
			np = strtok(NULL,"	"); 
		}
	}
	printf("=>Total number of GOs : %d\n",c+1);
	printf("=>Enter GO to find : ");
	scanf("%s",fn);	
	for ( i = 0; (data +i)->cnt ; i++) {
		if ( strcmp((data+i)->num,fn) == 0) {
			printf("  %d GO:%s were found.\n",(data+i)->cnt,fn);
			break;
		}	
	}
	if ( (data + i )->cnt == 0  )
			printf("Not found.\n");
	free(tmp);
	free(data);
	fclose(fpr);

	return 0;
}
