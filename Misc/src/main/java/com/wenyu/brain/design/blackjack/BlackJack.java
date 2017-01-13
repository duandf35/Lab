package com.wenyu.brain.design.blackjack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wenyu
 * @since 1/9/17
 */
public class BlackJack {

    /**
     * Rule:
     * 1. Two players
     * 2. Initially each one get two cards.
     * 3. Each turn one play can stand or hit.
     * 4. Judging the winner once two players both stand.
     * 5. One play will immediately lose the game once the total score of cards is greater than twenty one.
     * 6. Each player will hide one of the card.
     *
     * Design:
     * 1. Player model to hold the player's name and money.
     * 2. PlayerStatus model hold the Cards, scores, and stand status. Player can see the opponent's partial score (Rule 6).
     * 3. Stage hold the PlayerStatuses and Card library, controller the turn, deal Card, shuffle Cards, judge the winner and update money.
     *
     * @param args
     */
    public void main(String... args) {
        Stage stage = new Stage();

        stage.initPlayers();

        List<PlayerStatus> winners = new ArrayList<>();

        while (winners.isEmpty()) {
            winners = stage.nextTurn();
        }

        stage.settlement(winners);
    }

    // DAO
    class Player {
        String name;
        Integer money;

        Player(String name, Integer money) {
            this.name = name;
            this.money = money;
        }
    }

    // DTO
    class PlayerStatus {
        Player player;
        Card cardToHide = null;
        List<Card> cardsToShow = new ArrayList<>();
        Integer scores = 0;
        Boolean stand = false;

        PlayerStatus(Player player) {
            this.player = player;
        }

        // For UI
        private Integer scores() {
            return this.scores;
        }

        // For UI
        private Integer partialScores() {
            return this.scores - this.cardToHide.score;
        }

        // For UI
        private List<Card> cardsToShow() {
            return this.cardsToShow;
        }

        private void hit(Card card) {
            if (this.cardToHide == null) {
                this.cardToHide = card;
            } else {
                this.cardsToShow.add(card);
            }
            this.scores += card.score;
        }

        private void updateMoney(Integer change) {
            this.player.money += change;
        }

        // For user input
        private void stand() {
            this.stand = true;
        }

        private boolean isStand() {
            return this.stand;
        }
    }

    class Card {
        String color;
        Integer score;

        Card(String color, Integer score) {
            this.color = color;
            this.score = score;
        }
    }

    class Stage {
        ArrayDeque<Card> library;
        ArrayDeque<PlayerStatus> playerStatuses;

        Stage() {
            this.library = shuffle();
            // Two players
            this.playerStatuses = new ArrayDeque<>(2);
            this.playerStatuses.add(new PlayerStatus(new Player("player1", 1000)));
            this.playerStatuses.add(new PlayerStatus(new Player("player2", 1000)));
        }

        // For UI
        PlayerStatus[] getPlayerStatuses() {
            return this.playerStatuses.toArray(new PlayerStatus[this.playerStatuses.size()]);
        }

        void initPlayers() {
            PlayerStatus player;
            // Initially deal two cards to each player
            for (int i = 0; i < 4; i++) {
                player = this.playerStatuses.poll();
                player.hit(this.library.poll());
                this.playerStatuses.add(player);
            }
        }

        List<PlayerStatus> nextTurn() {
            PlayerStatus player;

            for (int i = 0; i < 2; i++) {
                player = this.playerStatuses.poll();

                askForStand(player);

                // TODO: if one player's score is equal to 21
                // TODO: skip asking for stand
                if (!player.isStand()) {
                    player.hit(this.library.poll());
                }

                this.playerStatuses.add(player);
            }

            return judgeWinner();
        }

        void askForStand(PlayerStatus playerStatus) {
            // handle user input
        }

        void settlement(List<PlayerStatus> winners) {
            int win = 5;
            winners.forEach(w -> w.updateMoney(win));
        }

        // May have two winners
        // TODO: if one player'scores is greater then 21
        // TODO: immediately lose the game
        private List<PlayerStatus> judgeWinner() {
            List<PlayerStatus> winners = new ArrayList<>();
//            winners.add(this.playerStatuses.poll());
//            PlayerStatus player;
//            while (!this.playerStatuses.isEmpty()) {
//                player = this.playerStatuses.poll();
//                if (winners.get(0).scores < player.scores) {
//                    winners = new ArrayList<>();
//                    winners.add(player);
//                } else if (winners.get(0).scores.equals(player.scores)) {
//                    winners.add(player);
//                }
//            }
//
            return winners;
        }

        private ArrayDeque<Card> shuffle() {
            return new ArrayDeque<>();
        }
    }
}
