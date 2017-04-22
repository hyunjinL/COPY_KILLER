#include <stdio.h>
#include <string.h>
#include <stdlib.h>
int main()
{
	FILE *f;
	int i,numgos=0,have=0;


	char *check;
	char str[5000],usrwant[8];

	char *rdata =(char *)malloc(50000000);
	int *rcount=(int *)malloc(sizeof(int)*3000000);

	f=fopen("/tmp/scheme.GO.data","r");
	while(fgets(str,sizeof(str),f)){
		check=strtok(str,"\t");
		while(1){
			if(strlen(check)!=7||check[0]!='0'){
					break;
			}
			for(i=0;i<numgos;i++){
				if(strcmp(check,rdata+(i*8))==0){
					have=1;
					break;
				}
			}
			if(have==0){
				strcpy(rdata+(numgos*8),check);
				*(rcount+numgos)=1;
				numgos++;
			}
			if(have==1){
				*(rcount+i)+=1;
				have=0;
			}
			check=strtok(NULL,"\t");
		}
	}
	printf("=>Reading scheme.Go.data\n");
	printf("=>Total number of GOs : %d\n",numgos);
	printf("=>Enter GO to find : ");
	scanf("%s",usrwant);
	for(i=0;i<=numgos;i++){
		if(strcmp(rdata+(i*8),usrwant)==0){
			printf("%d GO:%s were found.\n",*(rcount+i),usrwant);
			break;
		}
	}
	free(rcount);		
	fclose(f);
	return 0;
}
