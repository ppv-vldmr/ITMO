import math


def printTable(table):
    for i in range(len(table)):
        print(i.__str__() + ": " + table[i].__str__(), end="\n")


def evalProbability(classes, words, k, alpha): #оцениваем вероятность встречи слов через сглаживание Лапласа
    def evalCountWordEachClasses():
        wordsTmp = {word: {i: 0 for i in range(k)} for word in words}
        for key in classes:
            arrays = classes[key]
            for array in arrays:
                wasTouched = set()
                for el in array:
                    if not wasTouched.__contains__(el):
                        wasTouched.add(el)
                        wordsTmp[el][key] = wordsTmp[el][key] + 1
        return wordsTmp

    result = {i: {word: 0 for word in words} for i in range(k)}
    wordsStats = evalCountWordEachClasses()
    for i in range(k):
        for word in words:
            result[i][word] = ((wordsStats[word][i] + alpha), (len(classes[i]) + alpha * 2))
    return result



if __name__ == '__main__':
    eps = 0.00001 #задаем погрешность
    k = int(input()) #парсим число классов
    lambdas = [int(el) for el in input().split(" ")] #задаем штрафы за ошибки классификации сообщений соответствующих классов
    alpha = int(input()) #задаем интенсивность аддитивного сглаживания
    n = int(input()) #задаем число сообщений в обучающей выборке
    classes = {i: list() for i in range(k)}
    c_count = {i: 0 for i in range(k)}
    words = set()
    for i in range(n): #парсим слова из обучающей выборки
        inp = input().split(" ")
        classTmp = int(inp[0])
        c_count[classTmp - 1] = c_count[classTmp - 1] + 1 #относим слово к соответствующему классу
        count = int(inp[1]) #парсим число слов в сообщении
        arr = list()
        for j in range(count): #парсим слова в каждом сообщении
            word = inp[2 + j]
            words.add(word)
            arr.append(word)
        classes[classTmp - 1].append(arr)
    c_w_p = evalProbability(classes, words, k, alpha) #оцениваем вероятность встречи слов
    # printTable(c_w_p)

    m = int(input())
    for _ in range(m): #парсим сообщения из проверочной выборки
        count, *wordsTmp_ = input().split()
        foundWords = set(wordsTmp_) #набор слов в сообщении
        notFoundWords = words - foundWords #не найденные слова
        res_p = [0.0 for _ in range(k)]
        for i in range(k):
            resTmp = math.log(lambdas[i] + eps) + math.log(c_count[i] if c_count[i] != 0 else eps) - math.log(n + eps)
            for word in words:
                x, y = c_w_p[i][word]
                if foundWords.__contains__(word):
                    resTmp += math.log(x + eps) - math.log(y + eps)
                else:
                    resTmp += math.log(y - x + eps) - math.log(y + eps)
            res_p[i] = resTmp
        sumRes = sum(res_p)
        maxRes = max(res_p) #оценка апостериорного максимума для каждого класса
        helper = sumRes / k
        if maxRes - helper > 15:
            helper = maxRes + 10
        res_p = list(map(lambda el: math.exp(el - helper), res_p))
        newSum = sum(res_p)
        for i in range(k):
            print(0 if newSum == 0 else res_p[i] / newSum, end=" ")
        print("")