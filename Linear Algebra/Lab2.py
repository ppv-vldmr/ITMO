import math
import sys

def vectorMultiplication(NAME1, NAME2):
    RES = []
    x = float(NAME1[1] * NAME2[2] - NAME1[2] * NAME2[1])
    y = float(NAME1[2] * NAME2[0] - NAME1[0] * NAME2[2])
    z = float(NAME1[0] * NAME2[1] - NAME1[1] * NAME2[0])
    RES.append(x)
    RES.append(y)
    RES.append(z)
    return RES


def modulVector(NAME):
    return math.sqrt(NAME[0] * NAME[0] + NAME[1] * NAME[1] + NAME[2] * NAME[2])


def next(file):
    st = ""
    x = file.read(1)
    while x == " " or x == "\n":
        x = file.read(1)
    while x != " " and x != "\n" and x != "":
        st += x
        x = file.read(1)
    return str(float(st))


def readVector(file):
    NAME = []
    for i in range(int(3)):
        param = float(next(file))
        NAME.append(param)
    return NAME


def findCosOfCornerBetweenVectors(A, B):
    x = float((A[0] * B[0] + A[1] * B[1] + A[2] * B[2]) / (modulVector(A) * modulVector(B)))
    return x


fileIn = open("input.txt")
fileOut = open("output.txt", "w")
ship = readVector(fileIn)
directionOfShip = readVector(fileIn)
mast = readVector(fileIn)
enemy = readVector(fileIn)
straightWay = [enemy[0] - ship[0], enemy[1] - ship[1], enemy[2] - ship[2]]
leftGun = vectorMultiplication([0, 0, 1], directionOfShip)
rightGun = vectorMultiplication(directionOfShip, [0, 0, 1])
if math.fabs(findCosOfCornerBetweenVectors(mast, [0, 0, 1])) < 0.5:
    fileOut.write("0")
    fileIn.close()
    fileOut.close()
    sys.exit(0)
if math.fabs(findCosOfCornerBetweenVectors(straightWay, directionOfShip)) > math.sqrt(3)/2:
    fileOut.write("0")
    fileIn.close()
    fileOut.close()
    sys.exit(0)
if findCosOfCornerBetweenVectors(leftGun, straightWay) >= 0.5:
    if findCosOfCornerBetweenVectors(straightWay, directionOfShip) < 0:
        t = -1
    else:
        t = 1
    a = 1
    b = math.acos(math.fabs(findCosOfCornerBetweenVectors(leftGun, straightWay))) / math.pi * 180
    res = str(a) + "\n" + str(b*t) + "\n"
    fileOut.write(res)
else:
    if findCosOfCornerBetweenVectors(rightGun, straightWay) >= 0.5:
        if findCosOfCornerBetweenVectors(straightWay, directionOfShip) < 0:
            t = -1
        else:
            t = 1
        a = -1
        b = math.acos(math.fabs(findCosOfCornerBetweenVectors(rightGun, straightWay))) / math.pi * 180
        res = str(a) + "\n" + str(b*t) + "\n"
        fileOut.write(res)
fileOut.write(str(math.acos(math.fabs(findCosOfCornerBetweenVectors(mast, [0, 0, 1]))) * 180 / math.pi * t))
fileOut.write("\n" + "abacababa")
fileIn.close()
fileOut.close()