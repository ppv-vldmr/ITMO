#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct User {
    char first_name[21];
    char last_name[21];
    char patronymic[21];
    int phone_number;
} User;

void printUsers(FILE *output, User *user) {
    fprintf(output, "%s %s %s %i\n", user->last_name, user->first_name, user->patronymic, user->phone_number);
}

void swap(User *a, User *b) {
    User temp = *a;
    *a = *b;
    *b = temp;
}

int comparator(User *a, User *b) {
    if (strcmp(a->last_name, b->last_name) != 0)
        return strcmp(a->last_name, b->last_name);
    if (strcmp(a->first_name, b->first_name) != 0)
        return strcmp(a->first_name, b->first_name);
    if (strcmp(a->patronymic, b->patronymic) != 0)
        return strcmp(a->patronymic, b->patronymic);
    return a->phone_number - b->phone_number;
}

void qSort(User *a, int l, int r) {
    begin:
    if (l < r) {
        int i = l, j = r;
        User q = a[(i + j) / 2];
        do {
            while (comparator(&a[i], &q) < 0)
                i++;
            while (comparator(&a[j], &q) > 0)
                j--;
            if (i <= j) {
                swap(&a[i], &a[j]);
                i++;
                j--;
            }
        } while (i <= j);
        qSort(a, l, j);
        l = i;
        goto begin;
    }
}

int main(int argc, char **argv) {
    if (argc != 3) {
        printf("Wrong count of program arguments.\nExpected: 3, found: %d\n", argc);
        return 1;
    }
    FILE *input = fopen(argv[1], "r");
    if (input == NULL) {
        printf("An error has occured with input file %s\n", argv[1]);
        return 1;
    }
    User *users = NULL;
    users = (User*) malloc(1 * sizeof(User));
    int size = 1;
    char buffer[20];
    int curr = -1;

    while (!feof(input)) {
        curr++;
        if (curr == size) {
            size *= 2;
            users = (User*) realloc(users,size * sizeof(User));
        }
        fscanf(input, "%s", buffer);
        strcpy(users[curr].last_name, buffer);
        fscanf(input, "%s", buffer);
        strcpy(users[curr].first_name, buffer);
        fscanf(input, "%s", buffer);
        strcpy(users[curr].patronymic, buffer);
        fscanf(input, "%s", buffer);
        users[curr].phone_number = atoi(buffer);
    }
    fclose(input);

    qSort(users, 0, curr);

    FILE *output = fopen(argv[2], "w");
    if (output == NULL) {
        printf("An error has occured with output file %s\n", argv[2]);
        return 1;
    }
    for (int i = 0; i <= curr; i++) {
        printUsers(output, &users[i]);
    }
    fclose(output);

    free(users);
    return 0;
}