package com.avalon.backend.models;

import com.avalon.backend.beans.cards.WhiteCard;
import com.avalon.backend.beans.gamesession.Player;
import com.avalon.backend.beans.gamesession.Round;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Tonaz on 4/10/2015.
 */
public class GameSessionManager {

    public static Player findPlayer(final List<Player> players, final String email){

        for(Player player: players){

            if(player.getGameUser().getEmail().equalsIgnoreCase(email)){
                return player;
            }

        }

        return null;

    }

    public static List<WhiteCard> getCards(List<Integer> cardIndexes, Player player){
        List<WhiteCard> cards = new LinkedList<>();
        for(Integer index : cardIndexes){
            WhiteCard card = player.getCard(index);
            cards.add(card);
        }
        return cards;
    }

    public static boolean removeFromHand(List<WhiteCard> cards, Player player){

        boolean deleteSuccessful = true;

        for(WhiteCard card: cards){

            if(!player.removeCard(card)){
                deleteSuccessful = false;
            }

        }

        return deleteSuccessful;
    }

    public static boolean isRoundReady(List<Player> players, Map<Player, List<WhiteCard>> submissions){

        for(Player player : players){

            if(!submissions.containsKey(player)){
                return false;
            }

        }

        return true;
    }

    public static void addNewCardsToHand(List<WhiteCard> cards, Player player){
        for(WhiteCard card : cards){
            player.addCard(card);
        }
    }

    public static void dealNewCards(int cardsPlayed, List<WhiteCard> deck, Player player){

        for(int index = 0; index < cardsPlayed; index++){
            player.addCard(deck.remove(randomWithRange(0, deck.size()-1)));
        }

    }

    private static int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

}
