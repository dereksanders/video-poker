package main;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * The Class Card.
 * 
 * @author Derek Sanders
 * @version 1.0
 * @since August 9th, 2015
 */
public class Card implements Comparable<Object> {

	private int rank;
	private int suit;

	/**
	 * Instantiates a new card.
	 *
	 * @param rank
	 *            the rank
	 * @param suit
	 *            the suit
	 */
	public Card(int rank, int suit) {

		this.setRank(rank);
		this.setSuit(suit);
	}

	/**
	 * Builds the deck.
	 *
	 * @return the card[]
	 */
	public static ArrayList<Card> buildDeck() {

		ArrayList<Card> deck = new ArrayList<>();
		int i = 1;

		while (i < 14) {

			int s = 1;

			while (s < 5) {

				deck.add(new Card(i, s));
				s++;
			}

			i++;
		}

		return deck;
	}

	/**
	 * Draw card.
	 *
	 * @param deck
	 *            the deck
	 * @return the card
	 */
	private static Card drawCard(ArrayList<Card> deck) {

		Card drawn = deck.get(0);
		deck.remove(0);

		return drawn;
	}

	/**
	 * Deal hand.
	 *
	 * @param deck
	 *            the deck
	 * @return the array list
	 */
	public static ArrayList<Card> dealHand(ArrayList<Card> deck) {

		int i = 0;

		ArrayList<Card> hand = new ArrayList<>();

		while (i < 5) {

			hand.add(Card.drawCard(deck));
			i++;
		}

		return hand;
	}

	/**
	 * Shuffle.
	 *
	 * @param deck
	 *            the deck
	 */
	public static void shuffle(ArrayList<Card> deck) {

		Collections.shuffle(deck);
	}

	/**
	 * Replace card.
	 *
	 * @param deck
	 *            the deck
	 * @param hand
	 *            the hand
	 * @param index
	 *            the index
	 */
	public static void replaceCard(ArrayList<Card> deck, ArrayList<Card> hand,
			int index) {

		hand.remove(index);

		hand.add(index, Card.drawCard(deck));
	}

	/**
	 * Check hand value smart.
	 *
	 * @param hand
	 *            the hand
	 * @return the int
	 */
	public static int checkHandValueSmart(ArrayList<Card> hand) {

		Collections.sort(hand);

		int[] pairIndices = getPairIndices(hand); // returns null if hand does
													// not contain a Pair.

		if (pairIndices != null) {

			if (pairIndices[0] == 0) {

				if (hand.get(2).equals(hand.get(0))) {

					if (hand.get(3).equals(hand.get(0))) {

						return 7; // Four of a Kind
					}

					if (!hand.get(3).equals(hand.get(0))) {

						if (hand.get(4).equals(hand.get(3))) {

							return 6; // Full House
						}

						if (!hand.get(4).equals(hand.get(3))) {

							return 3; // Three of a Kind
						}
					}
				}

				if (!hand.get(2).equals(hand.get(0))) {

					if (hand.get(3).equals(hand.get(2))) {

						if (hand.get(4).equals(hand.get(3))) {

							return 6; // Full House
						}

						if (!hand.get(4).equals(hand.get(3))) {

							return 2; // Two Pair
						}
					}

					if (!hand.get(3).equals(hand.get(2))) {

						if (hand.get(4).equals(hand.get(3))) {

							return 2; // Two Pair
						}

						if (!hand.get(4).equals(hand.get(3))) {

							return 1; // Pair
						}
					}
				}
			}

			if (pairIndices[0] == 1) {

				if (hand.get(3).equals(hand.get(1))) {

					if (hand.get(4).equals(hand.get(3))) {

						return 7; // Four of a Kind
					}

					if (!hand.get(4).equals(hand.get(3))) {

						return 3; // Three of a Kind
					}
				}

				if (!hand.get(3).equals(hand.get(1))) {

					if (hand.get(4).equals(hand.get(3))) {

						return 2; // Two Pair
					}

					if (!hand.get(4).equals(hand.get(3))) {

						return 1; // Pair
					}
				}

			}

			if (pairIndices[0] == 2) {

				if (hand.get(4).equals(hand.get(2))) {

					return 3; // Three of a Kind
				}

				if (!hand.get(4).equals(hand.get(2))) {

					return 1; // Pair
				}

			}

			if (pairIndices[0] == 3) {

				return 1; // Pair

			}
		}

		// If a hand reaches this point, it has no pair. Only straights,
		// flushes, or straight flushes are possible.

		if (hand.get(1).getRank() - hand.get(0).getRank() == 1
				&& hand.get(2).getRank() - hand.get(1).getRank() == 1
				&& hand.get(3).getRank() - hand.get(2).getRank() == 1
				&& hand.get(4).getRank() - hand.get(3).getRank() == 1
				|| hand.get(0).getRank() == 1 && hand.get(1).getRank() == 10
				&& hand.get(2).getRank() == 11 && hand.get(3).getRank() == 12
				&& hand.get(4).getRank() == 13) {

			if (hand.get(0).getSuit() == hand.get(1).getSuit()
					&& hand.get(0).getSuit() == hand.get(2).getSuit()
					&& hand.get(0).getSuit() == hand.get(3).getSuit()
					&& hand.get(0).getSuit() == hand.get(4).getSuit()) {

				if (hand.get(0).getRank() == 1 && hand.get(1).getRank() == 10) {

					return 9; // Royal Flush
				}

				return 8; // Straight Flush
			}

			return 4; // Straight
		}

		// Only a flush is possible at this point.

		if (hand.get(0).getSuit() == hand.get(1).getSuit()
				&& hand.get(0).getSuit() == hand.get(2).getSuit()
				&& hand.get(0).getSuit() == hand.get(3).getSuit()
				&& hand.get(0).getSuit() == hand.get(4).getSuit()) {

			return 5; // Flush
		}

		return 0; // indicates High Card
	}

	/**
	 * Gets the pair indices.
	 *
	 * @param hand
	 *            the hand
	 * @return the pair indices
	 */
	private static int[] getPairIndices(ArrayList<Card> hand) {

		int[] indices = new int[2];

		int i = 0;

		while (i < 4) {

			if (hand.get(i).equals(hand.get(i + 1))) {

				indices[0] = i;
				indices[1] = i + 1;

				return indices;
			}

			i++;
		}

		return null;
	}

	/**
	 * Gets the rank.
	 *
	 * @return the rank
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * Sets the rank.
	 *
	 * @param rank
	 *            the new rank
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}

	/**
	 * Gets the suit.
	 *
	 * @return the suit
	 */
	public int getSuit() {
		return suit;
	}

	/**
	 * Sets the suit.
	 *
	 * @param suit
	 *            the new suit
	 */
	public void setSuit(int suit) {
		this.suit = suit;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		String rankString = "";
		String suitString = "";

		if (this.rank == 1) {

			rankString = "ace";

		} else if (this.rank == 11) {

			rankString = "jack";

		} else if (this.rank == 12) {

			rankString = "queen";

		} else if (this.rank == 13) {

			rankString = "king";

		} else {

			rankString = "" + rank;
		}

		if (this.suit == 1) {

			suitString = "hearts";

		} else if (this.suit == 2) {

			suitString = "diamonds";

		} else if (this.suit == 3) {

			suitString = "clubs";

		} else if (this.suit == 4) {

			suitString = "spades";
		}

		return rankString + "_of_" + suitString;
	}

	/**
	 * Gets the card image.
	 *
	 * @param cardName
	 *            the card name
	 * @return the card image
	 */
	public static ImageIcon getCardImage(String cardName) {

		int cardWidth = 160;
		int cardHeight = 250;
		String folderName = "Images/";

		BufferedImage cardImage = null;
		try {

			cardImage = ImageIO.read(new File(folderName + cardName + ".png"));

		} catch (IOException e) {

			e.printStackTrace();
		}

		Image cardImageScaled = cardImage.getScaledInstance(cardWidth,
				cardHeight, Image.SCALE_SMOOTH);

		ImageIcon cardIcon = new ImageIcon(cardImageScaled);

		return cardIcon;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Card) {

			Card card = (Card) obj;

			if (this.rank == card.rank) {

				return true;
			}
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Object arg0) {

		if (arg0 instanceof Card) {

			Card otherCard = (Card) arg0;

			if (this.rank > otherCard.rank) {

				return 1;

			} else if (this.rank < otherCard.rank) {

				return -1;
			}
		}

		return 0;
	}
}