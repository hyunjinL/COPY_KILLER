#include<stdio.h>
#include<string.h>
#include<stdlib.h>

typedef struct num
{
	char c[10000];
	int count;
	struct num *link;
}num;

int all = 0;

int main()
{
	int i=0;
	char a[100],n[10000];
	char *u;
	FILE *fp;
	num *first=NULL;
	num *p,*q,*r;

	fp=fopen("/tmp/scheme.GO.data","r");

	while(fgets(a,sizeof(a),fp)!=NULL){
		for(u=strtok(a," \t\n");u!=NULL;){
			if(isdigit(*u)&&isdigit(*(u+1))&&isdigit(*(u+2))){
				if(first==NULL){
					p=(num*)malloc(sizeof(num));
					strcpy(p->c,u); 
					p->count=1;
					first=p;
					p->link=NULL;
					q=p;
					all++;
				}
				else{
					p=first;
					while(p!=NULL){
						if(strcmp(p->c,u)==0){
							p->count++;
							all++;
							break;
						}
						else if(p->link==NULL){
							p=(num*)malloc(sizeof(num));
							strcpy(p->c,u);
							p->count = 1;
							q->link=p;
							p->link=NULL;
							q=p;
							all++;
							break;
						}
						p=p->link;
					}
				}
			}
			else{
				break;
			}
			u=strtok(NULL," \t\n");
		}
	}
	
	p=first;
	printf("=>Reading scheme.GO.data\n");
	printf("=>Total number of GOs : %d\n", all);
	printf("=>Enter GO to fine : ");
	scanf("%s",n);
	while(p!=NULL){
		if(strcmp(p->c,n)==0){
			printf("  %d GO:%s were found.\n",p->count,n);
			break;
		}
		else if(p->link==NULL){
			printf("error...\n");
		}
		p=p->link;
	}
	
	while(p!=NULL){
		r=p->link;
		free(p);
		p=r;
	}
	fclose(fp);
	return 0;
}
