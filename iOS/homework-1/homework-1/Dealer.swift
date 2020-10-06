//
//  Dealer.swift
//  homework-1
//
//  Created by Владимир on 21.09.2020.
//

import Foundation

class Dealer {
    var hand = Hand(), playerHand = Hand()
    let deck = Deck()
    
    enum roundResult: String {
        case win = "Win"
        case lose = "Lose"
        case draw = "Draw"
        case surrender = "You surrendered"
    }
    
    func StartGame() -> Void {
        var command: String = ""
        while command != "exit" {
            print()
            print("-------START ROUND-------")
            let result: roundResult = PlayRound()
            print("-------ROUND ENDED-------RESULT:", result.rawValue)
            print("\"exit\" - end game, otherwise - new round.")
            command = readLine() ?? "exit"
        }
        print()
        print("See you.")
    }
    
    func PlayRound() -> roundResult {
        hand = Hand()
        playerHand = Hand()
        hand.TakeCard(deck: deck)
        hand.TakeCard(deck: deck)
        hand.DealerHandAtStart()
        playerHand.TakeCard(deck: deck)
        playerHand.TakeCard(deck: deck)
        print("Your hand:")
        playerHand.ShowHand()
        switch hand.sum {
            case 21:
                print("Dealer's hand:")
                hand.ShowHand()
                if playerHand.sum == 21 {
                    print("You and dealer got blackjack. Draw.")
                    return .draw
                } else {
                    print("Dealer got 21. Lose.")
                    return .lose
                }
            default:
                if playerHand.sum == 21 {
                    print("Dealer's hand:")
                    hand.ShowHand()
                    print("Blackjack!")
                    return .win
                }
        }
        print("Enter \"surrender\" if you want to leave this round and start new. Otherwise you'll continue play this round.")
        let surrender: String = readLine() ?? "nope"
        guard surrender != "surrender" else {
            return .surrender
        }
        print("""
            Type:
            1 - take a card;
            2 - pass.
            """)
        var command: String = readLine() ?? "unknown"
        while command != "2" {
            switch command {
            case "1":
                playerHand.TakeCard(deck: deck)
                print("Your hand:")
                playerHand.ShowHand()
                if playerHand.sum > 21 {
                    print("Too much.")
                    return .lose
                }
            
            case "2":
                return dealersMoves()
                
            default:
                command = readLine() ?? "unknown"
                continue
            }
            command = readLine() ?? "2"
        }
        hand.ShowHand()
        return dealersMoves()
                
    }
            
    func dealersMoves() -> roundResult {
        while (hand.sum <= 21) {
            switch hand.sum {
            case 19...:
                return check(playerSum: playerHand.sum, dealerSum: hand.sum)
                
            case 18:
                switch hand.cards[0].value {
                case 9...:
                    hand.TakeCard(deck: deck)
                    print("Dealer's hand:")
                    hand.ShowHand()
                    break
                    
                default:
                    print("Dealer's hand:")
                    hand.ShowHand()
                    return check(playerSum: playerHand.sum, dealerSum: hand.sum)
                }
                break
                
            default:
                hand.TakeCard(deck: deck)
                print("Dealer's hand:")
                hand.ShowHand()
            }
        }
        print("Dealer got", hand.sum, ". Too much.")
        return .win
    }
    
    func check(playerSum: Int, dealerSum: Int) -> roundResult {
        if playerSum > dealerSum {
            return .win
        }
        if playerSum < dealerSum {
            return .lose
        }
        return .draw
    }
}
