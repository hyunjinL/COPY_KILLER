#include<stdio.h>
#include<string.h>
#include<stdlib.h>

typedef struct number{
        char num[2024];
        int count;
}num;

int main(int argc, char* argv[])
{
        FILE *file;
        char str[2024];
        char *ptr;
        num *string;
        int count = 0;
        int i;
        int unique=0; 

        file = fopen(argv[1],"r");
        
        while(fgets(str,sizeof(str),file)){
                ptr = strtok(str, "\t");
                if(count == 0){
                        string = (num *)malloc(sizeof(num) * 1);
                        strcpy(string->num, ptr);
                        string->count = 1;
                        count++;
                }
		else{
			for(i=0;i<count;i++){
                                if(strcmp((string+i)->num, ptr) == 0){
					(string+i)->count++;
					unique = 1;
                                        break;
                                }
                        }
                       if(unique == 0){
                               string = (num *)realloc(string, sizeof(num) * (count+1));
                               strcpy((string+count)->num, ptr);
                               (string+count)->count = 1;
                               count++;
                        }
			unique = 0; 
                }
                while(1){
			ptr = strtok(NULL,"\t\n ");
                        if( (ptr[0] <'0' || ptr[0] > '9')){
                                        break;
                        }
                        for(i=0;i<count;i++){
                                if(strcmp((string+i)->num, ptr) == 0){
                                        (string+i)->count++;
					unique = 1;
                                        break;
                                }
                        }
                        if(unique == 0){
                               string = (num *)realloc(string,sizeof(num) * (count+1));
                               strcpy((string+count)->num, ptr);
                               (string+count)->count = 1;
                               count++;
                        }
			unique = 0;
                }
        }
	printf("num : ");
	scanf("%s",str);

	for(i=0;i<count;i++){
		if(strcmp(str , string[i].num) == 0){
			printf("%d\n",string[i].count);
		}
	}    

        return 0;
}
