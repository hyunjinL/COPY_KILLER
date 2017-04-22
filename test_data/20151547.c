#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct a{
	struct a *next;
	char numb[10];
	int count; 
}T;
T *t=NULL;
int t_size = 0, flag = 0;

void chk_exist_char(char *token){
  T *chk = t;
  while(chk != NULL){
    if(strcmp(chk->numb,token) == 0){
			chk->count++;
			flag = 1;
		  break;
		}
  	chk = chk->next;
  	}
}

void make_linkedList(char *data,int type){
  if(type == 1){
    T *new_node = (T*)malloc(sizeof(T));
    strcpy(new_node->numb,data);
    new_node->count = 1;
    new_node->next = t;
    t = new_node;
	}else{
    T *new_node = (T*)malloc(sizeof(T));
    strcpy(new_node->numb,data);
    new_node->count = 1;
    new_node->next = NULL;
    t = new_node;
  }
   t_size++;
}
int is_only_number(char *token){
  int count = 0;
  for(int i=0; token[i]!='\0';i++)
	{
		if(token[i]>=48 && token[i]<=57){
			count ++;
		}
	}
  if(count == strlen(token))
    return 1;
  return 0;
}
void print(){
  T *print = t;
	printf("Num\tcount\n");
	while(print != NULL){
	  printf("(%s)\t%d\n",print->numb,print->count);
	  print = print->next;
	}
	printf("\nTotal count :  %d\n",t_size);
}
void my_input_number_count(){
  int num;
  printf("write count check number : ");
  scanf("%d",&num);
  T *find = t;
  while(find != NULL){
    if(atoi(find->numb) == num){
      printf("Founded.. number : %s, count : %d\n",find->numb,find->count);
      break;
    }
    find = find->next;
  }
}
int main(int argc, char *argv[])
{
  char temp[518];
	char *token = NULL;
	int count = 0;
	FILE *f=fopen(argv[1],"r");
	if(f==NULL){
		fprintf(stderr,"File open error.\n");
		return -1;
	}

	while(fgets(temp,518,f) !=NULL)
	{
		token = strtok(temp,"\t");
		while(token !=NULL){
			if(is_only_number(token))
			{
				chk_exist_char(token);
			  if(flag != 1){
			    if(t_size >= 1){
			      make_linkedList(token,1);
			    }else if(t_size == 0){
			      make_linkedList(token,0);
			    }
			  }
			}
		  count = 0;
		  flag = 0;
		  token=strtok(NULL,"\t");
		}
	}
	print();
	my_input_number_count();
	fclose(f);
	return 0;
}

