package org.jdominion.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

import org.jdominion.Card;
import org.jdominion.Supply;
import org.jdominion.decisions.ChooseFromRevealedCards;
import org.jdominion.decisions.multipleChoice.Choice;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JTextArea messageTextArea = null;
	private JScrollPane messageScrollPane = null;
	private JPanel leftPanel = null;
	private JTable playerTable = null;
	private JLabel messageToUser = null;
	private JButton cancelButton = null;
	private PlayerTableModel playerTableModel = null;
	private HandController handController = null;
	private SupplyController supplyController = null;
	private TurnController turnController = null;

	public MainWindow(final IGuiInformationSource guiInformationSource) {
		try {
			EventQueue.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					initialize(guiInformationSource);
				}
			});
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initialize(IGuiInformationSource guiInformationSource) {
		this.setSize(1024, 768);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.playerTable = this.initializePlayerTable(guiInformationSource);
		this.handController = new HandController(guiInformationSource);
		this.supplyController = new SupplyController(guiInformationSource);
		this.turnController = new TurnController(guiInformationSource);
		this.setContentPane(getJContentPane());
		this.setTitle("JDominion");
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			MigLayout layout = new MigLayout(new LC().fill());
			jContentPane = new JPanel();
			jContentPane.setLayout(layout);
			jContentPane.add(supplyController.getView(), new CC().wrap().spanX());
			jContentPane.add(getLeftPanel());
			jContentPane.add(getMessageToUser());
			jContentPane.add(getMessageScrollPane(), new CC().grow().wrap());
			jContentPane.add(handController.getHandView(), new CC().grow().spanX());
			layout.setRowConstraints("[16%][50%][34%]");
			layout.setColumnConstraints("[20%!][40%!][30%!]");
		}
		return jContentPane;
	}

	private JScrollPane getMessageScrollPane() {
		if (messageScrollPane == null) {
			messageScrollPane = new JScrollPane(getMessageTextArea());
		}
		return messageScrollPane;

	}

	private JTextArea getMessageTextArea() {
		if (messageTextArea == null) {
			messageTextArea = new JTextArea();
			messageTextArea.setEditable(false);
		}
		return messageTextArea;
	}

	private JPanel getLeftPanel() {
		if (leftPanel == null) {
			leftPanel = new JPanel();
			leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
			leftPanel.add(Box.createRigidArea(new Dimension(0, 5)));
			getplayerTable().setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			leftPanel.add(getplayerTable());
			turnController.getView().setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			leftPanel.add(turnController.getView());
			leftPanel.add(getCancelButton());
		}
		return leftPanel;
	}

	private JTable getplayerTable() {
		return playerTable;
	}

	private JTable initializePlayerTable(IGuiInformationSource guiInformationSource) {
		this.playerTableModel = new PlayerTableModel(guiInformationSource);
		return new JTable(this.playerTableModel);
	}

	private JLabel getMessageToUser() {
		if (messageToUser == null) {
			messageToUser = new JLabel();
		}
		return messageToUser;
	}

	private JButton getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new JButton("Cancel");
			cancelButton.setVisible(false);
		}
		return cancelButton;
	}

	private void showCancelButton(final ActionListener eventHandler) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				getCancelButton().addActionListener(eventHandler);
				getCancelButton().setVisible(true);

			}
		});
	}

	private void hideCancelButton() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				for (ActionListener listener : getCancelButton().getActionListeners()) {
					getCancelButton().removeActionListener(listener);
				}
				getCancelButton().setVisible(false);

			}
		});
	}

	public void addMessageText(final String message) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				messageTextArea.append(message + "\n");
			}
		});
	}

	public String choose(List<String> options, String message) {
		return (String) JOptionPane.showInputDialog(this, message, "Please choose", JOptionPane.PLAIN_MESSAGE, null,
				options.toArray(), options.get(0));

	}

	public Card chooseCardFromHand(String message, Boolean cancelable) {
		showPromptMessage(message);
		if (cancelable) {
			showCancelButton(handController);
		}
		Card choosenCard = handController.chooseCard();
		hideCancelButton();
		return choosenCard;
	}

	public Class<? extends Card> chooseCardFromSupply(String message, Supply supply, Boolean cancelable) {
		showPromptMessage(message);
		if (cancelable) {
			showCancelButton(supplyController);
		}
		Class<? extends Card> choosenCard = supplyController.chooseCard(supply);
		hideCancelButton();
		return choosenCard;
	}
	
	public boolean askUser(String message) {
		int answer = JOptionPane.showConfirmDialog(this, message, "", JOptionPane.YES_NO_OPTION);
		return (answer == JOptionPane.YES_OPTION);
	}
	
	public void chooseFromRevealedCards(ChooseFromRevealedCards decision) {
		showPromptMessage(decision.getUserMessage());
		RevealedCardsController revealedCardsController = new RevealedCardsController(this, decision.getUserMessage(), decision);
		revealedCardsController.askUser();
	}
	
	public List<Choice> multipleChoice(String userMessage, boolean cancelable, int minAnswers, int maxAnswers, List<Choice> choices) {
		showPromptMessage(userMessage);
		MultipleChoiceController controller = new MultipleChoiceController(this, userMessage, cancelable, minAnswers, maxAnswers, choices);
		return controller.askUser();
		
	}

	public void update() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				playerTableModel.fireTableDataChanged();
				handController.update();
				supplyController.update();
				turnController.update();
			}
		});
	}

	public void showPromptMessage(final String message) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				getMessageToUser().setText(message);
			}
		});
	}

}
