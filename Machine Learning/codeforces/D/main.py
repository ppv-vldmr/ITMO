n, m = map(int,
           input().split())  # парсим число объектов в наборе данных и число признаков у объектов исключая зависимую переменную

xs, ys = map(lambda _: [], range(2))

for _ in range(n):  # парсим описание объектов: признаки и значение целевой переменной
    inp = list(map(int, input().split()))
    xs.append(inp)

ws = list(map(int, input().split()))  # парсим коэффициенты прямой


def dot(w, x):
    res = 0
    for i in range(len(w) - 1):
        res += x[i] * w[i]
    res += w[-1]
    return res


def smapeGrad(y, pred):  # считаем градиент функции ошибки
    if y == 0 or pred - y == 0:
        return 0
    return (abs(y) / y * abs(pred) + pred) / (((abs(pred) + abs(y)) ** 2) * abs(y) / y * abs(y - pred) / (y - pred))


res = list(dot(ws, x) for x in xs)  # считаем для каждого объекта значение линейной функции

for i in range(n):
    tmp = smapeGrad(res[i], xs[i][-1])
    for x in xs[i][:-1]:  # считаем для объекта значение градиента
        print(x * tmp, end=' ')
    print(tmp)