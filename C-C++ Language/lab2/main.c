#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct User {
    char *first_name;
    char *last_name;
    char *patronymic;
    char *phone_number;
} User;

void printUsers(FILE *output, User *user) {
    fprintf(output, "%s %s %s %s\n", user->last_name, user->first_name, user->patronymic, user->phone_number);
}

void freeUsersArray(User** users, unsigned size) {
    unsigned i;
    for (i = 0; i < size; i++) {
        free((*users)[i].first_name);
        free((*users)[i].last_name);
        free((*users)[i].patronymic);
        free((*users)[i].phone_number);
    }
    free(*users);
}

void swap(User *a, User *b) {
    User temp = *a;
    *a = *b;
    *b = temp;
}

int comparator(User *a, User *b) {
    int first_name_compare = strcmp(a->first_name, b->first_name);
    int last_name_compare = strcmp(a->last_name, b->last_name);
    int patronymic_compare = strcmp(a->patronymic, b->patronymic);
    int phone_number_compare = strcmp(a->phone_number, b->phone_number);
    if (last_name_compare != 0)
        return last_name_compare;
    else
        if (first_name_compare != 0)
            return first_name_compare;
        else
            if (patronymic_compare != 0)
                return patronymic_compare;
            else
                return phone_number_compare;
}

void qSort(User *a, int l, int r) {
    if (r - l <= 1)
        return;
    int i = l, j = r;
    User q = a[(i + j) / 2];
    do {
        while (comparator(&a[i], &q) < 0)
            i++;
        while (comparator(&a[j], &q) > 0)
            j--;
        if (i < j) {
            swap(&a[i], &a[j]);
            i++;
            j--;
        }
    } while (i < j);
    if (l < j) qSort(a, l, j);
    if (i < r) qSort(a, i, r);
}

int main(int argc, char **argv) {
    if (argc != 4) {
        printf("Wrong count of program arguments.\nExpected: 3, found: %d\n", argc - 1);
        printf("Required 3 arguments: \"lab2\", input file, output file.");
        exit(1);
    }
    FILE *input = fopen(argv[2], "r");
    if (input == NULL) {
        printf("An error has occured with opening input file %s\n", argv[2]);
        printf("Make sure that input file exists in right directory.");
        exit(1);
    }
    User *users = NULL;
    int size = 0;
    char buffer[20];
    unsigned i;

    while (!feof(input)) {
        size++;
        users = (User*) realloc(users,size * sizeof(User));
        fscanf(input, "%20s", buffer);
        users[size - 1].last_name = (char*) malloc(strlen(buffer) + 1);
        strcpy(users[size - 1].last_name, buffer);
        fscanf(input, "%20s", buffer);
        users[size - 1].first_name = (char*) malloc(strlen(buffer) + 1);
        strcpy(users[size - 1].first_name, buffer);
        fscanf(input, "%20s", buffer);
        users[size - 1].patronymic = (char*) malloc(strlen(buffer) + 1);
        strcpy(users[size - 1].patronymic, buffer);
        fscanf(input, "%20s", buffer);
        users[size - 1].phone_number = (char*) malloc(strlen(buffer) + 1);
        strcpy(users[size - 1].phone_number, buffer);
    }
    fclose(input);

    qSort(users, 0, size - 1);

    FILE *output = fopen(argv[3], "w");
    for (i = 0; i < size; i++) {
        printUsers(output, &users[i]);
    }
    fclose(output);

    freeUsersArray(&users, size);
    return 0;
}