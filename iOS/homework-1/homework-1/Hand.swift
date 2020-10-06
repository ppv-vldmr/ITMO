//
//  Hand.swift
//  homework-1
//
//  Created by Владимир on 21.09.2020.
//

import Foundation

struct Hand {
    var cards: [Card] = []
    var sum: Int = 0
    
    mutating func TakeCard(deck: Deck) -> Void {
        let new: Card = deck.cards.randomElement()!
        self.cards.append(new)
        switch new.symbol {
            case "A":
                if self.sum < 11 {
                    self.sum += 11
                } else {
                    self.sum += 1
                }
                break
            default:
                self.sum += new.value
        }
    }
    
    func ShowHand() -> Void {
        for i in self.cards {
            print(i.symbol, i.suit)
        }
        print("Sum:", self.sum)
    }
    
    func DealerHandAtStart() -> Void {
        print("Dealer's hand:")
        if self.cards.capacity == 1 {
            print(self.cards[0].symbol, self.cards[0].suit)
        } else {
            print(self.cards[0].symbol, self.cards[0].suit, "+", self.cards.capacity - 1, "hidden cards")
        }
        print("Dealer's sum:", self.cards[0].value)
    }
}
