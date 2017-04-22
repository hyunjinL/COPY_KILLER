nclude <stdio.h>
#include <string.h>
#include <stdlib.h>

int main()
{
        int a;
        char *gom1[100] 
        int *gom2[100]
        struct gom {
                int *lyn1;
                int *lyn2;
        }
        struct gom data[100];
        FILE *fp;


        fp=fopen("../../../tmp/scheme.GO.data","r");

        for(a=0; a<1000; a++) {
                gom1[a] = (char*)malloc(sizeof(char));
                gom2[a] = (int*)malloc(sizeof(int));
                data[a].lyn1 = (int*)malloc(sizeof(int);
                data[a],lyn2 = 0;
        }

        a = 0 ;

        printf("%d\n", *gom2[a]);
}
