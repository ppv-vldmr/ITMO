//
//  Deck.swift
//  homework-1
//
//  Created by Владимир on 21.09.2020.
//

import Foundation

struct Deck {
    var cards: [Card] = []
    
    enum suits: String, CaseIterable {
        case hearts = "♥", spades = "♠", diamonds = "♦", crosses = "♣"
    }
    
    enum symbols: String, CaseIterable {
        case a = "A", two = "2", three = "3", four = "4", five = "5",
             six = "6", seven = "7", eight = "8", nine = "9",
             ten = "10", jack = "J", queen = "Q", king = "K"
        
        var value: Int {
            switch self {
            case .a:
                return 11
            case .two:
                return 2
            case .three:
                return 3
            case .four:
                return 4
            case .five:
                return 5
            case .six:
                return 6
            case .seven:
                return 7
            case .eight:
                return 8
            case .nine:
                return 9
            default:
                return 10
            }
        }
    }
    
    init() {
        suits.allCases.forEach {
            let i = $0
            symbols.allCases.forEach {
                self.cards.append(Card(value: $0.value, suit: i.rawValue, symbol: $0.rawValue))
            }
        }
    }
}
