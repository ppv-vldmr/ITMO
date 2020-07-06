import sys

def next(file):
    st = ""
    x = file.read(1)
    while x == " " or x == "\n":
        x = file.read(1)
    while x != " " and x != "\n" and x != "":
        st += x
        x = file.read(1)
    return str(float(st))

def multiplyByNumber(matrix, n, m, x):
    x = float(x)
    for i in range(int(n)):
        for j in range(int(m)):
            matrix[i][j] *= x
    return matrix

def transpose(matrix, n, m):
    temp = []
    for j in range(int(m)):
        temp.append([])
        for i in range(int(n)):
            temp[j].append(matrix[i][j])
    return temp, m, n

def insertMatrix(file, n, m):
    matrix = []
    for i in range(int(n)):
        matrix.append([])
        for j in range(int(m)):
            x = float(next(file))
            matrix[i].append(x)
    return matrix

def multiplyByMatrix(matrix1, n1, m1, matrix2, n2, m2, file):
    if (m1 != n2):
        file.write("0")
        file.close()
        sys.exit()
    else:
        res = []
        for i in range(int(n1)):
            res.append([])
            for j in range(int(m2)):
                temp = 0
                for k in range(int(min(n2,m1))):
                    temp += float(matrix1[i][k]) * int(matrix2[k][j])
                    #print(matrix1[i][k], matrix2[k][j], temp)
                res[i].append(temp)
        #print(res)
    return res, n1, m2
        

def additionMatrix(matrix1, n1, m1, matrix2, n2, m2):
    if (n1 != n2) or (m1 != m2):
        file.write("0")
        file.close()
        sys.exit()
    else:
        for i in range(int(n1)):
            for j in range(int(m1)):
                matrix1[i][j] += matrix2[i][j]
        return matrix1, n1, m1

def subtractionMatrix(matrix1, n1, m1, matrix2, n2, m2, file):
    if (n1 != n2) or (m1 != m2):
        file.write("0")
        file.close()
        sys.exit()
    else:
        for i in range(int(n1)):
            for j in range(int(m1)):
                matrix1[i][j] -= matrix2[i][j]
        return matrix1, n1, m1
        

def writeMatrix(matrix, n, m, file):
    for i in range(int(n)):
        for j in range(int(m)):
            try:
                matrix[i][j] = int(matrix[i][j])
            except:
                matrix[i][j] = float(matrix[i][j])
            finally:
                file.write(str(matrix[i][j]) + " ")
        file.write("\n")

fileIn = open("input.txt")
fileOut = open ("output.txt", "w")
alpha = float(next(fileIn))
beta = float(next(fileIn))
nA = float(next(fileIn))
mA = float(next(fileIn))
matrixA = insertMatrix(fileIn, nA, mA)
nB = float(next(fileIn))
mB = float(next(fileIn))
matrixB = insertMatrix(fileIn, nB, mB)
nC = float(next(fileIn))
mC = float(next(fileIn))
matrixC = insertMatrix(fileIn, nC, mC)
nD = float(next(fileIn))
mD = float(next(fileIn))
matrixD = insertMatrix(fileIn, nD, mD)
nF = float(next(fileIn))
mF = float(next(fileIn))
matrixF = insertMatrix(fileIn, nF, mF)
#print(matrixA, matrixB, matrixC, matrixD, matrixF)
matrixA = multiplyByNumber(matrixA, nA, mA, alpha)
#print(matrixA, matrixB, matrixC, matrixD, matrixF)
matrixB, nB, mB = transpose(matrixB, nB, mB)
#print(matrixA, matrixB, matrixC, matrixD, matrixF)
matrixB = multiplyByNumber(matrixB, nB, mB, beta)
#print(matrixA, matrixB, matrixC, matrixD, matrixF)
matrixA, nA, mB = additionMatrix(matrixA, nA, mA, matrixB, nB, mB)
matrixA, nA, mA = transpose(matrixA, nA, mA)
#print(matrixA, matrixB, matrixC, matrixD, matrixF)
matrixC, nC, mC = multiplyByMatrix(matrixC, nC, mC, matrixA, nA, mA, fileOut)
#print(matrixA, matrixB, matrixC, matrixD, matrixF)
matrixC, nC, mC = multiplyByMatrix(matrixC, nC, mC, matrixD, nD, mD, fileOut)
#print(matrixA, matrixB, matrixC, matrixD, matrixF)
matrixC, nC, mC = subtractionMatrix(matrixC, nC, mC, matrixF, nF, mF, fileOut)
fileOut.write("1" + "\n")
fileOut.write(str(int(nC)) + " " + str(int(mC)) + "\n")
writeMatrix(matrixC, nC, mC, fileOut)
fileIn.close()
fileOut.close()
