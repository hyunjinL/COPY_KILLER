#include<stdio.h>
#include<stdlib.h>
#include<string.h>

int main(int argc, char* argv[])
{
	int i = 0, j = 0, temp = 0;
	char *number[500000];
	char *blank[100];
        char buf[100];
        char *ch, find[30];

        FILE *fp = fopen("/tmp/scheme.GO.data","r");
        if( fp == NULL ){
                printf("Cannot open an input file\n");
                return 1;
        }

	printf("=>Reading scheme.GO.data\n");

	while( fgets(buf,sizeof(buf),fp) != NULL){
                ch = strtok(buf,"\t,\0");
		while(ch != NULL){
			if(*(ch+0) == '0'){
                        	temp++;
                       		blank[temp] = (char*)malloc(sizeof(ch));
                        	blank[temp] = ch;
			}
			else{
				if(blank[temp] != 0x00 && strlen(blank[temp]) == 7){
					number[i] = (char*)malloc(sizeof(ch));
					number[i] = blank[temp];
					i++;
					temp = 0;
				}
			}
               		ch = strtok(NULL,"\t,\0");
		}
        }
	printf("=>Total number of GOs : %d\n",i);
	printf("=>Enter GO to find : \n");

        fclose(fp);
        return 0;
}
