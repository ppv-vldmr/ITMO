module HW1.T1 
       ( Day(..),
         nextDay,
         afterDays,
         isWeekend,
         daysToParty
       ) where

import Numeric.Natural (Natural)


data Day = Monday | Tuesday | Wednesday | Thursday | Friday | Saturday | Sunday
    deriving (Show, Eq)

daysNumbers :: [Natural]
daysNumbers = [0..6]

days :: [Day]
days = [Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday]

week :: [(Day, Natural)]
week = zip days daysNumbers

getSecond :: (Day, Natural) -> Natural
getSecond (_, b) = b

getFirst :: (Day, Natural) -> Day
getFirst (a, _) = a

numberByDay :: Day -> Natural
numberByDay day = getSecond (head (filter (\(d, _) -> d == day) week))

dayByNumber :: Natural -> Day
dayByNumber num = getFirst (head (filter (\(_, i) -> i == num) week))

-- | Returns the day that follows the day of the week given as input.
nextDay :: Day -> Day
nextDay cur = dayByNumber (mod (numberByDay cur + 1) 7)

-- | Returns the day of the week after a given number of days has passed.
afterDays :: Natural -> Day -> Day
afterDays number cur = dayByNumber (mod (numberByDay cur + number) 7)

-- | Checks if the day is on the weekend.
isWeekend :: Day -> Bool
isWeekend day = day == Saturday || day == Sunday

-- | Computes the number of days until the next Friday.
daysToParty :: Day -> Natural
daysToParty day = let curDayNumber = numberByDay day
                      fridayNumber = numberByDay Friday
                  in if curDayNumber <= fridayNumber
                      then fridayNumber - curDayNumber
                      else 7 + fridayNumber - curDayNumber