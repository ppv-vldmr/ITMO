#include <stdio.h>
#include <stdlib.h>

void push_output_BOM(FILE *output, int mode) {
    unsigned const char BOM_utf_8[3] = {0xEF, 0xBB, 0xBF};
    unsigned const char BOM_utf_16BE[2] = {0xFE, 0xFF};
    unsigned const char BOM_utf_16LE[2] = {0xFF, 0xFE};
    unsigned const char BOM_utf_32BE[4] = {0x00, 0x00, 0xFE, 0xFF};
    unsigned const char BOM_utf_32LE[4] = {0xFF, 0xFE, 0x00, 0x00};
    switch (mode) {
        case 1:
            fwrite(BOM_utf_8, sizeof(BOM_utf_8), 1, output);
            break;
        case 2:
            fwrite(BOM_utf_16LE, sizeof(BOM_utf_16LE), 1, output);
            break;
        case 3:
            fwrite(BOM_utf_16BE, sizeof(BOM_utf_16BE), 1, output);
            break;
        case 4:
            fwrite(BOM_utf_32LE, sizeof(BOM_utf_32LE), 1, output);
            break;
        case 5:
            fwrite(BOM_utf_32BE, sizeof(BOM_utf_32BE), 1, output);
            break;
    }
}

int read_input_BOM(FILE *input) {
    unsigned int c;
    c = fgetc(input);
    switch (c) {
        case 0xEF:
            fseek(input, SEEK_CUR + 2, SEEK_CUR);
            return 1;
        case 0xFE:
            fseek(input, SEEK_CUR + 1, SEEK_CUR);
            return 3;
        case 0x00:
            fseek(input, SEEK_CUR + 3, SEEK_CUR);
            return 4;
        case 0xFF:
            fseek(input, SEEK_CUR + 1, SEEK_CUR);
            c = fgetc(input);
            if (c == 0x00) {
                fseek(input, SEEK_CUR + 1, SEEK_CUR);
                return 5;
            } else {
                fseek(input, -1, SEEK_CUR);
                return 2;
            }
    }
    fseek(input, -1, SEEK_CUR);
    return 0;
}

void same_encodings(FILE *input, FILE *output) {
    unsigned char c;
    while(!feof(input)) {
        fscanf(input, "%c", &c);
        if (feof(input))
            break;
        fprintf(output, "%c", c);
    }
}

unsigned long code_point_utf32(const unsigned char *buffer) {
    return (buffer[0] << 24) + (buffer[1] << 16) + (buffer[2] << 8) + buffer[3];
}

unsigned long code_point_utf16(const unsigned char * buffer, int bytes_count) {
    switch (bytes_count) {
        case 2:
            return (buffer[0] << 8) + buffer[1];
        case 4:
            return ((((buffer[0] << 8) + buffer[1]) - 0xD800) * 0x0400) +
            (((buffer[2] << 8) + buffer[3]) - 0xDC00) + 0x10000;
    }
}

unsigned long code_point_utf8(const unsigned char * buf, int bytes_count) {
    switch (bytes_count) {
        case 1:
            return buf[0];
        case 2:
            return (buf[0] - (1 << 6) - (1 << 7)) * (1 << 6) + (buf[1] - (1 << 7));
        case 3:
            return (buf[0] - (1 << 6) - (1 << 7) - (1 << 5)) * (1 << 12) + (buf[1] - (1 << 7)) * (1 << 6) + (buf[2] - (1 << 7));
        case 4:
            return (buf[0] - (1 << 6) - (1 << 7) - (1 << 5) - (1 << 4)) * (1 << 18) + (buf[1] - (1 << 7)) * (1 << 12) + (buf[2] - (1 << 7)) * (1 << 6) + (buf[3] - (1 << 7));
    }
}

void convert_code_point_utf32(unsigned char* buf, const unsigned long codePoint) {
    buf[0] = codePoint >> 24;
    buf[1] = (codePoint << 8) >> 24;
    buf[2] = (codePoint << 16) >> 24;
    buf[3] = (codePoint << 24) >> 24;
}

int convert_code_point_utf16(unsigned char* buf, unsigned long codePoint) {
    if (codePoint <= 0xFFFF) {
        buf[0] = codePoint >> 8;
        buf[1] = codePoint;
        return 2;
    } else if (codePoint <= 0x10FFFF) {
        codePoint = codePoint - 0x10000;
        buf[0] = ((codePoint >> 10) + 0xD800) >> 8;
        buf[1] = ((codePoint >> 10) + 0xD800) >> 24;
        buf[2] = (((codePoint << 22) >> 22) + 0xDC00 + 0xD800) >> 8;
        buf[3] = (((codePoint << 22) >> 22) + 0xDC00 + 0xD800) >> 24;
        return 4;
    }
    return 0;
}

int convert_code_point_utf8(unsigned char* buf, unsigned long codePoint) {
    if (codePoint <= 0x007F) {
        buf[0] = codePoint;
        return 1;
    }
    if (codePoint <= 0x07FF) {
        buf[0] = 6 * (1 << 5) + (codePoint >> 6);
        buf[1] = 2 * (1 << 6) + ((codePoint << 24) >> 24);
        return 2;
    }
    if (codePoint <= 0xFFFF) {
        buf[0] = 0xE * (1 << 4) + (codePoint >> 12);
        buf[1] = 2 * (1 << 6) + ((codePoint << 16) >> 24);
        buf[2] = 2 * (1 << 6) + ((codePoint << 24) >> 24);
        return 3;
    }
    if ((codePoint <= 0x10FFFF)) {
        buf[0] = 30 * (1 << 3) + (codePoint >> 18);
        buf[1] = 2 * (1 << 6) + ((codePoint << 8) >> 24);
        buf[2] = 2 * (1 << 6) + ((codePoint << 16) >> 24);
        buf[3] = 2 * (1 << 6) + ((codePoint << 24) >> 24);
        return 4;
    }
    return -1;
}

void reverse(char *res, int size) {
    if (size == 2) {
        char temp1;
        temp1 = res[0];
        res[0] = res[1];
        res[1] = temp1;
    }
    if (size == 4) {
        char temp1, temp2;
        temp1 = res[0];
        temp2 = res[1];
        res[0] = res[3];
        res[1] = res[2];
        res[3] = temp1;
        res[2] = temp2;
    }
}

void different_utf_16(FILE *in, FILE *out) {
    unsigned char bytes[2];
    while (fscanf(in, "%c%c", &bytes[0], &bytes[1]) != EOF) {
            fprintf(out, "%c%c", bytes[1], bytes[0]);
    }
}

void different_utf_32(FILE *in, FILE *out) {
    unsigned char bytes[4];
    while (fscanf(in, "%c%c%c%c", &bytes[0], &bytes[1], &bytes[2], &bytes[3]) != EOF) {
        fprintf(out, "%c%c%c%c", bytes[3], bytes[2], bytes[1], bytes[0]);
    }
}

void utf32_to_utf16(FILE *input, FILE *output, _Bool in_endian, _Bool out_endian) {
    unsigned char bytes[4];
    while(fscanf(input, "%c%c%c%c", &bytes[0], &bytes[1], &bytes[2], &bytes[3]) != EOF) {
        unsigned char res[4];
        if (in_endian) {
            reverse(bytes, 4);
        }
        unsigned int cp = convert_code_point_utf16(res, code_point_utf32(bytes));
        if (out_endian) {
            reverse(res, cp);
        }
        for (int i = 0; i < cp; i++)
            fprintf(output, "%c", res[i]);
    }
}

void utf16_to_utf32(FILE *in, FILE *out, _Bool in_endian, _Bool out_endian) {
    unsigned char bytes[4];
    unsigned char res[4];
    while (fscanf(in, "%c%c", &bytes[0], &bytes[1]) != EOF) {
        if (in_endian) {
            reverse(bytes, 2);
        }
        unsigned long cp;
        if ((cp = code_point_utf16(bytes, 2)) <= 0xD7FF) {
            convert_code_point_utf32(res, cp);
            if (out_endian)
                fprintf(out, "%c%c%c%c", res[0], res[1], res[2], res[3]);
            else
                fprintf(out, "%c%c%c%c", res[3], res[2], res[1], res[0]);
        }
        if (fscanf(in, "%c%c", &bytes[2], &bytes[3]) == EOF)
            break;
        if ((cp = code_point_utf16(bytes, 4)) <= 0x10FFFF) {
            convert_code_point_utf32(res, cp);
            if (out_endian)
                fprintf(out, "%c%c%c%c", res[0], res[1], res[2], res[3]);
            else
                fprintf(out, "%c%c%c%c", res[3], res[2], res[1], res[0]);
        }
    }
}

void utf16_to_utf8(FILE *in, FILE *out, _Bool endian) {
    unsigned char bytes[4];
    unsigned char res[4];
    while (fscanf(in, "%c%c", &bytes[0], &bytes[1]) != EOF) {
        if (endian) {
            reverse(bytes, 2);
        }
        unsigned long cp;
        if ((cp = code_point_utf16(bytes, 2)) <= 0xD7FF) {
            switch (convert_code_point_utf8(res, cp)) {
                case 1:
                    fprintf(out, "%c", res[0]);
                    break;
                case 2:
                    fprintf(out, "%c%c", res[0], res[1]);
                    break;
                case 3:
                    fprintf(out, "%c%c%c", res[0], res[1], res[2]);
                    break;
                case 4:
                    fprintf(out, "%c%c%c%c", res[0], res[1], res[2], res[3]);
                    break;
            }
            continue;
        }
        if (fscanf(in, "%c%c", &bytes[2], &bytes[3]) == EOF)
            break;
        if ((cp = code_point_utf16(bytes, 4)) <= 0x10FFFF) {
            switch (convert_code_point_utf8(res, cp)) {
                case 1:
                    fprintf(out, "%c", res[0]);
                    break;
                case 2:
                    fprintf(out, "%c%c", res[0], res[1]);
                    break;
                case 3:
                    fprintf(out, "%c%c%c", res[0], res[1], res[2]);
                    break;
                case 4:
                    fprintf(out, "%c%c%c%c", res[0], res[1], res[2], res[3]);
                    break;
            }
        }
    }
}

void utf8_to_utf16(FILE *in, FILE *out, _Bool endian) {
    unsigned char byte[4];
    unsigned char res[4];
    while(fscanf(in, "%c", &byte[0]) != EOF) {
        if (byte[0] <= 0x007F) {
            int count_bytes = convert_code_point_utf16(res, code_point_utf8(byte, 1));
            if (endian) {
                reverse(res, count_bytes);
            }
            fprintf(out, "%c%c", res[0], res[1]);
            continue;
        }
        if(fscanf(in, "%c", &byte[1]) == EOF)
            break;
        unsigned int cp;
        if ((cp = code_point_utf8(byte, 2)) <= 0x07FF) {
            int count_bytes = convert_code_point_utf16(res, cp);
            if (endian) {
                reverse(res, count_bytes);
            }
            fprintf(out, "%c%c", res[0], res[1]);
            continue;
        }
        if(fscanf(in, "%c", &byte[2]) == EOF)
            break;
        if ((cp = code_point_utf8(byte, 3)) <= 0xFFFF) {
            int count_bytes = convert_code_point_utf16(res, cp);
            if (endian) {
                reverse(res, count_bytes);
            }
            if (count_bytes == 2)
                fprintf(out, "%c%c", res[0], res[1]);
            else
                fprintf(out, "%c%c%c%c", res[0], res[1], res[2], res[3]);
            continue;
        }
        if(fscanf(in, "%c", &byte[3]) == EOF)
            break;
        if ((cp = code_point_utf8(byte, 4)) <= 0x10FFFF) {
            int count_bytes = convert_code_point_utf16(res, cp);
            if (endian) {
                reverse(res, count_bytes);
            }
            if (count_bytes == 2)
                fprintf(out, "%c%c", res[0], res[1]);
            else
                fprintf(out, "%c%c%c%c", res[0], res[1], res[2], res[3]);
        }
    }
}

void utf8_to_utf32(FILE *in, FILE *out, _Bool endian) {
    unsigned char bytes[4];
    unsigned char res[4];
    while(fscanf(in, "%c", &bytes[0]) != EOF) {
        if (bytes[0] <= 0x007F) {
            convert_code_point_utf32(res, code_point_utf8(bytes, 1));
            if (endian) {
                reverse(res,4 );
            }
            fprintf(out, "%c%c%c%c", res[0], res[1], res[2], res[3]);
            continue;
        }
        if(fscanf(in, "%c", &bytes[1]) == EOF)
            break;
        unsigned int cp;
        if ((cp = code_point_utf8(bytes, 2)) <= 0x07FF) {
            convert_code_point_utf32(res, cp);
            if (endian) {
                reverse(res, 4);
            }
            fprintf(out, "%c%c%c%c", res[0], res[1], res[2], res[3]);
            continue;
        }
        if(fscanf(in, "%c", &bytes[2]) == EOF)
            break;
        if ((cp = code_point_utf8(bytes, 3)) <= 0xFFFF) {
            convert_code_point_utf32(res, cp);
            if (endian) {
                reverse(res, 4);
            }
            fprintf(out, "%c%c%c%c", res[0], res[1], res[2], res[3]);
            continue;
        }
        if(fscanf(in, "%c", &bytes[3]) == EOF)
            break;
        if ((cp = code_point_utf8(bytes, 4)) <= 0x10FFFF) {
            convert_code_point_utf32(res, cp);
            if (endian) {
                reverse(res, 4);
            }
            fprintf(out, "%c%c%c%c", res[0], res[1], res[2], res[3]);
        }
    }
}

void utf32_to_utf8(FILE *in, FILE *out, _Bool endian) {
    unsigned char bytes[4];
    unsigned char res[4];
    while(fscanf(in, "%c%c%c%c", &bytes[0], &bytes[1], &bytes[2], &bytes[3]) != EOF) {
        if (endian) {
            reverse(bytes, 4);
        }
        switch (convert_code_point_utf8(res, code_point_utf32(bytes))) {
            case 1:
                fprintf(out, "%c", res[0]);
                break;
            case 2:
                fprintf(out, "%c%c", res[0], res[1]);
                break;
            case 3:
                fprintf(out, "%c%c%c", res[0], res[1], res[2]);
                break;
            case 4:
                fprintf(out, "%c%c%c%c", res[0], res[1], res[2], res[3]);
                break;
        }
    }
}

int main(int argc, char *argv[]) {
    if (argc != 5) {
        printf("Wrong count of program arguments.\nExpectred: 4\nActual: %d\n", argc - 1);
        printf("Arguments: \"lab1\", input file, output file, type of output encoding.\n");
        printf("Output encoding types:\n"
               "0) utf-8 without BOM\n"
               "1) utf-8 with BOM\n"
               "2) utf-16 LE\n"
               "3) utf-16 BE\n"
               "4) utf-32 LE\n"
               "5) utf-32 BE\n");
        exit(1);
    }
    FILE *input = fopen(argv[2],"r");
    if (!input) {
        printf("An error has occured with opening input file.\n");
        exit(1);
    }
    int in_mode = read_input_BOM(input);

    FILE *output = fopen(argv[3],"w");
    int out_mode = atoi(argv[4]);
    push_output_BOM(output, out_mode);

    //Big endian -> 0
    //Little endian -> 1
    
    switch (in_mode) {
        case 0:
        case 1:
            switch (out_mode) {
                case 0:
                case 1:
                    same_encodings(input, output);
                    break;
                case 2:
                    utf8_to_utf16(input, output, 1);
                    break;
                case 3:
                    utf8_to_utf16(input, output, 0);
                    break;
                case 4:
                    utf8_to_utf32(input, output, 1);
                    break;
                case 5:
                    utf8_to_utf32(input, output, 0);
                    break;
            }
            break;

        case 2:
            switch (out_mode) {
                case 0:
                case 1:
                    utf16_to_utf8(input, output, 0);
                    break;
                case 2:
                    same_encodings(input, output);
                    break;
                case 3:
                    different_utf_16(input, output);
                    break;
                case 4:
                    utf16_to_utf32(input, output, 1, 1);
                    break;
                case 5:
                    utf16_to_utf32(input, output, 1, 0);
                    break;
            }
            break;

        case 3:
            switch (out_mode) {
                case 0:
                case 1:
                    utf16_to_utf8(input, output, 0);
                    break;
                case 2:
                    different_utf_16(input, output);
                    break;
                case 3:
                    same_encodings(input, output);
                    break;
                case 4:
                    utf16_to_utf32(input, output, 0, 1);
                    break;
                case 5:
                    utf16_to_utf32(input, output, 0, 0);
                    break;
            }
            break;

        case 4:
            switch (out_mode) {
                case 0:
                case 1:
                    utf32_to_utf8(input, output, 1);
                    break;
                case 2:
                    utf32_to_utf16(input, output, 1, 1);
                    break;
                case 3:
                    utf32_to_utf16(input, output, 1, 0);
                    break;
                case 4:
                    same_encodings(input, output);
                    break;
                case 5:
                    different_utf_32(input, output);
                    break;
            }
            break;

        case 5:
            switch (out_mode) {
                case 0:
                case 1:
                    utf32_to_utf8(input, output, 0);
                    break;
                case 2:
                    utf32_to_utf16(input, output, 0, 1);
                    break;
                case 3:
                    utf32_to_utf16(input, output, 0, 0);
                    break;
                case 4:
                    different_utf_32(input, output);
                    break;
                case 5:
                    same_encodings(input, output);
                    break;
            }
            break;
    }

    fclose(input);
    fclose(output);
    return 0;
}