#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main()
{
        int i, j, k;
        FILE *info;
        char *ifda[1000];
        int *numlist[1000];
        int inta[100];
        int key,keyi;
        struct tag{
                int *num1;
                int *check;
        }datas[1000];


        info = fopen("../../../tmp/scheme.GO.data", "r");

        printf("파일 분석은 0 검색하려면 숫자를 입력");
        scanf("%d", &key);
        if(key==0)
        {
                keyi=1;
        }

        for (i=0;i<1000;i++)
        {
                ifda[i] = (char*)malloc(sizeof(char));
                numlist[i] = (int*)malloc(sizeof(int));
                datas[i].num1 = (int*)malloc(sizeof(int));
                datas[i].check = 0;
        }

        i=0;

        while(!feof(info))
        {
                if(i==99)
                {
                        i=0;
                }

                fscanf(info, "%s", ifda[i]);
                inta[i]=atoi(ifda[i]);
                if(inta[i]>0 && inta[i]<1000000)
                {
                        printf("%s\n", ifda[i]);
                        datas[i].num1=&inta[i];

                        for(j=0;j<1000;j++)
                        {
                                if(numlist[j]==datas[i].num1)
                                {
                                        datas[j].check++;
                                        break;
                                        k++;
                                }
                        }


                        if(k==1000)
                        {
                                for(j=0;j<1000;j++)
                                {
                                        if(datas[j].check==0)
                                        {
                                                numlist[j]=datas[i].num1;
                                                break;
                                        }

                                printf("%d\n", *numlist[i]);
                                }
                        i++;
                }

        if(keyi==1)
        {
                for (i=0;i<1000;i++)
                {
                        printf("%d = %d",*numlist[i],*datas[i].check);
                }
        }

        if(keyi==0)
        {
                for (i=0;i<1000;i++)
                {
                        if(*numlist[i]==key)
                                printf("%d",key);
                }
        }
}


}
}
