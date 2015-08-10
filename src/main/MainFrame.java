package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JFrame;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.JButton;

/**
 * The Class MainFrame.
 * 
 * @author Derek Sanders
 * @version 1.0
 * @since August 9th, 2015
 */
public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Card> deck = Card.buildDeck();
	ArrayList<Card> hand = Card.dealHand(deck);
	boolean newhand = true;
	String handCard0 = "back";
	String handCard1 = "back";
	String handCard2 = "back";
	String handCard3 = "back";
	String handCard4 = "back";
	String bankString = "";
	JLabel handCardLabel0;
	JLabel handCardLabel1;
	JLabel handCardLabel2;
	JLabel handCardLabel3;
	JLabel handCardLabel4;
	JLabel bankLabel;
	JToggleButton keepButton0;
	JToggleButton keepButton1;
	JToggleButton keepButton2;
	JToggleButton keepButton3;
	JToggleButton keepButton4;
	int cardWidth = 160;
	int cardHeight = 250;
	Dimension cardDimension = new Dimension(cardWidth, cardHeight);
	JLabel handValueLabel;
	int bank = 500;

	/**
	 * Instantiates a new main frame.
	 */
	public MainFrame() {

		super("Video Poker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setSize(846, 415);
		getContentPane().setBackground(new Color(70, 70, 70));
		getContentPane().setLayout(
				new MigLayout("", "[][][][][]", "[][52.00][52.00]"));

		handCardLabel0 = new JLabel(Card.getCardImage(handCard0));
		getContentPane().add(handCardLabel0, "cell 0 0");

		handCardLabel1 = new JLabel(Card.getCardImage(handCard1));
		getContentPane().add(handCardLabel1, "cell 1 0");

		handCardLabel2 = new JLabel(Card.getCardImage(handCard2));
		getContentPane().add(handCardLabel2, "cell 2 0");

		handCardLabel3 = new JLabel(Card.getCardImage(handCard3));
		getContentPane().add(handCardLabel3, "cell 3 0");

		handCardLabel4 = new JLabel(Card.getCardImage(handCard4));
		getContentPane().add(handCardLabel4, "cell 4 0");

		keepButton0 = new JToggleButton("Keep");
		getContentPane().add(keepButton0, "cell 0 1,grow");
		keepButton0.setEnabled(false);

		keepButton1 = new JToggleButton("Keep");
		getContentPane().add(keepButton1, "cell 1 1,grow");
		keepButton1.setEnabled(false);

		keepButton2 = new JToggleButton("Keep");
		getContentPane().add(keepButton2, "cell 2 1,grow");
		keepButton2.setEnabled(false);

		keepButton3 = new JToggleButton("Keep");
		getContentPane().add(keepButton3, "cell 3 1,grow");
		keepButton3.setEnabled(false);

		keepButton4 = new JToggleButton("Keep");
		getContentPane().add(keepButton4, "cell 4 1,grow");
		keepButton4.setEnabled(false);

		bankLabel = new JLabel("Bankroll: 500");
		bankLabel.setForeground(Color.WHITE);
		bankLabel.setFont(new Font("Helvetica LT Std", Font.PLAIN, 18));
		getContentPane().add(bankLabel, "cell 0 2");

		handValueLabel = new JLabel("");
		handValueLabel.setForeground(Color.WHITE);
		handValueLabel.setFont(new Font("Helvetica LT Std", Font.PLAIN, 18));
		getContentPane().add(handValueLabel, "cell 1 2,alignx left");

		JButton dealButton = new JButton("Deal");
		getContentPane().add(dealButton, "cell 2 2,grow");

		dealButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (newhand) {

					bank -= 5;

					bankString = "Bankroll: " + bank;

					bankLabel.setText(bankString);

					deck = Card.buildDeck();

					Card.shuffle(deck);

					hand = Card.dealHand(deck);

					handCard0 = hand.get(0).toString();

					handCardLabel0.setIcon(Card.getCardImage(handCard0));

					handCard1 = hand.get(1).toString();

					handCardLabel1.setIcon(Card.getCardImage(handCard1));

					handCard2 = hand.get(2).toString();

					handCardLabel2.setIcon(Card.getCardImage(handCard2));

					handCard3 = hand.get(3).toString();

					handCardLabel3.setIcon(Card.getCardImage(handCard3));

					handCard4 = hand.get(4).toString();

					handCardLabel4.setIcon(Card.getCardImage(handCard4));

					newhand = false;

					keepButton0.setSelected(false);
					keepButton1.setSelected(false);
					keepButton2.setSelected(false);
					keepButton3.setSelected(false);
					keepButton4.setSelected(false);

					keepButton0.setEnabled(true);
					keepButton1.setEnabled(true);
					keepButton2.setEnabled(true);
					keepButton3.setEnabled(true);
					keepButton4.setEnabled(true);

					handValueLabel.setText("");

					repaint();

				}

				else {

					if (!keepButton0.isSelected()) {

						Card.replaceCard(deck, hand, 0);

						handCard0 = hand.get(0).toString();

						handCardLabel0.setIcon(Card.getCardImage(handCard0));
					}

					if (!keepButton1.isSelected()) {

						Card.replaceCard(deck, hand, 1);

						handCard1 = hand.get(1).toString();

						handCardLabel1.setIcon(Card.getCardImage(handCard1));

					}

					if (!keepButton2.isSelected()) {

						Card.replaceCard(deck, hand, 2);

						handCard2 = hand.get(2).toString();

						handCardLabel2.setIcon(Card.getCardImage(handCard2));

					}

					if (!keepButton3.isSelected()) {

						Card.replaceCard(deck, hand, 3);

						handCard3 = hand.get(3).toString();

						handCardLabel3.setIcon(Card.getCardImage(handCard3));

					}

					if (!keepButton4.isSelected()) {

						Card.replaceCard(deck, hand, 4);

						handCard4 = hand.get(4).toString();

						handCardLabel4.setIcon(Card.getCardImage(handCard4));

					}

					keepButton0.setEnabled(false);
					keepButton1.setEnabled(false);
					keepButton2.setEnabled(false);
					keepButton3.setEnabled(false);
					keepButton4.setEnabled(false);

					int handValue = Card.checkHandValueSmart(hand);

					if (handValue == 0) {

						handValueLabel.setText("High Card");
						bank += 0;
					}

					else if (handValue == 1) {

						handValueLabel.setText("Pair");
						bank += 5;

						bankString = "Bankroll: " + bank;
						bankLabel.setText(bankString);
					}

					else if (handValue == 2) {

						handValueLabel.setText("Two Pair");
						bank += 10;

						bankString = "Bankroll: " + bank;
						bankLabel.setText(bankString);
					}

					else if (handValue == 3) {

						handValueLabel.setText("Three of a Kind");
						bank += 30;

						bankString = "Bankroll: " + bank;
						bankLabel.setText(bankString);
					}

					else if (handValue == 4) {

						handValueLabel.setText("Straight");
						bank += 50;

						bankString = "Bankroll: " + bank;
						bankLabel.setText(bankString);
					}

					else if (handValue == 5) {

						handValueLabel.setText("Flush");
						bank += 100;

						bankString = "Bankroll: " + bank;
						bankLabel.setText(bankString);
					}

					else if (handValue == 6) {

						handValueLabel.setText("Full House");
						bank += 200;

						bankString = "Bankroll: " + bank;
						bankLabel.setText(bankString);
					}

					else if (handValue == 7) {

						handValueLabel.setText("Four of a Kind");
						bank += 400;

						bankString = "Bankroll: " + bank;
						bankLabel.setText(bankString);
					}

					else if (handValue == 8) {

						handValueLabel.setText("Straight Flush");
						bank += 600;

						bankString = "Bankroll: " + bank;
						bankLabel.setText(bankString);
					}

					else if (handValue == 9) {

						handValueLabel.setText("Royal Flush");
						bank += 1000;

						bankString = "Bankroll: " + bank;
						bankLabel.setText(bankString);
					}

					newhand = true;
				}

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

			}

			@Override
			public void mousePressed(MouseEvent arg0) {

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {

			}
		});

		setVisible(true);
	}
}
