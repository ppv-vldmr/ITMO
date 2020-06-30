#include <stdio.h>
#include <math.h>
#include <malloc.h>

#define EPS 1e-8
#define swap(type, arg1, arg2) { \
    type temp = arg1;\
    arg1 = arg2;\
    arg2 = temp;\
}

void pivoting(int k, double **matrix, int n, int *roots) {
    int i_max = k, j_max = k;

    for (size_t i = k; i < n; i++) {
        for (size_t j = k; j < n; j++) {
            if (fabs(matrix[i_max][j_max]) < fabs(matrix[i][j])) {
                i_max = i;
                j_max = j;
            }
        }
    }

    swap(double*, matrix[k], matrix[i_max])

    for (size_t i = 0; i < n; i++) {
        swap(double, matrix[i][k], matrix[i][j_max])
    }

    swap(int, roots[k], roots[j_max])
}

int main(int argc, char** argv) {
    if (argc != 3) {
        printf("Wrong count of program arguments.\nExpected: 3, found: %d\n", argc);
        return 1;
    }
    FILE *input = fopen(argv[1], "r");
    if (input == NULL) {
        printf("An error has occured with input file %s\n", argv[1]);
        return 1;
    }
    int n;
    fscanf(input, "%d", &n);
    if (n <= 0) {
        printf("Wrong count of equations entered.\n");
        return 1;
    }
    double **matrix = NULL;
    matrix = (double**) malloc(n * sizeof(double*));
    if (matrix == NULL) {
        printf("Memory allocation error.");
        fclose(input);
        return 1;
    }

    for (size_t i = 0; i < n; i++) {
        matrix[i] = (double*) malloc((n + 1) * sizeof(double));
        if (matrix[i] == NULL) {
            printf("Memory allocation error.");
            free(matrix);
            fclose(input);
            return 1;
        }
    }

    double *x = NULL; //Корни системы
    x = (double*) malloc(n * sizeof(double));
    if (x == NULL) {
        printf("Memory allocation error.");
        for(size_t i = 0; i < n; i++) {
            free(matrix[i]);
        }
        free(matrix);
        fclose(input);
        return 1;
    }
    int *roots = NULL; //Отвечает за порядок корней
    roots = (int*) malloc(n * sizeof(int));
    if (roots == NULL) {
        printf("Memory allocation error.");
        for(size_t i = 0; i < n; i++) {
            free(matrix[i]);
        }
        free(matrix);
        free(x);
        fclose(input);
        return 1;
    }

    for (size_t i = 0; i < n; i++) {
        for(size_t j = 0; j <= n; j++) {
            fscanf(input, "%lf", &matrix[i][j]);
        }
    }
    fclose(input);

    FILE *output = fopen(argv[2], "w");
    if (output == NULL) {
        printf("An error has occured with output file %s\n", argv[2]);
        for(size_t i = 0; i < n; i++) {
            free(matrix[i]);
        }
        free(matrix);
        free(x);
        free(roots);
        return 1;
    }

    for (size_t i = 0; i < n; i++) {
        roots[i] = i;
    }

    //Прямой ход
    for (ptrdiff_t k = 0; k < n; k++) {
        pivoting(k, matrix, n, roots);

        for (ptrdiff_t j = n; j >= k; j--) {
            matrix[k][j] /= matrix[k][k];
        }
        for (size_t i = k + 1; i < n; i++) {
            for (ptrdiff_t j = n; j >= k; j--) {
                matrix[i][j] -= matrix[k][j] * matrix[i][k];
            }
        }

        for (size_t i = 0; i < n; i++) {
            int cnt = 0;
            for(int j = 0; j < n; j++) {
                if (fabs(matrix[i][j]) < EPS) {
                    cnt++;
                }
            }
            if (cnt == n) {
                if (fabs(matrix[i][n]) < EPS) {
                    fprintf(output, "many solutions");
                } else {
                    fprintf(output, "no solutions");
                }
                for (size_t q = 0; q < n; q++) {
                    free(matrix[q]);
                }
                free(matrix);
                free(x);
                free(roots);
                return 0;
            }
        }
    }

    for (size_t i = 0; i < n; i++) {
        x[i] = matrix[i][n];
    }

    for (ptrdiff_t i = n - 2; i >= 0; i--) {
        for (size_t j = i + 1; j < n; j++) {
            x[i] -= x[j] * matrix[i][j];
        }
    }

    for (size_t i = 0; i < n; i++) {
        for (size_t j = 0; j < n; j++) {
            if (i == roots[j]) {
                fprintf(output, "%.16f\n", x[j]);
                break;
            }
        }
    }

    for(size_t i = 0; i < n; i++) {
        free(matrix[i]);
    }
    free(matrix);
    free(x);
    free(roots);
    fclose(output);
    return 0;
}