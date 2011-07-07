package org.jdominion.gui;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.color.ColorSpace;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JToolTip;
import com.mortennobel.imagescaling.ResampleOp;

import org.jdominion.Card;

public class CardImage extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final double ERROR_MARGIN = 0.01;

	private static Map<Class<? extends Card>, BufferedImage> cardImages = null;
	private static Map<Class<? extends Card>, BufferedImage> greyCardImages = null;

	private BufferedImage image;
	private Card card;
	private Boolean greyedOut = false;
	private String overlayText = null;
	private String longOverlayText = null;
	private ImageToolTip tooltip = null;
	private boolean displayCost = false;
	private int oldWidth = 0;
	private int oldHight = 0;
	private BufferedImage oldImage = null;

	public Card getCard() {
		return card;
	}

	public void setGreyedOut(Boolean greyedOut) {
		this.greyedOut = greyedOut;
	}

	public Boolean getGreyedOut() {
		return greyedOut;
	}

	public void setOverlayText(String overlayText) {
		this.overlayText = overlayText;
		this.repaint();
	}

	public String getOverlayText() {
		return overlayText;
	}

	public String getLongOverlayText() {
		return longOverlayText;
	}

	public void setLongOverlayText(String longOverlayText) {
		this.longOverlayText = longOverlayText;
		this.repaint();
	}

	public void setDisplayCost(boolean displayCost) {
		this.displayCost = displayCost;
	}

	public boolean displayCost() {
		return displayCost;
	}

	public CardImage(Card card) {
		this.card = card;
		this.image = getImage(card);
		this.setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));
	}

	public void setWidth(int width) {
		this.setSize(width, (int) (width / getImageAspectRatio()));
		this.setPreferredSize(new Dimension(width, (int) (width / getImageAspectRatio())));
	}

	public void setHeight(int height) {
		this.setSize((int) (height * getImageAspectRatio()), height);
		this.setPreferredSize(new Dimension((int) (height * getImageAspectRatio()), height));
	}

	private double getImageAspectRatio() {
		return ((double) image.getWidth()) / ((double) image.getHeight());
	}

	private double getCurrentAspectRatio() {
		return ((double) this.getWidth()) / ((double) this.getHeight());
	}

	private void adjustSizeToKeepAspectRatio() {
		if (getCurrentAspectRatio() > getImageAspectRatio() + ERROR_MARGIN) {
			this.setSize((int) (getImageAspectRatio() * getHeight()), getHeight());
		} else if (getCurrentAspectRatio() + ERROR_MARGIN < getImageAspectRatio()) {
			this.setSize(getWidth(), (int) (getWidth() / getImageAspectRatio()));
		}
	}

	private Map<Class<? extends Card>, BufferedImage> getCardImages() {
		if (cardImages == null) {
			cardImages = new HashMap<Class<? extends Card>, BufferedImage>();
		}
		return cardImages;
	}

	private Map<Class<? extends Card>, BufferedImage> getGreyCardImages() {
		if (greyCardImages == null) {
			greyCardImages = new HashMap<Class<? extends Card>, BufferedImage>();
		}
		return greyCardImages;
	}

	private BufferedImage getImage(Card card) {
		if (!getCardImages().containsKey(card.getClass())) {
			BufferedImage cardImage = null;
			String filename = "images/cards/" + card.getName().toLowerCase() + ".jpg";
			try {
				if(new File(filename).exists()) {
					cardImage = ImageIO.read(new File(filename));
				} else {
					//TODO better error reporting
					System.err.println("Error: Could not find image file: " + filename);
					cardImage = createDummyImage(card);
				}
			} catch (IOException e) {
				//TODO better error reporting
				System.err.println("Error reading " + filename + ": " + e.getMessage());
				throw new RuntimeException(e);
			}
			getCardImages().put(card.getClass(), cardImage);
		}
		return getCardImages().get(card.getClass());
	}

	private BufferedImage createDummyImage(Card card) {
		BufferedImage cardImage = new BufferedImage(211, 330, BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics = cardImage.createGraphics();
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, 211, 330);
		graphics.setColor(Color.YELLOW);
		graphics.setStroke(new BasicStroke(8));
		graphics.drawRect(0, 0, 211, 330);
		graphics.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 36));
		graphics.setColor(Color.RED);
		graphics.drawString(card.getName(), 10, 100);
		return cardImage;
	}

	private BufferedImage getGreyImage() {
		if (!getGreyCardImages().containsKey(this.card.getClass())) {
			ColorConvertOp colorConverter = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
			getGreyCardImages().put(this.card.getClass(), colorConverter.filter(image, null));
		}
		return getGreyCardImages().get(this.card.getClass());
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		adjustSizeToKeepAspectRatio();
		drawImage(g);
		drawText(g);
	}

	private void drawText(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));

		if (this.displayCost) {
			drawString(g2d, Integer.toString(card.getCost()), Color.YELLOW, Color.BLACK, 4, getHeight() - 4);
		}
		if (this.getOverlayText() != null) {
			Rectangle2D boundingBox = g2d.getFont().getStringBounds(getOverlayText(),
					new FontRenderContext(null, true, false));
			drawString(g2d, getOverlayText(), Color.MAGENTA, Color.BLACK,
					getWidth() - (int) boundingBox.getWidth() - 4, getHeight() - 4);
		}
		if (this.getLongOverlayText() != null) {
			drawString(g2d, getLongOverlayText(), Color.GREEN, Color.BLACK, 10, 20);
		}
	}

	private void drawString(Graphics2D g2d, String string, Color color, Color backgroundColor, int x, int y) {
		g2d.setColor(backgroundColor);
		g2d.drawString(string, x - 1, y - 1);
		g2d.drawString(string, x - 1, y + 1);
		g2d.drawString(string, x + 1, y + 1);
		g2d.drawString(string, x + 1, y - 1);

		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		g2d.setColor(color);
		g2d.drawString(string, x, y);
	}

	private void drawImage(Graphics g) {
		// don't resize if the image didn't change
		if (this.getWidth() != oldWidth || this.getHeight() != oldHight || oldImage == null) {
			BufferedImage imageToDraw = null;
			if (greyedOut) {
				imageToDraw = getGreyImage();
			} else {
				imageToDraw = image;
			}
			BufferedImage image = resampleImage(imageToDraw);
			drawNewImage(g, image);
			oldWidth = getWidth();
			oldHight = getHeight();
			oldImage = image;
		} else {
			redrawOldImage(g);
		}
	}

	private void redrawOldImage(Graphics g) {
		g.drawImage(oldImage, 0, 0, this.getWidth(), this.getHeight(), null);
	}

	private void drawNewImage(Graphics g, BufferedImage image) {
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
	}

	private BufferedImage resampleImage(BufferedImage imageToDraw) {
		ResampleOp resampleOp = new ResampleOp(this.getWidth(), this.getHeight());
		BufferedImage image = resampleOp.filter(imageToDraw, null);
		return image;
	}

	@Override
	public JToolTip createToolTip() {
		if (tooltip == null) {
			tooltip = new ImageToolTip(image);
		}
		return tooltip;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((card == null) ? 0 : card.hashCode());
		result = prime * result + ((greyedOut == null) ? 0 : greyedOut.hashCode());
		result = prime * result + ((longOverlayText == null) ? 0 : longOverlayText.hashCode());
		result = prime * result + ((overlayText == null) ? 0 : overlayText.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CardImage other = (CardImage) obj;
		if (card == null) {
			if (other.card != null)
				return false;
		} else if (!card.equals(other.card))
			return false;
		if (greyedOut == null) {
			if (other.greyedOut != null)
				return false;
		} else if (!greyedOut.equals(other.greyedOut))
			return false;
		if (longOverlayText == null) {
			if (other.longOverlayText != null)
				return false;
		} else if (!longOverlayText.equals(other.longOverlayText))
			return false;
		if (overlayText == null) {
			if (other.overlayText != null)
				return false;
		} else if (!overlayText.equals(other.overlayText))
			return false;
		return true;
	}

}
