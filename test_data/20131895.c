#include<stdio.h>
#include<stdlib.h>
#include<string.h>

void main()
{	
	char *result = (char*)malloc(sizeof(char)*15000);
	char *str = (char*)malloc(sizeof(char)*15000);
	int n, i, count = 0;	
		
	FILE *file;
	file = fopen("scheme.GO.data","r");
	str = file;
	
	result = strtok(file," ");
	
	for(i = 0; ;i++){
		if(str[i] == 0){ //숫자를 하나씩 비교해서 중복되는  Unique데이터가 나오면 카운트를 올리려고 했는데 실패했습니다.
			result[i] = str[i];
			else if(str[i] == 1){
				result[i] = str[i];
				else if(str[i] == 2){
					result[i] = str[i];
					else if(str[i] == 3){
						result[i] = str[i];
						else if(str[i] == 4){
							result[i] = str[i];
							else if(str[i] == 5){
								result[i] = str[i];
								else if(str[i] == 6){
									result[i] = str[i];
									else if(str[i] == 7){
										result[i] = str[i];
										else if(str[i] == 8){
											result[i] = str[i];
											else if(str[i] == 9){
												result[i] = str[i];	
											}
										}									
									}	
								}
							}
						}
					}
				}
			}
		}	
		count = count+1;
	}	
	while(result != NULL){
		printf("%c",result);
	}
	printf("count = %d",count);
	
	fclose(file);
}
