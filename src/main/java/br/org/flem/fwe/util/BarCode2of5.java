package br.org.flem.fwe.util;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Gera Código de Barras no padrão brasileiro (FEBRABAN).
 * 
 * @author Framework Celepar - Componentes
 * @version 1.0, 21/12/2005
 * @since 1.0
 */
public class BarCode2of5 extends Canvas {

    /**
     *
     */
    private static final long serialVersionUID = 5882115291769072158L;
    private static String alphabet2of5 = "0123456789";
    private static String coded2of5Char[] = {"00110", "10001", "01001",
        "11000", "00101", "10100", "01100", "00011", "10010", "01010"};
    /** Constante para variação do código. */
    public static final int CODE2OF5 = 0;
    /** Constante para variação do código. */
    public static final int CODE2OF5CHK = 1;
    /** Constante para tamanho da barra. */
    public static final int SMALL = 1;
    /** Constante para tamanho da barra. */
    public static final int MEDIUM = 2;
    /** Constante para tamanho da barra. */
    public static final int LARGE = 3;
    /** Constante para alinhamento do texto. */
    public static final int BASELINE = 0;
    /** Constante para alinhamento do texto. */
    public static final int MIDDLELINE = 1;
    /** Constante para alinhamento do texto. */
    public static final int TOPLINE = 2;
    private static final Color DEFBACKCOLOR;
    private static final Color DEFFORECOLOR;
    private static final Font DEFFONT = new Font("Courier", 0, 12);
    private String strStartPattern;
    private String strStopPattern;
    private double wideToNarrowRatio;
    private double marginWidth;
    private double labelLength;
    private double labelHeight;
    private String stringToEncode;
    private String filledStringToEncode;
    private String encodedString;
    private int narrowestDim;
    private boolean textInside;
    private int style;
    private int initialWidth;
    private int initialHeight;
    private Color backColor;
    private Color foreColor;
    private Font font;
    private int textAlign;


    static {
        DEFBACKCOLOR = Color.white;
        DEFFORECOLOR = Color.black;
    }

    /**
     * <P>
     * Construtor
     * Seta os dados mínimos para geração do código de barras.
     */
    public BarCode2of5() {
        this("0123456789", 50, 25, 1, 0, false, DEFBACKCOLOR, DEFFORECOLOR, DEFFONT, 2);
    }

    /**
     * Construtor interno
     * @param s
     * @param i
     * @param j
     * @param k
     * @param l
     * @param flag
     * @param color
     * @param color1
     * @param font1
     * @param i1
     */
    private BarCode2of5(String s, int i, int j, int k, int l, boolean flag,
            Color color, Color color1, Font font1, int i1) {
        strStartPattern = "0000";
        strStopPattern = "100";
        //wideToNarrowRatio = 3D;
        wideToNarrowRatio = 2D; //*******
        stringToEncode = "";
        filledStringToEncode = "";
        encodedString = "";
        narrowestDim = 1;
        textInside = true;
        style = 0;
        initialWidth = 50;
        initialHeight = 25;
        backColor = DEFBACKCOLOR;
        foreColor = DEFFORECOLOR;
        font = DEFFONT;
        textAlign = 1;
        stringToEncode = s;
        narrowestDim = k;
        initialWidth = i;
        initialHeight = j;
        textInside = flag;
        style = l;
        backColor = color;
        foreColor = color1;
        font = font1;
        textAlign = i1;
        Encode();
    }

    /**
     * Retorna o tamanho minimo do componente.
     */
    @Override
    public Dimension getMinimumSize() {
        Dimension dimension = new Dimension(initialWidth, initialHeight);
        return dimension;
    }


    @Override
    public Dimension getPreferredSize() {
        return getMinimumSize();
    }


    @Override
    public void setSize(int i, int j) {
        initialWidth = i;
        initialHeight = j;
        super.setSize(i, j);
        repaint();
    }

    @Override
    public void setSize(Dimension dimension) {
        initialWidth = dimension.width;
        initialHeight = dimension.height;
        super.setSize(dimension.width, dimension.height);
        repaint();
    }

    @Override
    public String toString() {
        return getClass().getName() + "[" + paramString() + "]";
    }

    public Dimension requestedMinimumSize(String s) {
        int i = s.length() * 16 * narrowestDim + 31 * narrowestDim;
        if (style == 1) {
            i += 16 * narrowestDim;
        }
        int j = Math.max((int) (0.14999999999999999D * i), 35);
        return new Dimension(i, j);
    }

    public void setString(String s) throws IllegalArgumentException {
        stringToEncode = s;
        stringValidate();
        Encode();
    }

    public String getString() {
        return stringToEncode;
    }

    public void setDimension(int i) {
        switch (i) {
            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
                narrowestDim = i;
                repaint();
                return;
        }
        narrowestDim = 1;
        repaint();
    }

    public int getDimension() {
        return narrowestDim;
    }

    public void setTextInside(boolean flag) {
        textInside = flag;
        repaint();
    }

    public boolean isTextInside() {
        return textInside;
    }

    public void setStyle(int i) {
        switch (i) {
            case 0: // '\0'
            case 1: // '\001'
                style = i;
                Encode();
                return;
        }
        style = 0;
        Encode();
    }

    public int getStyle() {
        return style;
    }

    @Override
    public void setBackground(Color color) {
        backColor = color;
        repaint();
    }

    @Override
    public Color getBackground() {
        return backColor;
    }

    @Override
    public void setForeground(Color color) {
        foreColor = color;
        repaint();
    }

    @Override
    public Color getForeground() {
        return foreColor;
    }

    @Override
    public void setFont(Font font1) {
        font = font1;
        repaint();
    }

    @Override
    public Font getFont() {
        return font;
    }

    public void setTextAlign(int i) {
        switch (i) {
            case 0: // '\0'
            case 1: // '\001'
            case 2: // '\002'
                textAlign = i;
                repaint();
                return;
        }
        textAlign = 1;
        repaint();
    }

    public int getTextAlign() {
        return textAlign;
    }

    @Override
    public synchronized void paint(Graphics g) {
        int i = getSize().width;
        int j = getSize().height;
        g.setFont(font);
        FontMetrics fontmetrics = g.getFontMetrics(font);
        int k = fontmetrics.getAscent();
        labelLength = filledStringToEncode.length() * 8 * narrowestDim;
        //marginWidth = ((double)i - labelLength) / 2D;
        //marginHeight = ((double)j - labelLength) / 2D;
        marginWidth = 0;
        if (textInside) {
            if (textAlign == 0) {
                labelHeight = j;
            }

            if (textAlign == 1) {
                labelHeight = j - k / 2;
            }

            if (textAlign == 2) {
                labelHeight = j - k;
            }
        } else {
            labelHeight = j;
        }

        g.setColor(backColor);
        g.fillRect(0, 0, i, j);
        int l = encodedString.length();
        int i1 = 0;
        int j1 = 0;
        int k1 = 0;
        int l1 = 0;
        i1 = (int) marginWidth;
        j1 = 0;
        k1 = 0;
        l1 = 0;
        for (int j2 = 0; j2 < l; j2++) {
            if (j2 % 2 == 0) {
                g.setColor(foreColor);
            } else {
                g.setColor(backColor);
            }

            int i2;
            if (encodedString.charAt(j2) == '1') {
                i2 = (int) (narrowestDim * wideToNarrowRatio);
            } else {
                i2 = narrowestDim;
            }
            g.fillRect(k1 + i1, l1 + j1, i2, (int) labelHeight);
            k1 += i2;
        } //************

        if (textInside) {
            int k2 = fontmetrics.stringWidth(filledStringToEncode);
            int l2 = getSize().height;
            int i3 = (int) ((marginWidth + labelLength / 2D) - (k2 / 2));
            g.setColor(backColor);
            g.fillRect(i3, l2 - k, k2, k);
            g.setColor(foreColor);
            g.drawString(filledStringToEncode, i3, l2);
        }
    }

    @Override
    protected String paramString() {
        String s = filledStringToEncode + "," + initialWidth + "x" + initialHeight;
        return s;
    }

    private void stringValidate() throws IllegalArgumentException {
        this.formatStringToEncode();
        int i = stringToEncode.length();
        for (int j = 0; j < i; j++) {
            if (alphabet2of5.indexOf(stringToEncode.charAt(j)) == -1) {
                throw new IllegalArgumentException("Informar somente digitos para o codigo 2of5");
            }
        }
    }

    /**
     * Retira da string os espacos vazios e os tracos
     */
    private void formatStringToEncode() {
        stringToEncode = stringToEncode.replaceAll(" ", "");
        stringToEncode = stringToEncode.replaceAll("-", "");
    }

    private char addCheckChar(String s) {
        int i = 0;
        int j = 0;
        int l = 0;
        int k = 0;
        int i1 = s.length();
        for (int j1 = 0; j1 < i1; j1++) {
            if (j1 % 2 == 0) {
                l += alphabet2of5.indexOf(stringToEncode.charAt(j1));
            } else {
                k += alphabet2of5.indexOf(stringToEncode.charAt(j1));
            }
        }

        if (i1 % 2 == 0) {
            i = l;
            j = k;
        } else {
            i = k;
            j = l;
        }

        j *= 3;
        j += i;
        if (j % 10 == 0) {
            return alphabet2of5.charAt(0);
        }
        return alphabet2of5.charAt(10 - j % 10);
    }

    private void Encode() {
        filledStringToEncode = new String(stringToEncode);

        if (style == 1) {
            filledStringToEncode += addCheckChar(stringToEncode);
        }

        if (filledStringToEncode.length() % 2 != 0) {
            filledStringToEncode = "0" + filledStringToEncode;
        }

        StringBuffer stringbuffer = new StringBuffer();
        for (int i = 0; i < filledStringToEncode.length(); i += 2) {
            stringbuffer.append(funInterleave(filledStringToEncode.charAt(i),
                    filledStringToEncode.charAt(i + 1)));
        }

        encodedString = stringbuffer.toString();
        encodedString = strStartPattern + stringbuffer.toString();
        encodedString += strStopPattern;
        repaint();
    }

    private String funInterleave(char c, char c1) {
        StringBuffer stringbuffer = new StringBuffer();
        for (int i = 0; i < 5; i++) {
            stringbuffer.append(coded2of5Char[alphabet2of5.indexOf(c)].charAt(i));
            stringbuffer.append(coded2of5Char[alphabet2of5.indexOf(c1)].charAt(i));
        }

        return stringbuffer.toString();
    }

    /**
     * Constrói uma imagem contendo o código de barra.
     * @param Stream onde será retornada a imagem
     * @param Codigo que dará origem a imagem
     * @return imagem no formato stream
     * @throws Exception
     */
    public BufferedImage createBarCode(String codigo) throws Exception {
        this.setDimension(BarCode2of5.SMALL);
        this.setStyle(BarCode2of5.CODE2OF5);
        this.setForeground(Color.black);
        this.setTextAlign(BarCode2of5.TOPLINE);
        this.setTextInside(textInside);
        this.setSize(initialWidth, initialHeight);

        try {
            this.setString(codigo);
        } catch (Exception e) {
            throw new Exception("Erro ao configurar o Código de Barras", e);
        }

        try {
            return createComponentImage(this);
        } catch (Exception e) {
            throw new Exception("Erro ao gerar o Código de Barras", e);
        }

    }

    /**
     * Constrói uma imagem conforme o componente informado.
     *
     * @param component desenho que será transformado
     * @return imagem desenhada
     */
    private BufferedImage createComponentImage(Component component) throws Exception {
        Rectangle rect = component.getBounds();

        BufferedImage image = new BufferedImage(rect.width, rect.height,
                BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        //write to the image
        component.paint(g);

        //dispose of the graphics content
        g.dispose();

        return image;
    }
}